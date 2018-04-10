package com.td.fintech.rebo.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.rebo.fintech.R;

public class Splash_screen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(Splash_screen.this, LoginActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

        /*
            RotatingTextWrapper rotatingTextWrapper = (RotatingTextWrapper) findViewById(R.id.custom_switcher);
            rotatingTextWrapper.setSize(35);

            Rotatable rotatable = new Rotatable(Color.parseColor("#FFA036"), 1000, "Where you find plat", "Buy your ticket", "and enjoy your self");
            rotatable.setSize(35);
            rotatable.setAnimationDuration(500);

            rotatingTextWrapper.setContent("MyEvent is ", rotatable);
        */

    }
}
