package org.viciousanddelicious.viciousdelicious;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import static org.viciousanddelicious.viciousdelicious.branch_year.preference;

import static org.viciousanddelicious.viciousdelicious.branch_year.saveit;



public class website_crack extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_crack);
        final ProgressDialog pd = ProgressDialog.show(website_crack.this, "", "Please wait...", true);
        SharedPreferences sf3=getSharedPreferences(preference, Context.MODE_PRIVATE);
        String sem = sf3.getString(saveit,"");

        WebView webview = (WebView) findViewById(R.id.webview1);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), description, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                pd.show();
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();



            }
        });

        if(sem.trim().equalsIgnoreCase("SEMESTER 1")) {
            webview.loadUrl("http://viciousanddelicious.org/page7.html");
        }
        if(sem.trim().equalsIgnoreCase("SEMESTER 2")) {
            webview.loadUrl("http://viciousanddelicious.org/page8.html");
        }
        if(sem.trim().equalsIgnoreCase("SEMESTER 3")) {
            webview.loadUrl("http://viciousanddelicious.org/page9.html");
        }
        if(sem.trim().equalsIgnoreCase("SEMESTER 4")) {
            webview.loadUrl("http://viciousanddelicious.org/page6.html");
        }
        if(sem.trim().equalsIgnoreCase("SEMESTER 5")) {
            webview.loadUrl("http://viciousanddelicious.org/page6.html");
        }
        if(sem.trim().equalsIgnoreCase("SEMESTER 6")) {
            webview.loadUrl("http://viciousanddelicious.org/page6.html");
        }
        if(sem.trim().equalsIgnoreCase("SEMESTER 7")) {
            webview.loadUrl("http://viciousanddelicious.org/page6.html");
        }
        if(sem.trim().equalsIgnoreCase("SEMESTER 8")) {
            webview.loadUrl("http://viciousanddelicious.org/page6.html");
        }
    }
}
