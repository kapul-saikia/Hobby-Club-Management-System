package com.kapul.hobbyclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowMembersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MemberAdapter memberAdapter;
    private List<Member> mMembers;
    BottomNavigationView mainNavigation;
    DatabaseReference databaseReference;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_members);
        recyclerView = findViewById(R.id.rvMember);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMembers = new ArrayList<>();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        String value = getIntent().getStringExtra("name of the club");

        Query query= FirebaseDatabase.getInstance().getReference("Users").orderByChild("club_name").equalTo(value);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Member upload = postSnapshot.getValue(Member.class);
                            mMembers.add(upload);
                        }
                    memberAdapter = new MemberAdapter(ShowMembersActivity.this, mMembers);
                    recyclerView.setAdapter(memberAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ShowMembersActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mainNavigation = findViewById(R.id.mainNavigation);

        mainNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(ShowMembersActivity.this, MainActivity.class));
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
                                        startActivity(new Intent(ShowMembersActivity.this, LoggedUserActivity.class));
                                        Toast.makeText(ShowMembersActivity.this, "You're Logged in as Member", Toast.LENGTH_SHORT).show();

                                    } else if (type.equals("Admin")) {
                                        switch (club) {
                                            case "Adventure Club":
                                                startActivity(new Intent(ShowMembersActivity.this, AdminAdventureActivity.class));
                                                Toast.makeText(ShowMembersActivity.this, "You're Logged in as Admin of Adventure Club", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Astronomy Club":
                                                startActivity(new Intent(ShowMembersActivity.this, AdminAstronomyActivity.class));
                                                Toast.makeText(ShowMembersActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Book Club":
                                                startActivity(new Intent(ShowMembersActivity.this, AdminBookActivity.class));
                                                Toast.makeText(ShowMembersActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Dance Club":
                                                startActivity(new Intent(ShowMembersActivity.this, AdminDanceActivity.class));
                                                Toast.makeText(ShowMembersActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Film Sector Club":
                                                startActivity(new Intent(ShowMembersActivity.this, AdminFilmActivity.class));
                                                Toast.makeText(ShowMembersActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Fine Arts Club":
                                                startActivity(new Intent(ShowMembersActivity.this, AdminArtsActivity.class));
                                                Toast.makeText(ShowMembersActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Photography Club":
                                                startActivity(new Intent(ShowMembersActivity.this, AdminPhotographyActivity.class));
                                                Toast.makeText(ShowMembersActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }else {
                            startActivity(new Intent(ShowMembersActivity.this, UserActivity.class));
                        }
                    default:
                        return false;
                }
            }
        });

    }
}
