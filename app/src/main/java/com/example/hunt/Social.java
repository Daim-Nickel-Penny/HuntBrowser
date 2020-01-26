package com.example.hunt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Social extends AppCompatActivity {
    private Toolbar myToolbars;
    String t,i,s;
    ImageView twitter, insta, share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        getSupportActionBar().hide();
        myToolbars = findViewById(R.id.my_toolbars);
        twitter= findViewById(R.id.im_twitter);
        insta= findViewById(R.id.im_insta);
        share= findViewById(R.id.im_share);
        s="https://www.instagram.com/i_am_daim_/";
        i="https://www.instagram.com/i_am_daim_/";
        t="https://twitter.com/daimk29?lang=en";
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse(i);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);

            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent =new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,s);
                startActivity(Intent.createChooser(shareIntent,"Share the URL you selected"));
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent =new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,i);
                startActivity(Intent.createChooser(shareIntent,"Share the URL you selected"));
            }
        });
    }

}
