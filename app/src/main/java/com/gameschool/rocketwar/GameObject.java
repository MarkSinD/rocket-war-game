package com.gameschool.rocketwar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public class GameObject {

    private Transform mTransform;
    private boolean isActive = false;
    private String mTag;

    private GraphicsComponent graphicsComponent;
    private MovementComponent movementComponent;
    private SpawnComponent spawnComponent;

    void setSpawner(SpawnComponent s){ spawnComponent = s; }

    void setGraphics(GraphicsComponent g, Context c,
                     ObjectSpec spec, PointF objectSize){
        graphicsComponent = g;
        g.initialize(c, spec, objectSize);
    }

    void setMovement(MovementComponent m){ movementComponent = m;}

    void setTag( String tag ){ mTag = tag; }

    void setTransform(Transform t){ mTransform = t; }


    boolean checkActive() {
        return isActive;
    }

    String getTag() { return mTag; }

    void setInput( InputComponent s) { s.setTransform(mTransform); }

    void setInactive() { isActive = false; }

    boolean spawn(Transform playerTransform) {
        // Only spawnComponent if not already active
        if (!isActive) {
            spawnComponent.spawn(playerTransform, mTransform);
            isActive = true;
            return true;
        }
        return false;
    }

    void update(long fps, Transform playerTransform){
        if( !(movementComponent.move(fps, mTransform, playerTransform))){
            isActive = false;
        }
    }

    void draw(Canvas canvas, Paint paint){
        graphicsComponent.draw(canvas, paint, mTransform);
    }


    Transform getTransform(){ return mTransform; }

}
