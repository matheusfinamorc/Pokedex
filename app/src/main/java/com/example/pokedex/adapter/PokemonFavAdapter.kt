package com.example.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.model.PokemonItem
import kotlinx.android.synthetic.main.item_fav_pokemon.view.*

class PokemonFavAdapter(
    private val context: Context,
    private val pokemons: MutableList<PokemonItem> = mutableListOf(),
    var onItemDelete: (pokemon: PokemonItem) -> Unit = {}

) : RecyclerView.Adapter<PokemonFavAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonFavAdapter.ViewHolder {
        val viewCriada = LayoutInflater.from(context).inflate(
            R.layout.item_fav_pokemon,
            parent,
            false
        )
        return ViewHolder(viewCriada)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(pokemons[position])
    }

    override fun getItemCount() = pokemons.size

    fun add(pokemons: List<PokemonItem>) {
        this.pokemons.clear()
        this.pokemons.addAll(pokemons)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var pokemon: PokemonItem
        private val campoNome by lazy { itemView.item_pokemon_nome_fav }
        private val campoType by lazy { itemView.item_pokemon_tipo_fav }

        private val botaoDeleteFav by lazy { itemView.botao_deletar_fav}

        init {
            botaoDeleteFav.setOnClickListener {
                if (::pokemon.isInitialized) {
                    onItemDelete(pokemon)
                }
            }
        }

        fun vincula(pokemonItem: PokemonItem) {
            this.pokemon = pokemonItem
            campoNome.text = pokemonItem.nome
            campoType.text = pokemonItem.types.toString()
        }
    }
}


