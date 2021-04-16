package com.example.pokedex.ui.listaPokemons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.Resource
import com.example.pokedex.model.PokemonItem
import com.example.pokedex.repository.PokedexRepository
import com.example.pokedex.repository.PokemonsResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonViewModel(
    private val repository: PokedexRepository
): ViewModel(){
    val mResponse: MutableLiveData<Response<PokemonsResponse>> = MutableLiveData()
    val fPesquisaResponse: MutableLiveData<Resource<PokemonsResponse>> = MutableLiveData()
    var fPesquisaResponseNew:PokemonsResponse? = null


    fun getPokemons(){
        viewModelScope.launch {
            val response = repository.getPokemons()
            mResponse.value = response
        }
    }
    fun save(pokemon: PokemonItem){
        viewModelScope.launch {
            repository.saveListaCompleta(pokemon)
        }
    }
//    fun getSearch(name: String) {
//        viewModelScope.launch {
//            val response = repository.getPokemonsPesquisa(name)
//            mResponse.value = response
//            Log.i("RESPONSE", "PokemonViewModel getSearch")
//        }
//    }
    fun getSearchPokemon(name: String) = viewModelScope.launch {
    searchPokemons(name)
}

    private suspend fun searchPokemons(searchQuery: String){
        val response = repository.getPokemonsPesquisa(searchQuery)
        fPesquisaResponse.postValue(searchPokemonResponse(response))
    }

    private fun searchPokemonResponse(response: Response<PokemonsResponse>): Resource<PokemonsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (fPesquisaResponseNew == null) {
                    fPesquisaResponseNew = resultResponse
                }
                return Resource.Success(fPesquisaResponseNew ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}