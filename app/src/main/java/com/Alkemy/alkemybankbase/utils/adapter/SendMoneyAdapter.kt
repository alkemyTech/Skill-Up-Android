package com.Alkemy.alkemybankbase.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Alkemy.alkemybankbase.EnvioDinero
import com.Alkemy.alkemybankbase.R
import com.example.movie_showcase.ResponsesClass.Movies
import com.example.recyclerviewexamplo.R

class SendMoneyAdapter ( private val envioDineroLIst:List<EnvioDinero>) : RecyclerView.Adapter<SendMoneyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SendMoneyHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SendMoneyHolderHolder(layoutInflater.inflate(R.layout.item_superbank, parent,false))
    }

    override fun onBindViewHolder(holder: SendMoneyHolder, position: Int) {
        val item = superBankList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = superBankList.size

}

