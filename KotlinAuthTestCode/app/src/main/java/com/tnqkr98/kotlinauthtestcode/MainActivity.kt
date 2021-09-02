package com.tnqkr98.kotlinauthtestcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import com.tnqkr98.kotlinauthtestcode.RetrofitClient.gosleepApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var expireTime = 180
    private var mTimerHandler:Handler? = null
    private var mTimerRunnable:Runnable = object :Runnable{
        override fun run() {
            expireTime -= 1
            if(expireTime > 0)
                mTimerHandler?.postDelayed(this,1000);
            else
                expireTime = 180;

            runOnUiThread { tx_Expiration.text = "$expireTime 초 남음" }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mTimerThread = HandlerThread("backgroundThread")
        mTimerThread?.start()
        mTimerHandler = Handler(mTimerThread!!.looper)

        bt_AuthMsg.setOnClickListener {
            gosleepApiService.requestAuthMsg("01022115987").enqueue(object : Callback<Result>{
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    mTimerHandler?.postDelayed(mTimerRunnable,1000);
                }
                override fun onFailure(call: Call<Result>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }

        bt_Confirm.setOnClickListener {
            // ToDo - Confirm API
            gosleepApiService.requestAuthConfirm("01022115987",et_Verify.text.toString()).enqueue(object : Callback<Result>{
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    TODO("Not yet implemented")
                }
                override fun onFailure(call: Call<Result>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}