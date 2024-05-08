package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display=findViewById(R.id.display);
    }
    public void parsexml(View v)
    {
        try
        {
            InputStream is=getAssets().open("city.xml");
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document d=db.parse(is);
            StringBuilder sb=new StringBuilder();
            sb.append("xml data");
            NodeList nl=d.getElementsByTagName("place");
            for(int i=0;i<nl.getLength();i++)
            {
                Node n=nl.item(i);
                if(n.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element e=(Element) n;
                    sb.append("name").append(getelement("name",e));
                }
            }
            display.setText(sb.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"error reading xml",Toast.LENGTH_LONG).show();
        }
    }
    private String getelement(String tag,Element e)
    {
        return e.getElementsByTagName(tag).item(0).getChildNodes().item(0).getNodeValue();
    }
    public void parsejson(View v)
    {
        try
        {
            InputStream is=getAssets().open("city.json");
            int size=is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            StringBuilder sb=new StringBuilder();
            String json=new String(buffer, StandardCharsets.UTF_8);
            JSONArray ja=new JSONArray(json);
            for(int i=0;i<ja.length();i++)
            {
                JSONObject jo=ja.getJSONObject(i);
                sb.append("name").append(jo.getString("name"));
            }
            display.setText(sb.toString());
            is.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
