package com.example.tte;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DynamicActivity extends AppCompatActivity {
    CardView card[];
    int i=0;
    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        card = new CardView[6];
        card[0]=findViewById(R.id.card0);
        card[1]=findViewById(R.id.card1);
        card[2]=findViewById(R.id.card2);
        card[3]=findViewById(R.id.card3);
        card[4]=findViewById(R.id.card4);
        card[5]=findViewById(R.id.card5);
        textview=findViewById(R.id.pass_no);

        Button addPass = findViewById(R.id.add);
        addPass.setOnClickListener(view -> {
            i++;
            if(i<6)
            {
                card[i].setVisibility(view.VISIBLE);
                int temp=i+1;
                String s=String.format("Passenger Count: %d",temp);
                textview.setText(s);

            }

            else{
                Toast.makeText(this, "Only upto 6 passengers", Toast.LENGTH_SHORT).show();
            }
        });



    }

}