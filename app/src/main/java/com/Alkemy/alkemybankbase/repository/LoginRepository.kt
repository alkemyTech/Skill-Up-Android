package com.Alkemy.alkemybankbase.repository

import com.Alkemy.alkemybankbase.data.model.LoginInput
import com.Alkemy.alkemybankbase.data.model.LoginResponse
import com.Alkemy.alkemybankbase.data.remote.ApiService
import com.Alkemy.alkemybankbase.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityScoped
class LoginRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun loginUser(loginInput: LoginInput) : Resource<LoginResponse>{
        val resp = try{
            apiService.loginUser(loginInput)
        }catch(e:Exception){
            return Resource.Failure(e)
        }
        return Resource.Success(resp)
    }

}