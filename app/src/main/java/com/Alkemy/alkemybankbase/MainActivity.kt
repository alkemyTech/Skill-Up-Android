package com.Alkemy.alkemybankbase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Alkemy.alkemybankbase.data.model.ListaMovimientos
import com.Alkemy.alkemybankbase.ui.adapters.LastMovementsAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
    }

    lateinit var mRecyclerView: RecyclerView
    val mAdapter: LastMovementsAdapter = LastMovementsAdapter()

    fun setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.rvListaMovimientos) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.LastMovementsAdapter(getMovimientos(), this)
        mRecyclerView.adapter = mAdapter
    }

    fun getMovimientos(): MutableList<ListaMovimientos> {
        var movimientos: MutableList<ListaMovimientos> = ArrayList()
        movimientos.add(
            ListaMovimientos(
                1600,
                "Pago de Honorarios",
                "08-08-2022 15:00:00",
                "topup|payment",
                2,
                5,
                8
            )
        )
        movimientos.add(
            ListaMovimientos(
                800,
                "Otros",
                "22-10-2022 08:00:00",
                "topup|payment",
                8,
                4,
                3
            )
        )
        movimientos.add(
            ListaMovimientos(
                500,
                "Otros",
                "10-11-2022 17:00:00",
                "topup|payment",
                9,
                7,
                5
            )
        )
        movimientos.add(
            ListaMovimientos(
                2000,
                "Transfer",
                "11-11-2022 14:00:00",
                "topup|payment",
                6,
                6,
                4
            )
        )
        movimientos.add(
            ListaMovimientos(
                3000,
                "Transfer",
                "09-09-2022 23:00:00",
                "topup|payment",
                3,
                1,
                6
            )
        )
        movimientos.add(
            ListaMovimientos(
                1000,
                "Otros",
                "05-07-2022 18:00:00",
                "topup|payment",
                9,
                6,
                9
            )
        )
        movimientos.add(
            ListaMovimientos(
                2500,
                "Deposito",
                "04-12-2018 16:00:00",
                "topup|payment",
                6,
                2,
                6
            )
        )
        movimientos.add(
            ListaMovimientos(
                400,
                "Pago de Honorarios",
                "27-12-2021 20:00:00",
                "topup|payment",
                9,
                1,
                1
            )
        )
        movimientos.add(
            ListaMovimientos(
                800,
                "Pago de Honorarios",
                "15-06-2021",
                "topup|payment",
                3,
                2,
                9
            )
        )
        movimientos.add(
            ListaMovimientos(
                3500,
                "Transfer",
                "18-09-2020",
                "topup|payment",
                7,
                4,
                1,
            )
        )
        return movimientos
    }
}