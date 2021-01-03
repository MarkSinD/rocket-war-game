package com.gameschool.rocketwar;

import android.graphics.PointF;
import java.util.Random;

class AlienPatrolMovementComponent implements MovementComponent {

    private AlienLaserSpawner alienLaserSpawner;
    private Random mShotRandom = new Random();

    AlienPatrolMovementComponent(AlienLaserSpawner als){
        alienLaserSpawner = als;
    }

    @Override
    public boolean move(long fps, Transform t,
                        Transform playerTransform) {

        final int TAKE_SHOT = 0;
        final int SHOT_CHANCE = 100;

        PointF playerLocation = playerTransform.getLocation();

        final float MIN_VERTICAL_BOUNDS = 0;
        float screenX = t.getScreenSize().x;
        float screenY = t.getScreenSize().y;

        float mSeeingDistance = screenX * .5f;

        PointF loc = t.getLocation();
        float speed = t.getSpeed();
        float height = t.getObjectHeight();

        float MAX_VERTICAL_BOUNDS = screenY- height;
        final float MAX_HORIZONTAL_BOUNDS = 2 * screenX;
        final float MIN_HORIZONTAL_BOUNDS = 2 * -screenX;


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

            if(loc.x < MIN_HORIZONTAL_BOUNDS){
                loc.x = MIN_HORIZONTAL_BOUNDS;
                t.headRight();
            }
        }
        else{
            loc.x += (speed + horizontalSpeedAdjustmentRelativeToPlayer) / fps;

            if(loc.x > MAX_HORIZONTAL_BOUNDS){
                loc.x = MAX_HORIZONTAL_BOUNDS;
                t.headLeft();
            }
        }

        if(t.headingDown()){
            loc.y += (speed) / fps;
            if(loc.y > MAX_VERTICAL_BOUNDS){
                t.headUp();
            }
        }
        else{
            loc.y -= (speed) / fps;
            if(loc.y < MIN_VERTICAL_BOUNDS){
                t.headDown();
            }
        }
        t.updateCollider();

        if(mShotRandom.nextInt(SHOT_CHANCE) == TAKE_SHOT) {
            if (Math.abs(playerLocation.y - loc.y) < height) {
                if ((t.getFacingRight() && playerLocation.x > loc.x
                        || !t.getFacingRight() && playerLocation.x < loc.x)
                        && Math.abs(playerLocation.x - loc.x) < screenX) {
                    alienLaserSpawner.spawnAlienLaser(t);
                }

            }
        }

        return true;
    }
}
