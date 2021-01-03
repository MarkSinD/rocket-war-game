package com.gameschool.rocketwar;

import android.graphics.PointF;

public class AstroidTOPobliqueSpec extends ObjectSpec {

    private static final String tag = "Alien";
    private static final String bitmapName = "asteriodoblique";
    private static final float speed = 4f;
    private static final PointF relativeScale =
            new PointF(40f, 20f);

    private static final String[] components = new String [] {
            "StdGraphicsComponent",
            "AsteroidObliqueMovementComponent",
            "AlienVerticalSpawnComponent"};

    AstroidTOPobliqueSpec() {
        super(tag, bitmapName, speed, relativeScale, components);
    }
}
