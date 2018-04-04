package org.viciousanddelicious.viciousdelicious;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.viciousanddelicious.viciousdelicious.timetable.preference_day;
import static org.viciousanddelicious.viciousdelicious.timetable.saveit_day;

public class day_order extends AppCompatActivity {
     ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         pd = ProgressDialog.show(day_order.this, "", "Please wait...", true);
        setContentView(R.layout.activity_day_order);
        SharedPreferences sf3=getSharedPreferences(preference_day, Context.MODE_PRIVATE);
        String book_cover = sf3.getString(saveit_day,"");
        Toast.makeText(this, book_cover, Toast.LENGTH_SHORT).show();
        System.out.println("hello");
        new fetcher().execute();



    }


class fetcher extends AsyncTask<Void,Void,Void>{
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

            pd.dismiss();

        }

    }
}



