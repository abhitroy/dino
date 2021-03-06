package org.viciousanddelicious.viciousdelicious;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static org.viciousanddelicious.viciousdelicious.branch_year.preference;
import static org.viciousanddelicious.viciousdelicious.branch_year.preference1;
import static org.viciousanddelicious.viciousdelicious.branch_year.saveit;
import static org.viciousanddelicious.viciousdelicious.branch_year.saveit1;

public class splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            Window window = getWindow();

            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                // close this activity
                firebaseAuth = FirebaseAuth.getInstance();






                if(firebaseAuth.getCurrentUser()!=null) {

                    SharedPreferences sf = getSharedPreferences(preference, Context.MODE_PRIVATE);
                    SharedPreferences sf1 = getSharedPreferences(preference1, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sf.edit();
                    SharedPreferences.Editor editor1 = sf1.edit();


                    String x = sf.getString(saveit, "");
                    String y = sf1.getString(saveit1, "");
                    if (x.equals("SELECT YOUR DEPARTMENT") || y.equals("SELECT YOUR YEAR")) {
                        startActivity(new Intent(splash.this, evarsity.class));
                    }
                    else
                        startActivity(new Intent(splash.this,MainActivity.class));

                }
                else
                {
                    Intent x=new Intent(splash.this,option.class);
                    startActivity(x);
                }

                finish();
            }
        }, SPLASH_TIME_OUT);
    }


}

