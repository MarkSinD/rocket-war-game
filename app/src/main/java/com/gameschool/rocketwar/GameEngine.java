package com.gameschool.rocketwar;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameEngine extends SurfaceView implements Runnable,
        GameStarter, GameEngineBroadcaster, PlayerLaserSpawner, AlienLaserSpawner {


    private Thread mThread = null;
    private long mFPS;
    private ArrayList<InputObserver> inputObservers = new ArrayList();
    UIController mUIController;
    HUD mHUD;
    private GameState mGameState;
    ParticleSystem mParticleSystem;
    PhysicsEngine mPhysicsEngine;
    Renderer mRenderer;
    Level mLevel;
    private SoundEngine mSoundEngine;

    public GameEngine(Context context, Point size) {
        super(context);

        mUIController = new UIController(this);
        mGameState = new GameState(this, context);
        mSoundEngine = new SoundEngine(context);
        mHUD = new HUD(size, context);
        mRenderer = new Renderer(this);
        mLevel = new Level(context,
                new PointF(size.x, size.y), this);
        mPhysicsEngine = new PhysicsEngine();

        mParticleSystem = new ParticleSystem();
        mParticleSystem.init(1000);

    }
    public void stopThread() {
        // New code here soon
        mGameState.stopEverything();

        try {
            mThread.join();
        } catch (InterruptedException e) {
            Log.e("Exception","stopThread()" + e.getMessage());
        }
    }
    public void startThread() {
        // New code here soon
        mGameState.startThread();

        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    public void deSpawnReSpawn() {
        ArrayList<GameObject> objects = mLevel.getGameObjects();

        for(GameObject o : objects){
            o.setInactive();
        }
        Transform playerTransform = objects.get(Level.PLAYER_INDEX).getTransform();

        objects.get(Level.PLAYER_INDEX).spawn(playerTransform);
        objects.get(Level.BACKGROUND_INDEX).spawn(playerTransform);
        objects.get(Level.ASTERIOD_TOP).spawn(playerTransform);
        objects.get(Level.ASTEROID_TOP_OBLIQUE).spawn(playerTransform);
        objects.get(Level.ASTERIOD_BOTTOM_OBLIQUE).spawn(playerTransform);

        objects.get(Level.FIRST_ALIEN1).spawn(playerTransform);
        objects.get(Level.FIRST_ALIEN2).spawn(playerTransform);
        objects.get(Level.FIRST_ALIEN3).spawn(playerTransform);
        objects.get(Level.FIRST_ALIEN4).spawn(playerTransform);
        objects.get(Level.FIRST_ALIEN5).spawn(playerTransform);



    }

    @Override
    public void run() {
        while (mGameState.getThreadRunning()){
            long frameStartTime = System.currentTimeMillis();
            ArrayList<GameObject> objects = mLevel.getGameObjects();


            if(!mGameState.getPaused()){
                if (mPhysicsEngine.update(mFPS, objects, mGameState, mSoundEngine, mParticleSystem)) {
                    deSpawnReSpawn();

                }
            }



            mRenderer.draw(objects, mGameState, mHUD, mParticleSystem);

            long timeThisFrame = System.currentTimeMillis()
                    - frameStartTime;
            if (timeThisFrame >= 1) {
                final int MILLIS_IN_SECOND = 5000;
                mFPS = MILLIS_IN_SECOND / timeThisFrame;
            }
        }
    }

    @Override
    public void addObserver(InputObserver o) {
        inputObservers.add(o);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        for (InputObserver o : inputObservers) {
            o.handleInput(motionEvent, mGameState, mHUD.getControls());
        }



        return true;
    }

    @Override
    public boolean spawnPlayerLaser(Transform transform) {
        ArrayList<GameObject> objects = mLevel.getGameObjects();

        if(objects.get(Level.mNextPlayerLaser).spawn(transform)){
            mLevel.mNextPlayerLaser++;
            mSoundEngine.playShoot();
            if(Level.mNextPlayerLaser == Level.LAST_PLAYER_LASET){
                Level.mNextPlayerLaser = Level.FIRST_PLAYER_LASER;
            }
        }
        return true;
    }

    @Override
    public void spawnAlienLaser(Transform transform) {
        ArrayList<GameObject> objects = mLevel.getGameObjects();
        if(objects.get(Level.mNextAlienLaser).spawn(transform)){
            Level.mNextAlienLaser++;
            mSoundEngine.playShoot();
            if(Level.mNextAlienLaser == Level.LAST_ALIEN_LASER + 1){
                Level.mNextAlienLaser = Level.FIRST_ALIEN_LASER;
            }
        }
    }
}
