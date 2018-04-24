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

import static org.viciousanddelicious.viciousdelicious.branch_year.preference1;
import static org.viciousanddelicious.viciousdelicious.branch_year.saveit1;

public class website_ebook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_ebook);
        final ProgressDialog pd = ProgressDialog.show(website_ebook.this, "", "Please wait...", true);
        SharedPreferences sf3=getSharedPreferences(preference1, Context.MODE_PRIVATE);
        String branch = sf3.getString(saveit1,"");
        WebView webview = (WebView) findViewById(R.id.webview2);
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
        if(branch.trim().equalsIgnoreCase("YEAR 1")) {
            webview.loadUrl("http://vicious.byethost24.com/pagey91.html");
        }
        if(branch.trim().equalsIgnoreCase("YEAR 2")) {
            webview.loadUrl("http://vicious.byethost24.com/pagey92.html");
        }
        if(branch.trim().equalsIgnoreCase("YEAR 3")) {
            webview.loadUrl("http://vicious.byethost24.com/pagey93.html");
        }
        if(branch.trim().equalsIgnoreCase("YEAR 4")) {
            webview.loadUrl("http://vicious.byethost24.com/pagey94.html");
        }
    }
}
