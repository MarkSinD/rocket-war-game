package com.gameschool.rocketwar;

import android.graphics.PointF;

public class AlienChaseSpec extends ObjectSpec {
    private static final String tag = "Alien";
    private static final String bitmapName = "alien_ship1";
    private static final float speed = 4f;
    private static final PointF relativeScale = new PointF(15f,8f);
    private static final String[] components = new String[]{
            "StdGraphicsComponent",
            "AlienChaseMovementComponent",
            "AlienHorizontalSpawnComponent"
    };
    AlienChaseSpec() {
        super(tag, bitmapName, speed, relativeScale, components);
    }
}
