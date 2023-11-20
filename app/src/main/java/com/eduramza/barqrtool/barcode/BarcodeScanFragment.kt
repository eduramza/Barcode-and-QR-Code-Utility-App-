package com.eduramza.barqrtool.barcode

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.eduramza.barqrtool.R
import com.eduramza.barqrtool.databinding.FragmentBarcodeScanBinding
import com.google.zxing.integration.android.IntentIntegrator

class BarcodeScanFragment: Fragment(R.layout.fragment_barcode_scan) {
    private var _binding : FragmentBarcodeScanBinding? = null
    private val binding get() = _binding!!

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBarcodeScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == RESULT_OK) {
                val scanResult = IntentIntegrator.parseActivityResult(result.resultCode, result.data)
                if (scanResult.contents != null) {
                    handleResult(scanResult.contents)
                }
            }
        }
        setupListeners()
    }

    private fun setupListeners(){
        binding.btScan.setOnClickListener {
            scanQRCode()
        }
    }

    private fun handleResult(contents: String) {
        binding.tvCodeReader.text = contents
    }

    private fun scanQRCode() {
        val integrator = IntentIntegrator.forSupportFragment(this)
        integrator.setOrientationLocked(false)
        val intent = integrator.createScanIntent()
        resultLauncher.launch(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}