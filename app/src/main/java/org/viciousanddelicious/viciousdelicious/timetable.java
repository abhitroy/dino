package org.viciousanddelicious.viciousdelicious;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class timetable extends AppCompatActivity {

    String ArrayName[]={"Day order 1","Day order 2","Day order 3","Day order 4","Day order 5"};
    public static final String preference_day="pref_day";
    public static final String saveit_day="savekey_day";
    public static final String intent_jugaad="intent_jugaad";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timetable);
        SharedPreferences sf_day=getSharedPreferences(preference_day, Context.MODE_PRIVATE);


     final   SharedPreferences.Editor editor2 = sf_day.edit();

            final ArrayList<String> arr0 = this.getIntent().getStringArrayListExtra(Integer.toString(3));
      final  ArrayList<String> arr1= this.getIntent().getStringArrayListExtra(Integer.toString(4));
       final ArrayList<String> arr2 = this.getIntent().getStringArrayListExtra(Integer.toString(5));
       final ArrayList<String> arr3 = this.getIntent().getStringArrayListExtra(Integer.toString(6));
       final ArrayList<String> arr4 = this.getIntent().getStringArrayListExtra(Integer.toString(7));



        CircleMenu circlemenu = (CircleMenu) findViewById(R.id.circle_menu_timetable);
        circlemenu.setMainMenu(Color.parseColor("#FFF176"),R.drawable.timetable,R.drawable.multiply)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.day1)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.day2)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.day3)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.day4)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.day5)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        final int x=i;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), day_order.class);

                        if(x==0)
                        {
                            editor2.putString(saveit_day,Integer.toString(x+3) );
                            editor2.commit();
                            intent.putStringArrayListExtra(Integer.toString(x+3), arr0);

                        }
                        if(x==1)
                        {

                            editor2.putString(saveit_day,Integer.toString(x+3) );
                            editor2.commit();
                            intent.putStringArrayListExtra(Integer.toString(x+3), arr1);
                        }
                        if(x==2)
                        {
                            editor2.putString(saveit_day,Integer.toString(x+3) );
                            editor2.commit();
                            intent.putStringArrayListExtra(Integer.toString(x+3), arr2);
                        }
                        if(x==3)
                        {
                            editor2.putString(saveit_day,Integer.toString(x+3) );
                            editor2.commit();
                            intent.putStringArrayListExtra(Integer.toString(x+3), arr3);
                        }
                        if(x==4)
                        {
                            editor2.putString(saveit_day,Integer.toString(x+3) );
                            editor2.commit();
                            intent.putStringArrayListExtra(Integer.toString(x+3), arr4);
                        }
                        startActivity(intent);
                            }
                        },1000);

                    }
                });

    }

}
