package com.example.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.model.PokemonItem
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(
    private val context: Context,
    private val pokemons: MutableList<PokemonItem> = mutableListOf(),
): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.ViewHolder {
        val viewCriada = LayoutInflater.from(context).inflate(
            R.layout.item_pokemon,
            parent,
            false
        )
        return ViewHolder(viewCriada)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(pokemons[position])
    }

    override fun getItemCount() = pokemons.size

    fun add(pokemons: List<PokemonItem>){
        this.pokemons.clear()
        this.pokemons.addAll(pokemons)
        notifyDataSetChanged()
    }



    inner class ViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){

            private lateinit var pokemon: PokemonItem
            private val campoNome by lazy { itemView.item_pokemon_nome }


        fun vincula(pokemonItem: PokemonItem){
            this.pokemon = pokemonItem
            campoNome.text = pokemonItem.nome
        }
    }
}