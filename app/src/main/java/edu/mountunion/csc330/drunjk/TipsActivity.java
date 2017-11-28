package edu.mountunion.csc330.drunjk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class TipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
    } // end of method onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    } // end of method onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_calculator:
                Intent calculatorIntent = new Intent(this, MainActivity.class);
                this.startActivity(calculatorIntent);
                finish();
                return true;
            case R.id.action_contacts:
                Intent contactIntent = new Intent(this, ContactActivity.class);
                this.startActivity(contactIntent);
                finish();
                return true;
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, ContactActivity.class);
                this.startActivity(settingsIntent);
                finish();
                return true;
            case R.id.action_tips:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        } // end switch getting selected menu item
    } // end method onOptionsItemSelected

    public void idADrunk(View view) {
        RelativeLayout layout = new RelativeLayout( this );
        ScrollView scrollView = new ScrollView( this );

        String header = "Here are some tips!";


        TextView tipsView = new TextView(this);
        tipsView.setText( header + "\n\n" +
                "How to recognize if someone is drunk\n" +
                " • Slurred speech\n" +
                " • trouble with balance\n" +
                " • stumbling\n" +
                " • bloodshot, glassy or watery eyes\n" +
                " • unusual behavior\n" +
                " • aggressive, loud, or inappropriate\n" +
                " • trouble remembering things\n");
        tipsView.setTextColor(Color.BLACK);
        tipsView.setTextSize(20);
//        tipsView.setGravity(Gravity.CENTER_HORIZONTAL);
        // create a back button
        Button backButton = new Button( this );
        backButton.setText( "Back" );

        backButton.setOnClickListener( new View.OnClickListener( ) {
            public void onClick(View v) {
                setContentView(R.layout.activity_tips);
            }
        });

        scrollView.addView(tipsView);
        layout.addView( scrollView );

        // add back button at bottom
        RelativeLayout.LayoutParams params
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT );
        params.addRule( RelativeLayout.ALIGN_PARENT_BOTTOM );
        params.addRule( RelativeLayout.CENTER_HORIZONTAL );
        params.setMargins( 0, 0, 0, 50 );
        layout.addView( backButton, params );

        setContentView( layout );


    } // end of idADrunk

    public void helpADrunk(View view) {
        RelativeLayout layout = new RelativeLayout( this );
        ScrollView scrollView = new ScrollView( this );

        TextView tipsView = new TextView(this);
        tipsView.setText("Here are some tips!\n\n" +
        "Steps to help an intoxicated person\n" +
                " 1.Cut off the person from drinking any further\n" +
                " 2.Try to get them into a calmer and controlled environment\n" +
                " a.Someone’s room or a bathroom\n" +
                " 3.Try and get the person to drink some water\n" +
                " 4.If they are way too intoxicated to the point where vomiting is or may occur get them to a toilet or trash can\n" +
                " 5.If the person is unresponsive and you can’t get them to stand…\n" +
                " a.Lay them down and on their side with their bottom arm to rest their head on\n" +
                " b.Getting them on their side will allow for them to have the vomit leave the body rather than chocking on it\n" +
                " 6.If they are not vomiting or done vomiting get them into a comfortable position to sleep\n" +
                " a.If vomiting does not stop look to taking the person to the nearest ER\n" +
                " b.Do not leave them alone while they sleep, vomiting could reoccur\n" +
                " 7.Time is the best medicine so if the person is well enough to rest get them plenty of water and let time heal them\n");
        tipsView.setTextColor(Color.BLACK);
        // create a back button
        Button backButton = new Button( this );
        backButton.setText( "Back" );

        backButton.setOnClickListener( new View.OnClickListener( ) {
            public void onClick(View v) {
                setContentView(R.layout.activity_tips);
            }
        });

        scrollView.addView(tipsView);
        layout.addView( scrollView );

        // add back button at bottom
        RelativeLayout.LayoutParams params
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT );
        params.addRule( RelativeLayout.ALIGN_PARENT_BOTTOM );
        params.addRule( RelativeLayout.CENTER_HORIZONTAL );
        params.setMargins( 0, 0, 0, 50 );
        layout.addView( backButton, params );

        setContentView( layout );
    } // end of helpADrunk

    public void idAlcoholPoisoning(View view) {
        RelativeLayout layout = new RelativeLayout( this );
        ScrollView scrollView = new ScrollView( this );

        TextView tipsView = new TextView(this);
        tipsView.setText("Here are some tips!\n\n" +
        "Signs of alcohol poisoning\n" +
                " • Breathing drastically slows\n" +
                " • Unresponsive audibly or physically\n" +
                " • Unconscious\n" +
                " • Blue lips or fingertips\n" +
                " • Dehydration (vomiting)\n" +
                " • Rapid pulse\n" +
                " • Cold clammy hands/feet\n");
        tipsView.setTextColor(Color.BLACK);
        tipsView.setTextSize(20);
        // create a back button
        Button backButton = new Button( this );
        backButton.setText( "Back" );

        backButton.setOnClickListener( new View.OnClickListener( ) {
            public void onClick(View v) {
                setContentView(R.layout.activity_tips);
            }
        });

        scrollView.addView(tipsView);
        layout.addView( scrollView );

        // add back button at bottom
        RelativeLayout.LayoutParams params
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT );
        params.addRule( RelativeLayout.ALIGN_PARENT_BOTTOM );
        params.addRule( RelativeLayout.CENTER_HORIZONTAL );
        params.setMargins( 0, 0, 0, 50 );
        layout.addView( backButton, params );

        setContentView( layout );
    } // end of idAlcoholPoisoning

} // end of class TipsActivity