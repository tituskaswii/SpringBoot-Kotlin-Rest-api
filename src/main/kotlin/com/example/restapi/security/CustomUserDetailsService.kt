package com.example.restapi.security

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        // Replace this with your actual logic to load user details from your repository
        if (username == "admin") {
            return User("admin", "{bcrypt}\$2a\$10\$8tRgZYpJiuR.gj4haZdXT.k1uVxY9Lq2HqZdSlXomhfxFpM4XUSQC", listOf())
        } else {
            throw UsernameNotFoundException("User not found with username: $username")
        }
    }
}
