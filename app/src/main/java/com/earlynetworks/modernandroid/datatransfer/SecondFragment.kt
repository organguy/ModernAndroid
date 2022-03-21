package com.earlynetworks.modernandroid.datatransfer

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.earlynetworks.modernandroid.R
import kotlinx.android.synthetic.main.fragment_second2.*


class SecondFragment : Fragment(R.layout.fragment_second2) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("requestKey") { resultKey, bundle ->
            val data = bundle.getString("data", "")
            Toast.makeText(requireContext(), data, Toast.LENGTH_SHORT).show()
        }

        button.setOnClickListener {

            setFragmentResult(
                "requestKey",
                bundleOf("data" to "hello")
            )

            findNavController().navigate(R.id.action_secondFragment2_to_firstFragment)
        }
    }
}