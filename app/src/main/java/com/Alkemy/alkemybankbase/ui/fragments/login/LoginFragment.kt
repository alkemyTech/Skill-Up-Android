package com.Alkemy.alkemybankbase.ui.fragments.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.databinding.FragmentLoginBinding
import com.Alkemy.alkemybankbase.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        //Mockup
        binding.edtEmailLogin.setText("prueba3@alkemy.com")
        binding.edtPasswordLogin.setText("123456")

        events()
        setupObservers()
    }

    // Do login
    private fun events() = with(binding) {

        btnSingIn.setOnClickListener {
            val email = binding.edtEmailLogin.text.toString()
            val password = binding.edtPasswordLogin.text.toString()

            if (email.isEmpty()) {
                tilEmailLogin.error = getString(R.string.fragment_login_valid_email)
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                tilPasswordLogin.error = getString(R.string.fragment_login_valid_pwd)
                return@setOnClickListener
            }

            viewModel.auth(email, password)

            val preferences = requireContext().getSharedPreferences("PREFERENCES_USER", 0).edit()
            preferences.putString("TOKEN","demo")
            preferences.apply()

        }

        tvCreateUser.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    // Sets Up Login State
    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                LoginViewModel.LoginState.Init -> Unit
                is LoginViewModel.LoginState.Error -> showError(state.rawResponse)
                is LoginViewModel.LoginState.IsLoading -> showProgress(state.isLoading)
                is LoginViewModel.LoginState.Success -> {
                    val userRemote = state.user
                    // TODO Navigate to Home
                    requireContext().toast("Token ${userRemote.accessToken}")
                }
            }
        }
    }

    // Show error alert dialog if login fails
    private fun showError(error: String) {
        // TODO Show Error Dialog
        requireContext().toast(error)
    }

    // Show progress indicator while loading
    private fun showProgress(visibility: Boolean) {
        // TODO Show Pregress Indicator
    }

}