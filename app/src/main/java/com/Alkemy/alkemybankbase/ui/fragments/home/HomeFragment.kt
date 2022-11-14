package com.Alkemy.alkemybankbase.ui.fragments.transactions

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.databinding.FragmentHomeBinding
import com.Alkemy.alkemybankbase.ui.adapters.HomeRVAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding : FragmentHomeBinding

    private val transactionsViewModel: TransactionsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        transactionsViewModel.getTransactions()

        setupObservers()
    }

    private fun setupObservers() {
        transactionsViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                TransactionsViewModel.TransactionsState.Init -> Unit
                is TransactionsViewModel.TransactionsState.Error -> showError(state.rawResponse)
                is TransactionsViewModel.TransactionsState.IsLoading -> showProgress(state.isLoading)
                is TransactionsViewModel.TransactionsState.Success -> {

                    val adapter = HomeRVAdapter()

                    binding.rvh.layoutManager = LinearLayoutManager(requireContext())
                    val recyclerView: RecyclerView = binding.rvh
                    recyclerView.adapter = adapter

                    adapter.HomeRVAdapter(state.transactions,requireContext())
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    // Show error alert dialog if login fails
    private fun showError(error: String) {
        val dialog: AlertDialog =
            AlertDialog.Builder(context).setMessage(error).setTitle("Invalid user or password")
                .setNeutralButton(
                    "dissmiss"
                ) { _, _ -> }
                .create()
        dialog.show()
    }

    // Show progress indicator while loading
    private fun showProgress(visibility: Boolean) {
        // TODO Show Pregress Indicator
    }
}