package com.gameschool.rocketwar;

import android.view.MotionEvent;

import java.util.ArrayList;

class PlayerInputComponent implements InputComponent, InputObserver {

    private Transform mTransform;
    private PlayerLaserSpawner mPLS;

    PlayerInputComponent( GameEngine ger){

        ger.addObserver(this);
        mPLS = ger;
    }


    @Override
    public void setTransform(Transform t) {
        mTransform = t;
    }

    @Override
    public void handleInput(MotionEvent event, GameState gs, ArrayList<Circle> controls) {

        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);

        switch (event.getAction() & MotionEvent.ACTION_MASK){

            case MotionEvent.ACTION_UP:
                if(controls.get(HUD.UP).contains(x,y) ||
                        controls.get(HUD.DOWN).contains(x,y)) {
                    mTransform.stopVertical();
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                if(controls.get(HUD.UP).contains(x,y) ||
                        controls.get(HUD.DOWN).contains(x,y)){
                    mTransform.stopVertical();
                }
                break;

            case MotionEvent.ACTION_DOWN:
                if (controls.get(HUD.UP).contains(x,y)) {
                    // Player has pressed up
                    mTransform.headUp();
                } else if (controls.get(HUD.DOWN).contains(x,y)) {
                    // Player has pressed down
                    mTransform.headDown();
                } else if (controls.get(HUD.FLIP).contains(x,y)) {
                    // Player has released the flip button
                    mTransform.flip();
                } else if (controls.get(HUD.SHOOT).contains(x,y)) {
                    mPLS.spawnPlayerLaser(mTransform);
                }
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                if( controls.get(HUD.UP).contains(x,y)){
                    mTransform.headUp();
                }
                else if(controls.get(HUD.DOWN).contains(x,y)){
                    mTransform.headDown();
                }
                else if(controls.get(HUD.FLIP).contains(x,y)){
                    mTransform.flip();
                }
                else if(controls.get(HUD.SHOOT).contains(x,y)){
                    mPLS.spawnPlayerLaser(mTransform);
                }
                break;

        }

    }
}
