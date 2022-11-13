package com.Alkemy.alkemybankbase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Alkemy.alkemybankbase.data.datasource.RetrofitHelper
import com.Alkemy.alkemybankbase.data.model.ChargeBalanceTopUpRequest
import com.Alkemy.alkemybankbase.data.server.RemoteService
import com.Alkemy.alkemybankbase.utils.controlChargeBalance
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.*

class TopUpBalanceViewModel: ViewModel() {
    var isEnable = MutableLiveData<Boolean>()

    var isCalendar = MutableLiveData<String>()
    var mesagge = MutableLiveData<String>()

    //val money = resources.getStringArray(R.array.money)
    var money = MutableLiveData<Array<String>>()

    private var c = Calendar.getInstance()
    private var anio = c.get(Calendar.YEAR)
    private var mes = c.get(Calendar.MONTH)

    private var dia = c.get(Calendar.DAY_OF_MONTH)

    init {
        todayDate()
        monedasCambio()
    }


    fun sendMoney(request: ChargeBalanceTopUpRequest) {
        viewModelScope.launch {
            try {
                var call = RetrofitHelper.getRetrofit().create(RemoteService::class.java)
                    .topUpBalanceUser("/transactions",request)

                if (call.isExecuted){
                    //Mandamos mensaje que se envio
                    mesagge.postValue("Se envio el dinero")

                }

            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                when(throwable){
                    is TimeoutCancellationException ->{
                        mesagge.postValue("se acabo el tiempo")
                    }
                    is IOException ->{
                        mesagge.postValue("Error de red")
                    }
                    is HttpException ->{
                        mesagge.postValue("Errores de servidor")
                    }
                    else ->{
                        mesagge.postValue(":(")
                    }
                }


            }
        }
    }


    fun isCheckChargeBalance(monto: String?, moneda: String?, descripcion: String?) {
        isEnable.postValue(controlChargeBalance(monto!!, moneda!!, descripcion!!))
    }

    fun todayDate() {
        mes += 1
        isCalendar.value = "$dia/$mes/$anio"
    }

    fun monedasCambio() {
        money.value = arrayOf("Pesos", "Dolares", "Euros")
    }

}