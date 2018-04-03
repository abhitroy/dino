package org.viciousanddelicious.viciousdelicious;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class logbook extends AppCompatActivity {

    private TextView txt;

    String ArrayName[]={"Time Table","Attendance","Grade"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);
        final CircleMenu circlemenu = (CircleMenu) findViewById(R.id.circle_menu_log);
        circlemenu.setMainMenu(Color.parseColor("#81C784"),R.drawable.log,R.drawable.multiply)
                .addSubMenu(Color.parseColor("#81D4FA"),R.drawable.timetable)
                .addSubMenu(Color.parseColor("#FFF176"),R.drawable.attendence)
                .addSubMenu(Color.parseColor("#e57373"),R.drawable.grade)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int i) {
                        final int x=i;

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (x==0)
                                    startActivity(new Intent(logbook.this,timetable.class));
                                if (x==1)
                                    startActivity(new Intent(logbook.this,supply.class));
                                if (x==2)
                                    startActivity(new Intent(logbook.this,Profile.class));
                            }
                        },1000);

                        //Toast.makeText(logbook.this, "Hello", Toast.LENGTH_SHORT).show();

                    }
                });

        txt = (TextView) findViewById(R.id.pref_log);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(logbook.this, branch_year.class));
            }
        });

    }
}
