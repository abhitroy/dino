package org.viciousanddelicious.viciousdelicious;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.viciousanddelicious.viciousdelicious.timetable.intent_jugaad;
import static org.viciousanddelicious.viciousdelicious.timetable.preference_day;
import static org.viciousanddelicious.viciousdelicious.timetable.saveit_day;

public class day_order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_order);
        SharedPreferences sf3=getSharedPreferences(preference_day, Context.MODE_PRIVATE);
        String d = sf3.getString(saveit_day,"");
        Toast.makeText(this, d, Toast.LENGTH_SHORT).show();
            ArrayList<String> arr = this.getIntent().getStringArrayListExtra(d);

            System.out.println(arr.get(0));








    }



}



