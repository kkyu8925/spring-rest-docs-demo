package com.example.demo.descriptor

import com.example.demo.doc.DocumentLinkGenerator
import com.example.demo.doc.DocumentLinkGenerator.Companion.generateLinkCode
import org.springframework.restdocs.payload.JsonFieldType

enum class DemoDescriptor(
    override val path: String,
    override val type: JsonFieldType,
    override val description: String,
    override val optional: Boolean
) : Descriptor {

    Name(
        "name",
        JsonFieldType.STRING,
        generateLinkCode(DocumentLinkGenerator.DocUrl.CommonCodes),
        false
    );

    override fun getName(): String {
        return this.name
    }

    companion object {
        fun getNameList(): List<DemoDescriptor> {
            return listOf(
                Name,
            )
        }
    }
}
