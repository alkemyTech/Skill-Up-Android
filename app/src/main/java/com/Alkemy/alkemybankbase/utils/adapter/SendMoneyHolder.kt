package com.Alkemy.alkemybankbase.utils.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Alkemy.alkemybankbase.EnvioDinero


class SendMoneyHolder(view: View): RecyclerView.ViewHolder(view){

    val EnvioDinero = view.findViewById<TextView>(R.id.tvSuperBankName)
    val realName  = view.findViewById<TextView>(R.id.tvRealName)
    val publisher = view.findViewById<TextView>(R.id.tvpublisher)
    val photo = view.findViewById<ImageView>(R.id.tvSuperBank)

    fun render (superBankModel :SuperBank){
        SendMoneyAdapter.text = superBankModel.Superbank
        realName.text = superBankModel.realName
        publisher.text = superBankModel.publisher

    }
}




