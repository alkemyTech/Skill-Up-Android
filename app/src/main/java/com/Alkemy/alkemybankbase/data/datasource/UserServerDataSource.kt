package com.Alkemy.alkemybankbase.data.datasource

import arrow.core.Either
import com.Alkemy.alkemybankbase.data.Error
import com.Alkemy.alkemybankbase.data.model.UserRegisterRequest
import com.Alkemy.alkemybankbase.data.model.UserRegisterResponse
import com.Alkemy.alkemybankbase.data.server.RemoteService
import com.Alkemy.alkemybankbase.data.tryCall
import com.Alkemy.alkemybankbase.domain.User
import javax.inject.Inject

class UserServerDataSource @Inject constructor(private val remoteService: RemoteService) : UserRemoteDatasource {

    override suspend fun auth(email: String, password: String) {
//        TODO("Not yet implemented")

    }

    override suspend fun registerUser(request: UserRegisterRequest): Either<Error, User> = tryCall {

        remoteService.createUser(request).toDomainModel()

    }

    private fun UserRegisterResponse.toDomainModel() : User = User(first_name, last_name, email, password, roleId, points)


}