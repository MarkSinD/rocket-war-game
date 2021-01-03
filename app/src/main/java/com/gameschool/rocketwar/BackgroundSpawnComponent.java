package com.gameschool.rocketwar;

public class BackgroundSpawnComponent implements SpawnComponent {

    @Override
    public void spawn(Transform playerTransform, Transform t) {
        t.setLocation(0f,0f);
    }
}
