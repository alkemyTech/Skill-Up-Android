package com.Alkemy.alkemybankbase.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    var code:Int,
    var accessToken : String
) {
}