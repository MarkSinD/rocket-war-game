package com.gameschool.rocketwar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Random;

public class ParticleSystem {

    float mDuration;

    ArrayList<Particle> mParticles;
    Random random = new Random();
    boolean mIsRunning = false;

    // Для частицы рандомно выбирается угол и скорость
    void init( int numParticles){
        mParticles = new ArrayList<>();
        for( int i = 0; i < numParticles; i++){
            float angle = (random.nextInt(360));
            angle = angle * 3.14f/180.f;
            float speed = (random.nextInt(20)+1);
            PointF direction;
            direction = new PointF((float) Math.cos(angle) * speed,
                    (float)Math.sin(angle)* speed);
            mParticles.add(new Particle(direction));
        }
    }

    void update(long fps){
        mDuration -= (1f/fps);

        for( Particle p : mParticles){
            p.update(fps);
        }

        if( mDuration < 0 ){
            mIsRunning = false;
        }
    }

    void emitParticles(PointF startPosition){
        mIsRunning = true;
        mDuration = 1f;

        for(Particle p: mParticles){
            p.setPostion(startPosition);
        }
    }

    void draw(Canvas canvas, Paint paint){

        for(Particle p: mParticles){
            paint.setColor(Color.argb(255, 255,255,255));
            canvas.drawRect(p.getPosition().x, p.getPosition().y,
                    p.getPosition().x+5,p.getPosition().y+5, paint);
        }
    }
}
