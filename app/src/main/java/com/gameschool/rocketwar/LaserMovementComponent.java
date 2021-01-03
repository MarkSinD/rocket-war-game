package com.gameschool.rocketwar;

import android.graphics.PointF;

public class LaserMovementComponent  implements MovementComponent{

    @Override
    public boolean move(long fps, Transform t, Transform playerTransform) {
        float range = t.getScreenSize().x * 2;
        PointF location = t.getLocation();

        float speed = t.getSpeed();

        if( t.headingRight()){
            location.x += speed/fps;
        }

        else if( t.headingLeft()){
            location.x -= speed/fps;
        }

        if(location.x < - range || location.x > range){
            return false;
        }

        t.updateCollider();
        return true;

    }
}
