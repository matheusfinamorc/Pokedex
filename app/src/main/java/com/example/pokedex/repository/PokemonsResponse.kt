package com.example.pokedex.repository

import com.example.pokedex.model.PokemonItem
import com.google.gson.annotations.SerializedName

data class PokemonsResponse(
    @SerializedName("data") val pokemons: MutableList<PokemonItem>)