package com.eduramza.barqrtool.qrcode

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.eduramza.barqrtool.databinding.FragmentQrCodeScanBinding
import com.google.zxing.integration.android.IntentIntegrator

class QRCodeScanFragment: Fragment() {
    private var _binding: FragmentQrCodeScanBinding? = null
    private val binding get() = _binding!!

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val scanResult = IntentIntegrator.parseActivityResult(result.resultCode, result.data)
            if (scanResult.contents != null) {
                handleResult(scanResult.contents)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQrCodeScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners(){
        binding.btScan.setOnClickListener {
            scanQRCode()
        }
    }

    private fun scanQRCode() {
        val integrator = IntentIntegrator.forSupportFragment(this).apply {
            setOrientationLocked(false)
            setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            setPrompt("Scan QR Code")
            setBeepEnabled(false)
        }
        resultLauncher.launch(integrator.createScanIntent())
    }

    private fun handleResult(contents: String) {
        binding.tvCodeReader.text = contents
    }
}