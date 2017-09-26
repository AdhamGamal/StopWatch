package com.example.me.stopwatch.data;

public class Record {
    private int id;
    private String title;
    private String subTitle;
    private int hh;
    private int mm;
    private int ss;
    private int ms;
    private String measure;

    public Record(int id, String title, String subTitle, int hh, int mm, int ss, int ms, String measure) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.hh = hh;
        this.mm = mm;
        this.ss = ss;
        this.ms = ms;
        this.measure = measure;
    }

    public Record(String title, String subTitle, int hh, int mm, int ss, int ms) {
        this.title = title;
        this.subTitle = subTitle;
        this.hh = hh;
        this.mm = mm;
        this.ss = ss;
        this.ms = ms;
        if (hh > 0) {
            measure = (hh == 1) ? "Hour" : "Hours";
        } else if (mm > 0) {
            measure = (hh == 1) ? "Minute" : "Minutes";
        } else if (ss > 0) {
            measure = (hh == 1) ? "Second" : "Seconds";
        } else if (ms > 0) {
            measure = (hh == 1) ? "Mile Second" : "Mile Seconds";
        } else {
            measure = ".";
        }
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public int getHh() {
        return hh;
    }

    public int getMm() {
        return mm;
    }

    public int getSs() {
        return ss;
    }

    public int getMs() {
        return ms;
    }

    public String getMeasure() {
        return measure;
    }
}
