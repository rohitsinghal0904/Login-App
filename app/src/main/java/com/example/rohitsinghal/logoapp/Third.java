package com.example.rohitsinghal.logoapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class Third extends AppCompatActivity {
    Button b1;
    TextToSpeech ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        b1=(Button)findViewById(R.id.button5);
        ts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                ts.setSpeechRate(0.5f);
                ts.setLanguage(Locale.ENGLISH);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("Logout Successfully",TextToSpeech.QUEUE_FLUSH,null);
                Toast.makeText(Third.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                Intent k= new Intent(Third.this,MainActivity.class);
                startActivity(k);
                finish();
            }
        });
    }
}
