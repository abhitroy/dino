package org.viciousanddelicious.viciousdelicious;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
        check();

        CircleMenu circlemenu = (CircleMenu) findViewById(R.id.circle_menu_supply);
        circlemenu.setMainMenu(Color.parseColor("#FF7043"),R.drawable.supplies,R.drawable.multiply)
                .addSubMenu(Color.parseColor("#81C784"),R.drawable.supply)
                .addSubMenu(Color.parseColor("#81D4FA"),R.drawable.crack)
                .addSubMenu(Color.parseColor("#FFF176"),R.drawable.question)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        final int x=i;

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (x == 0){
                                    if (isNetworkAvailable()) {
                                        startActivity(new Intent(supply.this, website_ebook.class));
                                    }
                                    else
                                        Toast.makeText(supply.this, "Network Not available", Toast.LENGTH_SHORT).show();
                            }
                                if (x==1) {
                                    if (isNetworkAvailable())
                                    startActivity(new Intent(supply.this, website_crack.class));
                                    else
                                        Toast.makeText(supply.this, "Network Not Available", Toast.LENGTH_SHORT).show();
                                }
                                if (x==2)
                                    if (isNetworkAvailable())
                                    startActivity(new Intent(supply.this,website.class));
                                else Toast.makeText(supply.this, "Network Not Available", Toast.LENGTH_SHORT).show();
                            }
                        },1000);


                    }
                });


    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    void test()
    {
        final  AlertDialog.Builder builder=new AlertDialog.Builder(supply.this);
        builder.setTitle("VICIOUS AND DELICIOUS");
        builder.setIcon(R.drawable.vnd);
        builder.setCancelable(false);
        builder.setMessage("Update your app in order to continue using it...")
                .setPositiveButton("UPDATE NOW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.viciousanddelicious.org/")));
                        AlertDialog alert=builder.create();
                        alert.show();
                    }
                });
        AlertDialog alert=builder.create();
        alert.show();
    }
    void check()
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("version");

// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = dataSnapshot.getValue(String.class);
                if(str.equals("1"))
                    System.out.println("heloo");
                else
                {
                    test();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
