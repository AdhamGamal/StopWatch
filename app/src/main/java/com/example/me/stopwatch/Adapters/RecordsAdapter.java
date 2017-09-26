package com.example.me.stopwatch.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.me.stopwatch.R;
import com.example.me.stopwatch.data.Record;

import java.util.List;
import java.util.Random;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.recordViewHolder> {

    public interface clickListener{
        public void onClick(Record record);
    }

    clickListener listener;
    private List<Record> records;

    public RecordsAdapter(List<Record> records,clickListener listener) {
        this.records = records;
        this.listener=listener;
    }

    @Override
    public recordViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.record_layout, viewGroup, false);
        recordViewHolder viewHolder = new recordViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(recordViewHolder holder, int position) {
        holder.bind(records.get(position));
    }

    @Override
    public int getItemCount() {
        return records.size();
    }


    class recordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView shortTime;
        TextView title;
        TextView subTitle;
        TextView fullTime;
        Record record;

        public recordViewHolder(View itemView) {
            super(itemView);

            shortTime = (TextView) itemView.findViewById(R.id.shorttime);
            title = (TextView) itemView.findViewById(R.id.title);
            subTitle = (TextView) itemView.findViewById(R.id.subtitle);
            fullTime = (TextView) itemView.findViewById(R.id.fulltime);

            itemView.setOnClickListener(this);
        }
        void bind(Record record) {
            this.record=record;
            final int hh = record.getHh(),
                    mm = record.getMm(),
                    ss = record.getSs(),
                    ms = record.getMs();
            String time, mstime, stime, mtime, htime;

            mstime = ms < 10 ? ("0" + ms) : ("" + ms);
            stime = ss < 10 ? ("0" + ss) : ("" + ss);
            mtime = mm < 10 ? ("0" + mm) : ("" + mm);
            htime = hh < 10 ? ("0" + hh) : ("" + hh);
            if (hh > 0) {
                time = htime + ":" + mtime + "h";
            } else if (mm > 0) {
                time = mtime + ":" + stime + "m";
            } else if (ss > 0) {
                time = stime + ":" + mstime + "s";
            } else if (ms > 0) {
                time = "00:" + mstime + "ms";
            } else {
                time = "00:00";
            }

            shortTime.setText(time);
            title.setText(record.getTitle());
            subTitle.setText(record.getSubTitle());
            fullTime.setText(htime + ":" + mtime + ":" + stime + ":" + mstime + " " + record.getMeasure());
            shortTime.setBackgroundColor(getColor());
        }

        private int getColor() {
            float[] hsv = new float[3];
            Random rnd = new Random();
            Color.colorToHSV((Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)) + Color.BLACK), hsv);
            hsv[2] *= 0.6f;
            return Color.HSVToColor(hsv);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(record);
        }
    }
}
