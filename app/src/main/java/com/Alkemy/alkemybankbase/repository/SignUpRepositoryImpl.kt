package com.Alkemy.alkemybankbase.repository

import com.Alkemy.alkemybankbase.data.model.User
import com.Alkemy.alkemybankbase.data.model.UserResponse
import com.Alkemy.alkemybankbase.utils.Resource

class SignUpRepositoryImpl(val dataSource : DataSource) : SignUpRepository {

    override suspend fun createUser(user: User): Resource<UserResponse> {
        return dataSource.addUser(user)
    }
}