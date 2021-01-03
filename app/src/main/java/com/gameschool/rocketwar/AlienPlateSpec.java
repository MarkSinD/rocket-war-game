package com.gameschool.rocketwar;

import android.graphics.PointF;

public class AlienPlateSpec  extends ObjectSpec{

    private static final String tag = "Alien";
    private static final String bitmapName = "alien_ship3";
    private static final float speed = 4f;
    private static final PointF relativeScale = new PointF(15f,11f);
    private static final String[] components = new String[]{
            "StdGraphicsComponent",
            "AlienPlateMovementComponent",
            "AlienHorizontalSpawnComponent"
    };

    AlienPlateSpec() {
        super(tag, bitmapName, speed, relativeScale, components);
    }

}
