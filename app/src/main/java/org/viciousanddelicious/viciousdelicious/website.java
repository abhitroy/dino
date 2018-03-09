package org.viciousanddelicious.viciousdelicious;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import static org.viciousanddelicious.viciousdelicious.branch_year.preference1;
import static org.viciousanddelicious.viciousdelicious.branch_year.saveit1;

public class website extends AppCompatActivity {
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loading= ProgressDialog.show(website.this,"Please wait","Hold on......",true,true);
        setContentView(R.layout.activity_website);
        WebView webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        SharedPreferences sf3=getSharedPreferences(preference1, Context.MODE_PRIVATE);
        String branch = sf3.getString(saveit1,"");
        if(branch.trim().equalsIgnoreCase("YEAR 1")) {
            webview.loadUrl("http://viciousanddelicious.org/page10.html");
        }
        if(branch.trim().equalsIgnoreCase("YEAR 2")) {
            webview.loadUrl("http://viciousanddelicious.org/page11.html");
        }
        if(branch.trim().equalsIgnoreCase("YEAR 3")) {
            webview.loadUrl("http://viciousanddelicious.org/page12.html");
        }
        if(branch.trim().equalsIgnoreCase("YEAR 4")) {
            webview.loadUrl("http://viciousanddelicious.org/page13.html");
        }

        Toast.makeText(this, branch, Toast.LENGTH_SHORT).show();
        System.out.println(branch);
        loading.dismiss();
    }
}
