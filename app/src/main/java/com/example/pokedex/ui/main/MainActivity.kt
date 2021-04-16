package com.example.pokedex.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.pokedex.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private val controlador by lazy {
        findNavController(R.id.pokemons_activity_nav_host)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        configNavControler()
        configDrawer()

    }

    private fun configDrawer() {
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout as DrawerLayout?,
            R.string.open,
            R.string.close
        )
        (drawerLayout as DrawerLayout?)?.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun configNavControler() {
        val navView: BottomNavigationView = findViewById(R.id.main_activity_bottom_navigation)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.pokemons_activity_nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        navView.setupWithNavController(navController)

        configNavView(navController, navView)   // configura para ver se o Nav View aparece ou nao
    }

    private fun configNavView(
        navController: NavController,
        navView: BottomNavigationView

    ) {
        // pega o id do fragment e compara,se for igual, faz as atribuições necessárias (aparecer ou desaparecer Nav View)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.entrada_graph) {
                navView.visibility = View.GONE

            } else {
                navView.visibility = View.VISIBLE
            }

        }
    }

}
