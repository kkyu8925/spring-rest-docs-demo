package com.example.demo.docs.common

import com.example.demo.CommonCodes
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CommonDocumentationController {

    @GetMapping("/docs/common")
    fun findAll(): CommonResponse {
        return CommonResponse(
            commonCodes = CommonCodes.values().associate { it.name to it.description }
        )
    }
}

data class CommonResponse(
    val commonCodes: Map<String, String>,
)
