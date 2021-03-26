package com.example.pokedex.ui.listaPokemons

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.MyApplication
import com.example.pokedex.R
import com.example.pokedex.adapter.PokemonAdapter
import com.example.pokedex.model.PokemonItem
import com.example.pokedex.repository.PokedexRepository
import com.example.pokedex.ui.detalhesPokemons.DetalhesPokemonsViewModel
import com.example.pokedex.ui.detalhesPokemons.DetalhesPokemonsViewModelFactory
import kotlinx.android.synthetic.main.lista_pokemons.*

class ListaPokemonsFragment: Fragment() {

    private lateinit var listaViewModel: PokemonViewModel

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

        val repository = PokedexRepository()
        val viewModelFactory = ListaPokemonsViewModelFactory(repository)

        listaViewModel = ViewModelProvider(
            this,
            viewModelFactory)
            .get(PokemonViewModel::class.java)
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
        configuraLista()

    }

    // chama os pokemons para a lista
    private fun getPokemons() {
        listaViewModel.getPokemons()
        listaViewModel.mResponse.observe(this, { resposta ->
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

    // configura a lista e manda para o detalhe do item clicado
    private fun configuraLista(){
        adapter?.onItemClickListener = {
            goToDetalhes(it)
        }
        lista_pokemons_recyclerView.adapter = adapter
        lista_pokemons_recyclerView.layoutManager = LinearLayoutManager(context)
    }



    private fun configuraRecyclerView() {
        val divisor = DividerItemDecoration(context, VERTICAL)
        lista_pokemons_recyclerView.addItemDecoration(divisor)
        lista_pokemons_recyclerView.adapter = adapter
    }

    // acao de ir para os detalhes do pokemon clicado
    private fun goToDetalhes(pokemon: PokemonItem){
        val direcao = ListaPokemonsFragmentDirections
            .actionListaPokemonsToDetalhesPokemons(pokemon)
        controlador.navigate(direcao)
    }
}