package com.Alkemy.alkemybankbase.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("first_name")
    var firstname : String,
    @SerializedName("last_name")
    var lastname : String,
    @SerializedName("email")
    var email : String,
    @SerializedName("password")
    val password : String,
    @SerializedName("roleId")
    var roleId : Int,
    @SerializedName("points")
    var points : Int,
) {
}