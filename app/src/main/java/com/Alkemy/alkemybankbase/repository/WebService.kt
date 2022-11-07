package com.Alkemy.alkemybankbase.repository

import com.Alkemy.alkemybankbase.application.AppConstants
import com.Alkemy.alkemybankbase.data.model.LoginRequest
import com.Alkemy.alkemybankbase.data.model.LoginResponse
import com.Alkemy.alkemybankbase.data.model.WrappedResponse
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object WebService {

   private val builder : Retrofit.Builder = Retrofit.Builder()
       .baseUrl(AppConstants.API_URL)
       .addConverterFactory(GsonConverterFactory.create())

    interface ApiInterface{
        @POST("auth/login")
        suspend fun loginUser(@Body request: LoginRequest) : Response<WrappedResponse<LoginResponse>>
    }

    fun build() : ApiInterface{
        return builder.build().create(ApiInterface::class.java)
    }


}