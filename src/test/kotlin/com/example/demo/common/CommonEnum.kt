package com.example.demo.doc

interface CommonEnum {
    fun getName(): String
    fun getDescription(): String
}

enum class CommonCodes(
    private val description: String
) : CommonEnum {
    A("code A"),
    B("code B"),
    C("code C");

    override fun getName(): String {
        return this.name
    }

    override fun getDescription(): String {
        return description
    }
}
