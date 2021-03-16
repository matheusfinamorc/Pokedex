package com.example.pokedex.ui.detalhesPokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokedex.R
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetalhesPokemonsFragment: Fragment() {
   // private val argumentos by navArgs<DetalhesPokemonsFragmentArgs>()

   // private val pokemonId by lazy { argumentos.pokemonId }

  //  private val viewModel: DetalhesPokemonsViewModel by viewModel { parametersOf(pokemonId) }

    //private val controlador by lazy { findNavController() }

   // override fun onCreateView(
    //    inflater: LayoutInflater,
    //    container: ViewGroup?,
    //    savedInstanceState: Bundle?
   // ): View? {
   //     return inflater.inflate(
   //         R.layout.detalhes_pokemon,
   //         container,
   //         false
   //     )
   // }

   // override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
   //     super.onViewCreated(view, savedInstanceState)
       // buscaPokemons()
   // }
   // private fun buscaPokemons(){
       // viewModel.pokemonEncontrado.observe(this, Observer {
            // id_imagem_pokemon_detalhes
            // tv_nome_pokemon_detalhes
            // tv_hp_pokemon_detalhes
            // tv_tipo_pokemon_detalhes
            // tv_evolucoes_pokemon_detalhes
       // })
  //  }
}