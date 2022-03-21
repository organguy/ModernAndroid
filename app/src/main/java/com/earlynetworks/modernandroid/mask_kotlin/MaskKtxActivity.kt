package com.earlynetworks.modernandroid.mask_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.earlynetworks.modernandroid.R
import com.earlynetworks.modernandroid.mask_kotlin.model.Store
import kotlinx.android.synthetic.main.activity_mask.*

class MaskKtxActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mask)

        val storeAdapter = StoreAdapter()

        recycler_view.apply {
            this.layoutManager = LinearLayoutManager(
                this@MaskKtxActivity,
                RecyclerView.VERTICAL,
                false
            )

            adapter = storeAdapter
        }

        viewModel.apply {
            itemLiveData.observe(this@MaskKtxActivity, Observer {
                storeAdapter.updateItems(it)
            })

            loadingLiveData.observe(this@MaskKtxActivity, Observer { isLoading ->
                progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
            })
        }
    }
}