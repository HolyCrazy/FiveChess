package com.example.leeduo.fivechess;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by LeeDuo on 2018/12/9.
 */

public class AIRobot {
    private  Recorder recorder;
    private int blackPoint=0;
    private int whitePoint=0;
    private int blankPoint=0;
    private int lineToLine,fromX,fromY,firstToLastLine;
    private int X,Y,i,j,counter,value,standardValue;
    private Point point1,point2;
    private String[] probLocation1,probLocation2;
    private Map<String,Integer> map;
    private String bestPoint,tempLocation;
    public AIRobot(Recorder recorder,int lineToLine,int fromX,int fromY,int firstToLastLine){
        point1 = new Point();
        point2 = new Point();
        this.recorder = recorder;
        this.lineToLine = lineToLine;
        this.fromX = fromX;
        this.fromY = fromY;
        this.firstToLastLine = firstToLastLine;
        blackPoint=0;
        whitePoint=0;
        blankPoint=0;
        X = 0;
        Y = 0;
        map = new HashMap<>();
    }

    public void setRecorder(Recorder recorder) {
        this.recorder = recorder;
    }
    public void think(){
        map.clear();
        for(String key :recorder.getMap().keySet()){
            X = Integer.valueOf(recorder.getXLocation(key));
            Y = Integer.valueOf(recorder.getYLocation(key));
            //下一步只在有棋子的附近下
            point1.createPoint(X,Y);
            probLocation1 = point1.getString();
            //创建潜在的位置点
            for (i=0;i<8;i++){
                //若该点没人下，可以作为潜在点
                if(!recorder.exist(probLocation1[i])){
                    //统计开始
                    point2.createPoint(recorder.getXLocation(probLocation1[i]),recorder.getYLocation(probLocation1[i]));
                    probLocation2 = point2.getString();


                    for(j=0;j<8;j++){
                        if(recorder.exist(probLocation2[j])){
                            if(recorder.colorIsBlack(probLocation2[j])){
                                blackPoint = blackPoint+1;
                            }else{
                                whitePoint = whitePoint+1;
                            }
                        }else {
                            blankPoint = blankPoint+1;
                        }
                    }

                    //统计结束
                    //value = blankPoint*3 + whitePoint*5 - blackPoint*8;
                    value = blankPoint*1 + whitePoint*4 + blackPoint*2;
                    map.put(probLocation1[i],value);
                    clear();
                }
            }

        }
        thinkAgain(map,recorder);
    }

