package com.example.pokedex.ui.listaFavPokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pokedex.R

class ListaFavPokemonsFragment: Fragment() {
    private val argumentos by navArgs<ListaFavPokemonsFragmentArgs>()
    private val pokemon by lazy { argumentos.pokemon }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_fav_pokemons,
        container,false)
    }

}

