package com.example.pokedex.ui.listaPokemons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.repository.PokedexRepository

class ListaPokemonsViewModelFactory(
    private val repository: PokedexRepository
    ):
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
     return PokemonViewModel(repository) as T
    }
}