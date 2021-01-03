package com.gameschool.rocketwar;

import android.graphics.PointF;

public class PlayerSpec extends ObjectSpec {
    private static final String tag = "Player";
    private static final String bitmapName = "player_ship0";
    private static final float speed = 1f;
    private static final PointF relativeScale = new PointF(15f,8f);

    private static final String[] components = new String[]{
            "PlayerInputComponent",
            "StdGraphicsComponent",
            "PlayerMovementComponent",
            "PlayerSpawnComponent"
    };
    PlayerSpec() {
        super(tag, bitmapName, speed, relativeScale, components);
    }
}
