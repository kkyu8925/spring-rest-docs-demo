package com.example.demo.docs.common

import org.springframework.http.MediaType
import org.springframework.restdocs.operation.Operation
import org.springframework.restdocs.payload.AbstractFieldsSnippet
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.PayloadSubsectionExtractor
import org.springframework.restdocs.snippet.Attributes

class CustomResponseFieldsSnippet(
    type: String,
    subsectionExtractor: PayloadSubsectionExtractor<*>,
    descriptors: List<FieldDescriptor>,
    attributes: Map<String, Any>,
    ignoreUndocumentedFields: Boolean
) : AbstractFieldsSnippet(type, descriptors, attributes, ignoreUndocumentedFields, subsectionExtractor) {

    override fun getContentType(operation: Operation): MediaType {
        return operation.response.headers.contentType!!
    }

    override fun getContent(operation: Operation): ByteArray {
        return operation.response.content
    }

    companion object {
        fun createCustomResponseFieldsSnippet(
            descriptors: List<FieldDescriptor>,
            path: String,
            title: String
        ): CustomResponseFieldsSnippet {
            return CustomResponseFieldsSnippet(
                type = "custom-response",
                subsectionExtractor = PayloadDocumentation.beneathPath(path).withSubsectionId(path),
                descriptors = descriptors,
                attributes = Attributes.attributes(Attributes.key("title").value(title)),
                ignoreUndocumentedFields = true
            )
        }
    }
}
