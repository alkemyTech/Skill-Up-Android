package com.Alkemy.alkemybankbase.data.model

//Request User Register
data class UserRegisterRequest(
    val first_name: String, //"first_name": "Juan"
    val last_name: String, //"last_name": "Perez"
    val email: String, //"email": "juanperez@example.com"
    val password: String, //"password": "abc123"
    val roleId: Int = 2, //"roleId": 2
    val points: Int = 50 //"points": 50
)