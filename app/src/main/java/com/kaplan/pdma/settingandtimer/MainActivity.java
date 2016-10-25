package com.kaplan.pdma.settingandtimer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMinutes;
    private EditText editTextSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMinutes = (EditText) findViewById(R.id.editTextMinutes);
        editTextSeconds = (EditText) findViewById(R.id.editTextSeconds);
        Button buttonSave = (Button) findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int minutes = Integer.parseInt(editTextMinutes.getText().toString());
                int seconds = Integer.parseInt(editTextSeconds.getText().toString());

                //Write to SharedPreferences
                SharedPreferences sharedPref = getSharedPreferences("ptdipwt5", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("minutes", minutes);
                editor.putInt("seconds", seconds);
                editor.commit(); //save the values

                Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Read from SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("ptdipwt5", Context.MODE_PRIVATE);
        int minutes = sharedPref.getInt("minutes", 0);
        int seconds = sharedPref.getInt("seconds", 0);
        editTextMinutes.setText("" + minutes);
        editTextSeconds.setText("" + seconds);

    }
}
