package com.example.pokedex.ui.detalhesPokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.pokedex.MyApplication
import com.example.pokedex.R
import com.example.pokedex.repository.PokedexRepository
import com.example.pokedex.ui.listaPokemons.ListaPokemonsViewModelFactory
import com.example.pokedex.ui.listaPokemons.PokemonViewModel
import kotlinx.android.synthetic.main.detalhes_pokemon.*

class DetalhesPokemonsFragment: Fragment() {

    private val detalhesViewModel: DetalhesPokemonsViewModel by viewModels{
        DetalhesPokemonsViewModelFactory((activity?.application as MyApplication).repository)
    }
    private val argumentos by navArgs<DetalhesPokemonsFragmentArgs>()
    private val pokemon by lazy { argumentos.pokemon }

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
       detalhesViewModel.getDetalhes(pokemon.id)
        detalhesViewModel.mResponse.observe(viewLifecycleOwner, {
            if (it.isSuccessful) {
                tv_detalhes_nome_pokemon.text = pokemon.nome
                tv_detalhes_habilidades_pokemon.text = pokemon.abilities[0].name
                tv_hp_detalhes_pokemon.text = pokemon.hp
                Glide.with(requireContext())
                    .load(pokemon.images.smallImageUrl)
                    .transform(CenterCrop())
                    .into(id_imagem_pokemon_detalhes)
            }
        })
    }
}