package com.Alkemy.alkemybankbase.repository

import com.Alkemy.alkemybankbase.data.model.LoginInput
import com.Alkemy.alkemybankbase.data.model.LoginResponse
import com.Alkemy.alkemybankbase.data.model.User
import com.Alkemy.alkemybankbase.data.model.UserResponse
import com.Alkemy.alkemybankbase.data.remote.ApiService
import com.Alkemy.alkemybankbase.utils.Resource
<<<<<<< HEAD
import javax.inject.Inject

class SignUpRepository @Inject constructor(private val apiService:ApiService) : SignUpRepo{
    /***********************************************************
          IMPLEMENTATIONS GO HERE, MAKE SURE TO USE OVERRIDE
     ************************************************************/
    override suspend fun createUser(user: User) : Resource<UserResponse> {
=======
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class  SignUpRepository @Inject constructor(private val apiService:ApiService) {

    suspend fun createUser(user: User) : Resource<UserResponse> {
>>>>>>> e9a55a6 (Added register with retrofit, Added dialogs with errors)
        val resp = try{
            apiService.addUser(user)
        }catch (e:Exception){
            return Resource.Failure(e)
        }
        return Resource.Success(resp)
    }
}