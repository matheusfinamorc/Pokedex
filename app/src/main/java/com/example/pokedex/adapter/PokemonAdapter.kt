package com.example.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.pokedex.R
import com.example.pokedex.model.PokemonAbility
import com.example.pokedex.model.PokemonData
import com.example.pokedex.model.PokemonItem
import kotlinx.android.synthetic.main.detalhes_pokemon.view.*
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(
    private val context: Context,
    private val pokemons: MutableList<PokemonItem> = mutableListOf(),
    var onItemClickListener: (pokemon: PokemonItem) -> Unit = {}
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

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

    fun add(pokemons: List<PokemonItem>) {
        this.pokemons.clear()
        this.pokemons.addAll(pokemons)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var pokemon: PokemonItem
        private val campoNome by lazy { itemView.item_pokemon_nome }
        private val campoType by lazy { itemView.item_pokemon_tipo }
        private val campoImagem by lazy { itemView.pokemon_imagem_card }
       // private val campoAbilityNome by lazy { itemView.tv_detalhes_habilidades_pokemon }

        init {
            itemView.setOnClickListener {
                if(::pokemon.isInitialized){
                    onItemClickListener(pokemon)
                }
            }
        }


        fun vincula(pokemonItem: PokemonItem) {
            this.pokemon = pokemonItem
            campoNome.text = pokemonItem.nome
            campoType.text = pokemonItem.types.toString()
           // campoAbilityNome.text = pokemonItem.abilities.toString()


            //  Log.i("Response", pokemons[0].types.toString())
            Glide.with(itemView)
                .load("https://images.pokemontcg.io/"+pokemonItem.id+".png")
                .transform(CenterCrop())
                .into(campoImagem)


        }

    }
}