package com.example.pokedex.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pokedex.Converters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "PokemonItem")
@TypeConverters(Converters::class)
data class PokemonItem(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @SerializedName("name") val nome: String,
    val images: PokemonImage,
    val types: List<String>,
    val abilities: List<PokemonAbility>,
    val hp: String

) :Serializable




