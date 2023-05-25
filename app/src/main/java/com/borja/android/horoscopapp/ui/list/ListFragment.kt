package com.borja.android.horoscopapp.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.borja.android.horoscopapp.R
import com.borja.android.horoscopapp.databinding.FragmentListBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel by viewModels<ListViewModel>()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAries.setOnClickListener {
            //abrir detalle
        }

        binding.btnCapricornio.setOnClickListener {
            //abrir detalle
        }
        binding.btnLeo.setOnClickListener {
            //abrir detalle
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }
}