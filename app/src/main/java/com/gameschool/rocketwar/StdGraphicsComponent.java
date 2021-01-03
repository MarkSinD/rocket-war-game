package com.gameschool.rocketwar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

public class StdGraphicsComponent implements GraphicsComponent {

    private Bitmap mBitmap;
    private Bitmap mBitmapReversed;

    @Override
    public void initialize(Context c, ObjectSpec s, PointF objectSize) {
        int resID = c.getResources().
                getIdentifier(s.getBitmapName(),"drawable", c.getPackageName());

        mBitmap = BitmapFactory.decodeResource(c.getResources(), resID);

        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int)objectSize.x,
                (int)objectSize.y, false);

        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        mBitmapReversed = Bitmap.createBitmap(mBitmap,
                0,0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);


    }

    @Override
    public void draw(Canvas canvas, Paint paint, Transform t) {
        if(t.getFacingRight())
            canvas.drawBitmap(mBitmap, t.getLocation().x,
                    t.getLocation().y, paint);

        else
            canvas.drawBitmap(mBitmapReversed, t.getLocation().x,
                    t.getLocation().y, paint);
    }



}
