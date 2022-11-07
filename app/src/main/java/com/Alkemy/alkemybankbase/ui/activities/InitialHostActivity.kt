package com.Alkemy.alkemybankbase.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.Alkemy.alkemybankbase.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitialHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_host)
    }
}