package com.gameschool.rocketwar;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class GameState {
    private static volatile boolean mThreadRunning = false;
    private static volatile boolean mPaused = true;
    private static volatile boolean mGameOver = true;
    private static volatile boolean mDrawing = false;

    private int mScore;
    private int mHighScore;
    private int mNumShips;
    private static GameStarter gameStarter;
    private SharedPreferences.Editor mEditor;

    GameState(GameStarter gs, Context context){

        gameStarter = gs;
        SharedPreferences prefs;
        prefs = context.getSharedPreferences("HiScore",
                Context.MODE_PRIVATE);
        mEditor = prefs.edit();
        mHighScore = prefs.getInt("hi_score", 0);
    }
    int getNumShips(){
        return mNumShips;
    }
    void setScore(){ mScore = 101010; }
    int getScore(){
        return mScore;
    }
    int getHighScore(){ return mHighScore; }
    boolean getPaused(){
        return mPaused;
    }
    boolean getGameOver(){
        return mGameOver;
    }
    boolean getDrawing() {return mDrawing;}
    private void stopDrawing(){
        mDrawing = false;
    }
    private void startDrawing(){
        mDrawing = true;
    }
    boolean getThreadRunning(){
        return mThreadRunning;
    }
    void startThread(){
        mThreadRunning = true;
    }

    void pause(){
        mPaused = true;
    }

    void resume(){
        mGameOver = false;
        mPaused = false;
    }

    void startNewGame(){
        mScore = 0;
        mNumShips = 1000;
        // Don'mTransform want to be drawing objects while deSpawnReSpawn is
        // clearing ArrayList and filling it up again

        stopDrawing();
        Log.d("MY LOG!!!!!", "startNewGame запущен");
        gameStarter.deSpawnReSpawn();
        resume();

        // Now we can draw again
        startDrawing();
    }

    void stopEverything(){
        mPaused = true;
        mGameOver = true;
        mThreadRunning = false;
    }

    void loseLife( SoundEngine se){
        mNumShips--;
        se.playPlayerExplode();
        if(mNumShips == 0){
            pause();
            endGame();
        }
    }

    void increaseScore(){
        mScore++;
    }


    private void endGame(){
        mGameOver = true;
        mPaused = true;
        if(mScore > mHighScore){
            mHighScore = mScore;
            mEditor.putInt("hi_score", mHighScore);
            mEditor.commit();
        }
    }

}
