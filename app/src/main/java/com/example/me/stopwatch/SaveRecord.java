package com.example.me.stopwatch;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.me.stopwatch.data.Database;
import com.example.me.stopwatch.data.Record;

import java.util.Random;

public class SaveRecord extends AppCompatActivity {

    private TextView shortTime;
    private EditText title;
    private EditText subTitle;
    private TextView fullTime;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_record);
        try {
            Intent intent = getIntent();
            final int hh = intent.getIntExtra("hh", 0),
                    mm = intent.getIntExtra("mm", 0),
                    ss = intent.getIntExtra("ss", 0),
                    ms = intent.getIntExtra("ms", 0);
            String time, measure, mstime, stime, mtime, htime;
            mstime = ms < 10 ? ("0" + ms) : ("" + ms);
            stime = ss < 10 ? ("0" + ss) : ("" + ss);
            mtime = mm < 10 ? ("0" + mm) : ("" + mm);
            htime = hh < 10 ? ("0" + hh) : ("" + hh);

            if (hh > 0) {
                time = htime + ":" + mtime + "h";
                measure = (hh == 1) ? "Hour" : "Hours";
            } else if (mm > 0) {
                time = mtime + ":" + stime + "m";
                measure = (mm == 1) ? "Minute" : "Minutes";
            } else if (ss > 0) {
                time = stime + ":" + mstime + "s";
                measure = (ss == 1) ? "Second" : "Seconds";
            } else if (ms > 0) {
                time = "00:" + mstime + "ms";
                measure = (hh == 1) ? "Mile Second" : "Mile Seconds";
            } else {
                time = "00:00";
                measure = ".";
            }

            shortTime = ((TextView) findViewById(R.id.shorttime));
            title = (EditText) findViewById(R.id.title);
            subTitle = (EditText) findViewById(R.id.subtitle);
            ((TextView) findViewById(R.id.fulltime)).setText(htime + ":" + mtime + ":" + stime + ":" + mstime + " " + measure);
            save = (Button) findViewById(R.id.save);

            shortTime.setText(time);
            shortTime.setBackgroundColor(getColor());

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Database database = new Database(getBaseContext());
                    database.InsertRecord(new Record(title.getText().toString(),
                            subTitle.getText().toString(), hh, mm, ss, ms));
                    database.close();
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private int getColor() {
        float[] hsv = new float[3];
        Random rnd = new Random();
        Color.colorToHSV((Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)) + Color.BLACK), hsv);
        hsv[2] *= 0.6f;
        return Color.HSVToColor(hsv);
    }
}
