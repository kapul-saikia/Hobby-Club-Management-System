package com.kapul.hobbyclub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class AstronomyActivity extends AppCompatActivity {
    Button btnArrowIntroduction, btnArrowAstrophotography, btnAstrophotography, btnArrowWeekly, btnWeekly, btnArrowNew, btnNew;
    CardView cardViewIntroduction, cardViewNew, cardViewWeekly, cardViewAstrophotography;
    ConstraintLayout IntroductionExpandable, NewExpandable, WeeklyExpandable, AstrophotographyExpandable;

    DatabaseReference databaseReference;
    String uid, clubName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astronomy_home);

        btnArrowIntroduction = findViewById(R.id.btnArrowIntroduction);
        cardViewIntroduction = findViewById(R.id.cardViewIntroduction);
        IntroductionExpandable = findViewById(R.id.IntroductionExpandable);

        btnArrowNew = findViewById(R.id.btnArrowNew);
        cardViewNew = findViewById(R.id.cardViewNew);
        NewExpandable = findViewById(R.id.NewExpandable);
        btnNew = findViewById(R.id.btnNew);

        btnArrowWeekly = findViewById(R.id.btnArrowWeekly);
        cardViewWeekly = findViewById(R.id.cardViewWeekly);
        WeeklyExpandable = findViewById(R.id.WeeklyExpandable);
        btnWeekly = findViewById(R.id.btnWeekly);

        btnArrowAstrophotography = findViewById(R.id.btnArrowAstrophotography);
        cardViewAstrophotography = findViewById(R.id.cardViewAstrophotography);
        AstrophotographyExpandable = findViewById(R.id.AstrophotographyExpandable);
        btnAstrophotography = findViewById(R.id.btnAstrophotography);

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


        } else {
        }

        btnArrowIntroduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IntroductionExpandable.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardViewIntroduction, new AutoTransition());
                    IntroductionExpandable.setVisibility(View.VISIBLE);
                    btnArrowIntroduction.setBackgroundResource(R.drawable.ic_up_arrow);
                } else {
                    TransitionManager.beginDelayedTransition(cardViewIntroduction, new AutoTransition());
                    IntroductionExpandable.setVisibility(View.GONE);
                    btnArrowIntroduction.setBackgroundResource(R.drawable.ic_down_arrow);
                }
            }
        });

        btnArrowNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NewExpandable.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardViewNew, new AutoTransition());
                    NewExpandable.setVisibility(View.VISIBLE);
                    btnArrowNew.setBackgroundResource(R.drawable.ic_up_arrow);
                } else {
                    TransitionManager.beginDelayedTransition(cardViewNew, new AutoTransition());
                    NewExpandable.setVisibility(View.GONE);
                    btnArrowNew.setBackgroundResource(R.drawable.ic_down_arrow);
                }
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://tuastroclub.weebly.com/astrophotography"));
                startActivity(intent);
            }
        });

        btnArrowWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (WeeklyExpandable.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardViewWeekly, new AutoTransition());
                    WeeklyExpandable.setVisibility(View.VISIBLE);
                    btnArrowWeekly.setBackgroundResource(R.drawable.ic_up_arrow);
                } else {
                    TransitionManager.beginDelayedTransition(cardViewWeekly, new AutoTransition());
                    WeeklyExpandable.setVisibility(View.GONE);
                    btnArrowWeekly.setBackgroundResource(R.drawable.ic_down_arrow);
                }
            }
        });

        btnWeekly.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://tuastroclub.weebly.com/activity-blog"));
                startActivity(intent);
            }
        });

        btnArrowAstrophotography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AstrophotographyExpandable.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardViewAstrophotography, new AutoTransition());
                    AstrophotographyExpandable.setVisibility(View.VISIBLE);
                    btnArrowAstrophotography.setBackgroundResource(R.drawable.ic_up_arrow);
                } else {
                    TransitionManager.beginDelayedTransition(cardViewAstrophotography, new AutoTransition());
                    AstrophotographyExpandable.setVisibility(View.GONE);
                    btnArrowAstrophotography.setBackgroundResource(R.drawable.ic_down_arrow);
                }
            }
        });

        btnAstrophotography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clubName != null && clubName.equals("Astronomy Club")) {
                    Intent intent = new Intent(AstronomyActivity.this, RegisterAstrophotographyActivity.class);

                } else {
                    clubName = "Astronomy Club";
                    Intent intent = new Intent(AstronomyActivity.this, NotRegisteredActivity.class);
                    intent.putExtra("name", clubName);
                    startActivity(intent);
                }
            }
        });

    }
}
