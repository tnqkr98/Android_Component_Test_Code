package com.tnqkr98.kotlinauthtestcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.HandlerThread
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mHandlerThread : HandlerThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_AuthMsg.setOnClickListener {
            // ToDo - verfiy API
        }

        bt_Confirm.setOnClickListener {
            // ToDo - Confirm API
        }
    }
}