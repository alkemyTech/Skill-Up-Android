package com.Alkemy.alkemybankbase.repository

import com.Alkemy.alkemybankbase.data.model.LoginInput
import com.Alkemy.alkemybankbase.data.model.LoginResponse
import com.Alkemy.alkemybankbase.utils.Resource

interface SignUpRepository {

    suspend fun createUser(user: LoginInput) : Resource<LoginResponse>
}