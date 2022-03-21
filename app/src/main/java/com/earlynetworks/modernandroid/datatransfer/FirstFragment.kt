package com.earlynetworks.modernandroid.datatransfer

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

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){
        imageView.setImageURI(it)
    }

    val getStartActivityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
        activityResult.data?.let { intent ->
            intent.extras?.let { bundle ->
                //Log.d("First Fragment", "result : ${bundle.getString("data", "world")}")
                Toast.makeText(context, "result : ${bundle.getString("data", "world")}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            // MIME TYPE
            //getContent.launch("image/*")
            Intent(requireContext(), ResultActivity::class.java).apply {
                getStartActivityForResult.launch(this)
            }
        }

        /*setFragmentResultListener("requestKey") { resultKey, bundle ->
            val data = bundle.getString("data", "")
            Toast.makeText(requireContext(), data, Toast.LENGTH_SHORT).show()
        }

        button.setOnClickListener {

            setFragmentResult(
                "requestKey",
                bundleOf("data" to "hello")
            )

            findNavController().navigate(R.id.action_firstFragment_to_secondFragment2)
        }*/
    }
}