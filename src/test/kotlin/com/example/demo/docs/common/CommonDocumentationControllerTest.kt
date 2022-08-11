package com.example.demo.doc

import com.example.demo.SpringRestDocsSupport
import com.fasterxml.jackson.core.type.TypeReference
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.operation.Operation
import org.springframework.restdocs.payload.AbstractFieldsSnippet
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation.beneathPath
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadSubsectionExtractor
import org.springframework.restdocs.snippet.Attributes.attributes
import org.springframework.restdocs.snippet.Attributes.key
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
                customResponseFields(
                    type = "custom-response",
                    subsectionExtractor = beneathPath("commonCodes").withSubsectionId("commonCodes"),
                    attributes = attributes(key("title").value("CommonCodes")),
                    descriptors = enumConvertFieldDescriptor(response.commonCodes)
                )
            )
        )
    }

    private fun getData(result: MvcResult): CommonResponse {
        return objectMapper.readValue(result.response.contentAsByteArray, object : TypeReference<CommonResponse>() {})
    }

    companion object {
        fun customResponseFields(
            type: String,
            subsectionExtractor: PayloadSubsectionExtractor<*>,
            attributes: Map<String, Any>,
            vararg descriptors: FieldDescriptor?
        ): CustomResponseFieldsSnippet {
            return CustomResponseFieldsSnippet(
                type, subsectionExtractor, listOf(*descriptors), attributes, true
            )
        }
    }

    private fun enumConvertFieldDescriptor(enumValues: Map<String, String>): Array<out FieldDescriptor?> {
        return enumValues.entries.stream().map { (key, value): Map.Entry<String, String> ->
            fieldWithPath(key).description(value)
        }.toArray { arrayOfNulls<FieldDescriptor>(it) }
    }
}

class CustomResponseFieldsSnippet(
    type: String,
    subsectionExtractor: PayloadSubsectionExtractor<*>,
    descriptors: List<FieldDescriptor?>,
    attributes: Map<String, Any>,
    ignoreUndocumentedFields: Boolean
) : AbstractFieldsSnippet(type, descriptors, attributes, ignoreUndocumentedFields, subsectionExtractor) {

    override fun getContentType(operation: Operation): MediaType {
        return operation.response.headers.contentType!!
    }

    override fun getContent(operation: Operation): ByteArray {
        return operation.response.content
    }
}
