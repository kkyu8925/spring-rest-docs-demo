package com.example.demo.docs.common

import com.example.demo.SpringRestDocsSupport
import com.example.demo.docs.DocUrl
import com.example.demo.docs.common.CustomResponseFieldsSnippet.Companion.createCustomResponseFieldsSnippet
import com.example.demo.docs.descriptor.impl.DemoDescriptorCollector.Companion.commonFieldDescriptors
import com.fasterxml.jackson.core.type.TypeReference
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CommonDocumentationController::class)
internal class CommonDocumentationControllerTest : SpringRestDocsSupport() {

    @Test
    fun commons() {
        // when
        val result = mockMvc.perform(
            get("/docs/common")
        )
        val response = getData(result.andReturn())

        // then
        result.andExpect(status().isOk).andDo(
            document(
                "common",
                createCustomResponseFieldsSnippet(
                    descriptors = commonFieldDescriptors(response.commonCodes),
                    path = DocUrl.CommonCodesDocUrl.pageId,
                    title = DocUrl.CommonCodesDocUrl.text,
                ),
            )
        )
    }

    private fun getData(result: MvcResult): CommonResponse {
        return objectMapper.readValue(result.response.contentAsByteArray, object : TypeReference<CommonResponse>() {})
    }
}

