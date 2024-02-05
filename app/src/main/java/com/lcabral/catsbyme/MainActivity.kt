package com.lcabral.catsbyme

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.lcabral.catsbyme.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        window.statusBarColor = Color.BLACK
    }

    private fun initViews() {
        navHostFragment = supportFragmentManager.
        findFragmentById(binding.containerMain.id) as NavHostFragment

        val navController = navHostFragment.navController

        binding.bottomNavigation.apply {
            setupWithNavController(navController)

            window.statusBarColor = Color.BLACK
        }
    }
}
