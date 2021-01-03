package com.gameschool.rocketwar;

import android.graphics.PointF;
import android.graphics.RectF;

public class Transform {

    private int mXClip;
    private boolean mReversedFirst = false;

    private RectF mCollider;
    private PointF mLocation;
    private boolean mFacingRight = true;
    private boolean mHeadingUp = false;
    private boolean mHeadingDown = false;
    private boolean mHeadingLeft = false;
    private boolean mHeadingRight = false;
    private float mSpeed;
    private float mObjectHeight;
    private float mObjectWidth;
    private static PointF mScreenSize;

    Transform(float speed, float objectWidth, float objectHeight,
              PointF startingLocation, PointF screenSize){
        mCollider = new RectF();
        mSpeed = speed;
        mObjectHeight = objectHeight;
        mObjectWidth = objectWidth;
        mLocation = startingLocation;
        mScreenSize = screenSize;
    }

    boolean getReversedFirst(){ return mReversedFirst; }

    void flipReversedFirst(){
        mReversedFirst = !mReversedFirst;
    }

    int getXClip(){ return mXClip; }

    void setXClip( int newXClip){ mXClip = newXClip; }

    PointF getScreenSize() { return mScreenSize; }

    void headUp(){
        mHeadingUp = true;
        mHeadingDown = false;
    }

    void headDown(){
        mHeadingDown = true;
        mHeadingUp = false;
    }

    void headRight(){
        mHeadingRight = true;
        mHeadingLeft = false;
        mFacingRight = true;
    }

    void headLeft(){
        mHeadingLeft = true;
        mHeadingRight = false;
        mFacingRight = false;
    }

    boolean headingUp(){ return mHeadingUp; }

    boolean headingDown() { return mHeadingDown; }

    boolean headingRight() { return mFacingRight; }

    boolean headingLeft() { return mHeadingLeft; }

    void updateCollider(){
        mCollider.top = mLocation.y + (mObjectHeight / 10 );
        mCollider.left = mLocation.x + (mObjectWidth / 10 );
        mCollider.bottom = (mCollider.top + mObjectHeight)
                -mObjectHeight/10;
        mCollider.right = (mCollider.left + mObjectWidth)
                -mObjectWidth/10;
    }

    float getObjectHeight() { return mObjectHeight; }

    void stopVertical(){
        mHeadingDown = false;
        mHeadingUp = false;
    }

    float getSpeed(){
        return mSpeed;
    }

    void setLocation( float horizontal, float vertical){
        mLocation = new PointF(horizontal, vertical);
        updateCollider();
    }

    PointF getLocation(){
        return mLocation;
    }

    PointF getSize(){
        return new PointF((int)mObjectWidth, (int)mObjectHeight);
    }

    void flip() { mFacingRight =! mFacingRight; }

    boolean getFacingRight() { return mFacingRight; }

    RectF getCollider() { return mCollider; }


    PointF getFiringLocation( float laserLength){
        PointF mFiringLocation = new PointF();

        if( Level.mNextPlayerLaser % 2 == 0) {
            if (mFacingRight) {
                mFiringLocation.x = mLocation.x + mObjectWidth / 6f;
            } else {
                mFiringLocation.x = mLocation.x + mObjectWidth / 6f - laserLength;
            }

            mFiringLocation.y = mLocation.y + mObjectHeight / 1.28f;
        }
        else{
            if (mFacingRight) {
                mFiringLocation.x = mLocation.x + mObjectWidth / 6f;
            } else {
                mFiringLocation.x = mLocation.x + mObjectWidth / 6f - laserLength;
            }

            mFiringLocation.y = mLocation.y + mObjectHeight / 4.54f;
        }
        return mFiringLocation;
    }







}
