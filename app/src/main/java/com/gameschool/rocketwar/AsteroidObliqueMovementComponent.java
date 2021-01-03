package com.gameschool.rocketwar;

import android.graphics.PointF;

import java.util.Random;

public class AsteroidObliqueMovementComponent implements MovementComponent{
    @Override
    public boolean move(long fps, Transform t, Transform playerTransform) {
        // Where is the ship?
        PointF location = t.getLocation();
        // How fast is the ship?
        float speed = t.getSpeed();


        // Compensate for movement relative to player-
        // but only when in view.
        // Otherwise alien will disappear miles off to one side
        if(!playerTransform.getFacingRight()){
            location.x += speed * 3 / fps;
        } else{
            location.x -=  speed / (3 *fps);
        }

        // Fall down then respawn at the top
        location.y += speed / fps;

        if(location.y > t.getScreenSize().y){
            // Respawn at top
            Random random = new Random();
            location.y = random.nextInt(300) - t.getObjectHeight();
            location.x = random.nextInt((int)t.getScreenSize().x);
        }

        // Update the collider
        t.updateCollider();

        return true;
    }
}
