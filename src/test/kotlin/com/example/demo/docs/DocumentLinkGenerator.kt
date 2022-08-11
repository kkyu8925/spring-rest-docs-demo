package com.example.demo.docs

interface DocumentLinkGenerator {

    companion object {
        fun generateLinkCode(docUrl: DocUrl): String {
            return String.format("link:common/%s.html[%s,role=\"popup\"]", docUrl.pageId, docUrl.text)
        }

        fun generateText(docUrl: DocUrl): String {
            return String.format("%s %s", docUrl.text, "코드명")
        }
    }
}

enum class DocUrl(
    val pageId: String,
    val text: String
) {
    CommonCodesDocUrl("commonCodes", "공통코드"),
}

