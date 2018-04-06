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
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class timetable extends AppCompatActivity {

    String ArrayName[]={"Day order 1","Day order 2","Day order 3","Day order 4","Day order 5"};
    public static final String preference_day="pref_day";
    public static final String saveit_day="savekey_day";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pd = ProgressDialog.show(timetable.this, "", "Please wait...", true);
        setContentView(R.layout.activity_timetable);
        SharedPreferences sf_day=getSharedPreferences(preference_day, Context.MODE_PRIVATE);


     final   SharedPreferences.Editor editor2 = sf_day.edit();



        CircleMenu circlemenu = (CircleMenu) findViewById(R.id.circle_menu_timetable);
        circlemenu.setMainMenu(Color.parseColor("#ffffff"),R.drawable.timetable,R.drawable.multiply)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.day1)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.day2)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.day3)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.day4)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.day5)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        final int x=i;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                        if(x==0)
                        {
                            editor2.putString(saveit_day,Integer.toString(x+1) );
                            editor2.commit();

                        }
                        if(x==1)
                        {

                            editor2.putString(saveit_day,Integer.toString(x+1) );
                            editor2.commit();
                        }
                        if(x==2)
                        {
                            editor2.putString(saveit_day,Integer.toString(x+1) );
                            editor2.commit();
                        }
                        if(x==3)
                        {
                            editor2.putString(saveit_day,Integer.toString(x+1) );
                            editor2.commit();
                        }
                        if(x==4)
                        {
                            editor2.putString(saveit_day,Integer.toString(x+1) );
                            editor2.commit();
                        }
                        startActivity(new Intent(timetable.this,day_order.class));
                            }
                        },1000);

                    }
                });
        new fetchery().execute();
    }
    class fetchery extends AsyncTask<Void,Void,Void> {
        private Document doc=null;
        Element table=null;
        Elements temp=null;
        Element row=null;
        Elements cols=null;
        @Override
        protected Void doInBackground(Void... arg0) {
     /*   try {
            Connection.Response res = Jsoup
                    .connect("http://evarsity.srmuniv.ac.in/srmswi/usermanager/ParentLogin.jsp")
                    .data("txtRegNumber", "iamalsouser")
                    .data("txtPwd", "thanksandregards")
                    .data("txtSN", "PRA1611003040056")

                    .data("txtPD", "07061998")
                    .data("txtPA", "1")
                    .method(Connection.Method.GET)
                    .execute();

            //Get cookies
            Map<String, String> cookies = res.cookies();

            doc = Jsoup.connect("http://evarsity.srmuniv.ac.in/srmswi/resource/StudentDetailsResources.jsp?resourceid=5").cookies(cookies).get();
            table = doc.select("table").get(0); //select the first table.

            temp = table.select("tr");

            row = temp.get(1);
            cols = row.select("td");
        } catch (Exception e) {
            System.err.println(e);

            startActivity(new Intent(day_order.this,timetable.class));

        }*/
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            String str="15CS204J,15CS204J 15CS205,15CS205";
            List<String> list=new ArrayList<String>();

      /*      if (cols!=null) {


                for (int i = 0; i < cols.size(); i++)
                {
                    list.add(cols.get(i).text());
                System.out.println(cols.get(i).text());
                }

                System.out.println(list);
            }*/


      /*
          import java.util.*;
public class HelloWorld{

     public static void main(String []args){

         String str="15CS204J, 15CS203, 15CS205, 15CS205";
         Set<String> hash = new HashSet<String>();
          List<String> list=new ArrayList<String>();
          int k=0;
         String[] s=str.split(",");
         String s2;
         for(int i=0;i<s.length;i++)
         {
             s2=s[i].trim();
             hash.add(s2);
         }
         list.addAll(hash);
        System.out.println(list);
     }
} 
       */

            pd.dismiss();

        }

    }
}
