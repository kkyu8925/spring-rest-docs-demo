package com.example.demo

import org.springframework.stereotype.Service

@Service
class UserService {

    fun getUser(): User {
        return User("user")
    }
}
