package com.Alkemy.alkemybankbase.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.data.datasource.RvData

class HomeRVAdapter : RecyclerView.Adapter<HomeRVAdapter.ViewHolder>() {
    var moves: MutableList<RvData> = ArrayList()
    lateinit var context: Context

    fun HomeRVAdapter(moves: MutableList<RvData>, context: Context) {
        this.moves = moves
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = moves[position]
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_home_activity, parent, false))
    }

    override fun getItemCount(): Int {
        return moves.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById(R.id.textView11) as TextView
        val cuenta = view.findViewById(R.id.textView12) as TextView
        val amount = view.findViewById(R.id.textView13) as TextView
        //val photo = view.findViewById(R.id.img) as ImageView

        fun bind(moves: RvData, context: Context) {
            name.text = moves.name
            cuenta.text = moves.cuenta
            amount.text = moves.amount
            //photo.text = moves.photo
            itemView.setOnClickListener {
                Toast.makeText(
                    context,
                    moves.name,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}