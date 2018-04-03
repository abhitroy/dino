package org.viciousanddelicious.viciousdelicious;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class timetable extends AppCompatActivity {

    String ArrayName[]={"Day order 1","Day order 2","Day order 3","Day order 4","Day order 5"};
    public static final String preference_day="pref_day";
    public static final String saveit_day="savekey_day";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        SharedPreferences sf_day=getSharedPreferences(preference_day, Context.MODE_PRIVATE);


     final   SharedPreferences.Editor editor2 = sf_day.edit();



        CircleMenu circlemenu = (CircleMenu) findViewById(R.id.circle_menu_timetable);
        circlemenu.setMainMenu(Color.parseColor("#ffffff"),R.drawable.timetable,R.drawable.multiply)
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

                        if(x==0)
                        {
                            editor2.putString(saveit_day,Integer.toString(x+1) );
                            editor2.commit();

                        }
                        if(x==1)
                        {

                            editor2.putString(saveit_day,Integer.toString(x+1) );
                            editor2.commit();
                        }
                        if(x==2)
                        {
                            editor2.putString(saveit_day,Integer.toString(x+1) );
                            editor2.commit();
                        }
                        if(x==3)
                        {
                            editor2.putString(saveit_day,Integer.toString(x+1) );
                            editor2.commit();
                        }
                        if(x==4)
                        {
                            editor2.putString(saveit_day,Integer.toString(x+1) );
                            editor2.commit();
                        }
                        startActivity(new Intent(timetable.this,day_order.class));
                            }
                        },1000);

                    }
                });
    }
}
