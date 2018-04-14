package org.viciousanddelicious.viciousdelicious;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class marks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);
        Bundle b=this.getIntent().getExtras();
        String[] array=b.getStringArray("marks");
        String size=getIntent().getExtras().getString("size");
        System.out.println(size);
        String temp[]=new String[Integer.parseInt(size)];
        for (int i=0;i<Integer.parseInt(size);i++)
        {
            temp[i]=array[i];
        }
      ListView listView = (ListView)findViewById(R.id.number);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.two_line_list_item, android.R.id.text1,temp );

        listView.setAdapter(adapter);
    }
}
