package com.Alkemy.alkemybankbase.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("first_name")
    val firstname : String = "",
    @SerializedName("last_name")
    val lastname:String = "",
    @SerializedName("email")
    val email : String ="",
    @SerializedName("password")
    val password : String =""
) {
}