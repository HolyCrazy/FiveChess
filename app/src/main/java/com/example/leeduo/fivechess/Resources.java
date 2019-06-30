package com.example.leeduo.fivechess;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

/**
 * Created by LeeDuo on 2018/10/14.
 */

public class Resources {
    public static MediaPlayer mediaPlayer1,mediaPlayer2;
    public static Bitmap bitmap,background;

    public  static void load(Context context){
        if(background == null){
           // background = loadImage(R.drawable.background,context);
        }
        if(mediaPlayer1 == null){
           // mediaPlayer1 = MediaPlayer.create(context,R.raw.bounce);
        }
        if(mediaPlayer2 == null){
           // mediaPlayer2 = MediaPlayer.create(context,R.raw.fail);
        }
    }

    public static void loadSound(MediaPlayer mediaPlayer){
        mediaPlayer.start();
    }

    public static Bitmap loadImage(int imageReDrawble, Context context){
        bitmap = BitmapFactory.decodeResource(context.getResources(),imageReDrawble);
        return bitmap;
    }
    public static void stopSound(MediaPlayer mediaPlayer){
        mediaPlayer.stop();
    }
}
