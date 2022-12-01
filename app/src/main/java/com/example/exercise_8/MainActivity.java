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
import java.util.Locale;

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
    private int seconds = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startBtn);
        lapBtn = findViewById(R.id.lapBtn);
        timerTV = findViewById(R.id.timerTV);
        timerListView = findViewById(R.id.timerListView);
        lapBtn.setVisibility(View.GONE);
        ArrayAdapter<Timer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,timerList);
        timerListView.setAdapter(adapter);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (timerStarted){
                    startBtn.setText(getString(R.string.start));
                    handler.removeCallbacks(runnable);
                    lapBtn.setVisibility(View.GONE);
                    timerTV.setText(getString(R.string.timer));
                    timerStarted = false;
                    seconds = 0;
                    handler.removeCallbacks(runnable);
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
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void startTimer() {
        handler = new Handler();
        handler.post(new Runnable() {
            @Override

            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // Format the seconds into hours, minutes,
                // and seconds.
                String time
                        = String
                        .format(Locale.getDefault(),
                                "%02d:%02d:%02d", hours,
                                minutes, secs);

                // Set the text view text.
                timerTV.setText(time);

                // If running is true, increment the
                // seconds variable.
                if (timerStarted) {
                    seconds++;
                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }

}