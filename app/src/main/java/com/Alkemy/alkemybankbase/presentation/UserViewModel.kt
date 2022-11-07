package com.Alkemy.alkemybankbase.presentation

import androidx.lifecycle.*
import com.Alkemy.alkemybankbase.core.UserPreferences
import com.Alkemy.alkemybankbase.data.model.LoginRequest
import com.Alkemy.alkemybankbase.data.model.UserRemote
import com.Alkemy.alkemybankbase.repository.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel: ViewModel() {
    private val _state = MutableLiveData<LoginState>(LoginState.Init)
    val state: LiveData<LoginState> get() = _state

    private lateinit var userPreferences: UserPreferences

    fun auth(email:String, password:String){
        viewModelScope.launch {

            _state.value = LoginState.IsLoading(true)

            try {
                val response = withContext(Dispatchers.IO) {
                    WebService.build().loginUser(LoginRequest(email, password))
                }
                if (response.isSuccessful) {
                    val response = response.body()
                    response?.let {
                        if (it.accessToken != null) {
                            _state.value = LoginState.Success(UserRemote(it.accessToken))
                            userPreferences.saveAuthToken(it.accessToken)
                        }
                        else {
                            _state.value = LoginState.Error(it.error!!)
                        }
                    }
                } else {
                    _state.value = LoginState.Error(response.toString())
                }
            } catch (ex: Exception) {
                _state.value = LoginState.Error(ex.message.toString())
            } finally {
                _state.value = LoginState.IsLoading(false)
            }
        }
    }

    sealed class LoginState {

        object Init : LoginState()
        data class IsLoading(val isLoading: Boolean) : LoginState()
        data class Error(val rawResponse: String) : LoginState()
        data class Success(val user: UserRemote) : LoginState()

    }
}
