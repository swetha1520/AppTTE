package com.example.tte;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyAdapter extends ArrayAdapter<String> {
    Activity activity;
    String[] tName;
    int[] price;
    Integer[] imgId;
    String[] time;
    int[] seats;
    String[] date;

    public MyAdapter(Activity activity,String[] tName,int[] price,Integer[] imgId,String[] time,int[] seats,String[] date) {
        super(activity, R.layout.activity_ticket_list,tName);
        this.activity=activity;
        this.tName=tName;
        this.price=price;
        this.imgId=imgId;
        this.time=time;
        this.seats=seats;
        this.date=date;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater li = activity.getLayoutInflater();
        View row= li.inflate(R.layout.activity_ticket_list,null,true);

        TextView title,berth,timings,cost,date;
        title = row.findViewById(R.id.trainName1);
        berth=row.findViewById(R.id.reservedseats);
        timings=row.findViewById(R.id.journeytime);
        cost=row.findViewById(R.id.totalprice);
        date=row.findViewById(R.id.journeyDate);

        title.setText(tName[position]);
        berth.setText(String.valueOf(seats[position]));
        timings.setText(time[position]);
        cost.setText(String.valueOf(price[position]));

        return row;

    }
}