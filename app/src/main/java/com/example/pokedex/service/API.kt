package com.example.pokedex.service

import com.example.pokedex.repository.PokemonsResponse
import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET("v2/cards")
    suspend fun getPokemon(): Response<PokemonsResponse>

}
