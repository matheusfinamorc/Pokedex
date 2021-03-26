package com.example.pokedex.ui.detalhesPokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.pokedex.R
import com.example.pokedex.model.PokemonAbility
import com.example.pokedex.repository.PokedexRepository
import kotlinx.android.synthetic.main.detalhes_pokemon.*

class DetalhesPokemonsFragment: Fragment() {
    private val argumentos by navArgs<DetalhesPokemonsFragmentArgs>()
    private lateinit var detalhesViewModel: DetalhesPokemonsViewModel

    private val pokemon by lazy { argumentos.pokemon }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = PokedexRepository()
        val viewModelFactory = DetalhesPokemonsViewModelFactory(repository)

        detalhesViewModel = ViewModelProvider(
            this,
            viewModelFactory)
            .get(DetalhesPokemonsViewModel::class.java)
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
        Toast.makeText(activity, pokemon.nome, Toast.LENGTH_LONG).show()
       detalhesViewModel.getDetalhes(pokemon.id)
        detalhesViewModel.mResponse.observe(viewLifecycleOwner, {
            if(it.isSuccessful){
                tv_detalhes_nome_pokemon.text = pokemon.nome
                tv_detalhes_habilidades_pokemon.text = it.body()?.abilities.toString()
            }
        })
    }
}