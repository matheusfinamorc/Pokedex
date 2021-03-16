package com.example.pokedex.ui.entrada

import android.content.SharedPreferences
import androidx.core.content.edit

class EntradaRepository(private val preferences: SharedPreferences){
    fun loga(){
        salva(true)
    }

    fun estaLogado(): Boolean =
        preferences.getBoolean("LOGADO", false)


    fun desloga() {
        salva(false)
    }

    private fun salva(estado: Boolean) {
        preferences.edit {
            putBoolean("LOGADO", estado)
        }
    }
}
