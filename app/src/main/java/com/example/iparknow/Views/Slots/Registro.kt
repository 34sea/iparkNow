package com.example.iparknow.Views.Slots

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iparknow.R
import com.example.iparknow.databinding.ActivityEstatisticasBinding
import com.example.iparknow.databinding.ActivityRegistroBinding

class Registro : AppCompatActivity() {

    private  lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbtn.setOnClickListener{
            val voltar = Intent(this, slots::class.java)
            startActivity(voltar)
        }

        binding.btnRegistros.setOnClickListener {
            val intent = Intent(this, Estatisticas::class.java)
            startActivity(intent)
        }

        binding.btnPreco.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }
}