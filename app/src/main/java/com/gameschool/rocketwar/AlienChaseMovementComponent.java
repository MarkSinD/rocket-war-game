package com.gameschool.rocketwar;

import android.graphics.PointF;

import java.util.Random;

public class AlienChaseMovementComponent implements MovementComponent {
    private Random mShotRandom = new Random();
    private AlienLaserSpawner alienLaserSpawner;

    AlienChaseMovementComponent(AlienLaserSpawner als){
        alienLaserSpawner = als;
    }

    @Override
    public boolean move(long fps, Transform t, Transform playerTransform) {
        final int TAKE_SHOT = 0;
        final int SHOT_CHANCE = 100;

        float screenWidth = t.getScreenSize().x;
        PointF playerLocation = playerTransform.getLocation();

        float height = t.getObjectHeight();
        boolean facingRight = t.getFacingRight();
        float mChasingDistance = t.getScreenSize().x / 3f;
        float mSeeingDistance = t.getScreenSize().x / 1.5f;
        PointF location = t.getLocation();
        float speed = t.getSpeed();

        float verticalSpeedDifference = .3f;
        float slowDownRelativeToPlayer = 1.8f;
        float verticalSearchBounce = 20f;

        if(Math.abs(location.x - playerLocation.x) > mChasingDistance) {
            if (location.x < playerLocation.x) {
                t.headRight();
            } else if (location.x > playerLocation.x) {
                t.headLeft();
            }
        }


        if (Math.abs(location.x - playerLocation.x)
                <= mSeeingDistance) {


            if ((int) location.y - playerLocation.y < -verticalSearchBounce) {
                t.headDown();
            }
            else if ((int) location.y - playerLocation.y > verticalSearchBounce) {
                t.headUp();
            }


            if(!playerTransform.getFacingRight()){
                location.x += speed * slowDownRelativeToPlayer / fps;
            }
            else{
                location.x -=  speed * slowDownRelativeToPlayer / fps;
            }
        }
        else{
            t.stopVertical();
        }



        if(t.headingDown()){
            location.y += speed * verticalSpeedDifference / fps;
        }
        else if(t.headingUp()){
            location.y -= speed * verticalSpeedDifference / fps;
        }


        if(t.headingLeft()){
            location.x -= (speed) / fps;
        }
        if(t.headingRight()){
            location.x += (speed) / fps;
        }

        t.updateCollider();

        if(mShotRandom.nextInt(SHOT_CHANCE) == TAKE_SHOT) {
            if (Math.abs(playerLocation.y - location.y) < height) {
                if ((facingRight && playerLocation.x > location.x
                        || !facingRight && playerLocation.x < location.x)
                        && Math.abs(playerLocation.x - location.x)
                        < screenWidth) {
                    alienLaserSpawner.spawnAlienLaser(t);
                }

            }
        }
        return true;
    }
}
