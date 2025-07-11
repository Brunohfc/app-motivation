package com.example.motivation.ui.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.R
import com.example.motivation.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       configurarEventos()

    }

    private fun configurarEventos() {
        binding.buttonSave.setOnClickListener {
            enviarDados()
        }
    }

    private fun nomeEValido(nome: String): Boolean{
        if(nome.isEmpty()){
            return false
        }
        return true
    }

    private fun enviarDados() {
        val nome = binding.editText.text.toString()
        if(nomeEValido(nome).equals(false)){
            Toast.makeText(applicationContext,"Nome deve ser informado", Toast.LENGTH_SHORT ).show()
            return
        }
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("usuario", nome)
        startActivity(intent)
    }

}