package com.example.pokedex.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.model.PokemonItem
import com.example.pokedex.repository.PokemonsResponse
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(
    private val context: Context,
    private var pokemons: MutableList<PokemonItem> = mutableListOf(),
    var onItemClickListener: (pokemon: PokemonItem) -> Unit = {},
    var onItemSave: (pokemon: PokemonItem) -> Unit = {}

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
//    fun addSearch(pokemons: List<PokemonItem>) {
//        this.pokemons.clear()
//        this.pokemons.addAll(pokemons)
//        notifyDataSetChanged()
//        Log.i("RESPONSE", "addSearch: "+pokemons)
//    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var pokemon: PokemonItem
        private val campoNome by lazy { itemView.item_pokemon_nome }
        private val campoType by lazy { itemView.item_pokemon_tipo }

        //  private val campoImagem by lazy { itemView.pokemon_imagem_card }
        private val botaoFav by lazy { itemView.botao_favoritar }

        init {
            itemView.setOnClickListener {
                if (::pokemon.isInitialized) {
                    onItemClickListener(pokemon)
                }
            }
        }

        init {
            botaoFav.setOnClickListener {
                if (::pokemon.isInitialized) {
                    onItemSave(pokemon)
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


//    override fun getFilter(): Filter {
//        return object: Filter(){
//            override fun performFiltering(charsequence: CharSequence?): FilterResults {
//
//                val filterResults = FilterResults()
//                if(charsequence == null || charsequence.length < 0){
//                    filterResults.count = pokemonsFilter.size
//                    filterResults.values = pokemonsFilter
//                }else{
//                    var searchCh = charsequence.toString().toLowerCase()
//
//                    val pokemon = ArrayList<PokemonItem>()
//
//                    for (item in pokemonsFilter){
//                        if(item.nome.contains(searchCh)){
//                            pokemon.add(item)
//                        }
//                    }
//                    filterResults.count = pokemon.size
//                    filterResults.values = pokemon
//                }
//
//                return filterResults
//
//            }
//
//            override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
//
//                pokemons = filterResults!!.values as MutableList<PokemonItem>
//                notifyDataSetChanged()
//
//            }
//
//        }
//    }
}
