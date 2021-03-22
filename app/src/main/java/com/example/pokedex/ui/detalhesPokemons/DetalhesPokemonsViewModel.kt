package com.example.pokedex.ui.detalhesPokemons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.PokemonItem
import com.example.pokedex.repository.PokedexRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class DetalhesPokemonsViewModel(

    private val repository: PokedexRepository
): ViewModel() {
    val mResponse: MutableLiveData<Response<PokemonItem>> = MutableLiveData()

    fun getDetalhes(id: String){
        viewModelScope.launch {
            val response = repository.getPokemonsDetalhes(id)
            mResponse.value = response
        }
    }
}