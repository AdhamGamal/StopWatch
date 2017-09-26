package com.example.me.stopwatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.me.stopwatch.Adapters.RecordsAdapter;
import com.example.me.stopwatch.data.Database;
import com.example.me.stopwatch.data.Record;

public class RecordListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        try {
            RecyclerView recordsList = (RecyclerView) findViewById(R.id.rv_numbers);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recordsList.setLayoutManager(layoutManager);

            recordsList.setHasFixedSize(true);
            Database database = new Database(getBaseContext());

            RecordsAdapter.clickListener listener= new RecordsAdapter.clickListener() {
                @Override
                public void onClick(Record record) {
                    Toast.makeText(RecordListActivity.this,record.getMeasure()+"/"+record.getId() , Toast.LENGTH_SHORT).show();
                }
            };
            RecordsAdapter recordsAdapter = new RecordsAdapter(database.getAllRecords(),listener);
            database.close();


            recordsList.setAdapter(recordsAdapter);
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
