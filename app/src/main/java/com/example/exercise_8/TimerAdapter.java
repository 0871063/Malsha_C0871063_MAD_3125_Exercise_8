package com.example.exercise_8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.exercise_8.databinding.RowTimerLayoutBinding;

import java.util.List;

public class TimerAdapter extends ArrayAdapter {

    private Context context;
    int resLayout;

    public TimerAdapter(@NonNull Context context, int resource, @NonNull List userList) {
        super(context, resource, userList);
        this.context = context;
        this.resLayout = resource;
    }


    public TimerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resLayout = resource;
    }

    @Override
    public int getCount() {
        return Timer.timerList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        RowTimerLayoutBinding binding = RowTimerLayoutBinding.inflate(LayoutInflater.from(context));

        binding.timeTV.setText(Timer.timerList.get(position).getTime());
        binding.lapCountTV.setText(Timer.timerList.get(position).getLapCount());
        return binding.getRoot();

    }
}
