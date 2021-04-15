package com.example.pokedex

import android.app.Application
import com.example.pokedex.dao.AppDatabase
import com.example.pokedex.repository.PokedexRepository

open class MyApplication : Application() {

    val dataBase by lazy { AppDatabase.getDataBase(this)}
    val repository by lazy { PokedexRepository(dataBase.pokemonDAO()) }
}