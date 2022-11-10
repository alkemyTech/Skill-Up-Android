package com.Alkemy.alkemybankbase.presentation

import androidx.lifecycle.ViewModel
import com.Alkemy.alkemybankbase.repository.charge.ChargeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChargeViewModel @Inject constructor(private val chargeRepo : ChargeRepository) : ViewModel() {

    fun validateForm(){

    }
}
