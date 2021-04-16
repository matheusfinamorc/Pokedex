package com.example.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.model.PokemonItem
import kotlinx.android.synthetic.main.item_pokemon.view.*

class SearchAdapter(

    private val context: Context,
    private var pokemons: MutableList<PokemonItem> = mutableListOf(),
    var onItemClickListener: (pokemon: PokemonItem) -> Unit = {},

):RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val viewCriada = LayoutInflater.from(context).inflate(
            R.layout.search_item,
            parent,
            false
        )
        return ViewHolder(viewCriada)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(pokemons[position])
    }

    override fun getItemCount() = pokemons.size

    fun addSearch(pokemons: List<PokemonItem>) {
        this.pokemons.clear()
        this.pokemons.addAll(pokemons)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var pokemon: PokemonItem
        private val campoNome by lazy { itemView.item_pokemon_nome }
        private val campoType by lazy { itemView.item_pokemon_tipo }

        //  private val campoImagem by lazy { itemView.pokemon_imagem_card }

        init {
            itemView.setOnClickListener {
                if (::pokemon.isInitialized) {
                    onItemClickListener(pokemon)
                }
            }
        }

        fun vincula(pokemonItem: PokemonItem) {
            this.pokemon = pokemonItem
            campoNome.text = pokemonItem.nome
            campoType.text = pokemonItem.types.toString()

//            Glide.with(itemView)
//                 .load(pokemonItem.images.smallImageUrl)
//                 .transform(CenterCrop())
//                 .into(campoImagem)
        }
    }
}