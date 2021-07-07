package com.example.rohitsinghal.logoapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class Second extends AppCompatActivity {


    EditText e1,e2,e3;
    Button b1,b2;
    TextToSpeech ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        b1=(Button)findViewById(R.id.button3);
        b2=(Button)findViewById(R.id.button4);
        e1=(EditText)findViewById(R.id.editText3);
        e2=(EditText)findViewById(R.id.editText4);
        e3=(EditText)findViewById(R.id.editText5);
        e3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        ts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                ts.setLanguage(Locale.ENGLISH);
                ts.setSpeechRate(0.5f);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("Back to Login Page",TextToSpeech.QUEUE_FLUSH,null);
                Toast.makeText(Second.this, "Sign up", Toast.LENGTH_SHORT).show();
                Intent j=new Intent(Second.this,MainActivity.class);
                startActivity(j);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                String s3=e3.getText().toString();
                if(s1.equals("") || s2.equals("") || s3.equals("")){
                    ts.speak("Please Enter all Details",TextToSpeech.QUEUE_FLUSH,null);
                    Toast.makeText(Second.this, "PLZ Enter all Details", Toast.LENGTH_SHORT).show();
                }
                else {
                    SQLiteDatabase sql=openOrCreateDatabase("netcamp",MODE_PRIVATE,null);
                    sql.execSQL("create table if not exists student (name varchar,email varchar,password varchar)");
                    String s4="select * from student where name='"+s1+"' and email='"+s2+"' ";
                    Cursor c1=sql.rawQuery(s4,null);
                    if(c1.getCount()>0){
                        ts.speak("User Already Exist",TextToSpeech.QUEUE_FLUSH,null);
                        Toast.makeText(Second.this, "User Already Exist", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        sql.execSQL("insert into student values ('"+s1+"','"+s2+"','"+s3+"')");
                        ts.speak("Registration Successfully",TextToSpeech.QUEUE_FLUSH,null);
                        Toast.makeText(Second.this, "Registration Done", Toast.LENGTH_SHORT).show();
                        Intent k=new Intent(Second.this,MainActivity.class);
                        startActivity(k);
                        finish();

                    }

                }

            }
        });
    }
}
