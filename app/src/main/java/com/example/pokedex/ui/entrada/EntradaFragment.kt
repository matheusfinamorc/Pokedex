package com.example.pokedex.ui.entrada

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokedex.ComponentesVisuais
import com.example.pokedex.EstadoAppViewModel
import com.example.pokedex.R
import kotlinx.android.synthetic.main.entrada.*
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class EntradaFragment : Fragment() {
    private val controlador by lazy {
        findNavController()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.entrada,
            container,
            false
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        botao_entrar.setOnClickListener {
            val direcao = EntradaFragmentDirections.acaoEntradaParaListaPokemons()
            controlador.navigate(direcao)
        }
    }
}