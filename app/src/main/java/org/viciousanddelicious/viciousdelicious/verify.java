package org.viciousanddelicious.viciousdelicious;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class verify extends AppCompatActivity {
    Button verifyb;
    EditText verifye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        verifyb=(Button)findViewById(R.id.verifyb);
        verifye=(EditText) findViewById(R.id.verifye);
        verifyb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verifye.getText().toString().equals("windows8"))
                    startActivity(new Intent(verify.this,contact.class));
                else
                    Toast.makeText(verify.this, "Better luck next time", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
