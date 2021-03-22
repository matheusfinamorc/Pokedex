package com.example.pokedex.converter

import androidx.room.TypeConverter
import com.example.pokedex.model.PokemonAbility
import org.json.JSONObject

class Conversor {

    @TypeConverter
   fun fromAbility(abilities: PokemonAbility): String{
       return JSONObject().apply {
           put("name", abilities.name)
           put("text", abilities.text)
       }.toString()
   }
    @TypeConverter
    fun toAbility(abilities: String): PokemonAbility{
        val json = JSONObject(abilities)
        return PokemonAbility(json.getString("name"), json.getString("text"))
    }
}