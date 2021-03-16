package com.example.pokedex.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.pokedex.model.PokemonItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM PokemonItem")
    fun buscaCompleta(): Flow<List<PokemonItem>>

    @Query("SELECT * FROM PokemonItem WHERE id = :id")
    fun buscaPorId(id: String): LiveData<PokemonItem>


}
