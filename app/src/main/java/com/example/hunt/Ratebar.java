package com.example.hunt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.hsalf.smilerating.SmileRating;

import java.util.logging.Level;

public class Ratebar extends AppCompatActivity {
    private Toolbar myToolbarrate;
    SmileRating smileRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratebar);
        getSupportActionBar().hide();
        myToolbarrate = findViewById(R.id.my_toolbarrate);
        smileRating =findViewById(R.id.smile_rating);
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(Ratebar.this, "Oh-Ho! Needs Improvement", Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(Ratebar.this, "Voila! Good", Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(Ratebar.this, "Cha-Ching", Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(Ratebar.this, "Meh! Okay-ish", Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(Ratebar.this, "Ew", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

        });
        smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 5000);
                Toast.makeText(Ratebar.this, "Ratings Loaded On Server "+ level, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

