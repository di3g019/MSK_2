package com.example.msk2.view.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.msk2.R
import com.example.msk2.view.fragments.ListarProgramaciones
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val txtHoraMenu: TextView = findViewById(R.id.txtHoraMenu)

        // Función para actualizar la hora
        val updateTime = object : Runnable {
            override fun run() {
                // Obtener la hora actual
                val currentTime = Calendar.getInstance().time
                val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                val formattedTime = dateFormat.format(currentTime)

                // Mostrar la hora en el TextView
                txtHoraMenu.text = formattedTime

                // Repetir cada 60 segundos
                handler.postDelayed(this, 60000)
            }
        }

        // Ejecutar la actualización inicial
        handler.post(updateTime)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container, ListarProgramaciones())
                .commit()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        // Detener las actualizaciones del Handler
        handler.removeCallbacksAndMessages(null)
    }

}