package com.gameschool.rocketwar;

import java.util.Random;

public class AlienVerticalSpawnComponent implements  SpawnComponent {
    @Override
    public void spawn(Transform playerTransform, Transform t) {

        Random random = new Random();
        float xPosition =  random.nextInt((int)t
                .getScreenSize().x);

        // Set the height to vertically
        // just above the visible game
        float spawnHeight = random.nextInt(300) - 5*t.getObjectHeight();


        t.setLocation(xPosition, spawnHeight);
        t.headDown();
    }
}
