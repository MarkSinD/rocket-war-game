package com.gameschool.rocketwar;

import android.graphics.PointF;

import java.util.Random;

public class AsteroidBottomMovementComponent implements MovementComponent {
    @Override
    public boolean move(long fps, Transform t, Transform playerTransform) {
        // Where is the ship?
        PointF location = t.getLocation();
        // How fast is the ship?
        float speed = t.getSpeed();


        // Compensate for movement relative to player-
        // but only when in view.
        // Otherwise alien will disappear miles off to one side
        if (!playerTransform.getFacingRight()) {
            location.x += (speed * 3) / fps;
        } else {
            location.x -= speed / ( fps * 3 ) ;
        }

        // Fall down then respawn at the top
        location.y -= speed / fps;

        if (location.y < -50) {
            // Respawn at top
            Random random = new Random();
            location.y = t.getScreenSize().y;
            location.x = random.nextInt((int) t.getScreenSize().x);
        }

        // Update the collider
        t.updateCollider();
        return true;
    }
}
