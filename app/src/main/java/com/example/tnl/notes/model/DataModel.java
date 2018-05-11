package com.example.tnl.notes.model;

import android.content.Context;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tnl on 12/3/2017;
 */

public class DataModel extends RealmObject implements Serializable {

    String title;
    String notes;
    long dateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey
    int id;

    public DataModel() {
    }

    public DataModel(String title, String notes, long dateTime, int id) {
        this.title = title;
        this.notes = notes;
        this.dateTime = dateTime;
        this.id = id;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDateTimeFormatted(Context context) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return simpleDateFormat.format(calendar.getTime());
    }
}