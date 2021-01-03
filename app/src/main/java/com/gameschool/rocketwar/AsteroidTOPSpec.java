package com.gameschool.rocketwar;

import android.graphics.PointF;

public class AsteroidTOPSpec extends ObjectSpec{

    private  static final String tag = "Alien";
    private  static final String bitmapName = "asteriodtop";
    private static final float speed = 4f;
    private static final PointF relativeScale = new PointF(30f, 15f);


    private static final String[] components = new String[]{
            "StdGraphicsComponent",
            "AsteroidMovementComponent",
            "AlienVerticalSpawnComponent"
    };


    AsteroidTOPSpec() {
        super(tag, bitmapName, speed, relativeScale, components);
    }
}
