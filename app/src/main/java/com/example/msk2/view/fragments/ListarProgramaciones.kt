package com.example.msk2.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.msk2.R
import com.example.msk2.databinding.FragmentListarProgramacionesBinding
import com.example.msk2.view.adapter.ProgramacionAdapter
import com.example.msk2.viewmodel.EstibaViewModel
import com.example.msk2.viewmodel.ProgramacionesViewModel

class ListarProgramaciones : Fragment() {

    private var _binding : FragmentListarProgramacionesBinding? = null

    private val binding get() = _binding!!

    private lateinit var estibaViewModel: EstibaViewModel

    private lateinit var programacionViewModel: ProgramacionesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListarProgramacionesBinding.inflate(
            inflater, container, false)

        binding.rvProgramaciones.layoutManager = LinearLayoutManager(requireActivity())

        estibaViewModel = ViewModelProvider(requireActivity()).get(EstibaViewModel::class.java)
        programacionViewModel = ViewModelProvider(requireActivity()).get(ProgramacionesViewModel::class.java)


        estibaViewModel.obtenerNroDocumento().observe(viewLifecycleOwner, Observer { nrodocumento ->
            if (!nrodocumento.isNullOrEmpty()){
                listarProgramaciones(nrodocumento)
            }else{
                Log.e("listar programacion","No se encontró el nrodedocumento") }
        })

        return binding.root

    }

    private fun listarProgramaciones(nrodocumento:String) {
        programacionViewModel.cargarProgramaciones(nrodocumento).observe(
            viewLifecycleOwner, Observer {
                response -> binding.rvProgramaciones.adapter = ProgramacionAdapter(response)
            }
        )

    }

}