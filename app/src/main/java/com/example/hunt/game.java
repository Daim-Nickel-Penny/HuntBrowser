package com.example.hunt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class game extends AppCompatActivity {
    public String urlg;

    public WebView gamewebView;
    private Toolbar myToolbarg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        myToolbarg = findViewById(R.id.my_toolbarg);
        getSupportActionBar().hide();
        urlg="http://paper-io.com/";
        Toast.makeText(this, "Game will soon load...", Toast.LENGTH_SHORT).show();
        gamewebView=findViewById(R.id.gwebView);
        gamewebView.loadUrl(urlg);
        gamewebView.getSettings().setJavaScriptEnabled(true);
        gamewebView.setWebViewClient(new WebViewClient());
    }
}
