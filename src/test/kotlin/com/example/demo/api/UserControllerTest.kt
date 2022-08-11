package com.example.demo.api

import com.example.demo.SpringRestDocsSupport
import com.example.demo.User
import com.example.demo.UserController
import com.example.demo.UserService
import com.example.demo.docs.descriptor.impl.DemoDescriptorCollector.Companion.getFieldDescriptor
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserController::class)
internal class UserControllerTest : SpringRestDocsSupport() {

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
                    responseFields(getFieldDescriptor())
                )
            )
    }
}
