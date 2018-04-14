package org.viciousanddelicious.viciousdelicious;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
ProgressDialog pd;
    public static final String preference_user="pref_user";
    public static final String saveit_user="savekey_user";
    public static final String preference_pass="pref_pass";
    public static final String saveit_pass="savekey_pass";
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
                pd = ProgressDialog.show(evarsity.this, "", "Please wait...", true);

                check();

            }
        });
        ski.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sf_user=getSharedPreferences(preference_user, Context.MODE_PRIVATE);


                final   SharedPreferences.Editor editor2 = sf_user.edit();
                editor2.putString(saveit_user,"0" );
                editor2.commit();
                SharedPreferences sf_pass=getSharedPreferences(preference_pass, Context.MODE_PRIVATE);


                final   SharedPreferences.Editor editor3 = sf_pass.edit();
                editor3.putString(saveit_pass,"0" );
                editor3.commit();
                startActivity(new Intent(evarsity.this,branch_year.class));

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
                 document= Jsoup.connect("http://evarsity.srmuniv.ac.in/srmswi/usermanager/ParentLogin.jsp?txtRegNumber=iamalsouser&txtPwd=thanksandregards&txtSN="+txtRegno.getText().toString()+"&txtPD="+txtPwd.getText().toString()+"&txtPA=1").timeout(100000).get();

                   x=document.select("title").text().toString().trim();
                runOnUiThread(new Runnable(){

                    @Override
                    public void run(){
                        if (x.equals("::SRM Institute of Science and Technology - Student Web Interface - Beta::")) {
                            System.out.println("OK");
                            SharedPreferences sf_user=getSharedPreferences(preference_user, Context.MODE_PRIVATE);


                            final   SharedPreferences.Editor editor2 = sf_user.edit();
                            editor2.putString(saveit_user,txtRegno.getText().toString() );
                            editor2.commit();
                            SharedPreferences sf_pass=getSharedPreferences(preference_pass, Context.MODE_PRIVATE);


                            final   SharedPreferences.Editor editor3 = sf_pass.edit();
                            editor3.putString(saveit_pass,txtPwd.getText().toString() );
                            editor3.commit();
                            Toast.makeText(evarsity.this, "Login Completed", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                            startActivity(new Intent(evarsity.this,branch_year.class));

                        }
                        else {
                            Toast.makeText(evarsity.this, "Username or Password incorrect", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }


                    }
                });




            }
            catch (Exception e)
            {
                runOnUiThread(new Runnable(){

                    @Override
                    public void run(){
                        Toast.makeText(evarsity.this, "Either the server SUCKS or your internet ", Toast.LENGTH_SHORT).show();
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
