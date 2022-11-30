package com.example.exercise_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button startBtn;
    private Button lapBtn;
    private TextView timerTV;
    private ListView timerListView;
    private ArrayList<Timer> timerList = new ArrayList<>();
    private  boolean timerStarted ;
    private Handler handler = new Handler();
    private Runnable runnable;
    private int delay = 1000;
    private int lapCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startBtn);
        lapBtn = findViewById(R.id.lapBtn);
        timerTV = findViewById(R.id.timerTV);
        timerListView = findViewById(R.id.timerListView);

        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (timerStarted){
                    startBtn.setText(getString(R.string.start));
                    handler.removeCallbacks(runnable);
                    lapBtn.setVisibility(View.GONE);
                    timerTV.setText(getString(R.string.timer));
                }else {
                    startBtn.setText(getString(R.string.stop));
                    timerStarted = true;
                    lapBtn.setVisibility(View.VISIBLE);
                    startTimer();
                }
            }
        });
        lapBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lapCount += 1;
                String time = timerTV.getText().toString();
                String lap = "Lap " + String.valueOf(lapCount);
                Timer timer = new Timer(lap ,time);
                timerList.add(timer);

            }
        });

        ArrayAdapter<Timer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,timerList);
        timerListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void startTimer() {
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                timerAction();
            }
        }, delay);
    }

    private void timerAction(){

    }
}