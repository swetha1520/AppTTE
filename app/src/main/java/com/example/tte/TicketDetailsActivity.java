package com.example.tte;

import android.os.Bundle;

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
    DatabaseReference root,ticketid;
    private String phone=getIntent().getExtras().getString("phone");


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);
        rv=(RecyclerView)findViewById(R.id.recycler);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();

        root=db.getInstance().getReference().child("users").child(phone).child("current");
        ticketid = db.getReference().child("tickets").child(user.getUid());
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    String val = ds.getValue().toString();
                    if(val.equals(ticketid))
                    {
                        ticketid.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot snapshot1 : snapshot.getChildren())
                                {
                                    Tickets l=snapshot1.getValue(Tickets.class);
                                    list.add(l);
                                }
//                                Tickets tickets = snapshot.getValue(Tickets.class);
//                                tickets.getTrain_name();
//                                tickets.getTrain_no();
//                                tickets.getPassengers();
//                                tickets.getAge();
//                                tickets.getGender();
//                                tickets.getDate();
//                                tickets.getTimings();
//                                tickets.getSeats();
//                                tickets.getPrice();


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
                adapter=new MyAdapter(list);
                rv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
