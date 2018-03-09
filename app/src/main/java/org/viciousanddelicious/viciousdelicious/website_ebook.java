package org.viciousanddelicious.viciousdelicious;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class website_ebook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_ebook);
        WebView webview = (WebView) findViewById(R.id.webview2);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://viciousanddelicious.org/page6.html");
    }
}
