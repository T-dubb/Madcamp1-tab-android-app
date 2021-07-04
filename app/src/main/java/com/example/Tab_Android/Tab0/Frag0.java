package com.example.Tab_Android.Tab0;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.Tab_Android.R;

public class Frag0 extends Fragment {

    TextView dateView;

    TextView cityView;
    TextView weatherView;
    TextView tempView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tab0_activity_frag0,container,false);
        ImageButton button = v.findViewById(R.id.imageButton);
        dateView = v.findViewById(R.id.dateView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("HH:mm:ss");
                String getDay = simpleDateFormatDay.format(date);
                String getTime = simpleDateFormatTime.format(date);
                String getDate = getDay + "\n" + getTime;
                dateView.setText(getDate);
            }
        });

        return v;
    }
    private void CurrentCall(){
        String url = "http://api.openweathermap.org/data/2.5/weather?q=daejeon&appid=99162b3225b63083cfc32b51154370bf";

    }
}