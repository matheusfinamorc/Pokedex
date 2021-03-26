package com.example.pokedex.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "PokemonItem")
data class PokemonItem(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @SerializedName("name") val nome: String,
    val smallImageUrl: List<PokemonImage>,
    val types: List<String>,
    val abilities: List<PokemonAbility>

) :Serializable




