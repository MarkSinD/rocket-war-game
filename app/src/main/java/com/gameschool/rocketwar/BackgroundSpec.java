package com.gameschool.rocketwar;

import android.graphics.PointF;

public class BackgroundSpec extends ObjectSpec {

    private static final String tag = "Background";
    private static final String bitmapName = "background";
    private static final float speed = 2f;
    private static final PointF relativeScale = new PointF(1f, 1f);

    private static final String[] components = new String[]{
            "BackgroundGraphicsComponent",
            "BackgroundMovementComponent",
            "BackgroundSpawnComponent"
    };



    BackgroundSpec() {
        super(tag, bitmapName, speed, relativeScale, components);
    }
}
