package com.Alkemy.alkemybankbase.data.model

data class UserResponse(
    val user:User,
    val accessToken : String =""
) {
}