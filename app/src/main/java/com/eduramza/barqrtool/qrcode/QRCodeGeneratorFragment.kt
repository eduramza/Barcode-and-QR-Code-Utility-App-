package com.eduramza.barqrtool.qrcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eduramza.barqrtool.R
import com.eduramza.barqrtool.databinding.FragmentQrCodeGeneratorBinding

class QRCodeGeneratorFragment: Fragment(R.layout.fragment_qr_code_generator) {
    private var _binding: FragmentQrCodeGeneratorBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQrCodeGeneratorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btGenerate.setOnClickListener {
            val value = binding.etQrcodeOutput.text.toString()
            displayQRCode(
                imageView = binding.imQrcodeOutput,
                activity = requireActivity(),
                value = value,
            )
        }
    }
}