package com.example.me.stopwatch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView timer;
    private Button reset;
    private Button start;
    private Button stop;
    private Button records;
    private Button save;

    private int ms = 0;
    private int ss = 0;
    private int mm = 0;
    private int hh = 0;
    String mstime;
    String stime;
    String mtime;
    String htime;
    private Handler timerHandler = new Handler();
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            timerHandler.postDelayed(this, 50);
            ms++;
            if (ms == 20) {
                ss++;
                ms = 0;
            }
            if (ss == 60) {
                mm++;
                ss = 0;
            }
            if (mm == 60) {
                hh++;
                mm = 0;
            }
            mstime = ms < 10 ? "0" + ms : "" + ms;
            stime = ss < 10 ? "0" + ss : "" + ss;
            mtime = mm < 10 ? "0" + mm : "" + mm;
            htime = hh < 10 ? "0" + hh : "" + hh;
            timer.setText(htime + ":" + mtime + ":" + stime + ":" + mstime);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView) findViewById(R.id.timer);
        reset = (Button) findViewById(R.id.reset);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        records = (Button) findViewById(R.id.records);
        save = (Button) findViewById(R.id.save);

        stop.setEnabled(false);
        reset.setEnabled(false);
        save.setEnabled(false);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerHandler.postDelayed(timerRunnable, 0);
                start.setEnabled(false);
                stop.setEnabled(true);
                reset.setEnabled(false);
                save.setEnabled(false);
                records.setEnabled(false);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerHandler.removeCallbacks(timerRunnable);
                start.setEnabled(true);
                stop.setEnabled(false);
                reset.setEnabled(true);
                save.setEnabled(true);
                records.setEnabled(true);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ms = 0;
                ss = 0;
                mm = 0;
                hh = 0;
                timer.setText("00:00:00:00");
                stop.setEnabled(false);
                reset.setEnabled(false);
                save.setEnabled(false);
            }
        });
        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), RecordListActivity.class));
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SaveRecord.class);
                intent.putExtra("hh", hh);
                intent.putExtra("mm", mm);
                intent.putExtra("ss", ss);
                intent.putExtra("ms", ms);
                startActivity(intent);
            }
        });
    }
}

















