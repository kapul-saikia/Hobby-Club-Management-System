package com.kapul.hobbyclub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;

public class AdventureHomeActivity extends AppCompatActivity {

    Button btnArrowCycling, btnArrowHiking;
    CardView cardViewCycling, cardViewHiking;
    ConstraintLayout CyclingExpandable, HikingExpandable;
    Button btnArrowBiking;
    CardView cardViewBiking;
    ConstraintLayout BikingExpandable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_home);

        btnArrowCycling = findViewById(R.id.btnArrowCycling);
        cardViewCycling = findViewById(R.id.cardViewCycling);
        CyclingExpandable = findViewById(R.id.CyclingExpandable);

        btnArrowHiking = findViewById(R.id.btnArrowHiking);
        cardViewHiking = findViewById(R.id.cardViewHiking);
        HikingExpandable = findViewById(R.id.HikingExpandable);

        btnArrowBiking = findViewById(R.id.btnArrowBiking);
        cardViewBiking = findViewById(R.id.cardViewBiking);
        BikingExpandable = findViewById(R.id.BikingExpandable);


        btnArrowCycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CyclingExpandable.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardViewCycling, new AutoTransition());
                    CyclingExpandable.setVisibility(View.VISIBLE);
                    btnArrowCycling.setBackgroundResource(R.drawable.ic_up_arrow);
                }else {
                    TransitionManager.beginDelayedTransition(cardViewCycling, new AutoTransition());
                    CyclingExpandable.setVisibility(View.GONE);
                    btnArrowCycling.setBackgroundResource(R.drawable.ic_down_arrow);
                }
            }
        });

        btnArrowHiking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HikingExpandable.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardViewHiking, new AutoTransition());
                    HikingExpandable.setVisibility(View.VISIBLE);
                    btnArrowHiking.setBackgroundResource(R.drawable.ic_up_arrow);
                } else {
                    TransitionManager.beginDelayedTransition(cardViewHiking, new AutoTransition());
                    HikingExpandable.setVisibility(View.GONE);
                    btnArrowHiking.setBackgroundResource(R.drawable.ic_down_arrow);
                }
            }
        });
        btnArrowBiking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BikingExpandable.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardViewBiking, new AutoTransition());
                    BikingExpandable.setVisibility(View.VISIBLE);
                    btnArrowBiking.setBackgroundResource(R.drawable.ic_up_arrow);
                } else {
                    TransitionManager.beginDelayedTransition(cardViewBiking, new AutoTransition());
                    BikingExpandable.setVisibility(View.GONE);
                    btnArrowBiking.setBackgroundResource(R.drawable.ic_down_arrow);
                }
            }
        });
    }
}