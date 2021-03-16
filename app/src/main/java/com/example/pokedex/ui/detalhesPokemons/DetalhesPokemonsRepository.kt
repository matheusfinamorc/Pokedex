package com.example.pokedex.ui.detalhesPokemons

import androidx.lifecycle.LiveData
import com.example.pokedex.dao.PokemonDAO
import com.example.pokedex.model.PokemonItem
import kotlinx.coroutines.flow.Flow

class DetalhesPokemonsRepository(private val dao: PokemonDAO) {
    fun buscaPorId(id: String): LiveData<PokemonItem> = dao.buscaPorId(id)
}