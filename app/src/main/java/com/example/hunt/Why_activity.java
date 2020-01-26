package com.example.hunt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.widget.TextView;

import com.felipecsl.gifimageview.library.GifImageView;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;

public class Why_activity extends AppCompatActivity {
    private Toolbar myToolbar;
    private GifImageView gifwhy;
    private String maintext;
    private TextView maintv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_why);
        getSupportActionBar().hide();
        maintv = findViewById(R.id.maintv);
         myToolbar = findViewById(R.id.my_toolbar);
         gifwhy = findViewById(R.id.gifwhy_act);
        try {
            InputStream inputStream =getAssets().open("whytwo.gif");
            byte[] bytes= IOUtils.toByteArray(inputStream);
            gifwhy.setBytes(bytes);
            gifwhy.startAnimation();
        }catch (IOException ex){
    }

        maintext="We don’t store your personal information. Ever.\n We don’t follow you around with ads." +
                "\n We don’t follow you around with ads." +
                "\n We don’t track you in or out of private browsing mode." +
                "\nSwitch to HUNT and take back your privacy!";
        maintv.setText(maintext);


}}


