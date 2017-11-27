package edu.mountunion.csc330.drunjk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by marianga on 11/19/2017.
 */

public class GraphActivity extends Activity implements GridLayoutForGraph.Controller{
    private double[] bacArray;
    private double initialHour;
    private Draw drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        bacArray = intent.getDoubleArrayExtra("edu.mountunion.csc330.drunjk.ARRAY_OF_BAC");
        initialHour = intent.getDoubleExtra("edu.mountunion.csc330.drunjk.HOURS_ELAPSED", 1.0);
        //get status bar height
        Resources res = getResources();
        int statusBarHeight = 0;
        int statusBarId = res.getIdentifier("status_bar_height", "dimen", "android" );
        if (statusBarId > 0){
            statusBarHeight = res.getDimensionPixelSize(statusBarId);
        }
        Point size = new Point( );
        getWindowManager().getDefaultDisplay( ).getSize( size );
        drawView = new Draw(this, size.x, size.y-statusBarHeight, bacArray, initialHour);
        drawView.setBackgroundColor(Color.WHITE);
        GridLayoutForGraph grif = new GridLayoutForGraph(this, drawView, this);
        setContentView(grif);
        //setContentView(drawView);
    } // end of method onCreate

    public void goBack(View v){
        finish();
    }

    @Override
    public void finishIt() {
        finish();
    }
}
