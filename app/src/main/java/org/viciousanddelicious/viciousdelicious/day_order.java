package org.viciousanddelicious.viciousdelicious;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
    ArrayList<String> arr;
    String[] hor={"1","2","3","4","5","6","7","8"};
    ListView listView;
    String sub[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_order);
        SharedPreferences sf3=getSharedPreferences(preference_day, Context.MODE_PRIVATE);
        String d = sf3.getString(saveit_day,"");
        Toast.makeText(this, d, Toast.LENGTH_SHORT).show();
        arr = this.getIntent().getStringArrayListExtra(d);
        sub=arr.toArray(new String[0]);
        listView=(ListView)findViewById(R.id.list_of_hours);


        MyAdapter adapter = new MyAdapter(this,hor,sub);
        listView.setAdapter(adapter);








    }
    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String [] hor;
        String [] sub;
        MyAdapter(Context c,String[] hor1,String[] sub1)
        {
            super(c,R.layout.hour,R.id.thr, day_order.this.hor);
            this.context=c;
            this.hor=hor1;
            this.sub=sub1;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.hour,parent,false);
            TextView myTitle = (TextView) row.findViewById(R.id.thr);
            TextView myDesc = (TextView) row.findViewById(R.id.tsub);
            myTitle.setText(hor[position]);
            myDesc.setText(sub[position]);
            return row;
        }

    }



}



