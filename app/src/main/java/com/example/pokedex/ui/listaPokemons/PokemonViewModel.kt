package com.example.pokedex.ui.listaPokemons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.repository.PokedexRepository
import com.example.pokedex.repository.PokemonsResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonViewModel(
    private val repository: PokedexRepository
): ViewModel(){
    val mResponse: MutableLiveData<Response<PokemonsResponse>> = MutableLiveData()

    fun getPokemons(){
        viewModelScope.launch {
            val response = repository.getPokemons()
            mResponse.value = response
        }
    }

}