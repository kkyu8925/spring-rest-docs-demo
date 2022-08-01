package com.example.demo

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
internal class WebMvcTest : SpringRestDocsSupport() {

    @MockkBean
    private lateinit var helloService: UserService

    @Test
    fun hello() {
        // when
        val perform = mockMvc.perform(
            get("/api/hello").accept(MediaType.APPLICATION_JSON)
        )

        // then
        perform
            .andExpect(status().isOk)
            .andExpect(content().string("hello"))
    }

    @Test
    fun getUser() {
        // given
        every { helloService.getUser() } returns User("test")

        // when
        val perform = mockMvc.perform(
            get("/api/users").accept(MediaType.APPLICATION_JSON)
        )

        // then
        perform
            .andExpect(status().isOk)
            .andDo(
                document(
                    "user-get",
                    responseFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
                    )
                )
            )
    }
}
