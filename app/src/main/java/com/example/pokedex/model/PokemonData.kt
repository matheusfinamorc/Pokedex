package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

 class PokemonData(
    val data: List<PokemonItem>
)

  class PokemonAbility(
    val name: String,
    val text: String
)

  class PokemonImage(
    @SerializedName("small") val smallImageUrl: String
)
