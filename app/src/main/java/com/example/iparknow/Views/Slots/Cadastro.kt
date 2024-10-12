package com.example.iparknow.Views.Slots

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iparknow.Login
import com.example.iparknow.R
import com.example.iparknow.databinding.ActivityCadastroBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Cadastro : AppCompatActivity() {
    private  lateinit var binding: ActivityCadastroBinding
    private val fs = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backbtn.setOnClickListener{
            val voltar = Intent(this, slots::class.java)
            startActivity(voltar)
        }
        val dataAtual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val horaAtual = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        binding.lData.text = dataAtual
        binding.lHora.text = horaAtual
        val slo = intent.getStringExtra("slots")
        binding.Slot.text = slo

        binding.btnSalvar.setOnClickListener {view ->

            val placa = binding.editTextPlaca.text.toString()
            val marca = binding.editTextMarca.text.toString()
            val nomePro = binding.editTextNomePr.text.toString()
            if(placa.isEmpty() || marca.isEmpty() || nomePro.isEmpty()){
                val snackbar = Snackbar.make(view, "Campos vazios", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }else{
                val sloMap = hashMapOf<String, Any>(
                    "placa" to placa,
                    "marca" to marca,
                    "nome" to nomePro,
                    "data" to dataAtual.toString(),
                    "hora" to horaAtual.toString(),
                    "preench" to "s"
                )
                fs.collection("Slots").document(slo.toString())
                    .update(sloMap).addOnCompleteListener {
                        val snackbar = Snackbar.make(
                            view,
                            "Dados atualizados com sucesso",
                            Snackbar.LENGTH_SHORT
                        )
                        snackbar.setBackgroundTint(Color.BLUE)
                        snackbar.show()
                        telaPrincipal()

                    }
            }

        }

        dadosSlo(slo.toString())

    }
    private fun dadosSlo(documentId: String) {
        //binding.dadosEs.text = documentId
        fs.collection("Slots").document(documentId).get()
            .addOnSuccessListener { documento ->
                binding.Slot.text = documento.id
            }

    }

    private fun telaPrincipal(){
        val intent = Intent(this, slots::class.java)
        startActivity(intent)
    }
}