package com.example.makina.memorygame2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SectionScreen extends AppCompatActivity {

    private TextView tv_name;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_screen);

        //LoginScreen'den gelecek olan kullanıcı ismini alalım
        Intent i = getIntent();
        name = i.getStringExtra("name");

        tv_name = findViewById(R.id.txt_name);
        tv_name.setText("Welcome "+name);

        ((Button)findViewById(R.id.btn12)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseSection(name,4,3,12);
            }
        });

        ((Button)findViewById(R.id.btn16)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseSection(name,4,4,16);
            }
        });

        ((Button)findViewById(R.id.btn20)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseSection(name,5,4,20);
            }
        });

        ((Button)findViewById(R.id.btn36)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseSection(name,9,4,36);
            }
        });


    }

    //Bu activity'den ModScreen activity'sine geçişi burdan sağlıyacağız.
    //Her button'un onClickListener'ı için ayrı ayrı Intent yazmak yerine hangi button'a tıklandıysa
    //Belirli parametreleri karşı tarafa burdan geçireceğiz

    public void showChooseSection(String name,int row, int column,int area){

            Intent i = new Intent(SectionScreen.this,ModScreen.class);
            i.putExtra("name",name);
            i.putExtra("row",row);
            i.putExtra("column",column);
            i.putExtra("area",area);
            startActivity(i);
    }
}
