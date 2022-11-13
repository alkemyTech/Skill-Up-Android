package com.Alkemy.alkemybankbase

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.Alkemy.alkemybankbase.data.model.ChargeBalanceTopUpRequest
import com.Alkemy.alkemybankbase.databinding.FragmentTopUpBalanceBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TopUpBalanceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopUpBalanceFragment : Fragment(), AdapterView.OnItemClickListener {

    // Declaro las variables de vinculacion de datos
    private var _binding: FragmentTopUpBalanceBinding? = null
    private val binding get() = _binding!!


    // Declaro el ViewModel
    val viewModel by viewModels<TopUpBalanceViewModel>()


    // Utilizo la funcion Textwatcher para poder controlar el cambio de los textos editables a traves del objeto
    val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {

            viewModel.isCheckChargeBalance(
                binding.edtBalance.text.toString(),
                binding.tvTipoDeMoneda.text.toString(),
                binding.edtConcept.text.toString()
            )

        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopUpBalanceBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        binding.apply {
            edtBalance.addTextChangedListener(loginTextWatcher)
            edtConcept.addTextChangedListener(loginTextWatcher)
            autoCompleteTextView.addTextChangedListener(loginTextWatcher)
        }

    }

    private fun setupViewModel() {
        viewModel.isCalendar.observe(viewLifecycleOwner, Observer { calendar ->
            binding.tvFechaActual.text = calendar
        })
        viewModel.isEnable.observe(viewLifecycleOwner, Observer { enable ->
            binding.fabCheck.isEnabled = enable
            if (enable) {
                binding.fabCheck.setOnClickListener {
                    sendMoney()
                }
            }

        })
        viewModel.money.observe(viewLifecycleOwner, Observer { changeMoney ->
            controlAdapter(changeMoney)
        })
        viewModel.mesagge.observe(viewLifecycleOwner, Observer { Mesagge->
            Toast.makeText(context,Mesagge, Toast.LENGTH_LONG).show()

        })
    }


    private fun sendMoney() {
        viewModel.sendMoney(
            request = ChargeBalanceTopUpRequest(
                amount = binding.edtBalance.text.toString().toInt(),
                concept = binding.edtConcept.text.toString(),
                date = binding.tvFechaActual.text.toString(),
                type = "topup",
                accountId = 1,
                userId = 4,
                to_account_id = 5
            )
        )
    }


    private fun controlAdapter(changeMoney: Array<String>) {
        val adapter =
            ArrayAdapter(binding.root.context, R.layout.list_item_drop_drown_menu, changeMoney)
        with(binding.autoCompleteTextView) {
            setAdapter(adapter)
            onItemClickListener = this@TopUpBalanceFragment
        }

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
    }
}