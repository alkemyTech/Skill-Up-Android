package com.Alkemy.alkemybankbase.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.databinding.FragmentLoginBinding
import com.Alkemy.alkemybankbase.viewmodels.loginviewmodel.LoginViewModel


class LoginFragment : Fragment() {

    // Declaro las variables de vinculacion de datos
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    // Declaro el ViewModel
    val viewModel by viewModels<LoginViewModel>()

    // Utilizo la funcion Textwatcher para poder controlar el cambio de los textos editables a traves del objeto
    val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            //Llamo al metodo de validacion para que se realice la comprobacion en el viewModel, pasando los parametros del email y la contraseña
            viewModel.isValidateEmailAndPassword(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString()
            )
        }

        override fun afterTextChanged(p0: Editable?) {
            //Aplico el metodo observe para que controlo el ciclo de vida y se habilite o deshabilite
            //el boton dependendiendo de la variable
            viewModel.isEnable.observe(viewLifecycleOwner, Observer { enable ->
                binding.btnSingIn.isEnabled = enable
                navigateUP(enable)
            })
        }


    }

    //Creamos la funcion de navegacion
    private fun navigateUP(enable: Boolean) {
    // Realizamos un flujo de control en el caso de que se haya habilitado el buton le permitimos el acceso
        if (enable) {
            binding.btnSingIn.setOnClickListener {
//                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edtEmail.addTextChangedListener(loginTextWatcher)
        binding.edtPassword.addTextChangedListener(loginTextWatcher)

        events()

    }

    private fun events() = with(binding) {

        tvCreateUser.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}










