package com.example.tte;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.tte.models.Ticketmodel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.tte.R.id.ticketImage;

public class TicketDetailsActivity extends AppCompatActivity {

    CardView[] card;
    Ticketmodel model;
    TextView tName, PNR, passNo;
    ImageView pnrImage;
    Integer[] editNameId = {R.id.name0,R.id.name1,R.id.name2,R.id.name3,R.id.name4,R.id.name5};
    Integer[] editAgeId = {R.id.age0,R.id.age1,R.id.age2,R.id.age3,R.id.age4,R.id.age5};
    Integer[] genderId = {R.id.gender0,R.id.gender1,R.id.gender2,R.id.gender3,R.id.gender4,R.id.gender5};
    String qr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);
        card = new CardView[6];
        qr = getIntent().getStringExtra("result");
        findViews();
        getTicket(qr);
    }

    private void findViews() {
        card[0]=findViewById(R.id.card0);
        card[1]=findViewById(R.id.card1);
        card[2]=findViewById(R.id.card2);
        card[3]=findViewById(R.id.card3);
        card[4]=findViewById(R.id.card4);
        card[5]=findViewById(R.id.card5);
        tName = findViewById(R.id.trainName);
        PNR = findViewById(R.id.pnr);
        passNo = findViewById(R.id.pass_no);
        pnrImage = findViewById(ticketImage);
    }

    private void getTicket(String qr) {
        FirebaseDatabase.getInstance().getReference("tickets").child(qr).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    model = snapshot.getValue(Ticketmodel.class);
                    putData(model);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void putData(Ticketmodel model) {
        tName.setText("Train Name: "+model.getTrain_name());
        PNR.setText("PNR no: "+qr);
        int people = model.getSeats().size();
        passNo.setText("No of berths: "+people);
        for(int i=0;i<people;i++){
            card[i].setVisibility(View.VISIBLE);
            TextView name = findViewById(editNameId[i]);
            TextView age = findViewById(editAgeId[i]);
            TextView gender = findViewById(genderId[i]);
            name.setText("Name: "+ model.getPassengers().get(i));
            age.setText("Age: "+ model.getAge().get(i));
            gender.setText("Gender: "+model.getGender().get(i));
        }
    }
}