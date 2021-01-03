package com.gameschool.rocketwar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

import java.util.ArrayList;

public class HUD {
    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;

    private ArrayList<Circle> controls;

    static int UP = 0;
    static int DOWN = 1;
    static int FLIP = 2;
    static int SHOOT = 3;
    static int PAUSE = 4;
    static int LIVES1 = 5;
    static int LIVES2 = 6;
    static int LIVES3 = 7;

    Context mContext;
    HUD( Point size, Context context) {
        mScreenHeight = size.y;
        mScreenWidth = size.x;
        mTextFormatting = size.x / 50;
        mContext = context;

        prepareControls();
    }
    private void prepareControls(){
        int buttonWidth = mScreenWidth/14;
        int buttonHeight = mScreenWidth/12;
        int buttonPadding = mScreenWidth/90;
        int radius;
        int upСenterX;
        int upСenterY;

        int downСenterX;
        int downСenterY;

        int flipСenterX;
        int flipСenterY;

        int shootСenterX;
        int shootСenterY;

        int pauseСenterX;
        int pauseСenterY;

        int lives1СenterX;
        int lives1СenterY;

        int lives2СenterX;
        int lives2СenterY;

        int lives3СenterX;
        int lives3СenterY;



        // Первый круг кнопка UP
        /*Rect up = new Rect(buttonPadding, mScreenHeight-2*(buttonPadding + buttonHeight),
                buttonWidth+buttonPadding, mScreenHeight - 2*buttonPadding - buttonHeight);*/
        upСenterX = ( buttonPadding + buttonWidth+buttonPadding)/2;
        upСenterY = ( mScreenHeight-2*(buttonPadding + buttonHeight) + mScreenHeight - 2*buttonPadding - buttonHeight)/2;
        radius = ((buttonWidth+buttonPadding - (buttonPadding))*2)/3;

        Circle up = new Circle(upСenterX, upСenterY, radius, UP , mContext);





        // Второй круг кнопка DOWN
        /*Rect down = new Rect(buttonPadding, mScreenHeight - buttonPadding - buttonHeight,
                buttonWidth+buttonPadding, mScreenHeight - buttonPadding);*/
        downСenterX = ( buttonPadding + buttonWidth+buttonPadding )/2;
        downСenterY = ( mScreenHeight - buttonPadding - buttonHeight + mScreenHeight - buttonPadding )/2;
        Circle down = new Circle(downСenterX, downСenterY, radius, DOWN , mContext);






        // Третий круг кнопка FLIP
        /*Rect flip = new Rect(mScreenWidth - (buttonPadding+buttonWidth), mScreenHeight-2*(buttonPadding+buttonHeight),
                mScreenWidth - buttonPadding, mScreenHeight-buttonPadding- buttonHeight - buttonPadding);*/
        flipСenterX = (mScreenWidth - (buttonPadding+buttonWidth) + mScreenWidth - buttonPadding)/2;
        flipСenterY = ((mScreenHeight-2*(buttonPadding+buttonHeight) +  mScreenHeight-buttonPadding- buttonHeight - buttonPadding))/2;
        Circle flip = new Circle(flipСenterX, flipСenterY, radius, FLIP , mContext);






        // Четвертый круг кнопка SHOOT
        /*Rect shoot = new Rect(mScreenWidth - (buttonPadding+buttonWidth), mScreenHeight - buttonHeight-buttonPadding,
                mScreenWidth - buttonPadding,mScreenHeight - buttonPadding);*/
        shootСenterX = ( mScreenWidth - (buttonPadding+buttonWidth) +  mScreenWidth - buttonPadding)/2;
        shootСenterY = ( mScreenHeight - buttonHeight-buttonPadding + mScreenHeight - buttonPadding)/2;
        Circle shoot = new Circle(shootСenterX, shootСenterY,radius, SHOOT , mContext);





        // Пятый круг кнопка Pause
        /*Rect pause = new Rect(mScreenWidth - (buttonWidth+buttonPadding), buttonPadding,
                mScreenWidth - buttonPadding, buttonPadding+buttonHeight);*/
        pauseСenterX = ( mScreenWidth - (buttonWidth+buttonPadding) + mScreenWidth - buttonPadding )/2;
        pauseСenterY = ( buttonPadding + buttonPadding+buttonHeight )/2;
        Circle pause = new Circle(pauseСenterX, pauseСenterY,radius, PAUSE ,mContext);


        /*Rect lives1 = new Rect(
                buttonPadding,
                buttonPadding,
                buttonPadding+buttonWidth,
                buttonPadding + buttonHeight);*/

        lives1СenterX = ( buttonPadding + buttonPadding+buttonWidth )/2;
        lives1СenterY = ( buttonPadding +  buttonPadding + buttonHeight )/2;

        Circle lives1 = new Circle(lives1СenterX, lives1СenterY, radius/2, LIVES1 , mContext);

        /*Rect lives2 = new Rect(
                buttonPadding+ buttonWidth + buttonPadding,
                buttonPadding,
                buttonPadding+buttonWidth + buttonPadding+buttonWidth,
                buttonPadding + buttonHeight);*/

        lives2СenterX = ( buttonPadding+ buttonWidth + buttonPadding +  buttonPadding+buttonWidth + buttonPadding+buttonWidth) /2;
        lives2СenterY = (buttonPadding +  buttonPadding + buttonHeight ) /2;

        Circle lives2 = new Circle(lives2СenterX, lives2СenterY , radius/2, LIVES1 , mContext);

        /*Rect lives3 = new Rect(
                buttonPadding+buttonWidth + buttonPadding+buttonWidth + buttonPadding,
                buttonPadding,
                buttonPadding+buttonWidth + buttonPadding+buttonWidth + buttonPadding + buttonWidth,
                buttonPadding +  buttonPadding + buttonHeight);*/

        lives3СenterX = (buttonPadding+buttonWidth + buttonPadding+buttonWidth + buttonPadding + buttonPadding+buttonWidth + buttonPadding+buttonWidth + buttonPadding + buttonWidth)/2;
        lives3СenterY = ( buttonPadding +  buttonPadding + buttonHeight  )/2;

        Circle lives3 = new Circle(lives3СenterX, lives3СenterY, radius/2, LIVES1 , mContext);




        controls = new ArrayList<>();
        controls.add(UP,up);
        controls.add(DOWN,down);
        controls.add(FLIP,flip);
        controls.add(SHOOT,shoot);
        controls.add(PAUSE,pause);
        controls.add(LIVES1,lives1);
        controls.add(LIVES2,lives2);
        controls.add(LIVES3,lives3);



    }

