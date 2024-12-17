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
import com.example.msk2.databinding.ActivityRegistroBinding
import com.example.msk2.retrofit.response.ResponseRegistro
import com.example.msk2.utilitarios.AppMensaje
import com.example.msk2.utilitarios.TipoMensaje
import com.example.msk2.viewmodel.AuthViewModel

class RegistroActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.btnirlogin.setOnClickListener(this)
        binding.btnregistrarme.setOnClickListener(this)
        authViewModel.responseRegistro.observe(this,
            Observer {
                response -> obtenerResultadoRegistro(response)
            })
    }


    private fun obtenerResultadoRegistro(response: ResponseRegistro) {
        if (response.respuesta){
            setearControles()
        }
        AppMensaje.mensaje(binding.root, response.mensaje,TipoMensaje.INFO)
        estadoBotones(true)
    }

    private fun estadoBotones(estado: Boolean) {
        binding.btnirlogin.isEnabled = estado
        binding.btnregistrarme.isEnabled = estado
    }

    private fun setearControles() {
        binding.etnomusuario.setText("")
        binding.etapeusuario.setText("")
        binding.etedadusuario.setText("")
        binding.etcelusuario.setText("")
        binding.radiogroup.clearCheck()
        binding.etusureg.setText("")
        binding.etpassreg.setText("")
        binding.etrepassreg.setText("")

    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.btnirlogin ->startActivity(
                Intent(applicationContext,
                    LoginActivity::class.java))
            R.id.btnregistrarme -> registrarEstiba()
        }
    }

    private fun registrarEstiba() {
        estadoBotones(false)
        var documento = true
        if (validarDocumento()){
            if (binding.rdbtnDNI.isChecked){
                documento = true
            }else{
                documento = false;
            }
            if (validarContraseña()){
                authViewModel.registrarEstiba(
                    documento,
                    binding.etusureg.text.toString(),
                    binding.etrepassreg.text.toString(),
                    binding.etnomusuario.text.toString(),
                    binding.etapeusuario.text.toString(),
                    binding.etedadusuario.text.toString().toInt(),
                    binding.etcelusuario.text.toString()
                )
            }else{
                AppMensaje.mensaje(binding.root, "LAS CONTRASEÑAS NO COINCIDEN", TipoMensaje.ERROR)
                estadoBotones(true)
            }

        }else{
            AppMensaje.mensaje(binding.root,"SELECCIONA UN DOCUMENTO", TipoMensaje.ERROR)
            estadoBotones(true)
        }
    }

    private fun validarDocumento():Boolean{
        return when(binding.radiogroup.checkedRadioButtonId){
            R.id.rdbtnDNI, R.id.rdbtnCE -> true
            else -> false
        }
    }

    private fun validarContraseña():Boolean{
        var okValidacion = true
        if (binding.etpassreg.text.toString().trim().isEmpty() || binding.etrepassreg.text.toString().trim().isEmpty()){
            binding.etpassreg.isFocusableInTouchMode = true
            binding.etpassreg.requestFocus()
            okValidacion = false
        }else if (binding.etpassreg.text.toString() != binding.etrepassreg.text.toString()){
            okValidacion = false
        }
        return okValidacion
    }

}