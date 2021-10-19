package com.earlynetworks.modernandroid.mask_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.earlynetworks.modernandroid.R
import com.earlynetworks.modernandroid.mask_kotlin.model.Store
import kotlinx.android.synthetic.main.activity_mask.*

class MaskKtxActivity : AppCompatActivity() {
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

        val items = listOf(
            Store("abc", "abc", "abc", 31.3, 33.3, "약국", "plenty", "33", "33"),
            Store("abc", "abc", "abc", 31.3, 33.3, "약국", "plenty", "33", "33"),
            Store("abc", "abc", "abc", 31.3, 33.3, "약국", "plenty", "33", "33")
        )

        storeAdapter.updateItems(items)
    }
}