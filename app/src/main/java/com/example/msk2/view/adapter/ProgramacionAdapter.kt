package com.example.msk2.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.msk2.databinding.ItemProgramacionBinding
import com.example.msk2.retrofit.response.ResponseProgramacion
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ProgramacionAdapter(private var programaciones: List<ResponseProgramacion>) : RecyclerView.Adapter<ProgramacionAdapter.ProgramacionViewHolder>() {

    inner class ProgramacionViewHolder(val binding: ItemProgramacionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramacionAdapter.ProgramacionViewHolder {
        val binding = ItemProgramacionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ProgramacionViewHolder(binding)
    }

    override fun getItemCount()= programaciones.size

    override fun onBindViewHolder(holder: ProgramacionAdapter.ProgramacionViewHolder, position: Int) {
        with(holder){
            with(programaciones[position]){
                val calendar = Calendar.getInstance()
                calendar.time = fecha

                val dayOfWeek = SimpleDateFormat("EEE", Locale("es","ES")).format(calendar.time).uppercase()
                val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH).toString()


                binding.txtCuenta.text = cuenta
                binding.txtLugar.text = lugar
                binding.txtHorinicio.text = hora
                binding.txtHoraFin.text = "00:00"
                binding.nroDia.text = dayOfMonth
                binding.diaSemana.text = dayOfWeek
            }
        }
    }

}