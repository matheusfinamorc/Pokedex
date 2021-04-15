package com.example.pokedex.ui.listaPokemons

import android.app.SearchManager
import android.content.Context.SEARCH_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout.VERTICAL
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.MyApplication
import com.example.pokedex.R
import com.example.pokedex.adapter.PokemonAdapter
import com.example.pokedex.model.PokemonItem
import kotlinx.android.synthetic.main.lista_pokemons.*

class ListaPokemonsFragment() : Fragment() {

    private val listaViewModel: PokemonViewModel by viewModels {
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
        setHasOptionsMenu(true)
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
                    // Log.i("Response", pokemons.pokemons[0].nome)
                }
            } else {
                Log.i("Response", resposta.errorBody().toString())

            }
        })
    }

    private fun savePokemon(pokemon: PokemonItem) {
        listaViewModel.save(pokemon)
    }


    // configura a lista e manda para o detalhe do item clicado
    private fun configuraLista() {
        adapter?.onItemClickListener = {
            goToDetalhes(it)
        }
        lista_pokemons_recyclerView.adapter = adapter
        lista_pokemons_recyclerView.layoutManager = LinearLayoutManager(context)
        // botao de favoritar junto com acao de salvar para fav
        adapter?.onItemSave = {
            savePokemon(it)
            Toast.makeText(activity, "Pokemon " + it.nome + " salvo", Toast.LENGTH_LONG).show()
        }
    }

    private fun configuraRecyclerView() {
        val divisor = DividerItemDecoration(context, VERTICAL)
        lista_pokemons_recyclerView.addItemDecoration(divisor)
        lista_pokemons_recyclerView.adapter = adapter
    }

    // acao de ir para os detalhes do pokemon clicado
    private fun goToDetalhes(pokemon: PokemonItem) {
        val direcao = ListaPokemonsFragmentDirections
            .actionListaPokemonsToDetalhesPokemons(pokemon)
        controlador.navigate(direcao)
    }


    // criação da barra de pesquisa na lista de pokemons
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.i("RESPONSE", "Entrou no onCreateOptionsMenu: ")

        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        val searchManager = activity?.getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = menu.findItem(R.id.action_search).actionView as SearchView
        val searchMenuItem = menu.findItem(R.id.action_search)

        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.queryHint = "Pesquisar"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
//                Toast.makeText(activity, "Carregando $filterString", Toast.LENGTH_LONG).show()
                if(query?.length!! >= 5) {
                    listaViewModel.getSearch(query)
                    listaViewModel.mResponse.observe(this@ListaPokemonsFragment, {
                        if (it.isSuccessful) {
                            it.body()?.let { resultado ->
                                adapter?.add(resultado.pokemons)
                                Log.i("RESPONSE", "onQueryTextSubmit:"+ resultado.pokemons)
                            }
                        }
                    })
                }
                Log.i("RESPONSE", "ListaPokemonsFragment onQueryTextSubmit:")
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText?.length!! >= 5) {
                    listaViewModel.getSearch(newText)
                    listaViewModel.mResponse.observe(this@ListaPokemonsFragment, {
                        if (it.isSuccessful) {
                            it.body()?.let { resultado ->
                                adapter?.add(resultado.pokemons)
                            }
                        }
                    })
                }
                Log.i("RESPONSE", "ListaPokemonsFragment onQueryTextChange: ")
                return true
            }

        })
        searchMenuItem.icon.setVisible(false, false)
    }


}


