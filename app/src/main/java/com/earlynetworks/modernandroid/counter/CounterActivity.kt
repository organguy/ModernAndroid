package com.earlynetworks.modernandroid.counter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.earlynetworks.modernandroid.R
import com.earlynetworks.modernandroid.databinding.ActivityCounterBinding
import kotlinx.android.synthetic.main.activity_counter.*

class CounterActivity : AppCompatActivity() {


    private val viewModel by viewModels<CounterViewModel>()

    companion object {
        val TAG = CounterActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityCounterBinding>(this, R.layout.activity_counter)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}