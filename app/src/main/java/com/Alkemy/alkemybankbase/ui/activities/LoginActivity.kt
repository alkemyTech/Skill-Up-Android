package com.Alkemy.alkemybankbase.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.Alkemy.alkemybankbase.data.local.SessionManager
import com.Alkemy.alkemybankbase.databinding.ActivityLoginBinding
import com.Alkemy.alkemybankbase.presentation.LoginViewModel
import com.Alkemy.alkemybankbase.utils.afterTextChanged
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
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
        setupListeners()

        //If the user closed the app while logged in, when opened again he should be taken to home
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
        viewModel.isLoading.observe(this) {
            if(it) showLoading()
            else stopLoading()
        }
    }

    private fun setupListeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                //When clicked, sends the event to firebase analytics
                var bundle = Bundle()
                bundle.putString("message", "Login Pressed")
                firebaseAnalytics.logEvent("log_in_pressed", bundle)
                lifecycleScope.launch {
                    viewModel.loginUser(this@LoginActivity,email = binding.etEmail.text.toString(), password = binding.etPassword.text.toString())
                    if (viewModel.loginResponse.accessToken.isNotBlank()) {
                        bundle.putString("message", "Login Succeeded")
                        firebaseAnalytics.logEvent("log_in_success", bundle)
                        navigateToHome()
                    } else if(viewModel.loginError.isNotBlank()){
                        //TODO: Show AlertDialog
                        var dialog = AlertDialog.Builder(this@LoginActivity)
                        dialog.setTitle("No autorizado")
                        dialog.setMessage(viewModel.loginError)
                        dialog.show()
                        bundle.putString("message", "Login Failed")
                        firebaseAnalytics.logEvent("log_in_error", bundle)
                    }
                }
                btnFacebook.setOnClickListener{
                    var bundle = Bundle()
                    bundle.putString("message", "Login Facebook Pressed")
                    firebaseAnalytics.logEvent("facebook_pressed", bundle)

                }
                btnGmail.setOnClickListener{
                    var bundle = Bundle()
                    bundle.putString("message", "Login Gmail Pressed")
                    firebaseAnalytics.logEvent("gmail_pressed", bundle)
                }
                btnSignUp.setOnClickListener {
                    var bundle = Bundle()
                    bundle.putString("message", "SignUp Pressed")
                    firebaseAnalytics.logEvent("sign_up_pressed", bundle)
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

    private fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }
}