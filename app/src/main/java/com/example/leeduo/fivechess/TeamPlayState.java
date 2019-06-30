package com.example.leeduo.fivechess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by LeeDuo on 2018/10/17.
 */

public class TeamPlayState extends State {

    private int vLineNumber;
    private Paint paint,textPaint;
    private Game game;
    private int screenWidth,screenHeight,chessFromX,chessFromY,firstToLastLine,lineNumber, lineToLine;
    private Boolean begin = false,turnToBlack = true,winFlag = false;
    private Recorder recorder;
    private Chess chess;
    //screenWidth:屏幕宽度 screenHeight:屏幕高度
    //chessFromX:棋盘左上角起始X轴坐标 chessFromY:棋盘左上角起始Y坐标
    //firstToLastLine;棋盘第一根线到最后一根线的距离
    //lineNumber;棋盘线的根数
    //lineToLine;棋盘相邻两条线的间距
    public TeamPlayState(Game game) {
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
        chess = new Chess(game.getContext());
        chess.init();
        recorder = new Recorder();
        recorder.clear();
    }

    @Override
    public void update() {
            chess.update();
    }

    @Override
    public void render(Canvas canvas, Context context) {
        //drawChessBoard
        canvas.drawColor(Color.parseColor("#e7e3cb"));
            for(vLineNumber = 0;vLineNumber<lineNumber;vLineNumber++){
                canvas.drawLine(chessFromX+ lineToLine *vLineNumber,chessFromY,chessFromX+ lineToLine *vLineNumber,chessFromY+firstToLastLine,paint);
                canvas.drawLine(chessFromX,chessFromY+ lineToLine *vLineNumber,chessFromX+firstToLastLine,chessFromY+ lineToLine *vLineNumber,paint);
            }
        //drawPoint
        canvas.drawCircle(getChessFromX()+firstToLastLine/2,getChessFromY()+firstToLastLine/2,10,paint);
        canvas.drawCircle(getChessFromX()+lineToLine*3,getChessFromY()+lineToLine*3,10,paint);
        canvas.drawCircle(getChessFromX()+lineToLine*11,getChessFromY()+lineToLine*3,10,paint);
        canvas.drawCircle(getChessFromX()+lineToLine*3,getChessFromY()+lineToLine*11,10,paint);
        canvas.drawCircle(getChessFromX()+lineToLine*11,getChessFromY()+lineToLine*11,10,paint);
        //drawChess
        if(begin == true){
            //drawChess
            for(String key :recorder.getMap().keySet()){
                chess.setX(recorder.getXLocation(key));
                chess.setY(recorder.getYLocation(key));
                if(recorder.colorIsBlack(key)){
                    chess.setPaintColor(Color.BLACK);
                }else {
                    chess.setPaintColor(Color.WHITE);
                }
                chess.render(canvas);
            }
        }
        //drawText
        textPaint.setTextSize(150);
        textPaint.setColor(Color.BLACK);
        canvas.drawText("五  子  棋",(screenWidth/2)-320,getChessFromY()-200,textPaint);
        if(turnToBlack){
            if(winFlag == true){
                textPaint.setTextSize(70);
                textPaint.setColor(Color.BLACK);
                canvas.drawText("白方获胜，点击屏幕重新开始",(screenWidth/2)-450,getChessFromY()+firstToLastLine+300,textPaint);
            }else {
                textPaint.setTextSize(100);
                textPaint.setColor(Color.BLACK);
                canvas.drawText("黑棋",(screenWidth/2)-100,getChessFromY()+firstToLastLine+200,textPaint);
            }
        }else if(!turnToBlack){
            if(winFlag == true){
                textPaint.setTextSize(70);
                textPaint.setColor(Color.BLACK);
                canvas.drawText("黑方获胜，点击屏幕重新开始",(screenWidth/2)-450,getChessFromY()+firstToLastLine+300,textPaint);
            }else {
                textPaint.setTextSize(100);
                textPaint.setColor(Color.WHITE);
                canvas.drawText("白棋",(screenWidth/2)-100,getChessFromY()+firstToLastLine+200,textPaint);
            }
        }
    }

    @Override
    public void gameEvent(MotionEvent event) {
              switch (event.getAction()){
                  case MotionEvent.ACTION_DOWN:
                      begin = true;
                      //如果没人胜利
                      if(winFlag == false){
                          //判断点击区域是否在棋盘外面
                          if(chess.effectivePlaced((int) event.getX(),(int) event.getY(),getChessFromX(),getChessFromY(),getFirstToLastLine(),getLineToLine())){
                              //判断点击位置是否存在棋子
                              if(recorder.exist(String.valueOf(chess.correctLocationX(getChessFromX(),getLineToLine(),(int)event.getX()))+","+String.valueOf(chess.correctLocationY(getChessFromY(),getLineToLine(),(int)event.getY())))){
                                  Toast.makeText(game.getContext(),"棋子已经存在",Toast.LENGTH_SHORT).show();
                              }else {
                                  //设置棋子修正后的位置
                                  chess.setX(chess.correctLocationX(getChessFromX(),getLineToLine(),(int)event.getX()));
                                  chess.setY(chess.correctLocationY(getChessFromY(),getLineToLine(),(int)event.getY()));
                                  //通过判断下一步轮到黑或者白，进行存储
                                  if(turnToBlack){
                                      recorder.put(chess.getX()+","+chess.getY(),true);
                                      turnToBlack = false;
                                  }else {
                                      recorder.put(chess.getX()+","+chess.getY(),false);
                                      turnToBlack = true;
                                  }
                                  //判断是否有人胜利
                                  if (recorder.isWin(getChessFromX(),getChessFromY(),getLineToLine(),getLineNumber())){
                                          winFlag = true;
                                  }
                              }
                          }
                      }else{
                          recorder.clear();
                          winFlag = false;
                          turnToBlack = true;
                      }

                      break;
              }
    }


    public int getvLineNumber() {
        return vLineNumber;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getChessFromX() {
        return chessFromX;
    }

    public int getChessFromY() {
        return chessFromY;
    }

    public int getFirstToLastLine() {
        return firstToLastLine;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getLineToLine() {
        return lineToLine;
    }
}
