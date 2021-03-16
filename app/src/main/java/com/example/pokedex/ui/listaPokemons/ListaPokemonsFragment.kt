package com.example.pokedex.ui.listaPokemons

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.pokedex.MyApplication
import com.example.pokedex.R
import com.example.pokedex.adapter.PokemonAdapter
import kotlinx.android.synthetic.main.lista_pokemons.*

class ListaPokemonsFragment: Fragment() {

    private val viewModel: PokemonViewModel by viewModels{
        ListaPokemonsViewModelFactory((activity?.application as MyApplication).repository)
    }

    private val controlador by lazy {
        findNavController()
    }
    private val adapter by lazy {
        context?.let {
            PokemonAdapter(context = it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPokemons()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.lista_pokemons,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
    }

    //
    private fun getPokemons() {
        viewModel.getPokemons()
        viewModel.mResponse.observe(this, { resposta ->
            if (resposta.isSuccessful) {
                resposta.body()?.let { pokemons ->
                    adapter?.add(pokemons.pokemons)
                    //Log.i("Response", pokemons.pokemons[0].nome)
                }
            } else {
                Log.i("Response", resposta.errorBody().toString())

            }
        })
    }


    private fun configuraRecyclerView() {
        val divisor = DividerItemDecoration(context, VERTICAL)
        lista_pokemons_recyclerView.addItemDecoration(divisor)
        lista_pokemons_recyclerView.adapter = adapter
    }
}