    private void thinkAgain(Map<String,Integer> map,Recorder recorder){
        counter =0;
        for(String s:map.keySet()){
            X = Integer.valueOf(recorder.getXLocation(s));
            Y = Integer.valueOf(recorder.getYLocation(s));
            value = map.get(s);
            //左
            for(i=1;i<5;i++){
                tempLocation = String.valueOf(X-i*lineToLine)+","+String.valueOf(Y);
                if(recorder.exist(tempLocation)){
                    if(recorder.colorIsBlack(tempLocation)){
                        counter = counter+1;
                    }else{
                        counter =0;
                    }
                }
            }
            value = value +counterIs3(counter)+counterIs4(counter);
            counter = 0;
            //右
            for(i=1;i<5;i++){
                tempLocation = String.valueOf(X+i*lineToLine)+","+String.valueOf(Y);
                if(recorder.exist(tempLocation)){
                    if(recorder.colorIsBlack(tempLocation)){
                        counter = counter+1;
                    }else{
                        counter =0;
                    }
                }
            }
            value = value +counterIs3(counter)+counterIs4(counter);
            counter = 0;
            //上
            for(i=1;i<5;i++){
                tempLocation = String.valueOf(X)+","+String.valueOf(Y-i*lineToLine);
                if(recorder.exist(tempLocation)){
                    if(recorder.colorIsBlack(tempLocation)){
                        counter = counter+1;
                    }else{
                        counter =0;
                    }
                }
            }
            value = value +counterIs3(counter)+counterIs4(counter);
            counter = 0;
            //下
            for(i=1;i<5;i++){
                tempLocation = String.valueOf(X)+","+String.valueOf(Y+i*lineToLine);
                if(recorder.exist(tempLocation)){
                    if(recorder.colorIsBlack(tempLocation)){
                        counter = counter+1;
                    }else{
                        counter =0;
                    }
                }
            }
            value = value +counterIs3(counter)+counterIs4(counter);
            counter = 0;
            //左上
            for(i=1;i<5;i++){
                tempLocation = String.valueOf(X-i*lineToLine)+","+String.valueOf(Y-i*lineToLine);
                if(recorder.exist(tempLocation)){
                    if(recorder.colorIsBlack(tempLocation)){
                        counter = counter+1;
                    }else{
                        counter =0;
                    }
                }
            }
            value = value +counterIs3(counter)+counterIs4(counter);
            counter = 0;
            //左下
            for(i=1;i<5;i++){
                tempLocation = String.valueOf(X-i*lineToLine)+","+String.valueOf(Y+i*lineToLine);
                if(recorder.exist(tempLocation)){
                    if(recorder.colorIsBlack(tempLocation)){
                        counter = counter+1;
                    }else{
                        counter =0;
                    }
                }
            }
            value = value +counterIs3(counter)+counterIs4(counter);
            counter = 0;
            //右上
            for(i=1;i<5;i++){
                tempLocation = String.valueOf(X+i*lineToLine)+","+String.valueOf(Y-i*lineToLine);
                if(recorder.exist(tempLocation)){
                    if(recorder.colorIsBlack(tempLocation)){
                        counter = counter+1;
                    }else{
                        counter =0;
                    }
                }
            }
            value = value +counterIs3(counter)+counterIs4(counter);
            counter = 0;
            //右下
            for(i=1;i<5;i++){
                tempLocation = String.valueOf(X+i*lineToLine)+","+String.valueOf(Y+i*lineToLine);
                if(recorder.exist(tempLocation)){
                    if(recorder.colorIsBlack(tempLocation)){
                        counter = counter+1;
                    }else{
                        counter =0;
                    }
                }
            }
            value = value +counterIs3(counter)+counterIs4(counter);
            counter = 0;

          map.put(s,value);
        }
    }

    private int counterIs3(int counter){
        if(counter == 3){
            counter = 0;
            return 40;
        }else{
            counter = 0;
            return 0;
        }
    }

    private int counterIs4(int counter){
        if(counter == 4){
            counter = 0;
            return 60;
        }else{
            counter = 0;
            return 0;
        }
    }

    private void clear(){
        blackPoint =0;
        blankPoint =0;
        whitePoint =0;
        standardValue = -40;

    }

    public String getBestPoint(){
        standardValue = -1;
        for (String s:map.keySet()){
            if(!recorder.exist(s)){
                if(recorder.getXLocation(s)>fromX && recorder.getXLocation(s)<(fromX+firstToLastLine)){
                    if (recorder.getYLocation(s)>fromY && recorder.getYLocation(s)<(fromY+firstToLastLine)){
                        if(map.get(s)>standardValue){
                            standardValue = map.get(s);
                            bestPoint = s;
                        }
                    }
                }

            }
        }
        map.clear();
        return bestPoint;
    }

    public  class Point{
        private String[] string;
        public Point(){
            string = new String[8];
        }
        public  void createPoint(int x,int y){
            string[0] = String.valueOf(x-lineToLine)+","+String.valueOf(y-lineToLine);
            string[1] = String.valueOf(x)+","+String.valueOf(y-lineToLine);
            string[2] = String.valueOf(x+lineToLine)+","+String.valueOf(y-lineToLine);
            string[3] = String.valueOf(x-lineToLine)+","+String.valueOf(y);
            string[4] = String.valueOf(x+lineToLine)+","+String.valueOf(y);
            string[5] = String.valueOf(x-lineToLine)+","+String.valueOf(y+lineToLine);
            string[6] = String.valueOf(x)+","+String.valueOf(y+lineToLine);
            string[7] = String.valueOf(x+lineToLine)+","+String.valueOf(y+lineToLine);
        }

        public  String[] getString() {
            return string;
        }
    }
}
