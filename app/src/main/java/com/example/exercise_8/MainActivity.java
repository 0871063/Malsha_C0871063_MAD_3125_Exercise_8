package com.example.exercise_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.exercise_8.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private  boolean timerStarted ;
    private Handler handler ;
    private Runnable runnable;
    private int delay = 1000;
    private int lapCount = 0;
    private int seconds = 0;
    private TimerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.lapBtn.setVisibility(View.GONE);
        adapter = new TimerAdapter(this, android.R.layout.simple_list_item_1);
        binding.timerListView.setAdapter(adapter);
        binding.startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (timerStarted){
                    binding.startBtn.setText(getString(R.string.start));
                    handler.removeCallbacks(runnable);
                    timerStarted = false;
                    binding.lapBtn.setText(getString(R.string.reset));
                }else {
                    binding.startBtn.setText(getString(R.string.stop));
                    timerStarted = true;
                    binding.lapBtn.setVisibility(View.VISIBLE);
                    startTimer();

                }
            }
        });
        binding.lapBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (timerStarted) {
                    lapCount += 1;
                    String time = binding.timerTV.getText().toString();
                    String lap = "Lap " + String.valueOf(lapCount);
                    Timer timer = new Timer(lap, time);
                    Timer.timerList.add(timer);
                    adapter.notifyDataSetChanged();
                }else{
                    binding.lapBtn.setText(getString(R.string.lap));
                    binding.timerTV.setText(getString(R.string.timer));
                    Timer.timerList.removeAll(Timer.timerList);
                    adapter.notifyDataSetChanged();
                    seconds = 0;
                }
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
                binding.timerTV.setText(time);

                // If running is true, increment the
                // seconds variable.
                if (timerStarted) {
                    seconds++;
                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, delay);
            }
        });
    }

}