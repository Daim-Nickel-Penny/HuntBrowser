package com.example.hunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import androidx.appcompat.widget.Toolbar;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class SplashScreenActivity extends AppCompatActivity {
    private GifImageView gifImageView;
    private Toolbar myToolbars;
    private ProgressBar progressBarsplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        gifImageView =(GifImageView)findViewById(R.id.gifImageView);
        getSupportActionBar().hide();
        myToolbars = findViewById(R.id.my_toolbars);

        progressBarsplash= findViewById(R.id.myProgressBarsplash);
        progressBarsplash.setVisibility(progressBarsplash.VISIBLE);

        try{
            InputStream inputStream =getAssets().open("splashone.gif");
            byte[] bytes= IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }
        catch (IOException ex){

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                SplashScreenActivity.this.finish();
            }
        },3000);

    }
}
