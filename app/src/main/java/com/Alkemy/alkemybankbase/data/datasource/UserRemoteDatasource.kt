package com.Alkemy.alkemybankbase.data.datasource

import arrow.core.Either
import com.Alkemy.alkemybankbase.data.Error
import com.Alkemy.alkemybankbase.data.model.UserRegisterRequest
import com.Alkemy.alkemybankbase.domain.User

interface UserRemoteDatasource {

    suspend fun auth(email:String, password:String)

    suspend fun registerUser(request: UserRegisterRequest) : Either<Error, User>

}