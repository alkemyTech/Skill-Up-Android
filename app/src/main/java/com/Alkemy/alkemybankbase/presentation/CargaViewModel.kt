package com.Alkemy.alkemybankbase.presentation

import androidx.lifecycle.ViewModel
import com.Alkemy.alkemybankbase.repository.CargaRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CargaViewModel @Inject constructor(private val loginRepo : CargaRepo) : ViewModel() {
}
