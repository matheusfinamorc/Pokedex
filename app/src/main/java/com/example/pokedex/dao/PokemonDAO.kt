package com.example.pokedex.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.pokedex.model.PokemonItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM PokemonItem")
    fun buscaCompleta(): Flow<List<PokemonItem>>

    @Query("SELECT * FROM PokemonItem WHERE id = :id")
    fun buscaPorId(id: String): LiveData<PokemonItem>

    @Insert(onConflict = REPLACE)
    suspend fun saveListaCompleta(pokemon: PokemonItem)

    @Delete
    suspend fun deleteFav(pokemon: PokemonItem)


}
