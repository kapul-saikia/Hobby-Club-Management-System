package com.kapul.hobbyclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    public static int SPLASH_SCREEN = 3000;

    Animation top_anim, top_far_left_anim, top_medium_left_anim, top_near_left_anim,
            top_far_right_anim, top_medium_right_anim, top_near_right_anim, zoom_in_anim, fade_in_anim;
    ImageView ivSplashAdventure, ivSplashAstronomy, ivSplashBook, ivSplashDance, ivSplashFilm, ivSplashArt, ivSplashPhotography, ivSplashOuter, ivSplashTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        top_anim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        top_far_left_anim = AnimationUtils.loadAnimation(this, R.anim.top_far_left_anim);
        top_medium_left_anim = AnimationUtils.loadAnimation(this, R.anim.top_medium_left_anim);
        top_near_left_anim = AnimationUtils.loadAnimation(this, R.anim.top_near_left_anim);
        top_far_right_anim = AnimationUtils.loadAnimation(this, R.anim.top_far_right_anim);
        top_medium_right_anim = AnimationUtils.loadAnimation(this, R.anim.top_medium_right_anim);
        top_near_right_anim = AnimationUtils.loadAnimation(this, R.anim.top_near_right_anim);
        zoom_in_anim = AnimationUtils.loadAnimation(this, R.anim.zoom_in_anim);
        fade_in_anim =AnimationUtils.loadAnimation(this, R.anim.fade_in_anim);

        ivSplashAdventure =findViewById(R.id.ivSplashAdventure);
        ivSplashAstronomy =findViewById(R.id.ivSplashAstronomy);
        ivSplashBook =findViewById(R.id.ivSplashBook);
        ivSplashDance =findViewById(R.id.ivSplashDance);
        ivSplashFilm =findViewById(R.id.ivSplashFilm);
        ivSplashArt =findViewById(R.id.ivSplashArt);
        ivSplashPhotography =findViewById(R.id.ivSplashPhotography);
        ivSplashOuter =findViewById(R.id.ivSplashOuter);
        ivSplashTitle =findViewById(R.id.ivSplashTitle);

        ivSplashAdventure.setAnimation(top_far_right_anim);
        ivSplashAstronomy.setAnimation(top_medium_right_anim);
        ivSplashBook.setAnimation(top_near_right_anim);
        ivSplashDance.setAnimation(top_anim);
        ivSplashFilm.setAnimation(top_near_left_anim);
        ivSplashArt.setAnimation(top_medium_left_anim);
        ivSplashPhotography.setAnimation(top_far_left_anim);
        ivSplashTitle.setAnimation(zoom_in_anim);
        ivSplashOuter.setAnimation(fade_in_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}
