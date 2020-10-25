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

public class ShowBookReviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BookReviewAdapter bookReviewAdapter;
    LinearLayoutManager layoutManager;
    BottomNavigationView mainNavigation;
    DatabaseReference databaseReference;
    String uid;
    private List<BookReview> mBookReviews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book_review);

        recyclerView = findViewById(R.id.rvBookReview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mBookReviews = new ArrayList<>();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("Book/Review");
        databaseReference = FirebaseDatabase.getInstance().getReference("Book/Review");
        databaseReference.keepSynced(true);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    BookReview upload = postSnapshot.getValue(BookReview.class);
                    mBookReviews.add(upload);
                }
                bookReviewAdapter = new BookReviewAdapter(ShowBookReviewActivity.this,  mBookReviews);
                recyclerView.setAdapter(bookReviewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ShowBookReviewActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mainNavigation = findViewById(R.id.mainNavigation);

        mainNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(ShowBookReviewActivity.this, MainActivity.class));
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
                                        startActivity(new Intent(ShowBookReviewActivity.this, LoggedUserActivity.class));
                                        Toast.makeText(ShowBookReviewActivity.this, "You're Logged in as Member", Toast.LENGTH_SHORT).show();

                                    } else if (type.equals("Admin")) {
                                        switch (club) {
                                            case "Adventure Club":
                                                startActivity(new Intent(ShowBookReviewActivity.this, AdminAdventureActivity.class));
                                                Toast.makeText(ShowBookReviewActivity.this, "You're Logged in as Admin of Adventure Club", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Astronomy Club":
                                                startActivity(new Intent(ShowBookReviewActivity.this, AdminAstronomyActivity.class));
                                                Toast.makeText(ShowBookReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Book Club":
                                                startActivity(new Intent(ShowBookReviewActivity.this, AdminBookActivity.class));
                                                Toast.makeText(ShowBookReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Dance Club":
                                                startActivity(new Intent(ShowBookReviewActivity.this, AdminDanceActivity.class));
                                                Toast.makeText(ShowBookReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Film Sector Club":
                                                startActivity(new Intent(ShowBookReviewActivity.this, AdminFilmActivity.class));
                                                Toast.makeText(ShowBookReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Fine Arts Club":
                                                startActivity(new Intent(ShowBookReviewActivity.this, AdminArtsActivity.class));
                                                Toast.makeText(ShowBookReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Photography Club":
                                                startActivity(new Intent(ShowBookReviewActivity.this, AdminPhotographyActivity.class));
                                                Toast.makeText(ShowBookReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }else {
                            startActivity(new Intent(ShowBookReviewActivity.this, UserActivity.class));
                        }
                    default:
                        return false;
                }
            }
        });

    }
}
