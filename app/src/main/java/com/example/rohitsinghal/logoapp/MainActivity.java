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

public class MainActivity extends AppCompatActivity {

    Button b1,b2;
    EditText e1,e2;
    TextToSpeech ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        ts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                ts.setSpeechRate(0.5f);
                ts.setLanguage(Locale.ENGLISH);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("Switch to signup",TextToSpeech.QUEUE_FLUSH,null);
                Intent i=new Intent(MainActivity.this,Second.class);
                startActivity(i);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                if(s1.equals("") || s2.equals("")){
                    ts.speak("Please Fill all details",TextToSpeech.QUEUE_FLUSH,null);
                    Toast.makeText(MainActivity.this, "Plz Fill all details", Toast.LENGTH_SHORT).show();
                }else{
                    SQLiteDatabase sql=openOrCreateDatabase("netcamp",MODE_PRIVATE,null);
                    sql.execSQL("create table if not exists student (name varchar,email varchar,password varchar)");
                    String s4="select * from student where name='"+s1+"' and email='"+s2+"'";
                    Cursor cs=sql.rawQuery(s4,null);
                    if(cs.getCount()>0){
                        ts.speak("Login Successfully",TextToSpeech.QUEUE_FLUSH,null);
                        Toast.makeText(MainActivity.this, "Login Done", Toast.LENGTH_SHORT).show();
                        Intent j=new Intent(MainActivity.this,Third.class);
                        startActivity(j);
                        finish();
                    }else {
                        ts.speak("Mismatch",TextToSpeech.QUEUE_FLUSH,null);
                        Toast.makeText(MainActivity.this, "Mismatch", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
