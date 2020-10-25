package com.kapul.hobbyclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class PhotographyActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView tvTOTD, tvTOTW, tvTOTM;
    private DatabaseError error;
    DatabaseReference databaseReference, databaseReference1;
    String uid, clubName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography_home);

        tvTOTD = findViewById(R.id.tvTOTD);
        tvTOTW = findViewById(R.id.tvTOTW);
        tvTOTM = findViewById(R.id.tvTOTM);
        Button btnShowUploads = findViewById(R.id.btnShowuploads);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    clubName = Objects.requireNonNull(dataSnapshot.child("club_name").getValue()).toString();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


        databaseReference1 = FirebaseDatabase.getInstance().getReference("Events");

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String totd, totw, totm;
                //DISPLAY THEME OF THE DAY
                totd = dataSnapshot.child("ThemeOfTheDay").getValue().toString();
                tvTOTD.setText(totd);

                //DISPLAY THEME OF THE DAY
                totw = dataSnapshot.child("ThemeOfTheWeek").getValue().toString();
                tvTOTW.setText(totw);

                //DISPLAY THEME OF THE DAY
                totm = dataSnapshot.child("ThemeOfTheMonth").getValue().toString();
                tvTOTM.setText(totm);


                }

                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
        });


        //CLICK ON THEME OF THE DAY
        tvTOTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvTOTD.getText().toString().isEmpty())
                {
                    Toast.makeText(PhotographyActivity.this, "Please enter Theme of the Day", Toast.LENGTH_SHORT).show();
                }
                else if (clubName != null && clubName.equals("Photography Club")) {
                    Intent intent = new Intent(PhotographyActivity.this, UploadActivity.class);
                    startActivity(intent);
                } else {
                    clubName = "Photography Club";
                    Intent intent = new Intent(PhotographyActivity.this, NotRegisteredActivity.class);
                    intent.putExtra("name", clubName);
                    startActivity(intent);
                }
            }
        });

        //DISPLAY THEME OF THE WEEK

        tvTOTW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvTOTW.getText().toString().isEmpty())
                {
                    Toast.makeText(PhotographyActivity.this, "Please enter Theme of the Week", Toast.LENGTH_SHORT).show();
                }
                else if (clubName != null && clubName.equals("Photography Club")) {
                    Intent intent = new Intent(PhotographyActivity.this, UploadActivity.class);
                    startActivity(intent);
                } else {
                    clubName = "Photography Club";
                    Intent intent = new Intent(PhotographyActivity.this, NotRegisteredActivity.class);
                    intent.putExtra("name", clubName);
                    startActivity(intent);
                }
            }
        });

        //DISPLAY THEME OF THE MONTH

        tvTOTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvTOTM.getText().toString().isEmpty())
                {
                    Toast.makeText(PhotographyActivity.this, "Please enter Theme of the Month", Toast.LENGTH_SHORT).show();
                }
                else if (clubName != null && clubName.equals("Photography Club")) {
                    Intent intent = new Intent(PhotographyActivity.this, UploadActivity.class);
                    startActivity(intent);
                } else {
                    clubName = "Photography Club";
                    Intent intent = new Intent(PhotographyActivity.this, NotRegisteredActivity.class);
                    intent.putExtra("name", clubName);
                    startActivity(intent);
                }
            }
        });



        btnShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageActivity();
            }
        });

    }

    private void openImageActivity() {
        startActivity(new Intent(this, ImageActivity.class));
    }
}

