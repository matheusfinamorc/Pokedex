package com.example.pokedex.service

import com.example.pokedex.model.PokemonItem
import com.example.pokedex.repository.PokemonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("v2/cards")
    suspend fun getPokemon(): Response<PokemonsResponse>

    @GET("v2/cards/{id}/")
    suspend fun getDetalhes(@Path("id") id: String): Response<PokemonItem>

    @GET("v2/cards")
    suspend fun getPokemonsPesquisa(@Query("q") name: String): Response<PokemonsResponse>
}
