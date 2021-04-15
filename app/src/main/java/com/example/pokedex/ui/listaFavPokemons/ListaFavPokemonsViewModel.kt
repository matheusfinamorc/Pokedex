package com.example.pokedex.ui.listaFavPokemons

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.PokemonItem
import com.example.pokedex.repository.PokedexRepository
import kotlinx.coroutines.launch

class ListaFavPokemonsViewModel(
    private val repository: PokedexRepository

): ViewModel(){
    val todos: LiveData<List<PokemonItem>> = repository.todosInterno().asLiveData()

    fun delete(pokemon: PokemonItem){
        viewModelScope.launch {
            repository.deletePokFav(pokemon)
        }
    }
}

