package com.example.wall;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    WallpaperManager wpm;
    Timer mytimer;
    Drawable drawable;
    Button wall;
    int prev=1;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mytimer=new Timer();
        wpm=WallpaperManager.getInstance(this);
        wall=findViewById(R.id.button1);
        wall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWallpaper();
            }
        });
    }
    private void setWallpaper()
    {
        mytimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(prev==1)
                {
                    drawable=getResources().getDrawable(R.drawable.i1);
                    prev=2;
                }
                else if(prev==2)
                {
                    drawable=getResources().getDrawable(R.drawable.i2);
                    prev=1;
                }
                Bitmap wallpaper=((BitmapDrawable)drawable).getBitmap();
                try
                {
                    wpm.setBitmap(wallpaper);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        },0,30000);
    }

}
