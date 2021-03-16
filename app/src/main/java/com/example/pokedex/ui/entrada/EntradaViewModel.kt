package com.example.pokedex.ui.entrada

import androidx.lifecycle.ViewModel

class EntradaViewModel(private val repository: EntradaRepository): ViewModel() {
    fun loga(){
        repository.loga()
    }
    fun estaLogado(): Boolean =
        repository.estaLogado()


    fun desloga() {
        repository.desloga()
    }

    fun naoEstaLogado(): Boolean = !estaLogado()

}
