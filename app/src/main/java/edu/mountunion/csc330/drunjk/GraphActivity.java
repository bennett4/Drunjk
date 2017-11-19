package edu.mountunion.csc330.drunjk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by marianga on 11/19/2017.
 */

public class GraphActivity extends Activity {
    private double[] bacArray;
    private int initialHour;
    private Draw drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Intent intent = getIntent();
        bacArray = intent.getDoubleArrayExtra("edu.mountunion.csc330.drunjk.ARRAY_OF_BAC");
        initialHour = intent.getIntExtra("edu.mountunion.csc330.drunjk.HOURS_ELAPSED", 1);


    } // end of method onCreate

    public void goBack(View v){
        finish();
    }
}
