package com.example.tte;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class TicketDetailsActivity extends AppCompatActivity {
    CardView card[];
    Tickets tickets;
    int i=0;
    TextView tName,passengers,pnr;
    ImageView pnrImage;
    String qr = getIntent().getStringExtra("result");;
    Integer[] editNameId = {R.id.name0,R.id.name1,R.id.name2,R.id.name3,R.id.name4,R.id.name5};
    Integer[] editAgeId = {R.id.age0,R.id.age1,R.id.age2,R.id.age3,R.id.age4,R.id.age5};
    Integer[] genderId = {R.id.gender0,R.id.gender1,R.id.gender2,R.id.gender3,R.id.gender4,R.id.gender5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);
        card = new CardView[6];
        findViews();
        getTicketdetails(qr);

    }
    private void findViews()
    {
        card[0]=findViewById(R.id.card0);
        card[1]=findViewById(R.id.card1);
        card[2]=findViewById(R.id.card2);
        card[3]=findViewById(R.id.card3);
        card[4]=findViewById(R.id.card4);
        card[5]=findViewById(R.id.card5);
        tName = findViewById(R.id.trainName);
        passengers = findViewById(R.id.pass_no);
        pnr = findViewById(R.id.pnr);
    }
    private void getTicketdetails(String qr)
    {
        FirebaseDatabase.getInstance().getReference("tickets").child(qr).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    tickets = snapshot.getValue(Tickets.class);
                    putData(tickets);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TicketDetailsActivity.this,"Invalid",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void putData(Tickets tickets)
    {
        tName.setText("Train Name: "+tickets.getTrain_name());
        pnr.setText("PNR no: "+pnr);
        int count = tickets.getSeats().size();
        passengers.setText("No of berths: "+count);
        try{
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(qr, BarcodeFormat.QR_CODE,200,200);
            pnrImage.setImageBitmap(bitmap);
        }
        catch(Exception ignored){ }
        for(i=0;i<count;i++)
        {
            card[i].setVisibility(View.VISIBLE);
            TextView name = findViewById(editNameId[i]);
            TextView age = findViewById(editAgeId[i]);
            TextView gender =findViewById(genderId[i]);
            name.setText("Name: "+tickets.getPassengers().get(i));
            age.setText("Age: "+tickets.getAge().get(i));
            gender.setText("Gender: "+tickets.getGender().get(i));
        }
    }

}

