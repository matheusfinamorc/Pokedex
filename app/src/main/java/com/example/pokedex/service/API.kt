package com.example.pokedex.service

import com.example.pokedex.model.PokemonItem
import com.example.pokedex.repository.PokemonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("v2/cards")
    suspend fun getPokemon(): Response<PokemonsResponse>

    @GET("v2/sets?q=legalities.standard:legal/{id}/")
    suspend fun getDetalhes(@Path("id") id: String): Response<PokemonItem>

}
