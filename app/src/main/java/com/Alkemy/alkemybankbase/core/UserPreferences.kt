package com.Alkemy.alkemybankbase.core

import android.content.Context


class UserPreferences(context: Context) {
    private val sharedPreference = context.getSharedPreferences("token", Context.MODE_PRIVATE)

    // Saves token in SharedPreferences
    fun saveAuthToken(authToken: String){

        val editor = sharedPreference.edit()
        editor.putString("token",authToken)

        editor.apply()
    }
}