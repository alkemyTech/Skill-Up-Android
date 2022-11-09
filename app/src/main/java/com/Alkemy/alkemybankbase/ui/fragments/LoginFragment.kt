package com.Alkemy.alkemybankbase.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.lifecycle.Observer
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.core.UserPreferences
import com.Alkemy.alkemybankbase.databinding.FragmentLoginBinding
import com.Alkemy.alkemybankbase.presentation.UserViewModel
import com.Alkemy.alkemybankbase.utils.toast


open class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding : FragmentLoginBinding

    private val viewModel : UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        //Mockup
        binding.edtEmail.setText("juanperez@example.com")
        binding.edtPassword.setText("abc123")

        events()
        setupObservers()
    }

    // Do login
    private fun events() = with(binding) {

        btnSingIn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            viewModel.auth(email, password)
        }

        tvCreateUser.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    // Sets Up Login State
    private fun setupObservers(){
        viewModel.state.observe(viewLifecycleOwner){ state ->
            when(state){
                UserViewModel.LoginState.Init -> Unit
                is UserViewModel.LoginState.Error -> showError(state.rawResponse)
                is UserViewModel.LoginState.IsLoading -> showProgress(state.isLoading)
                is UserViewModel.LoginState.Success -> {
                    val userRemote = state.user
                    // TODO Navigate to Home
                    requireContext().toast("Bienvenido ${userRemote.accessToken}")
                }
            }
        }
    }

    // Show error alert dialog if login fails
    private fun showError(error:String){
        // TODO Show Error Dialog
        requireContext().toast(error)
    }

    // Show progress indicator while loading
    private fun showProgress(visibility:Boolean){
        // TODO Show Pregress Indicator
    }

}