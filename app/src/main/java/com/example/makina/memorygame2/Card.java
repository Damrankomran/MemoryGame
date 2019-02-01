package com.example.makina.memorygame2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

//Card classımız Button özellikleri taşıyacak
public class Card extends android.support.v7.widget.AppCompatButton {

     int foregroundID;
     int backgroundID;

     Drawable back;
     Drawable front;
     Drawable dr;

     boolean isOpen = false;
     boolean reversible = true;

    @SuppressLint({"RestrictedApi", "NewApi"})
    public Card(Context context, int id,int row, int column,int area, Point size) {
        super(context);

        setId(id);

        chooseDrawble(id,area);

        back = AppCompatDrawableManager.get().getDrawable(context,backgroundID);
        front = AppCompatDrawableManager.get().getDrawable(context,foregroundID);

        dr = back;
        Bitmap bitmap = ((BitmapDrawable)dr).getBitmap();
        back = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap,size.x/column,size.y/(row+2),true));
        dr = front;
        Bitmap bitmap1 = ((BitmapDrawable)dr).getBitmap();
        front = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap1,size.x/column,size.y/(row+2),true));

        setBackground(back);

    }

    @SuppressLint({"RestrictedApi", "NewApi"})
    public void goster(Context context , int id, int row,int column,int area, Point boyut){
        front=null;
        back=null;

        chooseDrawble(id,area);

        back = AppCompatDrawableManager.get().getDrawable(context,backgroundID);
        front = AppCompatDrawableManager.get().getDrawable(context,foregroundID);

        dr = back;
        Bitmap bitmap = ((BitmapDrawable)dr).getBitmap();
        back = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap,boyut.x/column,boyut.y/(row+2),true));

        dr = front;
        Bitmap bitmap1 = ((BitmapDrawable)dr).getBitmap();
        front = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap1,boyut.x/column,boyut.y/(row+2),true));

        setBackground(back);

    }

    //Kartı çevirmeye yarayan fonksiyonumuz
    //Amacı her çalıştığında duruma göre kartın background'unu değiştirmek
    //Böylece kartı çevirmiş olucaz
    @SuppressLint("NewApi")
    public void turnCard(){
        if(reversible){
            if(isOpen == false){
                setBackground(front);
                isOpen = true;
            }
            else{
                setBackground(back);
                isOpen = false;
            }
        }

    }

    public void chooseDrawble(int id,int area){

        backgroundID = R.drawable.arka;

        if(id % (area/2) == 1)
            foregroundID = R.drawable.img1;
        if (id % (area/2) == 2)
            foregroundID = R.drawable.img2;
        if (id % (area/2) == 3)
            foregroundID = R.drawable.img3;
        if (id % (area/2)== 4)
            foregroundID = R.drawable.img4;
        if (id % (area/2) == 5)
            foregroundID = R.drawable.img5;
        if (id % (area/2) == 6)
            foregroundID = R.drawable.img6;
        if (id % (area/2) == 7)
            foregroundID = R.drawable.img7;
        if (id % (area/2)== 8)
            foregroundID = R.drawable.img8;
        if (id % (area/2) == 9)
            foregroundID = R.drawable.img9;
        if (id % (area/2) == 10)
            foregroundID = R.drawable.img10;
        if (id % (area/2) == 11)
            foregroundID = R.drawable.img11;
        if (id % (area/2) == 12)
            foregroundID = R.drawable.img12;
        if (id % (area/2) == 13)
            foregroundID = R.drawable.img13;
        if (id % (area/2) == 14)
            foregroundID = R.drawable.img14;
        if (id % (area/2) == 15)
            foregroundID = R.drawable.img15;
        if (id % (area/2) == 16)
            foregroundID = R.drawable.img16;
        if (id % (area/2) == 17)
            foregroundID = R.drawable.img17;
        if (id % (area/2) == 0)
            foregroundID = R.drawable.img18;
    }

}
