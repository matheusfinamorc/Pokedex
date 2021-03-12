package com.example.pokedex.repository

import com.example.pokedex.dao.PokemonDAO
import com.example.pokedex.service.API
import com.example.pokedex.service.AppRetrofit
import retrofit2.Response

class PokedexRepository(
    private val dao: PokemonDAO,
    private val api: API = AppRetrofit().pokemonService
) {
    // pega os pokemons que estao saindo da getPokemon na API
      suspend fun getPokemons(): Response<PokemonsResponse>{
          return api.getPokemon()
      }
}

