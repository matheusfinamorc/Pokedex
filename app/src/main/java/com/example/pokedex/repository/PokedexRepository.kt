package com.example.pokedex.repository

import com.example.pokedex.dao.PokemonDAO
import com.example.pokedex.model.PokemonItem
import com.example.pokedex.service.API
import com.example.pokedex.service.AppRetrofit
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class PokedexRepository(
    private val dao: PokemonDAO,
    private val api: API = AppRetrofit().pokemonService
) {
    // pega os pokemons que estao saindo da getPokemon na API
      suspend fun getPokemons(): Response<PokemonsResponse>{
          return api.getPokemon()
      }

    // pega o pokemon pelo seu id da API
    suspend fun getPokemonsDetalhes(id: String): Response<PokemonItem>{
        return api.getDetalhes(id)
    }

    suspend fun saveListaCompleta(pokemon: PokemonItem){
        dao.saveListaCompleta(pokemon)
    }

    // pega todos os pokemons que estao salvos internamente
    fun todosInterno(): Flow<List<PokemonItem>> = dao.buscaCompleta()

    // deleta um pokemon da lista de favoritos
    suspend fun deletePokFav(pokemon: PokemonItem){
        dao.deleteFav(pokemon)
    }

    suspend fun getPokemonsPesquisa(name: String): Response<PokemonsResponse>{
        return api.getPokemonsPesquisa(name)
    }



}

