package com.example.leeduo.fivechess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by LeeDuo on 2018/10/17.
 */

public class Chess {

    private Context context;
    private int x,y;
    private int r;
    private int mod,consult;
    private Paint paint;

    public Chess(Context context){
        this.context = context;
        r = 20;
        paint = new Paint();
    }

    public void init(){}

    public void update(){}

    public void render(Canvas canvas){
        canvas.drawCircle(x,y,r,paint);

    }
    //棋子位置修正X
    public int correctLocationX(int chessFromX,int lineToLine, int x){
        mod = (x-chessFromX)%lineToLine;
        if(mod <lineToLine/2){
            return (x - mod);
        }else{
            return (x - mod + lineToLine);
        }
    }
    //棋子位置修正Y
    public int correctLocationY(int chessFromY,int lineToLine, int y){
        mod = (y - chessFromY)%lineToLine;
        consult = (y - mod)/lineToLine;
        if(mod <lineToLine/2){
            return (y - mod);
        }else{
            return (y - mod + lineToLine);
        }
    }
    //判断位置是否在棋盘里
    public boolean effectivePlaced(int x,int y,int chessFromX,int chessFromY,int firstLineToLine,int lineToLine){
        x = correctLocationX(chessFromX,lineToLine,x);
        y = correctLocationY(chessFromY,lineToLine,y);
        if(x < chessFromX){
            return false;
        }else if (x > (chessFromX + firstLineToLine)){
            return false;
        }else if (y < chessFromY){
            return false;
        }else if (y > (chessFromY + firstLineToLine)){
            return false;
        }
        return true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setPaintColor (int color){
        paint.setColor(color);
    }
}
