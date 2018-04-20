package org.viciousanddelicious.viciousdelicious;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import me.itangqi.waveloadingview.WaveLoadingView;

public class attendence extends AppCompatActivity {
   WaveLoadingView wave;
    TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
/*        final Subject[] arrayOfSubject = logbook.subjects;
        int i = 0;
        while (i < arrayOfSubject.length) {
            System.out.println(arrayOfSubject[i].subjectname);
            i++;
        }
        */
         ArrayList<Subject> data = (ArrayList<Subject>) getIntent().getSerializableExtra("attendance");

        String t=getIntent().getExtras().getString("total");
        GridView list = (GridView) findViewById(R.id.list_of_attendence);


        ArrayAdapter adapter = new CustomAdapter(this, R.layout.sub_attend, data);

        list.setAdapter(adapter);

        wave=(WaveLoadingView)findViewById(R.id.attend_progress);
        wave.setProgressValue(0);
        wave.setProgressValue((int)Double.parseDouble(t));
        total=(TextView)findViewById(R.id.total_attend);
        total.setText("Total Percentage: "+t);


    }
    int alfinder(int t,int a)
    {
        int k=0;
        for (k=0;100*(k+(t-a))/(t+k)<75;k++){}
        return k;
    }

    public class CustomAdapter extends ArrayAdapter<Subject> {

        Context context;
        int layoutResourceId;
        ArrayList<Subject> data = null;

        public CustomAdapter(Context context, int resource, ArrayList<Subject> objects) {
            super(context, resource, objects);
            this.layoutResourceId = resource;
            this.context = context;
            this.data = (ArrayList) objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Subject[] arrayOfSubject = logbook.subjects;


            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            View row = inflater.inflate(R.layout.sub_attend, parent, false);
            TextView title=(TextView)row.findViewById(R.id.title);
            TextView total=(TextView)row.findViewById(R.id.total);
            TextView max=(TextView)row.findViewById(R.id.maxhr);
            TextView atthr=(TextView)row.findViewById(R.id.atthr);
            TextView abshr=(TextView)row.findViewById(R.id.abshr);
            TextView avgp=(TextView)row.findViewById(R.id.avg_percent);
            TextView odp=(TextView)row.findViewById(R.id.od_percent);
            TextView subcode=(TextView)row.findViewById(R.id.subcode);
            TextView alert=(TextView)row.findViewById(R.id.alert);
            title.setText("Subject Name:\n"+data.get(position).subjectname);
            total.setText("Tot. Percentage: "+data.get(position).total);
            max.setText("Total Hours:"+data.get(position).maxhours);
            atthr.setText("Attended Hours: "+data.get(position).atthours);
            abshr.setText("Absent Hours: "+data.get(position).absenthours);
            avgp.setText("Avg. Percentage: "+data.get(position).average);
            odp.setText("OD/ML Percentage: "+data.get(position).OD_ML);
            subcode.setText("Subject Code: "+data.get(position).subjectcode);
           int val=alfinder(Integer.parseInt(data.get(position).maxhours),Integer.parseInt(data.get(position).absenthours));
           if(val!=0)
               alert.setText("You need "+Integer.toString(val)+" more classes for 75%");
           else
               alert.setText("");
            RelativeLayout lin=(RelativeLayout)row.findViewById(R.id.card_sub);

            if (Double.parseDouble(data.get(position).total)<65)
            {
                lin.setBackgroundResource(R.drawable.hour_card_less);
            }
            if (Double.parseDouble(data.get(position).total)>=65&&Double.parseDouble(data.get(position).total)<=75)
            {
                lin.setBackgroundResource(R.drawable.hour_card_avg);
            }
            if (Double.parseDouble(data.get(position).total)>75)
            {
                lin.setBackgroundResource(R.drawable.hour_card_ok);
            }


            return row;
        }








    }
}
