package com.example.pokedex.ui.detalhesPokemons

import androidx.lifecycle.ViewModel

class DetalhesPokemonsViewModel(
    pokemonId: String,
    repository: DetalhesPokemonsRepository
): ViewModel() {
    val pokemonEncontrado = repository.buscaPorId(pokemonId)
}