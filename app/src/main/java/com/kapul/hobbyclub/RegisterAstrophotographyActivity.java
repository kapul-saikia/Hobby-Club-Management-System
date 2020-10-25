package com.kapul.hobbyclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class RegisterAstrophotographyActivity extends AppCompatActivity {

    private TextView tvName, tvEmail, tvDept, tvRoll, tvPhone;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText etSpecify;

    DatabaseReference databaseReference;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_astrophotography);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvDept = findViewById(R.id.tvDept);
        tvRoll = findViewById(R.id.tvRoll);
        tvPhone = findViewById(R.id.tvPhone);
        etSpecify = findViewById(R.id.etSpecify);
        radioGroup = findViewById(R.id.radioGroup);
        Button btnRegister = findViewById(R.id.btnRegister);

        uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                String dept = dataSnapshot.child("dept").getValue().toString();
                String roll = dataSnapshot.child("roll").getValue().toString();
                String phone = dataSnapshot.child("phone").getValue().toString();

                tvName.setText(name);
                tvEmail.setText(email);
                tvDept.setText(dept);
                tvRoll.setText(roll);
                tvPhone.setText(phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbReferenceExperience = databaseReference.child("Experience");
                String experience = radioButton.getText().toString().trim();
                dbReferenceExperience.setValue(experience);

                DatabaseReference dbReferenceSpecification = databaseReference.child("Specification");
                String specification = etSpecify.getText().toString().trim();
                dbReferenceSpecification.setValue(specification);

                Toast.makeText(RegisterAstrophotographyActivity.this, "Registration for Astrophotography program Successful", Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void onRadioButtonClicked(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        String experience = radioButton.getText().toString().trim();
        Toast.makeText(RegisterAstrophotographyActivity.this, "Checked- " + experience, Toast.LENGTH_SHORT).show();
    }
}
