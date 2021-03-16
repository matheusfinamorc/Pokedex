package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

data class TiposPokemons(
    @SerializedName("types") val tipoPokemon: String
)