package com.Alkemy.alkemybankbase.presentation

import android.content.Context
import android.util.Log
import androidx.core.util.PatternsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.data.local.SessionManager
import com.Alkemy.alkemybankbase.data.model.LoginInput
import com.Alkemy.alkemybankbase.data.model.LoginResponse
import com.Alkemy.alkemybankbase.repository.LoginRepository
import com.Alkemy.alkemybankbase.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepo : LoginRepository) : ViewModel() {
    val emailErrorResourceIdLiveData = MutableLiveData<Int>()
    val passwordErrorResourceIdLiveData = MutableLiveData<Int>()
    val isFormValidLiveData = MutableLiveData<Boolean>()
    lateinit var currentResponse : LoginResponse
    lateinit var errorResponse : String


    //Check email & password
    fun validateForm(email: String, password: String) {
        // check if email is valid with pattern
        val isEmailValid = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
        // check if password is valid with pattern
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=\\S+\$).{8,}"
        val pattern = Pattern.compile(passwordPattern)
        val isPasswordValid = pattern.matcher(password).matches()

        if (!isEmailValid){
            emailErrorResourceIdLiveData.value = R.string.email_error
            isFormValidLiveData.value = false
        }else if (!isPasswordValid){
            passwordErrorResourceIdLiveData.value = R.string.password_error
            isFormValidLiveData.value = false
        }else{
            isFormValidLiveData.value = true
        }
    }

    suspend fun loginUser(context: Context, email:String, password:String){
        var loginResult: Resource<LoginResponse>

            val loginInput = LoginInput(email = email,
                password = password)
            loginResult = loginRepo.loginUser(loginInput = loginInput)
            Log.d("******************************************************",loginResult.toString())
            Log.d("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@",(loginResult as Resource.Success<LoginResponse>).data.toString())
            Log.d("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!",(loginResult as Resource.Success<LoginResponse>).data.accessToken)
            if(loginResult is Resource.Success){
                currentResponse = (loginResult as Resource.Success<LoginResponse>).data
                SessionManager.saveAuthToken(context,
                    (loginResult as Resource.Success<LoginResponse>).data.accessToken)
            }
            if(loginResult is Resource.Failure){
                errorResponse = (loginResult as Resource.Failure).toString()
            }
            Log.d("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^", loginResult::class.simpleName.toString())


    }
}