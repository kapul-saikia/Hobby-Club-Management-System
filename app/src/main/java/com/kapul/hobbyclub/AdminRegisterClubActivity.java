package com.kapul.hobbyclub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdminRegisterClubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_club);

        CardView cardAdventure = findViewById(R.id.cardAdventure);
        CardView cardAstronomy = findViewById(R.id.cardAstronomy);
        CardView cardBook = findViewById(R.id.cardBook);
        CardView cardDance = findViewById(R.id.cardDance);
        CardView cardFilm = findViewById(R.id.cardFilm);
        CardView cardFineArts = findViewById(R.id.cardFineArts);
        CardView cardPhotography = findViewById(R.id.cardPhotography);


        cardAdventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String club_name = "Adventure Club";
                Intent intent = new Intent(AdminRegisterClubActivity.this, AdminRegisterActivity.class);
                intent.putExtra("name", club_name);
                startActivity(intent);

            }
        });

        cardAstronomy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String club_name = "Astronomy Club";
                Intent intent = new Intent(AdminRegisterClubActivity.this, AdminRegisterActivity.class);
                intent.putExtra("name", club_name);
                startActivity(intent);

            }
        });

        cardBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String club_name = "Book Club";
                Intent intent = new Intent(AdminRegisterClubActivity.this, AdminRegisterActivity.class);
                intent.putExtra("name", club_name);
                startActivity(intent);

            }
        });

        cardDance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String club_name = "Dance Club ";
                Intent intent = new Intent(AdminRegisterClubActivity.this, AdminRegisterActivity.class);
                intent.putExtra("name", club_name);
                startActivity(intent);

            }
        });

        cardFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String club_name = "Film Sector Club";
                Intent intent = new Intent(AdminRegisterClubActivity.this, AdminRegisterActivity.class);
                intent.putExtra("name", club_name);
                startActivity(intent);

            }
        });

        cardFineArts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String club_name = "FineArts Club ";
                Intent intent = new Intent(AdminRegisterClubActivity.this, AdminRegisterActivity.class);
                intent.putExtra("name", club_name);
                startActivity(intent);

            }
        });

        cardPhotography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String club_name = "Photography Club";
                Intent intent = new Intent(AdminRegisterClubActivity.this, AdminRegisterActivity.class);
                intent.putExtra("name", club_name);
                startActivity(intent);

            }
        });
    }
}
