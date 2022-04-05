package com.earlynetworks.modernandroid.datatransfer

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import com.earlynetworks.modernandroid.R
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment(R.layout.fragment_first) {

    val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { map ->
        if(map[Manifest.permission.ACCESS_FINE_LOCATION]!!){
            Toast.makeText(requireContext(), "ACCESS_FINE_LOCATION 성공", Toast.LENGTH_SHORT).show()
        }
        if(map[Manifest.permission.ACCESS_COARSE_LOCATION]!!){
            Toast.makeText(requireContext(), "ACCESS_COARSE_LOCATION 성공", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            // MIME TYPE
            //getContent.launch("image/*")
            Intent(requireContext(), ResultActivity::class.java).apply {
                requestPermission.launch(arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ))
            }
        }
    }
}