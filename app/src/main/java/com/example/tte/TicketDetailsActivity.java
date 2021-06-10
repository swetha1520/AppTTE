package com.example.tte;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TicketDetailsActivity extends AppCompatActivity {
    private List<Tickets> list;
    private RecyclerView rv;
    private MyAdapter adapter;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase db;
    DatabaseReference root;
    String qr = getIntent().getStringExtra("scanresult");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);
        rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        root = db.getInstance().getReference().child("tickets").child(qr);
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    String pnr = dataSnapshot.getValue().toString();
                    if (dataSnapshot.exists()) {
                        Tickets l = dataSnapshot.getValue(Tickets.class);
                        list.add(l);
                    }

                    adapter=new MyAdapter(list);
                    rv.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TicketDetailsActivity.this,"Invalid",Toast.LENGTH_SHORT).show();
            }
        });
      }
   }




