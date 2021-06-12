package com.example.tte;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class HomeActivity extends AppCompatActivity {

//FirebaseUser user;
//DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("tickets");
//ArrayList<DataSnapshot> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton check_in = findViewById(R.id.check_in);

        check_in.setOnClickListener(v -> {
            IntentIntegrator intentIntegrator = new IntentIntegrator(
                    HomeActivity.this
            );
            intentIntegrator.setPrompt("For flash use volume up key");
            intentIntegrator.setBeepEnabled(true);
            intentIntegrator.setOrientationLocked(true);
            intentIntegrator.setCaptureActivity(Capture.class);
            intentIntegrator.initiateScan();
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data
        );


        if (intentResult.getContents() != null) {
//            if (TextUtils.equals(arrayList.toString(), intentResult.getContents())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    HomeActivity.this
            );

            builder.setMessage(intentResult.getContents());
            builder.setPositiveButton("OK", (dialogInterface, i) -> {
                Intent intent = new Intent(HomeActivity.this, TicketDetailsActivity.class);
                final String result = intentResult.getContents();
                intent.putExtra("result", result);
                startActivity(intent);
            })
                    .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel());
            builder.show();
        } else {
            Toast.makeText(getApplicationContext(), "OOPS... You did not scan anything", Toast.LENGTH_SHORT).show();
        }
    }
}
//        else
//        {
//            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
//            builder.setMessage("Invalid");
//            builder.setCancelable(false);
//            builder.setPositiveButton("OK", (dialog, which) -> {
//
//                dialog.dismiss();
//            });
//        }
//    }



    





