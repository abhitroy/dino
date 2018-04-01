package org.viciousanddelicious.viciousdelicious;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class supply extends AppCompatActivity {

    String ArrayName[]={"Study Material","Question Papers","Cracks"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply);

        CircleMenu circlemenu = (CircleMenu) findViewById(R.id.circle_menu_supply);
        circlemenu.setMainMenu(Color.parseColor("#FFF176"),R.drawable.supplies,R.drawable.multiply)
                .addSubMenu(Color.parseColor("#81C784"),R.drawable.supply)
                .addSubMenu(Color.parseColor("#81D4FA"),R.drawable.question)
                .addSubMenu(Color.parseColor("#e57373"),R.drawable.crack)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        Toast.makeText(supply.this, "Hello", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
