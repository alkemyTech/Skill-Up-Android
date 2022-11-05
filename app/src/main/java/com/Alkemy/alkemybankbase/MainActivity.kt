package com.Alkemy.alkemybankbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.Alkemy.alkemybankbase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialize navHostFragment and navController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        //Method to handler the bottom Navigation visivility
        observeDestinationChangeListener(navController)

    }

    private fun observeDestinationChangeListener(navController: NavController) {
        navController.addOnDestinationChangedListener {controller, destination, arguments ->
            when(destination.id){

                R.id.splashFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                R.id.loginFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                R.id.homeFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.transactionsFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.topUpBalanceFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.paymentsLoadFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.sendMoneyFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }

            }

        }
    }
}