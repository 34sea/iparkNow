package com.example.iparknow.Views.Slots

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.iparknow.Login
import com.example.iparknow.R
import com.example.iparknow.databinding.ActivityLoginBinding
import com.example.iparknow.databinding.ActivitySlotsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class slots : AppCompatActivity() {

    private  lateinit var binding: ActivitySlotsBinding

    private val fs = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySlotsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val voltar = Intent(this, Login::class.java)
            startActivity(voltar)
            finish()
        }
        verPre("slot01")
        verPre("slot02")
        verPre("slot03")
        verPre("slot04")
        verPre("slot05")
        verPre("slot06")
        verPre("slot07")
        verPre("slot08")
        verPre("slot09")
        verPre("slot10")
        binding.slot1.setOnClickListener{
            if(binding.textoSlot1.text == "Vazio") {
                val intent = Intent(this, Cadastro::class.java)
                intent.putExtra("slots", "slot01")
                startActivity(intent)
            }else{
                val intent = Intent(this, Clock::class.java)
                intent.putExtra("slots", "slot01")
                startActivity(intent)
            }
        }

        binding.slot2.setOnClickListener{
            if(binding.textoSlot2.text == "Vazio") {
                val intent = Intent(this, Cadastro::class.java)
                intent.putExtra("slots", "slot02")
                startActivity(intent)
            }else{
                val intent = Intent(this, Clock::class.java)
                intent.putExtra("slots", "slot02")
                startActivity(intent)
            }
        }

        binding.slot3.setOnClickListener{
            if(binding.textoSlot3.text == "Vazio") {
                val intent = Intent(this, Cadastro::class.java)
                intent.putExtra("slots", "slot03")
                startActivity(intent)
            }else{
                val intent = Intent(this, Clock::class.java)
                intent.putExtra("slots", "slot03")
                startActivity(intent)
            }
        }

        binding.slot4.setOnClickListener{
            if(binding.textoSlot4.text == "Vazio") {
                val intent = Intent(this, Cadastro::class.java)
                intent.putExtra("slots", "slot04")
                startActivity(intent)
            }else{
                val intent = Intent(this, Clock::class.java)
                intent.putExtra("slots", "slot04")
                startActivity(intent)
            }
        }

        binding.slot5.setOnClickListener{
            if(binding.textoSlot5.text == "Vazio") {
                val intent = Intent(this, Cadastro::class.java)
                intent.putExtra("slots", "slot05")
                startActivity(intent)
            }else{
                val intent = Intent(this, Clock::class.java)
                intent.putExtra("slots", "slot05")
                startActivity(intent)
            }
        }

        binding.slot6.setOnClickListener{
            if(binding.textoSlot6.text == "Vazio") {
                val intent = Intent(this, Cadastro::class.java)
                intent.putExtra("slots", "slot06")
                startActivity(intent)
            }else{
                val intent = Intent(this, Clock::class.java)
                intent.putExtra("slots", "slot06")
                startActivity(intent)
            }
        }

        binding.slot7.setOnClickListener{
            if(binding.textoSlot7.text == "Vazio") {
                val intent = Intent(this, Cadastro::class.java)
                intent.putExtra("slots", "slot07")
                startActivity(intent)
            }else{
                val intent = Intent(this, Clock::class.java)
                intent.putExtra("slots", "slot07")
                startActivity(intent)
            }
        }

        binding.slot8.setOnClickListener{
            if(binding.textoSlot8.text == "Vazio") {
                val intent = Intent(this, Cadastro::class.java)
                intent.putExtra("slots", "slot08")
                startActivity(intent)
            }else{
                val intent = Intent(this, Clock::class.java)
                intent.putExtra("slots", "slot08")
                startActivity(intent)
            }
        }

        binding.slot9.setOnClickListener{
            if(binding.textoSlot9.text == "Vazio") {
                val intent = Intent(this, Cadastro::class.java)
                intent.putExtra("slots", "slot09")
                startActivity(intent)
            }else{
                val intent = Intent(this, Clock::class.java)
                startActivity(intent)
            }
        }

        binding.slot10.setOnClickListener{
            if(binding.textoSlot10.text == "Vazio") {
                val intent = Intent(this, Cadastro::class.java)
                intent.putExtra("slots", "slot10")
                startActivity(intent)
            }else{
                val intent = Intent(this, Clock::class.java)
                intent.putExtra("slots", "slot10")
                startActivity(intent)
            }
        }


        binding.estatistica.setOnClickListener{
            val intent = Intent(this, Estatisticas::class.java)
            startActivity(intent)
        }

    }
    private fun dadosImagens(documentId: String) {
        //binding.dadosEs.text = documentId
        fs.collection("ImagensCar").document(documentId).get()
            .addOnSuccessListener { documento ->
                binding.dadosEs.text = documento.getString("imagem")
            }

    }

    private fun verPre(documentId: String) {
        var caminhoV="baseline_apps_outage_24"
        var caminhoPr="baseline_directions_car_24"
        //binding.dadosEs.text = documentId
        fs.collection("Slots").document(documentId).get()
            .addOnSuccessListener { documento ->
                //binding.dadosEs.text = documento.getString("imagem")
                if(documento.getString("preench").toString() == "s"){
                    if(documentId == "slot01"){
                        binding.slot1Image.setImageResource(R.drawable.baseline_directions_car_24)
                        binding.textoSlot1.text  = "Ocupado"
                        binding.tempoSlo1.text = documento.getString("hora").toString()
                    }else if(documentId == "slot02"){
                        binding.slot2Image.setImageResource(R.drawable.baseline_directions_car_24)
                        binding.textoSlot2.text  = "Ocupado"
                        binding.tempoSlo2.text = documento.getString("hora").toString()
                    }else  if(documentId == "slot03"){
                        binding.slot3Image.setImageResource(R.drawable.baseline_directions_car_24)
                        binding.textoSlot3.text  = "Ocupado"
                        binding.tempoSlo3.text = documento.getString("hora").toString()
                    }else if(documentId == "slot04"){
                        binding.slot4Image.setImageResource(R.drawable.baseline_directions_car_24)
                        binding.textoSlot4.text  = "Ocupado"
                        binding.tempoSlo4.text = documento.getString("hora").toString()
                    }else if(documentId == "slot05"){
                        binding.slot5Image.setImageResource(R.drawable.baseline_directions_car_24)
                        binding.textoSlot5.text  = "Ocupado"
                        binding.tempoSlo5.text = documento.getString("hora").toString()
                    }else  if(documentId == "slot06"){
                        binding.slot6Image.setImageResource(R.drawable.baseline_directions_car_24)
                        binding.textoSlot6.text  = "Ocupado"
                        binding.tempoSlo6.text = documento.getString("hora").toString()
                    }else if(documentId == "slot07"){
                        binding.slot7Image.setImageResource(R.drawable.baseline_directions_car_24)
                        binding.textoSlot7.text  = "Ocupado"
                        binding.tempoSlo7.text = documento.getString("hora").toString()
                    }else if(documentId == "slot08"){
                        binding.slot8Image.setImageResource(R.drawable.baseline_directions_car_24)
                        binding.textoSlot8.text  = "Ocupado"
                        binding.tempoSlo8.text = documento.getString("hora").toString()
                    }else  if(documentId == "slot09"){
                        binding.slot9Image.setImageResource(R.drawable.baseline_directions_car_24)
                        binding.textoSlot9.text  = "Ocupado"
                        binding.tempoSlo9.text = documento.getString("hora").toString()
                    }else if(documentId == "slot10"){
                        binding.slot10Image.setImageResource(R.drawable.baseline_directions_car_24)
                        binding.textoSlot10.text  = "Ocupado"
                        binding.tempoSlo10.text = documento.getString("hora").toString()
                    }
                }else{
                    if(documentId == "slot01"){
                        binding.slot1Image.setImageResource(R.drawable.baseline_apps_outage_24)
                        binding.textoSlot1.text  = "Vazio"
                        binding.tempoSlo1.text = "0s"
                    }else if(documentId == "slot02"){
                        binding.slot2Image.setImageResource(R.drawable.baseline_apps_outage_24)
                        binding.textoSlot2.text  = "Vazio"
                        binding.tempoSlo2.text = "0s"
                    }else if(documentId == "slot03"){
                        binding.slot3Image.setImageResource(R.drawable.baseline_apps_outage_24)
                        binding.textoSlot3.text  = "Vazio"
                        binding.tempoSlo3.text = "0s"
                    }else if(documentId == "slot04"){
                        binding.slot4Image.setImageResource(R.drawable.baseline_apps_outage_24)
                        binding.textoSlot4.text  = "Vazio"
                        binding.tempoSlo4.text = "0s"
                    }else if(documentId == "slot05"){
                        binding.slot5Image.setImageResource(R.drawable.baseline_apps_outage_24)
                        binding.textoSlot5.text  = "Vazio"
                        binding.tempoSlo5.text = "0s"
                    }else if(documentId == "slot06"){
                        binding.slot6Image.setImageResource(R.drawable.baseline_apps_outage_24)
                        binding.textoSlot6.text  = "Vazio"
                        binding.tempoSlo6.text = "0s"
                    }else if(documentId == "slot07"){
                        binding.slot7Image.setImageResource(R.drawable.baseline_apps_outage_24)
                        binding.textoSlot7.text  = "Vazio"
                        binding.tempoSlo7.text = "0s"
                    }else if(documentId == "slot08"){
                        binding.slot8Image.setImageResource(R.drawable.baseline_apps_outage_24)
                        binding.textoSlot8.text  = "Vazio"
                        binding.tempoSlo8.text = "0s"
                    }else if(documentId == "slot09"){
                        binding.slot9Image.setImageResource(R.drawable.baseline_apps_outage_24)
                        binding.textoSlot9.text  = "Vazio"
                        binding.tempoSlo9.text = "0s"
                    }else if(documentId == "slot10"){
                        binding.slot10Image.setImageResource(R.drawable.baseline_apps_outage_24)
                        binding.textoSlot10.text  = "Vazio"
                        binding.tempoSlo10.text = "0s"
                    }
                }
            }

    }

}