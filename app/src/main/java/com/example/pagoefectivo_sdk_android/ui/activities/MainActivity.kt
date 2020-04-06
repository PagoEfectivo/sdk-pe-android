package com.example.pagoefectivo_sdk_android.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pagoefectivo_sdk_android.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        btnGenerate.setOnClickListener {
            val intent = Intent(this@MainActivity, GenerateCipActivity::class.java)
            startActivity(intent)
        }
    }
}