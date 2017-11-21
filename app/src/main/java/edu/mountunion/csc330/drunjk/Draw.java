package edu.mountunion.csc330.drunjk;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by marianga on 11/19/2017.
 */

public class Draw extends View {
    private Paint paint;
    private int height, width;
    private double[] bacArray;
    private double initialHour;

    private void init() {
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10.0f);
        paint.setTextSize(40.0f);
    }

    public Draw(Context context, int w, int h, double[] d, double in) {
        super(context);
        height = h;
        width = w;
        bacArray = d;
        initialHour = in;
        paint = new Paint();
        init();
    }

    public Draw(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Draw(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onDraw(Canvas canvas) {
        int heightOffset = height/100;
        int weightOffset = width/100;
        int fromLeft = (20*(weightOffset)) + (5*weightOffset);
        int fromRight = (90*(weightOffset)) + (5*weightOffset);
        int fromTop = 10*(heightOffset);
        int fromBottom = 70*(heightOffset);
        int tikOffset = (fromRight-fromLeft)/5;
        int tikOffsetV = (fromBottom-fromTop)/6;
        int topToBottom = fromBottom-fromTop;
        int leftToRight = fromRight-fromLeft;
        double overLimit = bacArray[0]+.015;
        double upperLimit;
        double lowerLimit;

        String[] bacString = new String[5];
        for(int i = 0; i < 5; i++){
            bacString[i] = bacArray[i] + "";
            try {
                bacString[i] = bacString[i].substring(0, 6);
            } // end try to display bac with 2 digits to right of decimal
            catch (StringIndexOutOfBoundsException ex) {
            } // end catch if already less than shortened length
        }
        int[] tikD = tikDown(fromTop, tikOffsetV);
        int tikLeft = fromLeft-weightOffset;
        int tikRight = fromLeft+weightOffset;
        for (int i = 0; i < 6; i++) {
            canvas.drawLine(tikLeft, tikD[i], tikRight, tikD[i], paint);
        }
        if (bacArray[0]-0.075 < 0) {
            for (int i = 0; i < 6; i++) {
                String bacText = (.09-(.015*i))+"";
                if (bacText.length() == 4) {
                    canvas.drawText(bacText, tikLeft - (9 * weightOffset), tikD[i] + heightOffset, paint);
                }else{
                    canvas.drawText(bacText, tikLeft - (11 * weightOffset), tikD[i] + heightOffset, paint);
                }
            }
            upperLimit = .09;
            lowerLimit = (.09-(.015*6));
        }
        else{
            double init = findNearest();
            for (int i = 0; i < 6; i++) {
                String bacText = (init-(.015*i))+"";
                try {
                    bacText = bacText.substring(0, 5);
                } // end try to display bac with 5 digits
                catch (StringIndexOutOfBoundsException ex) {
                } // end catch if already less than shortened length
                canvas.drawText(bacText, tikLeft - (11 * weightOffset), tikD[i] + heightOffset, paint);
            }
            upperLimit = init;
            lowerLimit = (init-(.015*6));
        }
        if(.08 <= upperLimit && .08 > lowerLimit){
            paint.setColor(Color.GRAY);
            int legalLimitHeight = getPlacementHeight(.08, lowerLimit, topToBottom, fromBottom);
            canvas.drawLine(fromLeft, legalLimitHeight, fromRight, legalLimitHeight, paint);
            paint.setColor(Color.BLACK);
            canvas.drawText(".08", fromRight, legalLimitHeight+heightOffset, paint);
        }

        // vertical line
        canvas.drawLine(fromLeft, fromTop, fromLeft, fromBottom, paint);
        // horizontal line
        canvas.drawLine(fromLeft, fromBottom, fromRight, fromBottom, paint);

        String retVal = "Your Current Blood Alcohol Content is: " + bacString[0] + "%";
        canvas.drawText(retVal, 12*weightOffset, 90*heightOffset, paint);

        int[] tikO = tikOver(fromLeft, tikOffset);
        int tikUp = fromBottom-heightOffset;
        int tikDown = fromBottom+heightOffset;
        int hour = (int)initialHour;
        int dotHeight;
        int lineStartY=getAlternativePlacementHeight(bacArray[0]+.015, lowerLimit, topToBottom, fromBottom);
        int lineEndY=getAlternativePlacementHeight(bacArray[4], lowerLimit, topToBottom, fromBottom);
        double newLow;
        if(lineEndY > fromBottom){
            newLow = intersect(lowerLimit, topToBottom, fromBottom);
            Log.v("GraphNums", "lineEndY starts at " + lineEndY + "lineStartY " + lineStartY);
            lineEndY= getAlternativePlacementHeight(newLow, lowerLimit, topToBottom, fromBottom);
            Log.v("GraphNums", "lineEndY starts at " + lineEndY + " newLow " + newLow + " lowerLimit " + lowerLimit + " tTB " + topToBottom + " fB " + fromBottom);
            int lineEndX = getPlacementWidth(newLow, leftToRight, overLimit, fromLeft);
            Log.v("GraphNums", "lineEndX "+lineEndX );
            canvas.drawLine(tikO[0]-tikOffset, lineStartY, lineEndX, lineEndY, paint);
        }
        else{
            canvas.drawLine(tikO[0]-tikOffset, lineStartY, tikO[4], lineEndY, paint);
        }
        for (int i = 0; i < 5; i++) {
            canvas.drawLine(tikO[i], tikUp, tikO[i], tikDown, paint);
            canvas.drawText((hour+i)+"", tikO[i]-weightOffset, tikDown+3*heightOffset, paint);
            dotHeight = getPlacementHeight(bacArray[i], lowerLimit, topToBottom, fromBottom);
            paint.setColor(Color.RED);
            canvas.drawLine(tikO[i], dotHeight-weightOffset, tikO[i], dotHeight+weightOffset, paint);
            paint.setColor(Color.BLACK);
        }


        // graph title?

        paint.setTextSize(45.0f);
        canvas.drawText("Hours", 55*weightOffset, 80*heightOffset, paint);
        canvas.save();
        canvas.rotate(270f, 50, 50);
        canvas.drawText("BAC",0-36*heightOffset, 7*weightOffset, paint);
        canvas.restore();
    }

    private int getPlacementWidth(double newLow, int leftToRight, double overLimit, int fromLeft) {
        int ret = 0;
        double in = overLimit;
        Log.v("GraphNums", "newLow starts at " + newLow + " leftToRight " + leftToRight + " overLimit " + overLimit + " fromLeft " + fromLeft );

        while (in < newLow){
            newLow -=.005;
            ret++;
        }
        Log.v("GraphNums", "ret "+ret);
        ret = ret*(leftToRight/100);
        ret = ret+fromLeft;
        Log.v("GraphNums", "ret "+ret);
        return ret;
    }

    private int getAlternativePlacementHeight(double v, double lowerLimit, int topToBottom, int fromBottom) {
        int ret = 0;
        if (v>lowerLimit) {
            while (v >= lowerLimit) {
                lowerLimit += 0.0009;
                ret++;
            }
        }else{
            while (v < lowerLimit) {
                lowerLimit -= 0.0009;
                ret--;
            }
        }
        Log.v("GraphNums", "ret before "+ret );
        ret = ret*(topToBottom/100);
        ret = fromBottom-ret;
        Log.v("GraphNums", "ret after "+ret );
        return ret;
    }

    private int getPlacementHeight(double v, double lowerLimit, int topToBottom, int fromBottom) {
        int ret = 0;
        while (v>lowerLimit){
            lowerLimit+=0.0009;
            ret++;
        }
        ret = ret*(topToBottom/100);
        ret = fromBottom-ret;
        return ret;
    }

    private double findNearest() {                                        // takes the first in the array of BAC values
        double ret = bacArray[0];                                         // tries to fit it with a nice scale
        for (int i = 0; i < 31; i++){
            double currentNum =  bacArray[0]+(i*0.001);
            String bacString = currentNum + "";
            String nuString = "";
            try {
                nuString = bacString.substring(3, 5);
            } // end try to get 2 digits of bac
            catch (StringIndexOutOfBoundsException ex) {
            } // end catch if out of bounds
            if (nearest(nuString)){
                return currentNum;
            }
        }
        return ret;
    }

    private double intersect(double lowerLimit, int topToBottom, int fromBottom){
        double ret = lowerLimit;
        while (getAlternativePlacementHeight(ret, lowerLimit, topToBottom, fromBottom) > 0){
            ret = ret+.0003;
        }
        return ret;
    }

    private boolean nearest(String bacString) {
        for (int i = 0; i < 21; i++){
            String comp = 15*i+"";
            try {
                comp = comp.substring(comp.length() - 2, comp.length());
            }catch (Exception ex){}
            if (comp.equals(bacString)){
                return true;
            }
        }
        return false;
    }

    private int[] tikDown(int fromTop, int tikOffsetV) {
        int[] ret = new int[6];
        for (int i = 0; i < 6; i++){
            ret[i] = fromTop + (tikOffsetV*(i));
        }
        return ret;
    }

    private int[] tikOver(int x, int y){
        int[] ret = new int[5];
        for (int i = 0; i < 5; i++){
            ret[i] = x + (y*(i+1));
        }
        return ret;
    }
}
