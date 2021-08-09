package com.tnqkr98.componentservce;

import static com.tnqkr98.componentservce.Constant.EXTRA_RECEIVER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textView;
    private Button btn_started, btn_bound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        textView = findViewById(R.id.textView);
        btn_started = findViewById(R.id.btn_started);
        btn_bound = findViewById(R.id.btn_bound);

        // Start "Started Service"
        btn_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), StartedService.class);
                intent.putExtra(EXTRA_RECEIVER,resultReceiver); // 결과 수신자 전달
                startService(intent);       // 서비스 시작
            }
        });

        btn_bound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private Handler handler = new Handler();    // 수신측에서 UI 업데이트를 위해 사용하는 핸들러

    // ResultReceiver 는 단방향 메시지 수신시 BroadCastReceiver 보다 간단
    private ResultReceiver resultReceiver = new ResultReceiver(handler){

        int percentage = 0;

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if(resultCode == Constant.SYNC_PROGRESS){
                percentage+=20;
                textView.setText(percentage+" %");
            }
            else if(resultCode == Constant.SYNC_COMPLETED){
                progressBar.setVisibility(View.GONE);
                percentage=0;
                textView.setText("Complete!");
            }
        }
    };
}