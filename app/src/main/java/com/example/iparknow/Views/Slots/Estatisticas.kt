package com.example.iparknow.Views.Slots

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iparknow.R
import com.example.iparknow.databinding.ActivityEstatisticasBinding
import com.example.iparknow.databinding.ActivitySlotsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class Estatisticas : AppCompatActivity() {

    private lateinit var binding: ActivityEstatisticasBinding

    private val fs = FirebaseFirestore.getInstance()

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstatisticasBinding.inflate(layoutInflater)
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

        var documentList: List<DocumentSnapshot> = emptyList()
        loadFirstRecord()

        binding.btnNext.setOnClickListener {
            fs.collection("Carros").get()
                .addOnSuccessListener { documento ->
                    documentList = documento.documents

                    if (currentIndex >= 0 && currentIndex < documentList.size) {
                        val document = documentList[currentIndex]
                        val placa = document.getString("placa") ?: ""
                        val nome = document.getString("nome") ?: ""
                        val valores= document.getString("valorPago") ?: ""
                        binding.propri.text = nome
                        binding.plaque.text = placa
                        binding.valor.text = valores
                        currentIndex++

                    }

                    // Verifica se está no fim da lista
                    if (currentIndex == documentList.size) {
                        Snackbar.make(binding.root, "Fim da lista. Não é possível avançar.", Snackbar.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { exception ->
                    // Handle errors
                }
        }

        binding.btnPrev.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--

                if (currentIndex >= 0 && currentIndex < documentList.size) {
                    val document = documentList[currentIndex]
                    val placa = document.getString("placa") ?: ""
                    val nome = document.getString("nome") ?: ""
                    val valores= document.getString("valorPago") ?: ""
                    binding.propri.text = nome
                    binding.plaque.text = placa
                    binding.valor.text = valores
                }

                // Verifica se está no início da lista
                if (currentIndex == 0) {
                    Snackbar.make(binding.root, "Início da lista. Não é possível voltar.", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loadFirstRecord() {
        fs.collection("Carros").get()
            .addOnSuccessListener { documento ->
                val documentList = documento.documents

                if (documentList.isNotEmpty()) {
                    val firstDocument = documentList.first()

                    val placa = firstDocument.getString("placa") ?: ""
                    val nome = firstDocument.getString("nome") ?: ""
                    val valores= firstDocument.getString("valorPago") ?: ""
                    binding.propri.text = nome
                    binding.plaque.text = placa
                    binding.valor.text = valores

                    currentIndex = 0
                    total()
                }
            }
            .addOnFailureListener { exception ->
                // Handle errors
            }
    }

    private fun total(){
        fs.collection("Carros")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val numeroTotalRegistros = querySnapshot.size()
            }
            .addOnFailureListener { exception ->
            }
    }
}