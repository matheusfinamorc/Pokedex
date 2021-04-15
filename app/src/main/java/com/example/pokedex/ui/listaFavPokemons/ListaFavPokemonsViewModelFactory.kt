package com.example.pokedex.ui.listaFavPokemons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.repository.PokedexRepository

class ListaFavPokemonsViewModelFactory (
    private val repository: PokedexRepository
):
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListaFavPokemonsViewModel(repository) as T
    }
}


