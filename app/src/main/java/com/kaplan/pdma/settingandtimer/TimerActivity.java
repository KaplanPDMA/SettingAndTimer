package com.kaplan.pdma.settingandtimer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TimerActivity extends AppCompatActivity {

    TextView textViewSeconds;
    private int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        final CountDownTimer timer = new CountDownTimer(seconds*1000, 1000) {
            @Override
            public void onTick(long l) {
                long m = l/1000 /60;
                long s = l/1000 % 60;
                textViewSeconds.setText("" + m + " : " + s);
            }

            @Override
            public void onFinish() {
                Toast.makeText(TimerActivity.this, "Sayonana!", Toast.LENGTH_LONG).show();
                finish(); //exit activity
            }
        };


        textViewSeconds = (TextView) findViewById(R.id.textViewSeconds);
        Button buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.start();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Read from SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("ptdipwt5", Context.MODE_PRIVATE);
        int minutes = sharedPref.getInt("minutes", 0);
        seconds = sharedPref.getInt("seconds", 0);
        textViewSeconds.setText("" + minutes + " : " + seconds);

        seconds += minutes * 60; //update to the total number of seconds
    }

}
