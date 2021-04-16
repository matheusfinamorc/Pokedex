package com.example.pokedex.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.MyApplication
import com.example.pokedex.R
import com.example.pokedex.Resource
import com.example.pokedex.adapter.SearchAdapter
import com.example.pokedex.model.PokemonItem
import com.example.pokedex.ui.listaPokemons.ListaPokemonsFragmentDirections
import com.example.pokedex.ui.listaPokemons.PokemonViewModel
import kotlinx.android.synthetic.main.search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment: Fragment() {

    private val viewModel: PokemonViewModel by viewModels {
        SearchViewModelFactory((activity?.application as MyApplication).repository)
    }

    private val controlador by lazy {
        findNavController()
    }

    private val adapter by lazy {
        context?.let {
            SearchAdapter(context = it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.search,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configAdapterRecyclerView()

        var job: Job? = null
        pesquisa_pokemon.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.getSearchPokemon(editable.toString())
                        //Log.i("RESPONSE", "onViewCreated: "+editable)
                    }
                }
            }
        }

        viewModel.fPesquisaResponse.observe(viewLifecycleOwner, { response ->
            when(response){
                is Resource.Success<*> -> {
                    hideProgressBar()
                    response.data?.let { pokemons ->
                        pokemons.pokemons.let { it1 -> adapter?.addSearch(it1) }
                    }
                }
                is Resource.Error<*> -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity,"Erro: $message", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun hideProgressBar() {
        barra_progresso.visibility = View.INVISIBLE
    }
    private fun configAdapterRecyclerView() {
        adapter?.onItemClickListener = {
            goToDetalhes(it)
        }
        rv_pesquisa.adapter = adapter
        rv_pesquisa.layoutManager = GridLayoutManager(context, 3)
    }

    private fun goToDetalhes(pokemon: PokemonItem) {
        val direcao = ListaPokemonsFragmentDirections
            .actionListaPokemonsToDetalhesPokemons(pokemon)
        controlador.navigate(direcao)
    }
}