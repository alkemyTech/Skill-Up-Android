package com.Alkemy.alkemybankbase.ui.fragments.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.data.model.UserRegisterRequest
import com.Alkemy.alkemybankbase.databinding.FragmentRegisterBinding
import com.Alkemy.alkemybankbase.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding : FragmentRegisterBinding

    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)

        init()
        events()
        setupObservers()

    }

    private fun setupObservers() {

        viewModel.state.observe(viewLifecycleOwner) { state ->

            when (state) {
                RegisterViewModel.RegisterUserState.Init -> Unit
                is RegisterViewModel.RegisterUserState.Error -> showError(state.message)
                is RegisterViewModel.RegisterUserState.IsLoading -> showProgress(state.isLoading)
                is RegisterViewModel.RegisterUserState.SuccessRegister -> {
                    val userRemote = state.user
                    requireContext().toast("Registro Exitoso: ${userRemote.first_name} ${userRemote.last_name}, Bienvenido")
                    requireActivity().onBackPressed()
                }
            }
        }
    }

    private fun showError(message: String) {
        message.forEach {
        }
        binding.root.context.toast(message)
    }

    private fun showProgress(visibility:Boolean) = with(binding){
        if (visibility) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }

    private fun events() = with(binding) {

        include.imgBackHeader.setOnClickListener {
            activity?.onBackPressed()
        }

        btnRegister.setOnClickListener {

            val name = edtFirstName.text.toString()
            val lastName = edtLastName.text.toString()
            val email = edtEmailRegistro.text.toString()
            val password = edtPassword.text.toString()

            if(name.isEmpty()){
                tilFirstName.error = getString(R.string.fragment_register_validation_names)
                return@setOnClickListener
            }
            if(lastName.isEmpty()){
                tilLastName.error = getString(R.string.fragment_register_validation_surname)
                return@setOnClickListener
            }
            if(email.isEmpty()){
                tilEmailRegistro.error = getString(R.string.fragment_register_validation_email_register)
                return@setOnClickListener
            }
            if(password.isEmpty()){
                tilPassword.error = getString(R.string.fragment_register_validation_password)
                return@setOnClickListener
            }

            if(!swTerms.isChecked){
                //TODO Implementar popup de terminos
                requireContext().toast("Debe aceptar los terminos y condiciones")
                return@setOnClickListener
            }

            viewModel.createUsuario(UserRegisterRequest(name, lastName, email, password))

        }

    }

    private fun init() = with(binding) {

        include.tvTitleHeader.text = getString(R.string.fragment_register_title)

    }

}