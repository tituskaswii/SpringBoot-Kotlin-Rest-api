//this is a simple classes to represent authentication request
package com.example.restapi.dto

data class AuthenticationRequest(
    val username: String,
    val password: String
)
