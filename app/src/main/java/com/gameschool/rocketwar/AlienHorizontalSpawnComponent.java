package com.gameschool.rocketwar;

import android.graphics.PointF;

import java.util.Random;

public class AlienHorizontalSpawnComponent implements SpawnComponent {
    @Override
    public void spawn(Transform playerTransform, Transform t) {
        PointF ss = t.getScreenSize();
        Random random = new Random();
        boolean left = random.nextBoolean();

        float distance = random.nextInt(2000)+t.getScreenSize().x;
        float spawnHeight = random.nextFloat() * ss.y - t.getSize().y;

        if(left){
            t.setLocation(-distance, spawnHeight);
            t.headRight();
        }
        else{
            t.setLocation(distance, spawnHeight);
            t.headingLeft();
        }
    }
}
