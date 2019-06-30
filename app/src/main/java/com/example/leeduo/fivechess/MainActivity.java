package com.example.leeduo.fivechess;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private Game game;
    public static Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        typeface = Typeface.createFromAsset(getAssets(),"myTypeface.ttf");
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        game = new Game(MainActivity.this);
        game.setCurrentState(new MenuState(game));
        game.initGame();
        frameLayout.addView(game);
        game.setOnTouchListener(new GameHandler());


    }
}
