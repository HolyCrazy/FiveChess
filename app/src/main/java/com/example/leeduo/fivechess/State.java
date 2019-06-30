package com.example.leeduo.fivechess;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by LeeDuo on 2018/10/14.
 */

public  abstract class State {
    public abstract void init();
    public abstract void update();
    public abstract void render(Canvas canvas, Context context);
    public abstract void gameEvent(MotionEvent event);
    public State(Game game){
    }

}
