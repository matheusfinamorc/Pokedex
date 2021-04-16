package com.example.pokedex.ui.listaFavPokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.MyApplication
import com.example.pokedex.R
import com.example.pokedex.adapter.PokemonFavAdapter
import com.example.pokedex.model.PokemonItem
import kotlinx.android.synthetic.main.lista_fav_pokemons.*

class ListaFavPokemonsFragment: Fragment() {
    private val listaFavViewModel: ListaFavPokemonsViewModel by viewModels{
        ListaFavPokemonsViewModelFactory((activity?.application as MyApplication).repository)
    }

    private val adapter by lazy {
        context?.let {
            PokemonFavAdapter(context = it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPokemonsInternos()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.lista_fav_pokemons,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        configuraLista()
    }

    // pega os pokemons internos
    private fun getPokemonsInternos() {
        listaFavViewModel.todos.observe(this, {listaInterno->
            listaInterno.let {
                adapter?.add(it)
            }
        })
    }
    private fun deletePokemon(pokemon: PokemonItem){
        listaFavViewModel.delete(pokemon)
    }

    // configura a lista de favoritos
    private fun configuraLista(){
        lista_fav_pokemons_recyclerView.adapter = adapter
        lista_fav_pokemons_recyclerView.layoutManager = LinearLayoutManager(context)

        // deleta o pokemon que for desejado da lista de favoritos
        adapter?.onItemDelete = {
            deletePokemon(it)
        }

    }

    private fun configuraRecyclerView() {
        val divisor = DividerItemDecoration(context, LinearLayout.VERTICAL)
        lista_fav_pokemons_recyclerView.addItemDecoration(divisor)
        lista_fav_pokemons_recyclerView.adapter = adapter
    }

}

