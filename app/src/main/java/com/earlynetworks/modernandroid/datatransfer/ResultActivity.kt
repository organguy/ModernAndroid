package com.earlynetworks.modernandroid.datatransfer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.earlynetworks.modernandroid.R
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        button.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data", "hello")
            setResult(RESULT_OK, intent)
        }
    }
}