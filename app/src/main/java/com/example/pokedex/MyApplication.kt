package com.example.pokedex

import android.app.Application

open class MyApplication : Application() {

   // val dataBase by lazy { AppDataBase.getDataBase(this)}
   // val repository by lazy { PokedexRepository(dataBase.pokemonDAO()) }
}