package com.Alkemy.alkemybankbase.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.databinding.ActivityHomeBinding
import com.Alkemy.alkemybankbase.ui.fragments.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFragment()
    }

    //Link each icon for the bottom navigation to a fragment
    private fun initFragment(){
        val homeFragment = HomeFragment()
        val expenseFragment = ExpenseFragment()
        val chargeBalanceFragment = ChargeBalanceFragment()
        val movementFragment = MovementFragment()
        val sendFragment = SendFragment()

        //When the app initialize show home screen
        setCurrentFragment(homeFragment)

        binding.bottomNavigation.setOnItemSelectedListener{ item ->
            when(item.itemId) {
                R.id.home_page -> setCurrentFragment(homeFragment)
                R.id.expense_page -> setCurrentFragment(expenseFragment)
                R.id.chargeBalance_page -> setCurrentFragment(chargeBalanceFragment)
                R.id.movement_page -> setCurrentFragment(movementFragment)
                R.id.send_page -> setCurrentFragment(sendFragment)
            }
            true
        }
    }

    //Set the current fragment displayed depends on the selected icon
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}