package com.example.nh.contactsapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class RateActivity extends AppCompatActivity {

    RatingBar r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rateactivity);

        r=(RatingBar)findViewById(R.id.ratingBar);


        getStars();

        r.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                final float numStars = ratingBar.getRating();
                SharedPreferences data=getApplicationContext().getSharedPreferences("Data",MODE_PRIVATE);
                SharedPreferences.Editor editor=data.edit();
                editor.putFloat("num",numStars);
                editor.commit();
            }
        });
    }

    private void getStars() {
        SharedPreferences data=getApplicationContext().getSharedPreferences("Data",MODE_PRIVATE);
        Float star=data.getFloat("num",0);
        r.setRating(star);
    }
}
