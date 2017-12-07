package edu.mountunion.csc330.drunjk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

public class Draw extends View {
    private Paint paint;
    private int height, width;
    private double[] bacArray;
    private double initialHour;
    private static final int BLACK = R.color.black;
    private final int WHITE =  R.color.background_material_light;
    private int backgroundCol = BLACK;
    private int graphCol = WHITE;
    private Context cont;

    private void init() {
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10.0f);
        paint.setTextSize(40.0f);
    }

    public Draw(Context context, int w, int h, double[] d, double in) {
        super(context);
        cont = context;
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
        double overLimit = bacArray[0]+.015;
        if (bacArray[0] == 0.0){
            overLimit = bacArray[0];
        }
        double upperLimit;
        double lowerLimit;
        setBackgroundColor(ContextCompat.getColor(cont, graphCol));
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
        if (overLimit < 0.090) {
            for (int i = 0; i < 6; i++) {
                String bacText = (.09-(.015*i))+"";
                float lengBacText = paint.measureText(bacText);
                float overBacText = tikLeft - lengBacText - 10;
                canvas.drawText(bacText, overBacText, tikD[i] + heightOffset, paint);

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
        // horizontal line
        canvas.drawLine(fromLeft, fromBottom, fromRight, fromBottom, paint);
        if(.08 <= upperLimit && .08 > lowerLimit){
            paint.setColor(Color.GRAY);
            int legalLimitHeight = getPlacementHeight(.08, lowerLimit, topToBottom, fromBottom);
            canvas.drawLine(fromLeft, legalLimitHeight, fromRight, legalLimitHeight, paint);
            paint.setColor(ContextCompat.getColor(cont, backgroundCol));
            canvas.drawText(".08", fromRight, legalLimitHeight+heightOffset, paint);
        }
        else if (.08 <= lowerLimit){
            paint.setStrokeWidth(4.0f);
            paint.setColor(Color.GRAY);
            int legalLimitHeight = getPlacementHeight(.08, lowerLimit, topToBottom, fromBottom);
            canvas.drawLine(fromLeft, legalLimitHeight, fromRight, legalLimitHeight, paint);
            paint.setColor(ContextCompat.getColor(cont, backgroundCol));
            canvas.drawText(".08", fromRight+10, legalLimitHeight+heightOffset, paint);
            paint.setStrokeWidth(10.0f);
        }

        // vertical line
        canvas.drawLine(fromLeft, fromTop, fromLeft, fromBottom, paint);

        String retVal = "Your Current Blood Alcohol Content is: " + bacString[0] + "%";
        float lengRetVal = paint.measureText(retVal);
        float retValWidth = (width-lengRetVal)/2;
        canvas.drawText(retVal, retValWidth, 90*heightOffset, paint);

        int[] tikO = tikOver(fromLeft, tikOffset);
        int tikUp = fromBottom-heightOffset;
        int tikDown = fromBottom+heightOffset;
        int hour = (int)initialHour;
        int dotHeight;
        int lineStartY = getAlternativePlacementHeight(overLimit, lowerLimit, topToBottom, fromBottom);
        int lineEndY = getAlternativePlacementHeight(bacArray[4], lowerLimit, topToBottom, fromBottom);
        float newLow;

        /*if((int)initialHour == 1 || (int)initialHour == 0) {
            lineStartY = getAlternativePlacementHeight(bacArray[0], lowerLimit, topToBottom, fromBottom);
            if (lineEndY == fromBottom) {
                newLow = intersect(topToBottom, fromBottom, lowerLimit);
                float lineEndX = getPlacementWidth(newLow, tikOffset, fromLeft);
                canvas.drawLine(tikO[0], lineStartY, lineEndX, lineEndY, paint);
            } else {
                canvas.drawLine(tikO[0], lineStartY, tikO[4], lineEndY, paint);
            }
            for (int i = 0; i < 5; i++) {
                canvas.drawLine(tikO[i], tikUp, tikO[i], tikDown, paint);
                canvas.drawText((hour + i) + "", tikO[i] - 10, tikDown + 45, paint);
                dotHeight = getPlacementHeight(bacArray[i], lowerLimit, topToBottom, fromBottom);
                paint.setColor(ContextCompat.getColor(cont, R.color.colorAccent));
                canvas.drawLine(tikO[i], dotHeight - weightOffset, tikO[i], dotHeight + weightOffset, paint);
                paint.setColor(ContextCompat.getColor(cont, backgroundCol));
            }
        }
        else{*/
            if (lineEndY == fromBottom) {
                newLow = intersect(topToBottom, fromBottom, lowerLimit);
                float lineEndX = getPlacementWidth(newLow, tikOffset, fromLeft);
                canvas.drawLine(tikO[0] - tikOffset, lineStartY, lineEndX, lineEndY, paint);
            } else {
                canvas.drawLine(tikO[0] - tikOffset, lineStartY, tikO[4], lineEndY, paint);
            }
            for (int i = 0; i < 5; i++) {
                canvas.drawLine(tikO[i], tikUp, tikO[i], tikDown, paint);
                canvas.drawText((hour + i) + "", tikO[i] - 10, tikDown + 45, paint);
                dotHeight = getPlacementHeight(bacArray[i], lowerLimit, topToBottom, fromBottom);
                paint.setColor(ContextCompat.getColor(cont, R.color.colorAccent));
                canvas.drawLine(tikO[i], dotHeight - weightOffset, tikO[i], dotHeight + weightOffset, paint);
                paint.setColor(ContextCompat.getColor(cont, backgroundCol));
            }
        //}

        // graph title?

        paint.setTextSize(45.0f);
        canvas.drawText("Hours", 55*weightOffset, fromBottom+140, paint);
        canvas.save();
        canvas.rotate(270f, 50, 50);
        canvas.drawText("BAC",0-36*heightOffset, 7*weightOffset, paint);
        canvas.restore();
    }

    private float intersect(int topToBottom, int fromBottom, double lowerLimit){
        int ret = 0;
        double start = bacArray[0]+.015;
        if(getAlternativePlacementHeight(start, lowerLimit, topToBottom, fromBottom) == fromBottom){
            return ret;
        }
        for (int i = 0; i < 5; i++){
            if(getAlternativePlacementHeight(bacArray[i], lowerLimit, topToBottom, fromBottom)==fromBottom){
                ret = 100*i;
                try{
                double currentBAC = bacArray[i-1];

                while (true){
                    if(getAlternativePlacementHeight(currentBAC, lowerLimit, topToBottom, fromBottom) >= fromBottom) {
                        return ret;
                    }
                    currentBAC = currentBAC - .00015;
                    ret++;
                }
                }catch (ArrayIndexOutOfBoundsException ex){
                    return ret;
                }
            }
        }
        return ret;
    }

    private float getPlacementWidth(double newLow, int tikOffset, int fromLeft) {
        float ret = (float) newLow;
        float percent = (float)tikOffset/100;
        ret = ret*percent;
        ret = ret+fromLeft;
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
        ret = ret*(topToBottom/100);
        ret = fromBottom-ret;
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
        double ret = bacArray[0]+.015;
        if (bacArray[0] == 0.0){
            ret = bacArray[0];
        }                                                                 // tries to fit it with a nice scale
        for (int i = 0; i < 31; i++){
            double currentNum =  ret+(i*0.001);
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

    public void colorChange(){
        if (backgroundCol == BLACK){
            backgroundCol = WHITE;
            graphCol = BLACK;
        }
        else{
            backgroundCol = BLACK;
            graphCol = WHITE;
        }
    }
}