    public void draw(Canvas c, Paint p, GameState gs){

        p.setColor(Color.argb(255, 255, 255, 255));
        p.setTextSize(mTextFormatting);
        c.drawText("Hi: " + gs.getHighScore(), mTextFormatting, mTextFormatting,p);
        c.drawText("Scrore: " + gs.getScore(), mTextFormatting, 2*mTextFormatting, p);
        c.drawText("Lives: " + gs.getNumShips(), mTextFormatting, 3*mTextFormatting,p);

        for(int i = 0; i < gs.getNumShips(); i++){

        }

        if(gs.getGameOver()){
            p.setTextSize(mTextFormatting*5);
            c.drawText("PRESS PLAY", mScreenWidth/4, mScreenHeight/2,p);
        }
        if(gs.getPaused() && !gs.getGameOver()){
            p.setTextSize(mTextFormatting*5);
            c.drawText("PAUSED", mScreenWidth/3, mScreenHeight/2,p);
        }
        drawControls(c,p);
    }

    public void drawControls( Canvas c, Paint p){
        p.setColor(Color.argb(255,33,34,87));

        for(Circle r: controls){
            p.setAlpha(120);
            c.drawCircle(r.positionX, r.positionY,r.radius,p);
            c.drawBitmap(r.getBitmapButton(),
                    r.getTopXCoordinateButton(), r.getTopYCoordinateButton(), p);
        }

        p.setColor(Color.argb(255,255,255,255));
    }

    ArrayList<Circle> getControls(){ return controls;}
}

