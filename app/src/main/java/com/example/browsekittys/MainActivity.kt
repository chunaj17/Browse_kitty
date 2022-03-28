package com.example.browsekittys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.browsekittys.databinding.MainActivityBinding


class MainActivity : AppCompatActivity() {
  private lateinit var binding: MainActivityBinding
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    binding = MainActivityBinding.inflate(this.layoutInflater)
        setSupportActionBar(binding.toolbar)
        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.mainFragment,
                R.id.chooseFragment
            )
            .build()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }
}