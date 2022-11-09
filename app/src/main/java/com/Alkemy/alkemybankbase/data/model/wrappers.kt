package com.Alkemy.alkemybankbase.data.model

data class WrappedResponse<T>(
    val accessToken: String?,
    val error: String?,
    val status: Int?,
    val data: T? = null
)