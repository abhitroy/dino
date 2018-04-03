package org.viciousanddelicious.viciousdelicious;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class timetable extends AppCompatActivity {

    String ArrayName[]={"Day order 1","Day order 2","Day order 3","Day order 4","Day order 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

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
                        Toast.makeText(timetable.this, "Hello", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
