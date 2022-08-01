package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/users")
    fun getUsers(): User {
        return userService.getUser()
    }

    @GetMapping("hello")
    fun hello(): String {
        return "hello"
    }
}
