package com.gameschool.rocketwar;


import android.graphics.PointF;
import android.graphics.RectF;

import java.util.ArrayList;

public class PhysicsEngine {

    boolean update(long fps, ArrayList<GameObject> objects, GameState gs, SoundEngine se, ParticleSystem ps){


        for(GameObject object : objects){
            if(object.checkActive()){
                object.update(fps, objects.get(Level.PLAYER_INDEX).getTransform());
            }
        }

        if(ps.mIsRunning){
            ps.update(fps);
        }

        return detectCollisions(gs, objects, se, ps);
    }


    private boolean detectCollisions(GameState mGameState, ArrayList<GameObject> objects,
                                     SoundEngine se, ParticleSystem ps){
        boolean playerHit = false;

        for(GameObject go1 : objects){
            if( go1.checkActive() ){
                for(GameObject go2 : objects){
                    if(go2.checkActive()){
                        if(RectF.intersects(go1.getTransform().getCollider(), go2.getTransform().getCollider())){
                            switch ( go1.getTag() + " with " + go2.getTag()){
                                case "Player with Alien Laser":
                                    playerHit = true;
                                    mGameState.loseLife(se);
                                    break;
                                case "Player with Alien":
                                    playerHit = true;
                                    mGameState.loseLife(se);
                                    break;
                                case "Player Laser with Alien":
                                    mGameState.increaseScore();
                                    ps.emitParticles(
                                            new PointF(
                                                    go2.getTransform().getLocation().x,
                                                    go2.getTransform().getLocation().y

                                            )
                                    );
                                    go2.setInactive();
                                    go2.spawn(objects.get(Level.PLAYER_INDEX).getTransform());
                                    go1.setInactive();
                                    se.playPlayerExplode();
                                    break;

                                    default:
                                        break;

                            }
                        }
                    }
                }
            }
        }
        return playerHit;
    }

}
