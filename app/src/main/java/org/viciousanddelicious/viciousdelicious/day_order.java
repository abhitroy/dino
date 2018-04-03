package org.viciousanddelicious.viciousdelicious;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static org.viciousanddelicious.viciousdelicious.timetable.preference_day;
import static org.viciousanddelicious.viciousdelicious.timetable.saveit_day;

public class day_order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_order);
        SharedPreferences sf3=getSharedPreferences(preference_day, Context.MODE_PRIVATE);
        String book_cover = sf3.getString(saveit_day,"");
        Toast.makeText(this, book_cover, Toast.LENGTH_SHORT).show();
    }
}
