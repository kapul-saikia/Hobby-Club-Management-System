package com.kapul.hobbyclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class FilmHomeActivity extends AppCompatActivity {

    private int[] imageUrls = new int[]{ R.drawable.film_1, R.drawable.film_2, R.drawable.film_3, R.drawable.film_4, R.drawable.film_5 };

    private ViewPager viewPager;

    DatabaseReference databaseReference;
    String uid, clubName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_home);

        Button btnReview = findViewById(R.id.btnReview);
        Button btnShow = findViewById(R.id.btnShow);
        viewPager = findViewById(R.id.viewPagerFilm);
        FilmViewPagerAdapter adapter = new FilmViewPagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    clubName = dataSnapshot.child("club_name").getValue().toString();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 1000, 2000);


        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clubName != null && clubName.equals("Film Sector Club")) {
                    Intent intent = new Intent(FilmHomeActivity.this, FilmReviewActivity.class);
                    startActivity(intent);
                } else {
                    clubName = "Film Sector Club";
                    Intent intent = new Intent(FilmHomeActivity.this, NotRegisteredActivity.class);
                    intent.putExtra("name", clubName);
                    startActivity(intent);
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FilmHomeActivity.this, ShowFilmReviewActivity.class));
            }
        });
    }

    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            FilmHomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    }else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    }else if (viewPager.getCurrentItem() == 2) {
                        viewPager.setCurrentItem(3);
                    }else if (viewPager.getCurrentItem() == 3) {
                        viewPager.setCurrentItem(4);
                    }else
                        viewPager.setCurrentItem(0);
                }
            });
        }
    }
}
