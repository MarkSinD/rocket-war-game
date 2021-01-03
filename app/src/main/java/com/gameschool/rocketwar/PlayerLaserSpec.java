package com.gameschool.rocketwar;

import android.graphics.PointF;

public class PlayerLaserSpec extends ObjectSpec{
    private static final String tag = "Player Laser";
    private static final String bitmapName = "player_laser";
    private static final float speed = .75f;
    private static final PointF relativeScale = new PointF(45f, 90f);

    private static final String[] components = new String[]{
            "StdGraphicsComponent",
            "LaserMovementComponent",
            "LaserSpawnComponent"
    };

    PlayerLaserSpec(){
        super(tag, bitmapName, speed, relativeScale, components);
    }
}
