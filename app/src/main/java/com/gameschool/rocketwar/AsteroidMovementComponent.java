package com.gameschool.rocketwar;

import android.graphics.PointF;
import android.text.method.MovementMethod;

import java.util.Random;

public class AsteroidMovementComponent implements MovementComponent {
    @Override
    public boolean move(long fps, Transform t, Transform playerTransform) {


        PointF location = t.getLocation();
        float speed = t.getSpeed();
        Random random = new Random();
        float slowDownRelativeToPlayer = 1.8f;




        if(!playerTransform.getFacingRight()){
            location.x += speed * slowDownRelativeToPlayer  / fps;
        } else{
            location.x -=  speed * slowDownRelativeToPlayer / fps;
        }


        location.y += (speed)  / fps;

        if(location.y > t.getScreenSize().y){
            location.y = random.nextInt(500) - t.getObjectHeight();
            location.x = random.nextInt((int)t.getScreenSize().x);
        }


        t.updateCollider();

        return true;
    }
}
