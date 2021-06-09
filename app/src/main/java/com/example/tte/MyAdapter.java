package com.example.tte;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Tickets> listdata;
    public MyAdapter(List<Tickets> listdata)
    {
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tickets,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Tickets t = listdata.get(position);
        holder.train_name.setText(t.getTrain_name());
        holder.train_no.setText(t.getTrain_no());
        holder.passengers.setText((CharSequence) t.getPassengers());
        holder.age.setText((CharSequence) t.getAge());
        holder.gender.setText((CharSequence) t.getAge());
        holder.date.setText(t.getDate());
        holder.timings.setText(t.getTimings());
        holder.seats.setText((CharSequence) t.getSeats());
        holder.price.setText(t.getPrice());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView train_name,train_no,passengers,age,gender,date,timings,seats,price;
        public ViewHolder(View view) {
            super(view);
            train_name=(TextView)view.findViewById(R.id.train_name);
            train_no=(TextView)view.findViewById(R.id.train_no);
            passengers=(TextView)view.findViewById(R.id.passengers);
            age=(TextView)view.findViewById(R.id.age);
            gender=(TextView)view.findViewById(R.id.gender);
            date=(TextView)view.findViewById(R.id.date);
            timings=(TextView)view.findViewById(R.id.timings);
            seats=(TextView)view.findViewById(R.id.seats);
            price=(TextView)view.findViewById(R.id.price);
        }
    }
}

