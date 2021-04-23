package com.example.pokedex.ui.listaPokemons

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.MyApplication
import com.example.pokedex.R
import com.example.pokedex.adapter.PokemonAdapter
import com.example.pokedex.model.PokemonItem
import com.example.pokedex.ui.main.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.android.synthetic.main.lista_pokemons.*
import kotlinx.android.synthetic.main.main_activity.*
import java.io.File
import java.io.FileInputStream

class ListaPokemonsFragment() : Fragment() {

    private val listaViewModel: PokemonViewModel by viewModels {
        ListaPokemonsViewModelFactory((activity?.application as MyApplication).repository)
    }

    private val controlador by lazy {
        findNavController()
    }
    private val adapter by lazy {
        context?.let {
            PokemonAdapter(context = it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        getPokemons()

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.lista_pokemons,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        configuraLista()

    }


    // chama os pokemons para a lista
    fun getPokemons() {
        listaViewModel.getPokemons()
        listaViewModel.mResponse.observe(this, { resposta ->
            if (resposta.isSuccessful) {
                resposta.body()?.let { pokemons ->
                    adapter?.add(pokemons.pokemons)
                    // Log.i("Response", pokemons.pokemons[0].nome)
                }
            } else {
                Log.i("Response", resposta.errorBody().toString())

            }
        })
    }

    private fun savePokemon(pokemon: PokemonItem) {
        listaViewModel.save(pokemon)
    }


    // configura a lista e manda para o detalhe do item clicado
    private fun configuraLista() {
        adapter?.onItemClickListener = {
            goToDetalhes(it)
        }
        lista_pokemons_recyclerView.adapter = adapter
        lista_pokemons_recyclerView.layoutManager = LinearLayoutManager(context)
        // botao de favoritar junto com acao de salvar para fav
        adapter?.onItemSave = {
            savePokemon(it)
            Toast.makeText(activity, "Pokemon " + it.nome + " salvo", Toast.LENGTH_LONG).show()
        }
    }

    private fun configuraRecyclerView() {
        val divisor = DividerItemDecoration(context, VERTICAL)
        lista_pokemons_recyclerView.addItemDecoration(divisor)
        lista_pokemons_recyclerView.adapter = adapter
    }

    // acao de ir para os detalhes do pokemon clicado
    private fun goToDetalhes(pokemon: PokemonItem) {
        val direcao = ListaPokemonsFragmentDirections
            .actionListaPokemonsToDetalhesPokemons(pokemon)
        controlador.navigate(direcao)
    }


}