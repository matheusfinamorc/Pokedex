package com.example.pokedex.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokedex.model.PokemonItem
import com.example.pokedex.service.PokemonMigrations.Companion.migration_1_2

@Database(entities = arrayOf(PokemonItem::class), version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDAO(): PokemonDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDataBase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pokemon-db"
                ).addMigrations(migration_1_2)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
 }