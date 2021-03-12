package com.example.pokedex.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokedex.model.PokemonItem



@Database(entities = [PokemonItem::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase(){

    abstract fun pokemonDAO(): PokemonDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "pokemon-db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}