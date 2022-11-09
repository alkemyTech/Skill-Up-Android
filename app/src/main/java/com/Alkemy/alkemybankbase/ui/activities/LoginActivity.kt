package com.Alkemy.alkemybankbase.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.data.local.SessionManager
import com.Alkemy.alkemybankbase.databinding.ActivityLoginBinding
import com.Alkemy.alkemybankbase.presentation.LoginViewModel
import com.Alkemy.alkemybankbase.utils.Constants.GOOGLE_SIGN_IN
import com.Alkemy.alkemybankbase.utils.afterTextChanged
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
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
            binding.btnLogin.isEnabled = it
            binding.btnGmail.isEnabled = it
            binding.btnFacebook.isEnabled = it
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
                        showAlert("Error",viewModel.loginError)
                        bundle.putString("message", "Login Failed")
                        firebaseAnalytics.logEvent("log_in_error", bundle)
                    }
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
                var googleClient = viewModel.loginGoogle(this@LoginActivity)
                startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)

                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        SessionManager.saveAuthToken(this@LoginActivity, it.result.user?.getIdToken(true).toString())
                        navigateToHome()
                    }else{
                        showAlert("Error","No se pudo autenticar el usuario")
                    }
                }
            }
            btnSignUp.setOnClickListener {
                var bundle = Bundle()
                bundle.putString("message", "SignUp Pressed")
                firebaseAnalytics.logEvent("sign_up_pressed", bundle)

                val signIntent = Intent(this@LoginActivity, SignUpActivity::class.java)
                startActivity(signIntent)
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
            btnLogin.isEnabled = false
            btnGmail.isEnabled = false
            btnFacebook.isEnabled = false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)

                if (account != null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener{
                        if (it.isSuccessful){
                            navigateToHome()
                        }else{
                            showAlert("Error","No se pudo autenticar el usuario")
                        }
                    }
                }
            }catch (e: ApiException){
                showAlert("Error",e.toString())
            }
        }

    }
    private fun showAlert(title:String, message:String){
        val builder = AlertDialog.Builder(this@LoginActivity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
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