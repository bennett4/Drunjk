package edu.mountunion.csc330.drunjk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
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

    @Override
    public void onDraw(Canvas canvas) {
        int heightOffset = height/18;
        int weightOffset = width/18;
        canvas.drawLine(2*(weightOffset), 2*(heightOffset), 2*(weightOffset), 14*(heightOffset), paint);
        canvas.drawLine(2*(weightOffset), 14*(heightOffset), 16*(weightOffset), 14*(heightOffset), paint);
        String bacString = bacArray[0] + "";
        try {
            bacString = bacString.substring(0, 6);
        } // end try to display bac with 2 digits to right of decimal
        catch (StringIndexOutOfBoundsException ex) {
        } // end catch if already less than shortened length
        String retVal = "Your Current Blood Alcohol Content is: " + bacString + "%";
        canvas.drawText(retVal, 2*weightOffset, 17*heightOffset, paint);
        // draw 5 vertical lines and label them with hours elapsed
        // draw x horizontal lines and label them with BAC
        // draw a long grey horizontal line if .08 is on the graph, labeled as the legal limit
        // graph title?
        // Label units for the vertical axis
        paint.setTextSize(50.0f);
        canvas.drawText("Hours", 8*weightOffset, 16*heightOffset, paint);
    }
}
