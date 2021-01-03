package com.gameschool.rocketwar;

import android.graphics.PointF;

public class AsteroidBottomSpec extends ObjectSpec{
    private  static final String tag = "Alien";
    private  static final String bitmapName = "asteriodbottom";
    private static final float speed = 4f;
    private static final PointF relativeScale = new PointF(36f, 18f);


    private static final String[] components = new String[]{
            "StdGraphicsComponent",
            "AsteroidBottomMovementComponent",
            "AlienVerticalBottomSpawnComponent"
    };


    AsteroidBottomSpec() {
        super(tag, bitmapName, speed, relativeScale, components);
    }
}
