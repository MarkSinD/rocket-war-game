package com.gameschool.rocketwar;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

public class GameObjectFactory {
    private Context mContext;
    private PointF mScreenSize;
    private GameEngine mGameEngineReference;

    GameObjectFactory(Context c, PointF mScreenSize, GameEngine gameEngine){

        this.mContext = c;
        this.mScreenSize = mScreenSize;
        mGameEngineReference = gameEngine;
    }

    GameObject create(ObjectSpec spec){
        GameObject object = new GameObject();

        int mNumComponents = spec.getComponents().length;

        final float HIDDEN = -2000f;

        object.setTag(spec.getTag());

        float mSpeed = mScreenSize.x / spec.getSpeed();

        PointF objectSize = new PointF(mScreenSize.x/spec.getScale().x,
                mScreenSize.y/spec.getScale().y);

        PointF mLocation = new PointF(HIDDEN, HIDDEN);


        object.setTransform( new Transform(mSpeed, objectSize.x,
                objectSize.y, mLocation, mScreenSize));



        for (int i = 0; i < mNumComponents; i++) {
            switch (spec.getComponents()[i]) {

                // Передает управление в PlayerInputComponent
                case "PlayerInputComponent":
                    object.setInput(new PlayerInputComponent
                            (mGameEngineReference));
                    break;

                case "StdGraphicsComponent":
                    object.setGraphics(new StdGraphicsComponent(),
                            mContext, spec, objectSize);
                    Log.i("LEVEL", "StdGraphicsComponent прошел");

                    break;

                case "PlayerMovementComponent":
                    object.setMovement(new PlayerMovementComponent());
                    break;


                case "PlayerSpawnComponent":
                    object.setSpawner(new PlayerSpawnComponent());
                    break;

                case "LaserMovementComponent":
                    object.setMovement(new LaserMovementComponent());
                    break;

                case "LaserSpawnComponent":
                    object.setSpawner(new LaserSpawnComponent());
                    break;

                case "BackgroundGraphicsComponent":
                    object.setGraphics(new BackgroundGraphicsComponent(),
                            mContext, spec, objectSize);
                    break;

                case "BackgroundMovementComponent":
                    object.setMovement(new BackgroundMovementComponent());
                    break;

                case "BackgroundSpawnComponent":
                    object.setSpawner(new BackgroundSpawnComponent());
                    break;

                case "AlienVerticalSpawnComponent":
                    object.setSpawner(new AlienVerticalSpawnComponent());
                    break;

                case "AsteroidMovementComponent":
                    object.setMovement(new AsteroidMovementComponent());
                    break;

                case "AsteroidObliqueMovementComponent":
                    object.setMovement(new AsteroidObliqueMovementComponent());
                    break;

                case "AsteroidBottomMovementComponent":
                    object.setMovement(new AsteroidBottomMovementComponent());
                    break;

                case "AlienVerticalBottomSpawnComponent":
                    object.setSpawner(new AlienVerticalBottomSpawnComponent());
                    break;

                case "AlienHorizontalSpawnComponent":
                    object.setSpawner(new AlienHorizontalSpawnComponent());
                    Log.i("LEVEL", "AlienHorizontalSpawnComponent прошел");
                    break;
                case "AlienChaseMovementComponent":
                    object.setMovement(
                            new AlienChaseMovementComponent(mGameEngineReference));
                    Log.i("LEVEL", "AlienChaseMovementComponent прошел");
                    break;

                case "AlienPatrolMovementComponent":
                    object.setMovement(
                            new AlienPatrolMovementComponent(mGameEngineReference));
                    Log.i("LEVEL", "AlienPatrolMovementComponent прошел");
                    break;

                case "AlienPlateMovementComponent":
                    object.setMovement(
                            new AlienPlateMovementComponent());
                    Log.i("LEVEL", "AlienPatrolMovementComponent прошел");
                    break;

                default:
                    break;
            }
        }
        return object;
    }
}
