package com.example.pokedex.ui.detalhesPokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokedex.MyApplication
import com.example.pokedex.R
import kotlinx.android.synthetic.main.detalhes_pokemon.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetalhesPokemonsFragment: Fragment() {
    private val argumentos by navArgs<DetalhesPokemonsFragmentArgs>()

    private val pokemonId by lazy { argumentos.pokemonId }

    private val viewModel: DetalhesPokemonsViewModel by viewModels{
        DetalhesPokemonsViewModelFactory(
        (activity?.application
                    as MyApplication).repository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
         ): View? {
        return inflater.inflate(
            R.layout.detalhes_pokemon,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configDetalhes()
    }

    private fun configDetalhes() {
        viewModel.getDetalhes(pokemonId)
        viewModel.mResponse.observe(viewLifecycleOwner,{
            if(it.isSuccessful){
                tv_nome_pokemon_detalhes.text = it.body()?.nome
            }
        })
    }
}