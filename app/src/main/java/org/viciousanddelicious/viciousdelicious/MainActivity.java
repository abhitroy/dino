package org.viciousanddelicious.viciousdelicious;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import static org.viciousanddelicious.viciousdelicious.evarsity.preference_pass;
import static org.viciousanddelicious.viciousdelicious.evarsity.preference_user;
import static org.viciousanddelicious.viciousdelicious.evarsity.saveit_pass;
import static org.viciousanddelicious.viciousdelicious.evarsity.saveit_user;


public class MainActivity extends AppCompatActivity {

    String ArrayName[]={"Log Book","Supply","Explore"};

    private LinearLayout crack;
    private LinearLayout qpaper;
    private LinearLayout book;
    private Button set;
    String x;
    String y;
    private TextView profile;
    public static  String ebook_video="none";
    public static final String preference2="pref2";
    public static final String saveit2="savekey2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       check();



        final CircleMenu circlemenu = (CircleMenu) findViewById(R.id.circle_menu);
        circlemenu.setMainMenu(Color.parseColor("#ffffff"),R.drawable.vnd,R.drawable.multiply)
                .addSubMenu(Color.parseColor("#e57373"),R.drawable.log)
                .addSubMenu(Color.parseColor("#81C784"),R.drawable.supplies)
                .addSubMenu(Color.parseColor("#FFF176"),R.drawable.explore)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        final int x=i;

                            new Handler().postDelayed(new Runnable() {
                                                          @Override
                                                          public void run() {
                                                              if (x==0) {
                                                                  final SharedPreferences sf_pass=getSharedPreferences(preference_pass, Context.MODE_PRIVATE);
                                                                  String pass = sf_pass.getString(saveit_pass,"");

                                                                  final SharedPreferences sf_user=getSharedPreferences(preference_user, Context.MODE_PRIVATE);
                                                                  String user = sf_user.getString(saveit_user,"");
                                                                  if (user.equals("0")||pass.equals("0"))
                                                                  {
                                                                      startActivity(new Intent(MainActivity.this,evarsity.class));
                                                                  }
                                                                  else {
                                                                      Intent i = new Intent(MainActivity.this, logbook.class);
                                                                      i.putExtra("ch", "valuesssss");
                                                                      startActivity(i);
                                                                  }
                                                              }
                                                              if (x==1) {
                                                                  SharedPreferences sf3=getSharedPreferences(preference, Context.MODE_PRIVATE);
                                                                  String sem = sf3.getString(saveit,"");
                                                                  if (sem.equalsIgnoreCase("SELECT YOUR SEMESTER"))
                                                                      startActivity(new Intent(MainActivity.this,branch_year.class));
                                                                  else
                                                                  startActivity(new Intent(MainActivity.this, supply.class));
                                                              }
                                                              if (x==2)
                                                                  startActivity(new Intent(MainActivity.this,Profile.class));
                                                          }
                                                      },1000);



                    }
                });

        set = (Button) findViewById(R.id.pref_main);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, evarsity.class));
            }
        });



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }
    void test()
    {
      final  AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("VICIOUS AND DELICIOUS");
        builder.setIcon(R.drawable.vnd);
        builder.setCancelable(false);
        builder.setMessage("Update your app in order to continue using it...")
                .setPositiveButton("UPDATE NOW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.viciousanddelicious.org/")));
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

