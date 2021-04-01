package com.example.pokedex

import androidx.room.TypeConverter
import com.example.pokedex.model.PokemonAbility
import com.example.pokedex.model.PokemonImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromAbilityPokemonList(value: List<PokemonAbility>): String {
        val gson = Gson()
        val type = object : TypeToken<List<PokemonAbility>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toAbilityPokemonList(value: String): List<PokemonAbility> {
        val gson = Gson()
        val type = object : TypeToken<List<PokemonAbility>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromTypePokemonList(value: List<String>): String {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toTypePokemonList(value: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromImagePokemon(value: PokemonImage): String{
        val gson = Gson()
        val type = object : TypeToken<PokemonImage>() {}.type
        return gson.toJson(value,type)
    }
    fun toImagePokemon(value: String): PokemonImage{
        val gson = Gson()
        val type = object : TypeToken<PokemonImage>() {}.type
        return gson.fromJson(value,type)
    }
}