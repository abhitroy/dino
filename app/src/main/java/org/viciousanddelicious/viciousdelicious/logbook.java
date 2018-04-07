package org.viciousanddelicious.viciousdelicious;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

import static org.viciousanddelicious.viciousdelicious.evarsity.preference_pass;
import static org.viciousanddelicious.viciousdelicious.evarsity.preference_user;
import static org.viciousanddelicious.viciousdelicious.evarsity.saveit_pass;
import static org.viciousanddelicious.viciousdelicious.evarsity.saveit_user;

public class logbook extends AppCompatActivity {

    private TextView txt;

    String ArrayName[]={"Time Table","Attendance","Grade"};
    ProgressDialog pd;
    String user="";
    String pass="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);
        final SharedPreferences sf_pass=getSharedPreferences(preference_pass, Context.MODE_PRIVATE);
         pass = sf_pass.getString(saveit_pass,"");

        final SharedPreferences sf_user=getSharedPreferences(preference_user, Context.MODE_PRIVATE);
         user = sf_user.getString(saveit_user,"");
        Toast.makeText(this, user, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, pass, Toast.LENGTH_SHORT).show();


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
                                if (x==0) {
                                    pd = ProgressDialog.show(logbook.this, "", "Please wait...", true);
                                    new fetcheryt().execute();
                                }
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
    class fetcheryt extends AsyncTask<Void,Void,Void> {
        private Document doc=null;
        Element table=null;
        Elements days=null;
        Element row=null;
        Elements cols=null;
        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                Connection.Response res = Jsoup
                        .connect("http://evarsity.srmuniv.ac.in/srmswi/usermanager/ParentLogin.jsp")
                        .data("txtRegNumber", "iamalsouser")
                        .data("txtPwd", "thanksandregards")
                        .data("txtSN", user)

                        .data("txtPD", pass)
                        .data("txtPA", "1")
                        .method(Connection.Method.GET)
                        .execute();

                //Get cookies
                Map<String, String> cookies = res.cookies();

                doc = Jsoup.connect("http://evarsity.srmuniv.ac.in/srmswi/resource/StudentDetailsResources.jsp?resourceid=5").cookies(cookies).get();
                table = doc.select("table").get(0); //select the first table.

                days = table.select("tr");


            } catch (Exception e) {
                System.err.println(e);

                runOnUiThread(new Runnable(){

                    @Override
                    public void run(){
                        Toast.makeText(logbook.this, "Either the server SUCKS or your internet ", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
try {
    Intent intent = new Intent(getApplicationContext(), timetable.class);
    for (int f = 3; f <= 7; f++) {
        row = days.get(f);
        cols = row.select("td");
        ArrayList<String> list1 = new ArrayList<>();
        int k = 0;

        if (cols != null) {


            for (int i = 1; i < cols.size(); i++) {
                String str = cols.get(i).text().toString();
                ArrayList<String> list = new ArrayList<String>();
                Set<String> hash = new HashSet<String>();

                int z = 0;
                String[] s = str.split(",");
                String s2;
                for (int j = 0; j < s.length; j++) {
                    s2 = s[j].trim();
                    hash.add(s2);
                }

                list.addAll(hash);
                Iterator itr = list.iterator();
                String jnl = "";
                while (itr.hasNext()) {
                    jnl = jnl + itr.next().toString() + "/ ";
                }
                list1.add(k, null);
                list1.set(k++, jnl.substring(0, jnl.length() - 2));  //hour value

            }

        }


        intent.putStringArrayListExtra(Integer.toString(f), list1);


    }
    startActivity(intent);
    pd.dismiss();
}
catch (Exception e)
{
    runOnUiThread(new Runnable(){

        @Override
        public void run(){
            Toast.makeText(logbook.this, "Either the server SUCKS or your internet ", Toast.LENGTH_SHORT).show();
            pd.dismiss();
        }
    });
}

        }

    }
}
