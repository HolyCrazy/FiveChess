package com.example.leeduo.fivechess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by LeeDuo on 2018/12/9.
 */

public class MenuState extends State {

    private Paint paint,textPaint;
    private Game game;
    private int screenWidth,screenHeight,chessFromX,chessFromY,firstToLastLine,lineNumber, lineToLine;
    //screenWidth:屏幕宽度 screenHeight:屏幕高度
    //chessFromX:棋盘左上角起始X轴坐标 chessFromY:棋盘左上角起始Y坐标
    //firstToLastLine;棋盘第一根线到最后一根线的距离
    //lineNumber;棋盘线的根数
    //lineToLine;棋盘相邻两条线的间距
    public MenuState(Game game) {
        super(game);
        this.game = game;
        screenHeight = GameUtils.getScreenHeight(game.getContext());
        screenWidth = GameUtils.getScreenWidth(game.getContext());
        lineNumber = 15;
        lineToLine = 60;
        chessFromX = (screenWidth-(lineNumber-1)* lineToLine)/2;
        chessFromY = (screenHeight-(lineNumber-1)* lineToLine)/2;
        firstToLastLine = lineToLine *(lineNumber-1);
    }

    @Override
    public void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        textPaint = new Paint();
        textPaint.setTypeface(MainActivity.typeface);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Canvas canvas, Context context) {
        //drawChessBoard
        canvas.drawColor(Color.parseColor("#e7e3cb"));
        //drawText
        textPaint.setTextSize(150);
        textPaint.setColor(Color.BLACK);
        canvas.drawText("五  子  棋",(GameUtils.getScreenWidth(game.getContext())/2)-320,getChessFromY()-200,textPaint);
        textPaint.setTextSize(100);
        canvas.drawText("单人模式",(screenWidth/2)-220,getChessFromY()+300,textPaint);
        canvas.drawText("双人模式",(screenWidth/2)-220,getChessFromY()+600,textPaint);
    }

    @Override
    public void gameEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (event.getX()>(screenWidth/2)-220 && event.getX()<(screenWidth/2)+220){
                    if(event.getY()>getChessFromY()+180 && event.getY()<getChessFromY()+350){
                        game.setCurrentState(new SinglePlayState(game));
                    }
                    if(event.getY()>getChessFromY()+480 && event.getY()<getChessFromY()+650){
                        game.setCurrentState(new TeamPlayState(game));
                    }
                }
                break;
        }
    }

    public int getChessFromY() {
        return chessFromY;
    }

}
