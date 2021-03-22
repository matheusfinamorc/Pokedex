package com.example.pokedex.ui.detalhesPokemons

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.dao.PokemonDAO
import com.example.pokedex.model.PokemonData
import com.example.pokedex.model.PokemonItem
import com.example.pokedex.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow

class DetalhesPokemonsViewModelFactory(
    private val repository: PokedexRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetalhesPokemonsViewModel(repository) as T
    }

}