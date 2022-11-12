package com.Alkemy.alkemybankbase.data.datasource

import android.content.SharedPreferences
import arrow.core.Either
import com.Alkemy.alkemybankbase.data.Error
import com.Alkemy.alkemybankbase.data.model.UserLoginRequest
import com.Alkemy.alkemybankbase.data.model.UserLoginResponse
import com.Alkemy.alkemybankbase.data.model.UserRegisterRequest
import com.Alkemy.alkemybankbase.data.model.UserRegisterResponse
import com.Alkemy.alkemybankbase.data.server.RemoteService
import com.Alkemy.alkemybankbase.data.tryCall
import com.Alkemy.alkemybankbase.domain.User
import com.Alkemy.alkemybankbase.domain.UserLogin
import com.Alkemy.alkemybankbase.utils.Constants
import javax.inject.Inject

class UserServerDataSource @Inject
constructor(private val remoteService: RemoteService,
            private val sharedPreferences: SharedPreferences) : UserRemoteDatasource {

    override suspend fun auth(email: String, password: String): Either<Error, UserLogin> = tryCall {
        val response = remoteService.auth(UserLoginRequest(email, password))
        response?.let {
            sharedPreferences.edit().putString(Constants.TOKEN, it.accessToken).apply()
            it.toDomainModelLogin()
        }!!

    }

    override suspend fun registerUser(request: UserRegisterRequest): Either<Error, User> = tryCall {
        remoteService.createUser(request).toDomainModel()
    }

    //Mappers
    private fun UserRegisterResponse.toDomainModel() : User = User(first_name, last_name, email, password, roleId, points)

    private fun UserLoginResponse.toDomainModelLogin(): UserLogin = UserLogin(accessToken)

}