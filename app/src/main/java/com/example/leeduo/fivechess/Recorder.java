package com.example.leeduo.fivechess;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by LeeDuo on 2018/10/17.
 */

public class Recorder {
    private Map<String,Boolean> map;
    private String[] location;
    private int number1 = 0,number2 = 0,counter = 0;
    private String string,nextString;
    public Recorder(){
        map = new HashMap<>();
        location =new String[2];
    }
    //存数据
    public void put(String XandY,Boolean bool){
        map.put(XandY,bool);
    }
    //取数据
    public Boolean colorIsBlack(String XandY){
        return map.get(XandY);
    }
    //清空数据
    public void clear(){
        map.clear();
    }
    //判断该位置棋子是否存在
    public Boolean exist(String XandY){
        return map.containsKey(XandY);
    }
    //判断存入的数据数量
    public int getLength(){
        return map.size();
    }
    //解析字符串，返回X轴坐标
    public int getXLocation(String XandY){
        location = XandY.split(",");
        return Integer.parseInt(location[0]);
    }
    //解析字符串，返回Y轴坐标
    public int getYLocation(String XandY){
        location = XandY.split(",");
        return Integer.parseInt(location[1]);
    }
    //获取map
    public Map<String,Boolean> getMap(){
        return map;
    }
    //判断是否胜利，通过扫描
    public Boolean isWin(int chessFromX,int chessFromY,int lineToLine,int lineNumber){
        //纵向扫描
        for(number1 =0;number1<lineNumber;number1++){
            counter =0;
            for (number2 = 0;number2<(lineNumber-1);number2++){
                string = String.valueOf(chessFromX+number1*lineToLine)+","+String.valueOf(chessFromY+number2*lineToLine);
                nextString = String.valueOf(chessFromX+number1*lineToLine)+","+String.valueOf(chessFromY+lineToLine+number2*lineToLine);
                if(exist(string)&&exist(nextString)){
                    if(colorIsBlack(string) == colorIsBlack(nextString)){
                        counter =counter+1;
                        if (counter == 4){
                            counter =0;
                            return true;
                        }
                    }else {
                        counter =0;
                    }
                }else {
                    counter =0;
                }

            }
        }
        //横向扫描
        for(number1 =0;number1<lineNumber;number1++){
            counter =0;
            for (number2 = 0;number2<(lineNumber-1);number2++){
                string = String.valueOf(chessFromX+number2*lineToLine)+","+String.valueOf(chessFromY+number1*lineToLine);
                nextString = String.valueOf(chessFromX+lineToLine+number2*lineToLine)+","+String.valueOf(chessFromY+number1*lineToLine);
                if(exist(string)&&exist(nextString)){
                    if(colorIsBlack(string) == colorIsBlack(nextString)){
                        counter =counter+1;
                        if (counter == 4){
                            counter =0;
                            return true;
                        }
                    }else {
                        counter =0;
                    }
                }else {
                    counter =0;
                }

            }
        }
        //右下到左上扫描
        for(number1 = 0;number1<=lineNumber;number1++){
            counter =0;
            for(number2 = 0;number2<=15-number1;number2++){
                string = String.valueOf(chessFromX+number2*lineToLine+number1*lineToLine)+","+String.valueOf(chessFromY+number2*lineToLine);
                nextString = String.valueOf(chessFromX+number2*lineToLine+number1*lineToLine+lineToLine)+","+String.valueOf(chessFromY+number2*lineToLine+lineToLine);
                if(exist(string)&&exist(nextString)){

                    if(colorIsBlack(string) == colorIsBlack(nextString)){
                        counter =counter+1;
                        if (counter == 4){
                            counter =0;
                            return true;
                        }
                    }else {
                        counter =0;
                    }
                }else {
                    counter =0;
                }
            }

        }
        //左下到右上
        for(number1 = 0;number1<=lineNumber;number1++){
            counter =0;
            for(number2 = 0;number2<=15-number1;number2++){
                string = String.valueOf(chessFromX+number2*lineToLine)+","+String.valueOf(chessFromY+number2*lineToLine+number1*lineToLine);
                nextString = String.valueOf(chessFromX+number2*lineToLine+lineToLine)+","+String.valueOf(chessFromY+number2*lineToLine+number1*lineToLine+lineToLine);
                if(exist(string)&&exist(nextString)){

                    if(colorIsBlack(string) == colorIsBlack(nextString)){
                        counter =counter+1;
                        if (counter == 4){
                            counter =0;
                            return true;
                        }
                    }else {
                        counter =0;
                    }
                }else {
                    counter = 0;
                }
            }

        }
        //左上到右下
        for(number1 = 0;number1<=lineNumber;number1++){
            counter =0;
            for(number2 = 0;number2<=15-number1;number2++){
                string = String.valueOf(chessFromX+number2*lineToLine)+","+String.valueOf(chessFromY+(lineNumber+number1-number2)*lineToLine);
                nextString = String.valueOf(chessFromX+number2*lineToLine+lineToLine)+","+String.valueOf(chessFromY+(lineNumber+number1-number2)*lineToLine-lineToLine);
                if(exist(string)&&exist(nextString)){
                    if(colorIsBlack(string) == colorIsBlack(nextString)){
                        counter =counter+1;
                        Log.d(TAG, "isWin: "+counter);
                        if (counter == 4){
                            counter =0;
                            return true;
                        }
                    }else {
                        counter =0;
                    }
                }else {
                    counter = 0;
                }
            }
        }
        //左上到右下
        for(number1 = 0;number1<=lineNumber;number1++){
            counter =0;
            for(number2 = 0;number2<=15-number1;number2++){
                string = String.valueOf(chessFromX+number2*lineToLine)+","+String.valueOf(chessFromY+(lineNumber-number1-number2)*lineToLine);
                nextString = String.valueOf(chessFromX+number2*lineToLine+lineToLine)+","+String.valueOf(chessFromY+(lineNumber-number1-number2)*lineToLine-lineToLine);
                if(exist(string)&&exist(nextString)){
                    if(colorIsBlack(string) == colorIsBlack(nextString)){
                        counter =counter+1;
                        Log.d(TAG, "isWin: "+counter);
                        if (counter == 4){
                            counter =0;
                            return true;
                        }
                    }else {
                        counter =0;
                    }
                }else {
                    counter = 0;
                }
            }
        }

        return false;
    }

}
