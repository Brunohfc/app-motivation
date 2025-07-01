package com.example.motivation.ui.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.R
import com.example.motivation.data.repository.PhrasesRepository
import com.example.motivation.databinding.ActivityMainBinding
import com.example.motivation.helpers.Constantes

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding;
    private val phrasesRepository: PhrasesRepository = PhrasesRepository()

    private var filter: Int = Constantes.PHRASE.PHRASE_ALL

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var nomeUsuario = binding.texViewName
        nomeUsuario.text = "Olá ${intent.extras?.getString("usuario") ?: "Usuario nao encontrado"}"
        setListeners()
        //deixando marcado no inicio porque nao tem filtro selecionado ainda, ou seja, todos(ALL)
        filterPhrases(R.id.imageView_inclusive)
        handleNewSentence()
    }

    override fun onClick(view: View) {

        //criando lista com os IDs do xml para validar click
        val listId = listOf(R.id.imageView_light, R.id.imageView_insert, R.id.imageView_inclusive)

        if (view.id == R.id.button_new_sentence) {
            handleNewSentence()
        }else if(view.id in listId){
            filterPhrases(view.id)
        }
    }

    private fun filterPhrases(id: Int) {

        //colocando todos icones preto e depois a logica e executada para marcar novamente
        binding.imageViewLight.setColorFilter(ContextCompat.getColor(this,R.color.black))
        binding.imageViewInsert.setColorFilter(ContextCompat.getColor(this,R.color.black))
        binding.imageViewInclusive.setColorFilter(ContextCompat.getColor(this,R.color.black))

        when(id){
            R.id.imageView_light -> {
                filter = Constantes.PHRASE.PHRASE_SUNNY
                binding.imageViewLight.setColorFilter(ContextCompat.getColor(this,R.color.white))
            }
            R.id.imageView_inclusive ->{
                filter = Constantes.PHRASE.PHRASE_ALL
                binding.imageViewInclusive.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
            R.id.imageView_insert ->{
                filter = Constantes.PHRASE.PHRASE_HAPPY
                binding.imageViewInsert.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
        }
    }

    private fun handleNewSentence() {
        binding.textViewSentences.text = phrasesRepository.obterFrase(filter)
    }


    private fun setListeners() {
        binding.buttonNewSentence.setOnClickListener(this)
        binding.imageViewLight.setOnClickListener(this)
        binding.imageViewInsert.setOnClickListener(this)
        binding.imageViewInclusive.setOnClickListener(this)
    }


}