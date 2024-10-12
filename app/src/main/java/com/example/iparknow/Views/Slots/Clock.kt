package com.example.iparknow.Views.Slots

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import com.example.iparknow.R
import com.example.iparknow.databinding.ActivityCadastroBinding
import com.example.iparknow.databinding.ActivityClockBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Clock : AppCompatActivity() {

    private  lateinit var binding: ActivityClockBinding
    private val fs = FirebaseFirestore.getInstance()
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var timeTextView: TextView
    private val taxa = 200 // 200 meticais por hora
    private var fixedTime = "16:24:17" // Hora fixa de início
    private var con=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backbtn.setOnClickListener{
            val voltar = Intent(this, slots::class.java)
            startActivity(voltar)
        }
        val slo = intent.getStringExtra("slots")
        dadosSlo(slo.toString())
        timeTextView = binding.relogio
        startClockUpdate()
       // main()
        binding.btnLiberar.setOnClickListener {m ->
            val idCarro = fs.collection("Carros").document().id
            val usuariosMap = hashMapOf(
                "nome" to binding.nomPro.text.toString(),
                "placa" to binding.txtPlaca.text.toString(),
                "marca" to binding.marcaCarro.text.toString(),
                "data" to binding.dataCada.text.toString(),
                "horaDoRegistro" to fixedTime.toString(),
                "horaDaSaida" to timeTextView.text.toString(),
                "valorPago" to binding.converti.text.toString()
            )
            fs.collection("Carros").document(idCarro)
                .set(usuariosMap).addOnCompleteListener{
                    val sloMap = hashMapOf<String, Any>(
                        "placa" to binding.txtPlaca.text.toString(),
                        "marca" to binding.marcaCarro.text.toString(),
                        "nome" to binding.nomPro.text.toString(),
                        "data" to binding.dataCada.text.toString(),
                        "hora" to fixedTime.toString(),
                        "preench" to "n"
                    )
                    fs.collection("Slots").document(slo.toString())
                        .update(sloMap).addOnCompleteListener {
                            val snackbar = Snackbar.make(
                                m,
                                "Dados atualizados com sucesso",
                                Snackbar.LENGTH_SHORT
                            )
                            snackbar.setBackgroundTint(Color.BLUE)
                            snackbar.show()
                            telaPrincipal()

                        }
                }.addOnFailureListener{
                    val snackbar = Snackbar.make(m, "Falha", Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()
                }
        }

    }

    private fun dadosSlo(documentId: String) {
        //binding.dadosEs.text = documentId
        fs.collection("Slots").document(documentId).get()
            .addOnSuccessListener { documento ->
                binding.slotNum.text = documento.id
                binding.dataCada.text = documento.getString("data").toString()
                binding.horaCada.text = documento.getString("hora").toString()
                binding.txtPlaca.text = documento.getString("placa").toString()
                binding.nomPro.text = documento.getString("nome").toString()
                binding.marcaCarro.text = documento.getString("marca").toString()
                fixedTime = documento.getString("hora").toString()
            }

    }

    private fun startClockUpdate() {
        var dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        var currentTime = Date()
        var fixedTimeDate = dateFormat.parse(fixedTime)
        // Atualize o TextView com a hora atual
        timeTextView.text = dateFormat.format(currentTime)

        // Agende a atualização a cada 1 segundo
        handler.postDelayed(object : Runnable {
            override fun run() {
                var updatedTime = Date()
                timeTextView.text = dateFormat.format(updatedTime)

                val diferenca = calcularDiferencaHoras(fixedTime.toString(), timeTextView.text.toString())
                con = diferenca*taxa
                val df = DecimalFormat("#.###")
                val numeroFormatado = df.format(con)
                binding.converti.text = numeroFormatado .toString()+",00Mt"
                handler.postDelayed(this, 1000)
            }
        }, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Pare de atualizar o relógio quando a atividade for destruída
        handler.removeCallbacksAndMessages(null)
    }

    fun calcularDiferencaHoras(horaFixa: String, horaAtual: String): Double {
        // Divida as horas em partes (HH, mm, ss)
        val partesHoraFixa = horaFixa.split(":").map { it.toDouble() }
        val partesHoraAtual = horaAtual.split(":").map { it.toDouble() }

        // Calcule a soma das partes para a hora fixa e a hora atual
        val horaFixaTotal = partesHoraFixa[0] + partesHoraFixa[1] / 60.0 + partesHoraFixa[2] / 3600.0
        val horaAtualTotal = partesHoraAtual[0] + partesHoraAtual[1] / 60.0 + partesHoraAtual[2] / 3600.0

        // Calcule a diferença entre a hora atual e a hora fixa (em horas)
        val diferencaHoras = horaAtualTotal - horaFixaTotal

        // Retorne o valor absoluto da diferença
        return Math.abs(diferencaHoras)
    }

    fun main() {
        val horaFixa = "16:00:00"
        val horaAtual = "17:00:00"

        val diferenca = calcularDiferencaHoras(horaFixa, horaAtual)
        con = diferenca*200
    }

    private fun telaPrincipal(){
        val intent = Intent(this, slots::class.java)
        startActivity(intent)
    }
}