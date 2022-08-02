package com.example.demo.doc

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors

@RestController
class CommonDocumentationController {

    @GetMapping("/docs/common")
    fun findAll(): CommonResponse {
        return CommonResponse(
            commonCodes = getDocs(CommonCodes.values()),
        )
    }

    fun getDocs(commonEnum: Array<out CommonEnum>): Map<String, String> {
        return Arrays.stream(commonEnum).collect(Collectors.toMap(CommonEnum::getName, CommonEnum::getDescription))
    }
}

data class CommonResponse(
    val commonCodes: Map<String, String>,
)
