package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    RadioGroup rdg;
    Button sub,clr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sub=findViewById(R.id.submit);
        clr=findViewById(R.id.clear);
        rdg=findViewById(R.id.groupradio);

        rdg.clearCheck();

        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rdg.clearCheck();
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=rdg.getCheckedRadioButtonId();
                RadioButton btn=rdg.findViewById(id);
                if(id==-1)
                {
                    Toast.makeText(MainActivity.this,"nothing slected",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(MainActivity.this,"selected :"+btn.getText(),Toast.LENGTH_LONG).show();
                Intent i=new Intent(MainActivity.this,yearwise.class);
                startActivity(i);
            }
        });

    }

}









package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class yearwise extends AppCompatActivity {
    CheckBox c1,c2,c3,c4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yearwise);
        c1=findViewById(R.id.checkBox);
        c2=findViewById(R.id.checkBox2);
        c3=findViewById(R.id.checkBox3);
        c4=findViewById(R.id.checkBox4);
    }
    public void Check(View v)
    {
        String msg="";
        if(c1.isChecked())
            msg=msg+c1.getText();
        if(c2.isChecked())
            msg=msg+c2.getText();
        if(c3.isChecked())
            msg=msg+c3.getText();
        if(c4.isChecked())
            msg=msg+c4.getText();

        Toast.makeText(yearwise.this,"selected"+msg,Toast.LENGTH_LONG).show();
    }
}
