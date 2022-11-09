package com.Alkemy.alkemybankbase.data.server

import com.Alkemy.alkemybankbase.data.model.UserRegisterRequest
import com.Alkemy.alkemybankbase.data.model.UserRegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteService {


//        @POST("/api/usuarios/login")
//        suspend fun auth(@Body request: LoginRequest) : Response<WrappedResponse<UserRemote>>

    @POST("/users")
    suspend fun createUser(@Body request: UserRegisterRequest): UserRegisterResponse


}