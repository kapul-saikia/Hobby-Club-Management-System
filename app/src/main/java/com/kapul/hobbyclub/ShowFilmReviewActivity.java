package com.kapul.hobbyclub;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowFilmReviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FilmReviewAdapter FilmReviewAdapter;
    LinearLayoutManager layoutManager;
    BottomNavigationView mainNavigation;
    DatabaseReference databaseReference;
    String uid;
    private List<FilmReview> mFilmReviews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_film_review);

        recyclerView = findViewById(R.id.rvFilmReview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFilmReviews = new ArrayList<>();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("Film/Review");
        databaseReference = FirebaseDatabase.getInstance().getReference("Film/Review");
        databaseReference.keepSynced(true);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    FilmReview upload = postSnapshot.getValue(FilmReview.class);
                    mFilmReviews.add(upload);
                }
                FilmReviewAdapter = new FilmReviewAdapter(ShowFilmReviewActivity.this,  mFilmReviews);
                recyclerView.setAdapter(FilmReviewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ShowFilmReviewActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mainNavigation = findViewById(R.id.mainNavigation);

        mainNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(ShowFilmReviewActivity.this, MainActivity.class));
                        return true;


                    case R.id.account:
                        mainNavigation.setItemBackgroundResource(R.drawable.gradient_navigation_bar_account);

                        if (currentUser != null) {
                            uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

                            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);

                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String type = dataSnapshot.child("type").getValue().toString();
                                    String club = dataSnapshot.child("club_name").getValue().toString();
                                    if (type.equals("Member")) {
                                        startActivity(new Intent(ShowFilmReviewActivity.this, LoggedUserActivity.class));
                                        Toast.makeText(ShowFilmReviewActivity.this, "You're Logged in as Member", Toast.LENGTH_SHORT).show();

                                    } else if (type.equals("Admin")) {
                                        switch (club) {
                                            case "Adventure Club":
                                                startActivity(new Intent(ShowFilmReviewActivity.this, AdminAdventureActivity.class));
                                                Toast.makeText(ShowFilmReviewActivity.this, "You're Logged in as Admin of Adventure Club", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Astronomy Club":
                                                startActivity(new Intent(ShowFilmReviewActivity.this, AdminAstronomyActivity.class));
                                                Toast.makeText(ShowFilmReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Book Club":
                                                startActivity(new Intent(ShowFilmReviewActivity.this, AdminBookActivity.class));
                                                Toast.makeText(ShowFilmReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Dance Club":
                                                startActivity(new Intent(ShowFilmReviewActivity.this, AdminDanceActivity.class));
                                                Toast.makeText(ShowFilmReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Film Sector Club":
                                                startActivity(new Intent(ShowFilmReviewActivity.this, AdminFilmActivity.class));
                                                Toast.makeText(ShowFilmReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Fine Arts Club":
                                                startActivity(new Intent(ShowFilmReviewActivity.this, AdminArtsActivity.class));
                                                Toast.makeText(ShowFilmReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Photography Club":
                                                startActivity(new Intent(ShowFilmReviewActivity.this, AdminPhotographyActivity.class));
                                                Toast.makeText(ShowFilmReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }else {
                            startActivity(new Intent(ShowFilmReviewActivity.this, UserActivity.class));
                        }
                    default:
                        return false;
                }
            }
        });

    }
}
