package com.Alkemy.alkemybankbase.adapter


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexamplo.R
import com.example.recyclerviewexamplo.SuperBank


class SuperBankHolder(view:View):RecyclerView.ViewHolder(view){

    val superBank = view.findViewById<TextView>(R.id.tvSuperBankName)
    val realName  = view.findViewById<TextView>(R.id.tvRealName)
    val publisher = view.findViewById<TextView>(R.id.tvpublisher)
    val photo = view.findViewById<ImageView>(R.id.tvSuperBank)

    fun render (superBankModel :SuperBank){
        superBank.text = superBankModel.Superbank
        realName.text = superBankModel.realName
        publisher.text = superBankModel.publisher

    }
}