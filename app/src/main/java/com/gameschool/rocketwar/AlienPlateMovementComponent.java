package com.gameschool.rocketwar;

import android.graphics.PointF;

import java.util.Random;

public class AlienPlateMovementComponent implements MovementComponent {

    @Override
    public boolean move(long fps, Transform t, Transform playerTransform) {
        // Where is the ship?

        PointF playerLocation = playerTransform.getLocation();

        final float MIN_VERTICAL_BOUNDS = 0;
        float screenX = t.getScreenSize().x;
        float screenY = t.getScreenSize().y;

        float mSeeingDistance = screenX * .5f;

        PointF loc = t.getLocation();
        float speed = t.getSpeed();



        float horizontalSpeedAdjustmentRelativeToPlayer = 0 ;
        float horizontalSpeedAdjustmentModifier = .8f;

        if (Math.abs(loc.x - playerLocation.x)
                < mSeeingDistance) {
            if(playerTransform.getFacingRight()
                    != t.getFacingRight()){

                horizontalSpeedAdjustmentRelativeToPlayer =
                        speed * horizontalSpeedAdjustmentModifier;
            } else{
                horizontalSpeedAdjustmentRelativeToPlayer =
                        -(speed * horizontalSpeedAdjustmentModifier);
            }
        }


        if(t.headingLeft()){
            loc.x -= (speed + horizontalSpeedAdjustmentRelativeToPlayer) / fps;

        }
        else{
            loc.x += (speed + horizontalSpeedAdjustmentRelativeToPlayer) / fps;
        }

        if(Math.abs(playerLocation.x - loc.x) > screenX/4){
            if(loc.x > playerLocation.x && t.headingRight()){
                t.headLeft();
            }
            if(loc.x < playerLocation.x && t.headingLeft()){
                t.headRight();
            }
        }

        if(loc.x > 1.5f * screenX){
            loc.x = 1.5f * screenX;
        }
        else if(loc.x < -screenX/10){
            loc.x = -screenX/10;
        }

        if(t.headingDown()){
            loc.y += (speed) / fps;
            if(loc.y > playerLocation.y + screenY/4){
                t.headUp();
            }
        }
        else{
            loc.y -= (speed) / fps;
            if(loc.y < playerLocation.y - screenY/4){
                t.headDown();
            }
        }
        t.updateCollider();

        return true;
    }
}
