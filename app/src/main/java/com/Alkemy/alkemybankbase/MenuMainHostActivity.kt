package com.Alkemy.alkemybankbase

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.Alkemy.alkemybankbase.adapter.EnviarDineroAdapter
import com.Alkemy.alkemybankbase.adapter.EnvioDineroProvider


class MenuMainHostActivity : AppCompatActivity() {

  override fun onCreate(saveInstanceState: AlertDialog.Builder){
      super.onCreate(saveInstanceState)
      EnvioDineroProvider.enviarDineroList
      setContentView(R.layout.activity_menu_main)
}
}