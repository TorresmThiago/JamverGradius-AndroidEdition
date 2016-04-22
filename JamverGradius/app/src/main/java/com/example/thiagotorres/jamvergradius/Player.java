package com.example.thiagotorres.jamvergradius;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.List;

/**
 * Created by Thiago.Torres on 08/04/2016.
 */

public class Player {

    private Paint paint = new Paint();
    private int posX = 100;
    private int posY = 100;
    private int currentFrame = 0;
    private Bitmap image;
    private int shipSize;
    private int canvasWidth, canvasHeight;
    public Rect body = new Rect();
    private boolean start = true;

    public Player(Bitmap bitmap) {
        image = bitmap;
        shipSize = image.getWidth() / 10;
    }

    int cannonPositionX(){
        return posX + image.getWidth() / 20;
    }

    int cannonPositionY(){
        return posY;
    }

    void drawPlayer(Canvas canvas){
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        Bitmap sprite = Bitmap.createBitmap(image, shipSize * currentFrame, 0, shipSize, image.getHeight());
        body = new Rect();
        body.set(posX, posY, posX + image.getWidth() / 10, posY + image.getHeight());
        canvas.drawBitmap(sprite, posX, posY, paint);
    }

    private void canvasBoundary(){
        if (posX <= 0) {
            posX = 0;
        } else if(posX >= canvasWidth - shipSize){
            posX = canvasWidth - shipSize;
        }

        if (posY + image.getHeight() >= canvasHeight){
            posY = canvasHeight - image.getHeight();
        } else if (posY <= 0){
            posY = 0;
        }

    }

    void updatePlayer(int x, int y){

        if(start){
            posX = canvasWidth / 2 - shipSize / 2;
            posY = canvasHeight / 3;
            start = false;
        }


        posY += y * 3;
        posX -= x * 2;

        canvasBoundary();

        currentFrame = currentFrame + 1;
        if (currentFrame > 9) currentFrame = 0;
    }

}
