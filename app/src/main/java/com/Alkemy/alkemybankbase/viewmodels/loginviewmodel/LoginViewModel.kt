package com.Alkemy.alkemybankbase.viewmodels.loginviewmodel

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.Alkemy.alkemybankbase.utils.controlEmailAndPassword

class LoginViewModel : ViewModel() {

    //Declaro la variable mutable booleana la cual controla el cambio si permite
    var isEnable = MutableLiveData<Boolean>()


    // Lo que realiza esta funcion es recoger y enviar el parametro a la otra funcion "controlEmailAndPassowrd",
    // la cual va verificando en tiempo si acepta la expresion regular
    fun isValidateEmailAndPassword(email: String?, password: String?) {
        //La varialbe "isEnable" toma el valor de esa funcion y se actualiza con el metodo postValue
        isEnable.postValue(controlEmailAndPassword(email!!, password!!))
    }


}



