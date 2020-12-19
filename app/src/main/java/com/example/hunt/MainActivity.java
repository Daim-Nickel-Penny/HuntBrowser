package com.example.hunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mNavDrawer;
    ProgressBar superProgressBar;
    ImageView superImageView;
    WebView superWebView;
    SwipeRefreshLayout superSwipeLayout;
    String myCurrentUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        superProgressBar = findViewById(R.id.myProgressBar);
        superImageView = findViewById(R.id.myImageView);
        superWebView = findViewById(R.id.myWebView);
        superSwipeLayout = findViewById(R.id.myswipeLayout);


        mNavDrawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mNavDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );

        mNavDrawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Homefrag())
                    .commit();
        }
        navigationView.setCheckedItem(R.id.nav_home);

        superProgressBar.setMax(100);
        superWebView.loadUrl("https://duckduckgo.com/");
        superWebView.getSettings().setJavaScriptEnabled(true);
        superWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                superSwipeLayout.setRefreshing(false);
                super.onPageFinished(view, url);
                myCurrentUrl = url;
            }
        });
        superWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                superProgressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                superImageView.setImageBitmap(icon);
            }
        });

        superWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                DownloadManager.Request myrequest = new DownloadManager.Request(Uri.parse(s));
                myrequest.allowScanningByMediaScanner();
                myrequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                
                
                DownloadManager myManager= (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                myManager.enqueue(myrequest);
                Toast.makeText(MainActivity.this, "Ta-Da File is downloading", Toast.LENGTH_SHORT).show();
            }
        });

        superSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                superWebView.reload();
            }
        });



    }


    @Override
    public void onBackPressed() {

         if(superWebView.canGoBack()){
             superWebView.goBack();
         }
         else{
             finish();
        }


        if (mNavDrawer.isDrawerOpen(GravityCompat.START))
            mNavDrawer.closeDrawer(GravityCompat.START);
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_home:
                Toast.makeText(this, "Already at home.", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Homefrag())
                        .commit();
                break;

            case R.id.nav_fav:
                Toast.makeText(this, "Favourites will be uploaded soon", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Favfrag())
                        .commit();
                break;

            case R.id.nav_about:
                Intent intent_why = new Intent(MainActivity.this, Why_activity.class);
                startActivity(intent_why);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Aboutfrag())
                        .commit();
                break;

            case R.id.nav_share:
                Intent shareIntent =new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,myCurrentUrl);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"The title");
                startActivity(Intent.createChooser(shareIntent,"Share the URL you selected"));
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Sharefrag())
                        .commit();
                break;

            case R.id.nav_rate:
                Intent intent_rate = new Intent(MainActivity.this, Ratebar.class);
                startActivity(intent_rate);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Ratefrag())
                        .commit();
                break;
            case R.id.nav_exit:
                Toast.makeText(this, "Exitting", Toast.LENGTH_SHORT).show();
                finish();
                break;

        }
        mNavDrawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drop_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.it_why) {
            Intent intent_why = new Intent(MainActivity.this, Why_activity.class);
            startActivity(intent_why);
            return false;
        }

        switch (item.getItemId()) {
            case R.id.it_exit:
                Toast.makeText(this, "See Ya", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.it_save:
                Toast.makeText(this, "Yeah!", Toast.LENGTH_SHORT).show();
                Intent intent_game = new Intent(MainActivity.this, game.class);
                startActivity(intent_game);
                break;
            case R.id.it_mail:
                Toast.makeText(this, "Mailing", Toast.LENGTH_SHORT).show();
                sendEmail();
                break;

            case R.id.it_me:
                Toast.makeText(getApplicationContext(), "Hello!!!", Toast.LENGTH_LONG).show();
                break;

            case R.id.it_back:
                onBackPressed();
                break;
            case R.id.it_refresh:
                superWebView.reload();
                break;
            case R.id.it_forward:
                onForwardPressed();
                break;
            case R.id.it_share:
                Intent shareIntent =new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,myCurrentUrl);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"The title");
                startActivity(Intent.createChooser(shareIntent,"Share the URL you selected"));
                break;
            case R.id.it_rate:
                Intent intent_rate = new Intent(MainActivity.this, Ratebar.class);
                startActivity(intent_rate);
                return false;
            case R.id.it_social:
                Intent intent_social= new Intent(MainActivity.this, Social.class);
                startActivity(intent_social);
                break;

        }
        return super.onOptionsItemSelected(item);


    }
    private void onForwardPressed(){
        if(superWebView.canGoForward()){
            superWebView.goForward();
        }else {
            Toast.makeText(this, "Can't go Further", Toast.LENGTH_SHORT).show();
        }

    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi There.\n Thank You for using HUNT- Privacy Simplified" +
                "\n Having issues or wanna to ping us! Put your message here:\n");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail"));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
