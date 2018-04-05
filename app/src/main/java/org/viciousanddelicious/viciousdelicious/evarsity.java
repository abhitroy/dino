package org.viciousanddelicious.viciousdelicious;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class evarsity extends AppCompatActivity {

EditText txtRegno;
EditText txtPwd;
TextView loge;
    TextView ski;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evarsity);
        txtRegno = (EditText) findViewById(R.id.reg);
        txtPwd = (EditText) findViewById(R.id.pass);
        loge=(TextView) findViewById(R.id.loge);
        ski=(TextView) findViewById(R.id.skip);
        loge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();

            }
        });
        ski.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(evarsity.this,MainActivity.class));

            }
        });


    }
    void check()
    {
        new fetcherx().execute();
    }
    class fetcherx extends AsyncTask<Void,Void,Void> {
        private Document document=null;
        String x="";

        @Override
        protected Void doInBackground(Void... arg0) {
            try{
                 document= Jsoup.connect("http://evarsity.srmuniv.ac.in/srmswi/usermanager/ParentLogin.jsp?txtRegNumber=iamalsouser&txtPwd=thanksandregards&txtSN=P"+txtRegno.getText().toString()+"&txtPD="+txtPwd.getText().toString()+"&txtPA=1").timeout(10000).get();

                   x=document.select("title").text().toString().trim();
                runOnUiThread(new Runnable(){

                    @Override
                    public void run(){
                        if (x.equals("::SRM Institute of Science and Technology - Student Web Interface - Beta::")) {
                            System.out.println("OK");
                            Toast.makeText(evarsity.this, "kaam set", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(evarsity.this, "Lag gaye", Toast.LENGTH_SHORT).show();


                    }
                });




            }
            catch (Exception e)
            {
                runOnUiThread(new Runnable(){

                    @Override
                    public void run(){
                        Toast.makeText(evarsity.this, "Server", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {



        }

    }

}
