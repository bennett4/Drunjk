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
            b.setBackgroundColor(getResources().getColor(R.color.buttonColor));
            b.setBackgroundColor(ContextCompat.getColor(context, R.color.buttonColor));
            b.setText("Back");
            b.getLayoutParams();
            b.setOnClickListener(new ButtonHandler());
            if (h>w) {
                b.setTextSize(h/100);
                b.setX(w / 2 - (w/5)/2);
                b.setY(h - (h/10)-(h/80));
                addView(b, w/5,h/15);
            }else{
                b.setTextSize(w/100);
                b.setX(w / 8 - 130);
                b.setY(h - 250);
                addView(b, h/5,w/15);
            }
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



