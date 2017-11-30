package edu.mountunion.csc330.drunjk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GridLayoutForGraph extends RelativeLayout{

        private Context context;
        private Draw grap;

        interface Controller{
            void finishIt();
        }

        private Controller controller;

        public GridLayoutForGraph(Context context, Draw graph, Controller controller) {
            super(context);
            this.context = context;
            this.controller = controller;
            // Get width of the screen
            Point size = new Point( );
            WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay( ).getSize( size );
            int w = size.x;
            int h = size.y;
            grap = graph;
            grap.setOnLongClickListener(new viewHandler());
            addView(grap);

            Button b = new Button( context );
            b.setTextSize(15);
            b.setBackgroundColor(ContextCompat.getColor(context, R.color.buttonColor));
            b.setText("Back");
            if (h>w) {
                b.setX(w / 2 - 100);
                b.setY(h - 200);
            }else{
                b.setX(w / 8 - 100);
                b.setY(h - 200);
            }
            b.setOnClickListener(new ButtonHandler());
            addView(b);

        }


    private class ButtonHandler implements View.OnClickListener {

        public ButtonHandler() {
        }

        @Override
        public void onClick(View v) {controller.finishIt();}
    }
    private class viewHandler implements OnLongClickListener{

        @Override
        public boolean onLongClick(View v) {
            grap.colorChange();
            grap.invalidate();
            return false;
        }
    }
    }



