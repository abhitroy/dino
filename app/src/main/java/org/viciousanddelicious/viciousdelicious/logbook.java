package org.viciousanddelicious.viciousdelicious;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
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


    String ArrayName[]={"Time Table","Attendance","Grade"};
    ProgressDialog pd;
    String user="";
    String pass="";
    static Subject[] subjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);
        check();
        final SharedPreferences sf_pass=getSharedPreferences(preference_pass, Context.MODE_PRIVATE);
         pass = sf_pass.getString(saveit_pass,"");

        final SharedPreferences sf_user=getSharedPreferences(preference_user, Context.MODE_PRIVATE);
         user = sf_user.getString(saveit_user,"");
         if (user.equals("0")||pass.equals("0"))
         {
             startActivity(new Intent(logbook.this,evarsity.class));
         }


        final CircleMenu circlemenu = (CircleMenu) findViewById(R.id.circle_menu_log);
        circlemenu.setMainMenu(Color.parseColor("#FFF176"),R.drawable.log,R.drawable.multiply)
                .addSubMenu(Color.parseColor("#e57373"),R.drawable.attendence)
                .addSubMenu(Color.parseColor("#FFF176"),R.drawable.timetable)
                .addSubMenu(Color.parseColor("#81D4FA"),R.drawable.grade)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int i) {
                        final int x=i;

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (x == 0) {
                                    pd = ProgressDialog.show(logbook.this, "", "Please wait...", true);
                                    new fetcherya().execute();

                                }
                                if (x == 1) {
                                    pd = ProgressDialog.show(logbook.this, "", "Please wait...", true);
                                    new fetcheryt().execute();
                                }

                                if (x == 2) {
                                    pd = ProgressDialog.show(logbook.this, "", "Please wait...", true);
                                    new fetcherym().execute();
                                }
                            }
                        },1000);

                        //Toast.makeText(logbook.this, "Hello", Toast.LENGTH_SHORT).show();

                    }
                });


    }
    class fetcheryt extends AsyncTask<Void,Void,Void> {
        private Document doc=null;
        Element table=null;
        Elements days=null;
        Element row=null;
        Elements cols=null;
        Element table1=null;
        Elements days1=null;
        ArrayList<String> sub=new ArrayList<String>();
        ArrayList<String> code=new ArrayList<String>();
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
                table1=doc.select("table").get(1);
                days = table.select("tr");
                days1=table1.select("tr");
                code.add("-");
                sub.add("-");
                for (int i=2;i<days1.size();i++)
                {
                    Element x=days1.get(i);
                    Elements y=x.select("td");
                    code.add(y.get(0).text().toString());
                    sub.add(y.get(1).text().toString());


                }



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
            System.out.println(code);
            System.out.println(sub);
            String temp[]=sub.toArray(new String[0]);
            String temp1[]=code.toArray(new String[0]);
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
                    int index=2;
                    for (int l=0;l<temp1.length;l++) {
                        if (temp1[l].equals(s2)) {
                            index = l;
                            break;
                        }
                    }
                    hash.add(temp[index]);

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
            Toast.makeText(logbook.this, "No Record found ", Toast.LENGTH_SHORT).show();
            pd.dismiss();
        }
    });
}

        }

    }
    class fetcherya extends AsyncTask<Void,Void,Void> {
        private Document doc=null;
        Element table=null;
        Elements all=null;
        Element row=null;
        Elements cols=null;
        ArrayList<String> sub=new ArrayList<String>();
        ArrayList<String> code=new ArrayList<String>();
        ArrayList<Subject> value=new ArrayList<Subject>();
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

                doc = Jsoup.connect("http://evarsity.srmuniv.ac.in/srmswi/resource/StudentDetailsResources.jsp?resourceid=7").cookies(cookies).get();
                table = doc.select("table").get(0); //select the first table.





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
                Intent intent = new Intent(logbook.this, attendence.class);
                all = table.select("tr");
                subjects = new Subject[all.size()-4];

                for (int i1=0, j=3;i1<all.size()-4&&j<all.size()-1;i1++,j++)
                {


          //      for (int i1 = 0; i1 < all.size()-4; i1++)
           //     {
           //     for (int j=3;j<all.size()-1;j++) {
                    row=all.get(j);
                    cols=row.select("td");
                    int k=-1;

                    value.add(new Subject(cols.get(++k).text().toString(),cols.get(++k).text().toString(),cols.get(++k).text().toString(),cols.get(++k).text().toString(),cols.get(++k).text().toString(),cols.get(++k).text().toString(),cols.get(++k).text().toString(),cols.get(++k).text().toString()));


         /*           subjects[i1] = new Subject();
                    subjects[i1].setSubjectcode(cols.get(++k).text().toString());
                    System.out.println(cols.get(0).text().toString());
                    subjects[i1].setSubjectname(cols.get(++k).text().toString());
                    subjects[i1].setMaxhours(cols.get(++k).text().toString());
                    subjects[i1].setAtthours(cols.get(++k).text().toString());
                    subjects[i1].setAbsenthours(cols.get(++k).text().toString());
                    subjects[i1].setAverage(cols.get(++k).text().toString());
                    subjects[i1].setOD_ML(cols.get(++k).text().toString());
                    subjects[i1].setTotal(cols.get(++k).text().toString());  */


                }
                Element row1=all.get(all.size()-1);
                Elements cols1=row1.select("td");
                String jug=cols1.get(cols1.size()-1).text().toString();
                intent.putExtra("total",jug);





                pd.dismiss();
                intent.putExtra("attendance",value);
                System.out.println(".........................");
                startActivity(intent);
            }
            catch (Exception e)
            {
                System.out.println(e);
                runOnUiThread(new Runnable(){

                    @Override
                    public void run(){
                        Toast.makeText(logbook.this, "No Record found ", Toast.LENGTH_SHORT).show();

                        pd.dismiss();
                    }
                });
            }

        }

    }
    class fetcherym extends AsyncTask<Void,Void,Void> {
        private Document doc=null;
        Element table=null;
        Elements days=null;
        Element row=null;
        Elements cols=null;
        Element table1=null;
        Elements days1=null;

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

                doc = Jsoup.connect("http://evarsity.srmuniv.ac.in/srmswi/resource/StudentDetailsResources.jsp?resourceid=16").cookies(cookies).get();
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
                Intent intent = new Intent(getApplicationContext(), marks.class);
                String fx[]=new String[100];
                int v=0;
                for (int i=2;i<days.size();i++)
                {
                    row=days.get(i);
                    cols=row.select("td");
                    for(int j=0;j<cols.size();j++)
                    {
                        if(cols.size()==1) {
                            fx[v] ="<------"+cols.get(0).text().toString()+"------>";
                            v++;
                            break;
                        }
                        fx[v]=cols.get(j).text().toString();
                        v++;
                    }
                }
                for (int i=0;i<v;i++)
                    System.out.println(fx[i]);
                Bundle b=new Bundle();
                b.putStringArray("marks",fx);
                intent.putExtras(b);
                intent.putExtra("size",Integer.toString(v));



                startActivity(intent);
                pd.dismiss();
            }
            catch (Exception e)
            {
                runOnUiThread(new Runnable(){

                    @Override
                    public void run(){
                        Toast.makeText(logbook.this, "No Record found ", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                });
            }

        }

    }  void test()
    {
        final  AlertDialog.Builder builder=new AlertDialog.Builder(logbook.this);
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
