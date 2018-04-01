package org.viciousanddelicious.viciousdelicious;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class logbook extends AppCompatActivity {

    String ArrayName[]={"Time Table","Attendance","Grade"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);

        CircleMenu circlemenu = (CircleMenu) findViewById(R.id.circle_menu_log);
        circlemenu.setMainMenu(Color.parseColor("#81C784"),R.drawable.log,R.drawable.multiply)
                .addSubMenu(Color.parseColor("#81D4FA"),R.drawable.timetable)
                .addSubMenu(Color.parseColor("#FFF176"),R.drawable.attendence)
                .addSubMenu(Color.parseColor("#e57373"),R.drawable.grade)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        Toast.makeText(logbook.this, "Hello", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
