package com.example.makina.memorygame2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Scroller;
import android.widget.TextView;

public class ScoreScreen extends AppCompatActivity {

    private String name;
    private String mode;
    private int row;
    private int column;
    private int area;
    private String finishTime;
    private int mistake;
    private int time;

    Intent j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);


        Intent i = getIntent();
        name = i.getStringExtra("name");
        mode = i.getStringExtra("mode");
        row = i.getIntExtra("row",0);
        column = i.getIntExtra("column",0);
        area = i.getIntExtra("area",0);
        finishTime = i.getStringExtra("finishTime");
        mistake = i.getIntExtra("mistake",0);
        time = i.getIntExtra("time",0);
        TextView tv = findViewById(R.id.txt_finish_game);


        if(mode.equalsIgnoreCase("free")){
            tv.setText("Harika!! "+name+" oyunu "+mistake+" hata ile "+finishTime+" sürede bitirdiniz!!");
        }

        if(mode.equalsIgnoreCase("time") || mode.equalsIgnoreCase("hard")){
            if(time == 0)
            tv.setText("Malesef "+name+" zaman yetmedi, oyunu kaybettin!!!" );
            else
                tv.setText("Harika!! " + name + " oyunu " + mistake + " hata ile bitirdin!!");
        }





        ((Button)findViewById(R.id.btn_restart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseGameScreenMode(mode);
                j.putExtra("name",name);
                j.putExtra("row",row);
                j.putExtra("column",column);
                j.putExtra("area",area);
                startActivity(j);
            }
        });

        ((Button)findViewById(R.id.btn_section)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(ScoreScreen.this,SectionScreen.class);
                j.putExtra("name",name);
                startActivity(j);
            }
        });
    }

    public void chooseGameScreenMode(String mode){

        if(mode.equalsIgnoreCase("free")){
            j = new Intent(ScoreScreen.this,GameScreenFree.class);
        }
        if(mode.equalsIgnoreCase("time")){
            j = new Intent(ScoreScreen.this,GameScreenTime.class);
        }
        if(mode.equalsIgnoreCase("hard")){
            j = new Intent(ScoreScreen.this,GameScreenHard.class);
        }
    }
}
