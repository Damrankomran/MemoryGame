package com.example.makina.memorygame2;

import android.content.Intent;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class GameScreenTime extends AppCompatActivity {

    private String name;
    private int row;
    private int column;
    private int area;

    private int lastCard=0;

    private int score = 0;
    private int mistake = 0;

    TextView text;
    CountDownTimer countDownTimer;

    Point size = new Point();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen_time);

        //Ekran'ın boyutunu alalım
        getWindowManager().getDefaultDisplay().getSize(size);


        //Diğer activitylerden name,row,column,area bilgilerini alalım

        Intent i = getIntent();
        name = i.getStringExtra("name");
        row = i.getIntExtra("row",0);
        column = i.getIntExtra("column",0);
        area = i.getIntExtra("area",0);

        TextView txt_name = findViewById(R.id.txt_name);
        txt_name.setText("Welcome "+name);

        final TextView txt_score = findViewById(R.id.txt_score);
        final TextView txt_mistake = findViewById(R.id.txt_mistake);

        //------------------------------------------------------//
                    // Oyun Alanı İşlemleri

        //GridLayoutumuzun row ve column degerlerini oluşturalım
        GridLayout gl = findViewById(R.id.gl_game);
        gl.setRowCount(row);
        gl.setColumnCount(column);

        //Her karta bir id vermemiz ve her karta bir tıklama özelliği eklememiz gerekiyor
        final Card cards[] = new Card[area]; //Card sınıfından kart sayısı kadar bir dizi oluşturduk

        for(int j=1;j<=area;j++){
            cards[j-1] = new Card(this,j,row,column,area,size);

            //Buttonlarımıza(Kart) tıklama özelliği koyalım
            cards[j-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Card k = (Card)v; //tıknalın button k da tutulacak;
                    startAnimation(k);
                    k.turnCard();
                    k.setClickable(false);
                    //İkinci bir kart'a(button'a) tıklanıldığında bu button k da tutulucak
                    //İlk tıkladığımız button'un bilgileri lastCard'da tutuluyordu
                    //Bu yüzden bir tane daha card nesnesi yaratıp bunu k2 de tutacağız

                    if(lastCard > 0){
                        final Card k2 = findViewById(lastCard); //İlk bastığımız kart'ın id'sini k2 ye atadık
                        //k ve k2'nin foregroundID'lerini karşılaştıralım
                        //Aynı button'a basma ihtimaline karşı id'lerini kontrol edelim
                        if(k2.foregroundID == k.foregroundID && k2.getId() != k.getId()){
                            //Eşleştiler
                            k2.reversible = false;
                            k.reversible = false;

                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    k.setVisibility(View.INVISIBLE);
                                    k2.setVisibility(View.INVISIBLE);

                                }
                            }, 500);

                            score++;
                            txt_score.setText("Score: "+score);

                            lastCard = 0;

                            if(score == area/2){
                                //Oyun Bitti

                                Intent i = new Intent(GameScreenTime.this,ScoreScreen.class);
                                i.putExtra("name",name);
                                i.putExtra("row",row);
                                i.putExtra("column",column);
                                i.putExtra("area",area);
                                i.putExtra("mistake",mistake);
                                i.putExtra("mode","time");
                                i.putExtra("time",1);
                                startActivity(i);
                            }

                        }
                        else{
                            //Eşleşmediler geri çevir

                            //aynı kartı acayıp kapayınca bekleme olmasın diye
                            for (int j = 0; j < area; j++) {
                                cards[j].setClickable(false);
                            }

                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    k.turnCard();
                                    k2.turnCard();

                                    for(int j=0;j<area;j++){
                                        cards[j].setClickable(true);
                                    }
                                }
                            },700);

                            mistake++;
                            txt_mistake.setText("Mistake: "+mistake);

                            lastCard = 0;
                        }
                    }
                    else{
                        lastCard = k.getId();
                    }
                }
            });
        }

        //Kartları karıştıralım
        for(int j=0;j<area;j++){
            int random =(int)(Math.random()*area);
            Card k = cards[random];
            cards[random] = cards[j];
            cards[j] = k;
        }


        for(int j=0; j<area;j++){
            gl.addView(cards[j]);
        }

        text = (TextView)findViewById(R.id.txt_countdown);
        countDownTimer = new CountDownTimer(180000,1000) { //ilk parametre başlangıc zamanı 2. parametre tekrarlama sn'yesi
            @Override
            public void onTick(long l) {
                //Her tetiklendiğinde yapılmasını istenilen olaylar.
                int minutes = (int)l / 60000;
                int seconds = (int)l % 60000 /1000;
                text.setText(minutes+":"+seconds);
            }

            @Override
            public void onFinish() {
                //iş bittiğinde gerçekleşecek olaylar
                Intent i = new Intent(GameScreenTime.this, ScoreScreen.class);
                i.putExtra("name",name);
                i.putExtra("row",row);
                i.putExtra("column",column);
                i.putExtra("area",area);
                i.putExtra("mistake",mistake);
                i.putExtra("mode","time");
                i.putExtra("time",0);
                startActivity(i);
            }
        }.start();
    }

    public void startAnimation(Button button){
        Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);

        findViewById(button.getId()).startAnimation(rotate);

    }
}
