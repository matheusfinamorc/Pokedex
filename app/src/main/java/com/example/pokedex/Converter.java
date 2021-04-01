package com.example.pokedex;

import androidx.room.TypeConverter;

import com.example.pokedex.model.PokemonAbility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converter {
        @TypeConverter
        public String fromAbilityPokemonList(List<PokemonAbility> abilityPokemon) {
            if (abilityPokemon == null) {
                return (null);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<List<PokemonAbility>>() {}.getType();
            String json = gson.toJson(abilityPokemon, type);
            return json;
        }

        @TypeConverter
        public List<PokemonAbility> toAbilityPokemonList(String abilityPokemonString) {
            if (abilityPokemonString == null) {
                return (null);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<List<PokemonAbility>>() {}.getType();
            List<PokemonAbility> pokemonAbilityList = gson.fromJson(abilityPokemonString, type);
            return pokemonAbilityList;
        }










    @TypeConverter
    public String fromTypePokemonList (List<String> typePokemon) {
        if (typePokemon == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        String json = gson.toJson(typePokemon, type);
        return json;
    }

    @TypeConverter
    public List<String> toTypePokemonList(String typePokemonString) {
        if (typePokemonString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> types = gson.fromJson(typePokemonString, type);
        return types;
    }

}
