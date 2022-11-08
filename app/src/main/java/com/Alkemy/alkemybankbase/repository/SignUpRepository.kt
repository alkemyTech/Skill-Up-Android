package com.Alkemy.alkemybankbase.repository

import com.Alkemy.alkemybankbase.data.model.User
import com.Alkemy.alkemybankbase.data.model.UserResponse
import com.Alkemy.alkemybankbase.utils.Resource

interface SignUpRepository {

    suspend fun createUser(user: User) : Resource<UserResponse>
}