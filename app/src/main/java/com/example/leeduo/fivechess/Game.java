package com.example.leeduo.fivechess;
import android.content.Context;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


/**
 * Created by LeeDuo on 2018/10/14.
 */

public class Game extends View implements Runnable {


    private int screenWidth,screenHeight;
    private volatile State mystate;
    private Context myContext;
    private volatile Boolean running;
    private Thread gameThread;
    private long everyFrameTime = 12;
    private long renderTime = 0,fromTime = 0, toTime = 0;

    public Game(Context context) {
        super(context);
        screenWidth = GameUtils.getScreenWidth(context);
        screenHeight = GameUtils.getScreenHeight(context);
        myContext = context;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mystate.render(canvas,myContext);
    }


    public void setCurrentState(State state){
        state.init();
        mystate = state ;
        GameHandler.setCurrentState(state);
    }
    public  State getCurrentState(){
            return mystate;

    }

    public void initGame(){
        running = true;
        Resources.load(myContext);
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        while(running){
            fromTime = System.currentTimeMillis();
            mystate.update();
            postInvalidate();
            toTime = System.currentTimeMillis();
            try {
                renderTime = toTime - fromTime;
                Thread.sleep(Math.max(5,everyFrameTime-renderTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public  void exit(){
        running = false;
    }

}
