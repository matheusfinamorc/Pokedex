package com.example.pokedex.model

import java.io.Serializable


data class PokemonData(
    val data: List<PokemonItem>
)

data class PokemonAbilities(
    var ability: List<PokemonAbility>
)
data class PokemonAbility(
    var name: String,
    var text: String,
    var type: String
): Serializable

// data class PokemonImage(
//    @SerializedName("small") val smallImageUrl: String
//)
