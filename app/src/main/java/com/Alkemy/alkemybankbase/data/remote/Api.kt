package com.Alkemy.alkemybankbase.data.remote

import com.Alkemy.alkemybankbase.data.model.UserRegisterRequest
import com.Alkemy.alkemybankbase.data.model.UserRegisterResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

object Api {

    //ENDPOINT = URLBASE + METODO
    //URLBASE = https://marketapp2021.herokuapp.com/
    //ENPOINT = api/usuarios/login

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl("http://wallet-main.eba-ccwdurgr.us-east-1.elasticbeanstalk.com")
        .addConverterFactory(GsonConverterFactory.create())

    interface ApiInterface {

//        @POST("/api/usuarios/login")
//        suspend fun auth(@Body request: LoginRequest) : Response<WrappedResponse<UserRemote>>

        @POST("/users")
        suspend fun createUser(@Body request: UserRegisterRequest): Response<UserRegisterResponse>
    }

    fun build(): ApiInterface {
        return builder.build().create(ApiInterface::class.java)
    }

}