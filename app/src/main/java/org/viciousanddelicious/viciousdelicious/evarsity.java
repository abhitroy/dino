package org.viciousanddelicious.viciousdelicious;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class evarsity extends AppCompatActivity {

EditText txtRegno;
EditText txtPwd;
Button loge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evarsity);
        txtRegno = (EditText) findViewById(R.id.reg);
        txtPwd = (EditText) findViewById(R.id.pass);
        loge=(Button)findViewById(R.id.loge);
        loge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
