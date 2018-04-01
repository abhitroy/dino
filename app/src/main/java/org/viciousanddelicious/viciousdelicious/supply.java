package org.viciousanddelicious.viciousdelicious;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import static org.viciousanddelicious.viciousdelicious.branch_year.preference;
import static org.viciousanddelicious.viciousdelicious.branch_year.saveit;

public class supply extends AppCompatActivity {

    private TextView txt;

    String ArrayName[]={"Study Material","Crack","Question Paper"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply);

        CircleMenu circlemenu = (CircleMenu) findViewById(R.id.circle_menu_supply);
        circlemenu.setMainMenu(Color.parseColor("#FFF176"),R.drawable.supplies,R.drawable.multiply)
                .addSubMenu(Color.parseColor("#81C784"),R.drawable.supply)
                .addSubMenu(Color.parseColor("#81D4FA"),R.drawable.crack)
                .addSubMenu(Color.parseColor("#e57373"),R.drawable.question)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        final int x=i;

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (x==0)
                                    startActivity(new Intent(supply.this,website_ebook.class));
                                if (x==1)
                                    startActivity(new Intent(supply.this,website_crack.class));
                                if (x==2)
                                    startActivity(new Intent(supply.this,website.class));
                            }
                        },1000);


                    }
                });


    }
}
