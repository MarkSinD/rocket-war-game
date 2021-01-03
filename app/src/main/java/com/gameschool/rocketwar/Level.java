package com.gameschool.rocketwar;

import android.content.Context;
import android.graphics.PointF;

import java.util.ArrayList;

public class Level {

    // Keep track of specific types
    public static final int BACKGROUND_INDEX  = 0;
    public static final int PLAYER_INDEX = 1;
    public static final int FIRST_PLAYER_LASER = 2;
    public static final int LAST_PLAYER_LASET = 8;
    public static final int ASTERIOD_TOP = 9;
    public static final int ASTEROID_TOP_OBLIQUE = 10;
    public static final int ASTERIOD_BOTTOM_OBLIQUE = 11;
    public static final int FIRST_ALIEN_LASER = 12;
    public static final int LAST_ALIEN_LASER = 16;
    public static final int FIRST_ALIEN1 = 17;
    public static final int FIRST_ALIEN2 = 18;
    public static final int FIRST_ALIEN3 = 19;
    public static final int FIRST_ALIEN4 = 20;
    public static final int FIRST_ALIEN5 = 21;



    public static int mNextAlienLaser;
    public static int mNextPlayerLaser;
    public static int countOfAsteroids = 3;

    // This will hold all the instances of GameObject
    private ArrayList<GameObject> objects;

    public Level(Context context,
                 PointF mScreenSize,
                 GameEngine ge){

        objects = new ArrayList<>();
        GameObjectFactory factory = new GameObjectFactory(
                context, mScreenSize, ge);
        buildGameObjects(factory);
    }

    ArrayList<GameObject> buildGameObjects(
            GameObjectFactory factory){

        objects.clear();
        objects.add(BACKGROUND_INDEX, factory.create(new BackgroundSpec()));
        objects.add(PLAYER_INDEX, factory.create(new PlayerSpec()));




        for(int i = FIRST_PLAYER_LASER; i <= LAST_PLAYER_LASET; i++) {
            objects.add(i, factory.create((new PlayerLaserSpec())));
        }

        objects.add(ASTERIOD_TOP, factory.create(new AsteroidTOPSpec()));
        objects.add(ASTEROID_TOP_OBLIQUE, factory.create(new AstroidTOPobliqueSpec()));
        objects.add(ASTERIOD_BOTTOM_OBLIQUE, factory.create(new AsteroidBottomSpec()));

        for(int i = FIRST_ALIEN_LASER; i <= LAST_ALIEN_LASER; i++ ){
            objects.add(i , factory.create(new AlienLaserSpec()));
        }


        objects.add(FIRST_ALIEN1, factory.create(new AlienChaseSpec()));
        objects.add(FIRST_ALIEN2, factory.create(new AlienChaseSpec()));
        objects.add(FIRST_ALIEN3, factory.create(new AlienPlateSpec()));
        objects.add(FIRST_ALIEN4, factory.create(new AlienPatrolSpec()));
        objects.add(FIRST_ALIEN5, factory.create(new AlienPatrolSpec()));
        mNextAlienLaser = FIRST_ALIEN_LASER;


        mNextPlayerLaser = FIRST_PLAYER_LASER;


        return objects;
    }

    ArrayList<GameObject> getGameObjects(){
        return objects;
    }

}
