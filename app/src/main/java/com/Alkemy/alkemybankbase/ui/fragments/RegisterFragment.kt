package com.Alkemy.alkemybankbase.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.databinding.FragmentRegisterBinding

//inflamos el onCreateView en el constructor del fragmento
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding : FragmentRegisterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)

        init()
        events()


    }

    private fun events() = with(binding) {

        include.imgBackHeader.setOnClickListener {
            activity?.onBackPressed()
        }

    }

    private fun init() = with(binding) {

        include.tvTitleHeader.text = getString(R.string.fragment_register_title)

    }

}