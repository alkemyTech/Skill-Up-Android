package com.Alkemy.alkemybankbase.repository

import com.Alkemy.alkemybankbase.data.model.User
import com.Alkemy.alkemybankbase.data.model.UserResponse
import com.Alkemy.alkemybankbase.utils.Resource
import com.Alkemy.alkemybankbase.utils.RetrofitClient

class DataSource {
    suspend fun addUser(user: User) : Resource<UserResponse>{
        return Resource.Success(RetrofitClient.webService.addUser(user))
    }
}