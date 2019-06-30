package com.example.leeduo.fivechess;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by LeeDuo on 2018/10/14.
 */

public class GameHandler implements View.OnTouchListener {
    private  static State mystate;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mystate.gameEvent(event);
        //Toast.makeText(v.getContext(),mystate.getClass()+"", Toast.LENGTH_SHORT).show();
        return true;
    }
    public static void setCurrentState(State state){
        mystate = state;
    }
}
