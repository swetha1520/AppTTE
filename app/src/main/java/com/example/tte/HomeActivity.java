package com.example.tte;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class HomeActivity extends AppCompatActivity {
    private Button check_in;
    private Button check_out;
    private Button book;
    private FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();
        check_in = findViewById(R.id.check_in);
        check_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(
                        HomeActivity.this
                );
                intentIntegrator.setPrompt("For flash use volume up key");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });

    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        IntentResult res = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (res != null) {
//            if (res.getContents() == null) {
//                Toast.makeText(this, "OOPS... You did not scan anything", Toast.LENGTH_LONG).show();
//
//            } else {
//
//                Intent intent = new Intent(HomeActivity.this, TicketDetailsActivity.class);
//                intent.putExtra("phone", "+91" + phone);
//                startActivity(intent);
//                // Toast.makeText(this,res.getContents(),Toast.LENGTH_LONG).show();
//
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);

        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode,resultCode,data
        );


        if(intentResult.getContents()!=null)
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(
                    HomeActivity.this
            );
            builder.setMessage(intentResult.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
////                    dialogInterface.dismiss();
                    Intent intent = new Intent(HomeActivity.this,TicketDetailsActivity.class);
                    final String scanresult = intentResult.getContents();
                    intent.putExtra("SCAN RESULT",scanresult);
                    startActivity(intent);
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
            });
          builder.show();
        }else {
            Toast.makeText(getApplicationContext(), "OOPS... You did not scan anything",Toast.LENGTH_SHORT).show();
        }
    }
}


    





