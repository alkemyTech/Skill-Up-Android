package com.Alkemy.alkemybankbase.data.model

data class UserRegisterResponse (
    val first_name: String, //"first_name": "Juan"
    val last_name:String, //"last_name": "Perez"
    val email: String, //"email": "juanperez@example.com"
    val password: String, //"password": "abc123"
    val roleId: Int, //"roleId": 2
    val points: Int //"points": 50
)

data class UserLoginResponse(
    val accessToken:String
)

data class ErrorResponse(
    val error: String, //"error": "No autorizado"
    val status:Int //"status": 401
){
    override fun toString(): String {
        return "ErrorResponse(error= $error, status= $status)"
    }
}

data class TransactionsResponse(
    val amount: String,
    val concept: String,
    val date: String,
    val type: String,
)