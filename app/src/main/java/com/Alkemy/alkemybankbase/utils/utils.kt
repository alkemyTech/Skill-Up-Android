package com.Alkemy.alkemybankbase.utils

import android.content.Context
import android.widget.Toast
import android.util.Patterns

fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}

/*
    Es una funcion con tipo lo que hace es recibir dos strings(email y la contraseña) y devuelve un valor boleano
    En el caso de que sea verdadero se admite el mail ingresado y el password
    Utilizamos expresiones regulares para coomparar  los dos strings
 */
fun controlEmailAndPassword(email: String, password: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email)
        .matches() && (password.matches("^[a-zA-Z0-9]*$".toRegex()) && password.isNotEmpty())

}