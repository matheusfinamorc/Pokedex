package com.example.pokedex.ui.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.pokedex.R
import com.example.pokedex.ui.listaFavPokemons.ListaFavPokemonsFragment
import com.example.pokedex.ui.listaPokemons.ListaPokemonsFragment
import kotlinx.android.synthetic.main.lista_fav_pokemons.*
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.nav_header.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    var currentPath: String? = null
    val TAKE_PICTURE = 1
    val SELECT_PICTURE = 2
    lateinit var favFragment: ListaFavPokemonsFragment
    lateinit var listaFragment: ListaPokemonsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        configBottomNavegacao()
        configNavDrawer()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.pokemons_activity_nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        main_activity_bottom_navigation.setupWithNavController(navController)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.drawer_Home -> dispatchHome()
                R.id.drawer_camera -> dispatchCameraIntent()
                R.id.drawer_fav -> dispatchFavoritosIntent()
                R.id.drawer_maps -> dispatchMaps()
            }
            true
        }
    }

    private fun dispatchHome() {
        listaFragment = ListaPokemonsFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.pokemons_activity_nav_host, listaFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    private fun dispatchMaps() {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

    private fun dispatchFavoritosIntent() {
        favFragment = ListaFavPokemonsFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.pokemons_activity_nav_host, favFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    private fun configNavDrawer() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.pokemons_activity_nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        // verifica onde vai ou nao mostrar o drawer
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.listaPokemons -> mostraNavDrawer()
                R.id.listaFavPokemons -> mostraNavDrawer()
                R.id.search -> mostraNavDrawer()
                R.id.detalhesPokemons -> mostraNavDrawer()
            else -> ocultaNavDrawer()
            }
        }
    }

    private fun mostraNavDrawer() {
        navView.visibility = View.VISIBLE
    }
    private fun ocultaNavDrawer(){
        navView.visibility = View.GONE
    }

    // controle bottom navigation
    private fun configBottomNavegacao() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.pokemons_activity_nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        // verifica onde vai ou nao mostrar o bottom navigation
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.listaPokemons -> mostraBottomNav()
                R.id.listaFavPokemons -> mostraBottomNav()
                //R.id.search -> mostraBottomNav()
                R.id.detalhesPokemons -> mostraBottomNav()
                else -> ocultaBottomNavigation()
            }

        }

    }

    //oculta o botao de navegaçao
    private fun ocultaBottomNavigation() {
        main_activity_bottom_navigation.visibility = View.GONE
    }

    // exibe o botao de navegaçao
    private fun mostraBottomNav() {
        main_activity_bottom_navigation.visibility = View.VISIBLE
    }

    // abre o menu lateral (exibe ele)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    // recebo a foto
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK) {
            try {
                val file = File(currentPath)
                val uri = Uri.fromFile(file)
                imagem_usuario.setImageURI(uri)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK) {
            try {
                val uri = data!!.data
                imagem_usuario.setImageURI(uri)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    // abre a camera
    private fun dispatchCameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            var photoFile: File? = null
            try {
                photoFile = createImage()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            if (photoFile != null) {
                var photoUri = FileProvider.getUriForFile(this,
                    "com.example.pokedex.fileprovider", photoFile)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(intent, TAKE_PICTURE)
            }
        }
    }
        //cria a imagem
    private fun createImage(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageName = "JPEG_" + timeStamp + "_"
        var storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var image = File.createTempFile(imageName, ".jpg", storageDir)
        currentPath = image.absolutePath
        return image
    }

}