package com.Alkemy.alkemybankbase.data.model

data class UserRegisterResponse (
    val error: String?, //"error": "No autorizado"
    val status:Int?, //"status": 401
    val first_name: String, //"first_name": "Juan"
    val last_name:String, //"last_name": "Perez"
    val email: String, //"email": "juanperez@example.com"
    val password: String, //"password": "abc123"
    val roleId: Int, //"roleId": 2
    val points: Int //"points": 50
)