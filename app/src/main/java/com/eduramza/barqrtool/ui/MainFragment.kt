package com.eduramza.barqrtool.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.eduramza.barqrtool.R
import com.eduramza.barqrtool.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners(){
        binding.btBarcodeGenerator.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_barcodeFragment)
        }
        binding.btBarcodeScan.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_barcodeScanFragment)
        }

        binding.btQrcodeGenerator.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_QRCodeGeneratorFragment)
        }
        binding.btQrcodeScan.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_QRCodeScanFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}