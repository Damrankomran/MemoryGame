package com.example.makina.memorygame2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ModScreen extends AppCompatActivity {


    private String name;
    private int row;
    private int column;
    private int area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_screen);

        //SectionScreen'den gelen bilgileri getIntent() metodu ile alalım.
        Intent i = getIntent();
        name = i.getStringExtra("name");
        row = i.getIntExtra("row",0);
        column = i.getIntExtra("column",0);
        area = i.getIntExtra("area",0);

        TextView tv_name = (TextView)findViewById(R.id.txt_name);
        tv_name.setText("Welcome "+name);

        ((Button)findViewById(R.id.btn_free)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseMod(name,row,column,area,"free");
            }
        });

        ((Button)findViewById(R.id.btn_time)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseMod(name,row,column,area,"time");
            }
        });

        ((Button)findViewById(R.id.btn_hard)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseMod(name,row,column,area,"hard");
            }
        });



    }

    public void showChooseMod(String name,int row,int column, int area, String mode){
            Intent i = null;
        if(mode.equalsIgnoreCase("free")) {
            i = new Intent(ModScreen.this,GameScreenFree.class);
        }
        if (mode.equalsIgnoreCase("time")){
            i = new Intent(ModScreen.this,GameScreenTime.class);
        }
        if(mode.equalsIgnoreCase("hard")){
             i = new Intent(ModScreen.this,GameScreenHard.class);
        }

        i.putExtra("name",name);
        i.putExtra("row",row);
        i.putExtra("column",column);
        i.putExtra("area",area);

        startActivity(i);
    }
}
