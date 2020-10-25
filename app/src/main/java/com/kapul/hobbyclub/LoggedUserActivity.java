package com.kapul.hobbyclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoggedUserActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvEmail;
    private TextView tvDept;
    private TextView tvRoll;
    private TextView tvPhone;

    BottomNavigationView mainNavigation;
    DatabaseReference databaseReference;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_user);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvDept = findViewById(R.id.tvDept);
        tvRoll = findViewById(R.id.tvRoll);
        tvPhone = findViewById(R.id.tvPhone);
        TextView tvFgtPassword = findViewById(R.id.tvFgtPassword);
        TextView tvChangePassword = findViewById(R.id.tvChangePassword);
        TextView tvLogout = findViewById(R.id.tvLogout);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = firebaseAuth.getCurrentUser();

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

        tvFgtPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoggedUserActivity.this, PasswordReset.class));
            }
        });

        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoggedUserActivity.this, ChangePassword.class));
            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(LoggedUserActivity.this, MainActivity.class));
            }
        });

        mainNavigation = findViewById(R.id.mainNavigation);

        mainNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(LoggedUserActivity.this, MainActivity.class));
                        return true;

                    case R.id.notification:
                        startActivity(new Intent(LoggedUserActivity.this, ShowNotificationActivity.class));
                        return true;

                    case R.id.account:
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}
