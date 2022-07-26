package com.mintic22g1.recetapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.mintic22g1.recetapp.R
import com.mintic22g1.recetapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.homeToolbar)
    }

    override fun onStart() {
        super.onStart()
        val navController = findNavController(R.id.nav_host_home_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment,
            R.id.profileFragment,
            R.id.favoritesFragment,
            R.id.categoriesFragment
        ))
        binding.homeNavigation.setupWithNavController(navController)
        binding.homeToolbar.setupWithNavController(navController, appBarConfiguration)
    }
}