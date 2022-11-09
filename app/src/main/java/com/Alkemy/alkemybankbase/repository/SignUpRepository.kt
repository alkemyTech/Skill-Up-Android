package com.Alkemy.alkemybankbase.repository

import com.Alkemy.alkemybankbase.data.model.LoginInput
import com.Alkemy.alkemybankbase.data.model.LoginResponse
import com.Alkemy.alkemybankbase.data.model.User
import com.Alkemy.alkemybankbase.data.remote.ApiService
import com.Alkemy.alkemybankbase.utils.Resource

class SignUpRepository(private val apiService:ApiService) {

    suspend fun createUser(user: User) : Resource<LoginResponse> {
        val resp = try{
            apiService.addUser(user)
        }catch (e:Exception){
            return Resource.Failure(e)
        }
        return Resource.Success(resp)
    }
}