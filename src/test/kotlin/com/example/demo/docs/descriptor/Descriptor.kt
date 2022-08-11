package com.example.demo.docs.descriptor

import org.springframework.restdocs.payload.JsonFieldType

interface Descriptor {
    val path: String
    val type: JsonFieldType
    val description: String
    val optional: Boolean
    fun getName(): String
}
