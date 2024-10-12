package com.example.iparknow

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iparknow.Views.Slots.slots
import com.example.iparknow.databinding.ActivityLoginBinding
import com.example.iparknow.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private  lateinit var binding: ActivityLoginBinding

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener { view ->
            val email = binding.editUsuario.text.toString()
            val senha = binding.editSenha.text.toString()
            if (email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(view, "Campos vazios", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }else{
                auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener{ autenticacao ->
                    if(autenticacao.isSuccessful){
                        telaPrincipal()
                    }
                }.addOnFailureListener{exception ->
                    val mensagemErro = when(exception){
                        is FirebaseAuthWeakPasswordException -> "Senha invalida"
                        is FirebaseAuthInvalidCredentialsException -> "Email invalido"
                        is FirebaseNetworkException -> "Sem internet!!!"
                        else -> "Erro no login"
                    }
                    val snackbar = Snackbar.make(view,mensagemErro, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()
                }

            }
        }
    }

    private fun telaPrincipal(){
        val intent = Intent(this, slots::class.java)
        startActivity(intent)
    }

    override fun onStart(){
        super.onStart()
        val usuarioAtual = FirebaseAuth.getInstance().currentUser
        if(usuarioAtual!=null){
            telaPrincipal()
        }
    }
}