package com.example.pokedex.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.pokedex.EstadoAppViewModel
import com.example.pokedex.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val controlador by lazy {
        findNavController(R.id.pokemons_activity_nav_host)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        configNavControler()
    }

    private fun configNavControler() {
        val navView: BottomNavigationView = findViewById(R.id.main_activity_bottom_navigation)
        val navHostFragment = supportFragmentManager
           .findFragmentById(R.id.pokemons_activity_nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        navView.setupWithNavController(navController)
    }

}