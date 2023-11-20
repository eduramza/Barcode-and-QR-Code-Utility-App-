package com.eduramza.barqrtool.barcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eduramza.barqrtool.R
import com.eduramza.barqrtool.databinding.FragmentBarcodeGeneratorBinding
import com.eduramza.barqrtool.ui.displayBarCode

class BarcodeGeneratorFragment: Fragment(R.layout.fragment_barcode_generator) {
    private var _binding: FragmentBarcodeGeneratorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBarcodeGeneratorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners(){
        binding.btGenerate.setOnClickListener {
            displayBarCode(
                binding.imBarcode,
                requireActivity(),
                binding.etBarcodeOutput.text.toString()
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}