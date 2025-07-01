package com.example.motivation.ui.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.R
import com.example.motivation.data.repository.PhrasesRepository
import com.example.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding;
    private val phrasesRepository : PhrasesRepository = PhrasesRepository()

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
        handleNewSentence()
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_new_sentence){
            handleNewSentence()
        }
    }

    private fun handleNewSentence(){
        binding.textViewSentences.text = phrasesRepository.obterFrase()
    }


    private fun setListeners(){
        binding.buttonNewSentence.setOnClickListener(this)
    }


}