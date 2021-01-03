package com.gameschool.rocketwar;

import android.graphics.PointF;

public class AlienPatrolSpec extends ObjectSpec {


    private static final String tag = "Alien";
    private static final String bitmapName = "alien_ship2";
    private static final float speed = 5f;
    private static final PointF relativeScale = new PointF(15f,8f);

    private static final String[] components = new String[]{
            "StdGraphicsComponent",
            "AlienPatrolMovementComponent",
            "AlienHorizontalSpawnComponent"
    };

    AlienPatrolSpec() {
        super(tag, bitmapName, speed, relativeScale, components);
    }
}
