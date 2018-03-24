package com.example.sunun.sunun3application;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailpageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent itn = getIntent();
        int recID = itn.getIntExtra("recID", -1);
        // Toast.makeText(this,String.valueOf(recID),Toast.LENGTH_SHORT).show();

        //String urlStr = BASE_URL + "getrecord.php?id=" + String.valueOf(recID);

        String[] webs = new String[] {
                "http://itpart.com/android/json/img/ro02.jpg",
                "http://itpart.com/android/json/img/filter01.jpg",
                "http://www.treat.co.th/product/reverse-osmosis-ro/",
                "http://www.treat.co.th/product/ozone/",
                "http://bbc.com",
                "http://google.com",
                "http://cnn.com",
                "http://hotmail.com" };

        String urlStr = webs[recID];

        WebView wv = (WebView)findViewById(R.id.webView);
        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setDefaultFontSize(18);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.loadUrl(urlStr);
    }

}
