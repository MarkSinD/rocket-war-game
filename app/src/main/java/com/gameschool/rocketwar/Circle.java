package com.gameschool.rocketwar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

public class Circle {
    int positionX;
    int positionY;
    int radius;
    int iD;
    Paint paint;
    private Bitmap mButton;
    public Circle(int gamePositionX, int gamePositionY, int gameRadius, int index, Context context){
        positionX = gamePositionX;
        positionY = gamePositionY;
        radius = gameRadius;
        iD = index;

        switch (index){
            case 0 :
                mButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.up);
                mButton = Bitmap.createScaledBitmap(mButton, 2*radius, 2*radius, false);
                break;

            case 1 :
                mButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.down);
                mButton = Bitmap.createScaledBitmap(mButton, 2*radius, 2*radius, false);
                break;

            case 2 :
                mButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.flip);
                mButton = Bitmap.createScaledBitmap(mButton, 2*radius, 2*radius, false);
                break;

            case 3 :
                mButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.fire);
                mButton = Bitmap.createScaledBitmap(mButton, 2*radius, 2*radius, false);
                break;

            case 4 :
                mButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.play);
                mButton = Bitmap.createScaledBitmap(mButton, 2*radius, 2*radius, false);
                break;

            case 5:
                mButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.ive);
                mButton = Bitmap.createScaledBitmap(mButton, 2*radius, 2*radius, false);
                break;

        }

    }


    public int getTopXCoordinateButton(){
        return positionX - radius;
    }
    public int getTopYCoordinateButton(){
        return positionY - radius;
    }
    public Bitmap getBitmapButton(){
        return mButton;
    }
    public boolean contains(int x, int y){
        return ( (x-positionX)*(x-positionX) + (y-positionY)*(y-positionY) <= radius*radius );
    }

}