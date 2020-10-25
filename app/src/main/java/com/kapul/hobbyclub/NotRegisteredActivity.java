package com.kapul.hobbyclub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class NotRegisteredActivity extends AppCompatActivity
{
    TextView tvClubName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_registered);

        TextView tvLogin = findViewById(R.id.tvLogin);
        TextView tvClubName = findViewById(R.id.tvClubName);
        Button btnRegister = findViewById(R.id.btnRegister);

        final String clubName = Objects.requireNonNull(getIntent().getStringExtra("name")).trim();
        tvClubName.setText(clubName);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotRegisteredActivity.this, RegisterClubActivity.class));
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotRegisteredActivity.this, LoginActivity.class));
            }
        });
    }
}
