package com.Alkemy.alkemybankbase.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.data.local.SessionManager
import com.Alkemy.alkemybankbase.data.model.LoginResponse
import com.Alkemy.alkemybankbase.databinding.ActivityLoginBinding
import com.Alkemy.alkemybankbase.databinding.ActivitySignUpBinding
import com.Alkemy.alkemybankbase.presentation.LoginViewModel
import com.Alkemy.alkemybankbase.presentation.SignUpViewModel
import com.Alkemy.alkemybankbase.utils.Resource
import com.Alkemy.alkemybankbase.utils.afterTextChanged
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var firebaseAnalytics : FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        setuplistener()

        val token = SessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

    }


    private fun setupObservers() {
        viewModel.isFormValidLiveData.observe(this) {
            // enable or disable button
            binding.btnSignUp.isEnabled = it
        }
        viewModel.emailErrorResourceIdLiveData.observe(this) { resId ->
            //show email error
            binding.etEmail.error = getString(resId)
        }
        viewModel.passwordErrorResourceIdLiveData.observe(this) { resId ->
            //show password error
            binding.etPassword.error = getString(resId)
        }
    }

    private fun setuplistener() {
        with(binding) {
            btnLogin.setOnClickListener {
                var bundle = Bundle()
                bundle.putString("message", "Login Pressed")
                firebaseAnalytics.logEvent("log_in_pressed", bundle)

                lifecycleScope.launch {
                    viewModel.loginUser(this@LoginActivity,email = binding.etEmail.text.toString(), password = binding.etPassword.text.toString())

                    if (!viewModel.currentResponse.accessToken.isNullOrEmpty()) {
                        bundle.putString("message", "Login Succeeded")
                        firebaseAnalytics.logEvent("log_in_success", bundle)
                        navigateToHome()
                    } else if(!viewModel.errorResponse.isNullOrEmpty()){
                        bundle.putString("message", "Login Failed")
                        firebaseAnalytics.logEvent("log_in_error", bundle)
                    }
                }


            }
            etEmail.afterTextChanged {
                viewModel.validateForm(
                    etEmail.text.toString(),
                    etPassword.text.toString()
                )
            }
            etPassword.afterTextChanged {
                viewModel.validateForm(
                    etEmail.text.toString(),
                    etPassword.text.toString()
                )
            }
            btnSignUp.isEnabled = false
        }
    }

    private fun navigateToHome(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}