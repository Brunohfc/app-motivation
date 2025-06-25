package com.example.motivation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding;
    private lateinit var intent: Intent;


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

    private fun enviarDados() {
        val nome = binding.editText.text.toString()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("usuario", nome)
        startActivity(intent)
    }

}