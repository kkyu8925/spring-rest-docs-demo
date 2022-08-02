package com.example.demo

import com.example.demo.doc.DocumentLinkGenerator
import com.example.demo.doc.DocumentLinkGenerator.Companion.generateLinkCode
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
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
            get("/api/hello")
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
            get("/api/users")
        )

        // then
        perform
            .andExpect(status().isOk)
            .andDo(
                document(
                    "user-get",
                    responseFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description(generateLinkCode(
                            DocumentLinkGenerator.DocUrl.CommonCodes
                        ))
                    )
                )
            )
    }
}
