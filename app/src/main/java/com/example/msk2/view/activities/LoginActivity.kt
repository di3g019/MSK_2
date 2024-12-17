package com.example.msk2.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.msk2.R
import com.example.msk2.databinding.ActivityLoginBinding
import com.example.msk2.retrofit.response.ResponseLogin
import com.example.msk2.utilitarios.AppMensaje
import com.example.msk2.utilitarios.TipoMensaje
import com.example.msk2.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.btnlogin.setOnClickListener(this)
        binding.btnregistrar.setOnClickListener(this)

        authViewModel.responseLogin.observe(
            this, Observer {
                response -> obtenerDatosLogin(response)
            }
        )
    }

    private fun obtenerDatosLogin(responseLogin: ResponseLogin) {
        if (responseLogin.success){
            startActivity(Intent(applicationContext, HomeActivity::class.java))
        }else {
            AppMensaje.mensaje(binding.root, responseLogin.mensaje, TipoMensaje.ERROR)
        }
        binding.btnlogin.isEnabled = true
        binding.btnregistrar.isEnabled = true
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.btnlogin -> autenticarEstiba()
            R.id.btnregistrar -> startActivity(Intent(applicationContext, RegistroActivity::class.java))
        }
    }

    private fun autenticarEstiba() {
        binding.btnregistrar.isEnabled = false
        binding.btnlogin.isEnabled = false
        if (validarUsuarioPassword()){
            authViewModel.autenticarEstiba(
                binding.etusuario.text.toString(),
                binding.etpassword.text.toString()
            )
        }else{
            AppMensaje.mensaje(binding.root, "Ingrese N° de documento y/o contraseña", TipoMensaje.ERROR)
            binding.btnregistrar.isEnabled = true
            binding.btnlogin.isEnabled = true
        }
    }

    private fun validarUsuarioPassword():Boolean{
        var okValidacion = true
        if(binding.etusuario.text.toString().trim().isEmpty()){
            binding.etusuario.isFocusableInTouchMode = true
            binding.etusuario.requestFocus()
            okValidacion= false
        }else if(binding.etpassword.text.toString().trim().isEmpty()){
            binding.etpassword.isFocusableInTouchMode = true
            binding.etpassword.requestFocus()
            okValidacion = false
        }
        return okValidacion
    }
}