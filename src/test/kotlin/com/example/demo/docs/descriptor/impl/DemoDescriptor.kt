package com.example.demo.docs.descriptor.impl

import com.example.demo.docs.DocUrl
import com.example.demo.docs.DocumentLinkGenerator.Companion.generateLinkCode
import com.example.demo.docs.descriptor.Descriptor
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
        generateLinkCode(DocUrl.CommonCodesDocUrl),
        false
    );

    override fun getName(): String {
        return this.name
    }

    companion object {

        fun getNameList(): List<DemoDescriptor> {
            return listOf(Name)
        }
    }
}
