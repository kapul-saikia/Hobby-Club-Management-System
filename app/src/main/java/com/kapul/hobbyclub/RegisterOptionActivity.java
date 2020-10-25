package com.kapul.hobbyclub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegisterOptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_option);

        CardView cardAdmin = findViewById(R.id.cardAdmin);
        CardView cardMember = findViewById(R.id.cardMember);

        cardAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterOptionActivity.this, AdminRegisterClubActivity.class));

            }
        });

        cardMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterOptionActivity.this, RegisterClubActivity.class));

            }
        });
    }
}
