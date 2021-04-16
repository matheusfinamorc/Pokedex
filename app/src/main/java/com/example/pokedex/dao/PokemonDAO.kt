package com.example.pokedex.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.pokedex.model.PokemonItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDAO {
    @Insert(onConflict = REPLACE)
    suspend fun saveInterno(pokemon: PokemonItem)

    @Query("SELECT * FROM PokemonItem")
    fun buscaCompleta(): Flow<List<PokemonItem>>

    @Delete
    suspend fun deleteFav(pokemon: PokemonItem)
}
