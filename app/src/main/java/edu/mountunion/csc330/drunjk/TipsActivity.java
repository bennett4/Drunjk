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

    private String extra;
    private static final String TIPS_NAME = "edu.mountunion.csc330.drunjk.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Bundle extras = getIntent().getExtras();
            extra = extras.getString("edu.mountunion.csc330.drunjk.NAME");
        }catch (Exception ex) {}
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
                calculatorIntent.putExtra(TIPS_NAME, "tipsValue");
                this.startActivity(calculatorIntent);
                finish();
                return true;
            case R.id.action_contacts:
                Intent contactIntent = new Intent(this, ContactActivity.class);
                contactIntent.putExtra(TIPS_NAME, "tipsValue");
                this.startActivity(contactIntent);
                finish();
                return true;
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                settingsIntent.putExtra(TIPS_NAME, "tipsValue");
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

        // Set Title View
        String header = "How to Recognize a Drunk";
        TextView titleView = new TextView(this);
        titleView.setText(header);
        titleView.setTextSize(34);

        RelativeLayout.LayoutParams paramT
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT );
        paramT.addRule( RelativeLayout.ALIGN_PARENT_TOP );
        paramT.addRule( RelativeLayout.CENTER_HORIZONTAL );
        paramT.setMargins( 0, 25, 0, 50 );

        // Set Content View
        TextView tipsView = new TextView(this);
        tipsView.setText( "\n\n\n\n" +
                "\t\t • Slurred speech\n" +
                "\t\t • Trouble with balance\n" +
                "\t\t • Stumbling\n" +
                "\t\t • Bloodshot, glassy or watery eyes\n" +
                "\t\t • Unusual behavior\n" +
                "\t\t • Aggressive, loud, or inappropriate\n" +
                "\t\t • Trouble remembering things\n");
        tipsView.setTextColor(Color.BLACK);
        tipsView.setTextSize(23);


        // create a back button
        Button backButton = new Button( this );
        backButton.setBackgroundColor(getResources().getColor(R.color.buttonColor));
        backButton.setText( "Back" );

        backButton.setOnClickListener( new View.OnClickListener( ) {
            public void onClick(View v) {
                setContentView(R.layout.activity_tips);
            }
        });

        //Add the textView to layout
        scrollView.addView(tipsView);
        layout.addView( titleView, paramT );
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

        // Set Title View
        String header = "How to Help";
        TextView titleView = new TextView(this);
        titleView.setText(header);
        titleView.setTextSize(34);

        RelativeLayout.LayoutParams paramT
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT );
        paramT.addRule( RelativeLayout.ALIGN_PARENT_TOP );
        paramT.addRule( RelativeLayout.CENTER_HORIZONTAL );
        paramT.setMargins( 0, 25, 0, 50 );

        TextView tipsView = new TextView(this);
        tipsView.setText("\n\n\n\n\n" +
                " 1. Cut off the person from drinking any further\n" +
                " 2. Try to get them into a calmer and controlled environment\n" +
                "\t\ta. Someone’s room or a bathroom\n" +
                " 3. Try and get the person to drink some water\n" +
                " 4. If they are way too intoxicated to the point where vomiting\n\t\t  is or may occur get them to a toilet or trash can\n" +
                " 5. If the person is unresponsive and you can’t get\n\t\t them to stand…\n" +
                "\t\ta. Lay them down and on their side with their bottom arm to\n\t\t\t\trest their head on\n" +
                "\t\tb. Getting them on their side will allow for them to have the\n\t\t\t\tvomit leave the body rather than chocking on it\n" +
                " 6. If they are not vomiting or done vomiting get them into a\n\t\t comfortable position to sleep\n" +
                "\t\ta. If vomiting does not stop look to taking the person to the\n\t\t\t\tnearest ER\n" +
                "\t\tb. Don't leave them while they sleep, vomiting could reoccur\n" +
                " 7. Time is the best medicine so if the person is well enough to\n\t\t rest get them plenty of water and let time heal them\n\n\n\n\n\n");
        tipsView.setTextColor(Color.BLACK);
        tipsView.setTextSize(15);

        // create a back button
        Button backButton = new Button( this );
        backButton.setBackgroundColor(getResources().getColor(R.color.buttonColor));
        backButton.setText( "Back" );

        backButton.setOnClickListener( new View.OnClickListener( ) {
            public void onClick(View v) {
                setContentView(R.layout.activity_tips);
            }
        });

        scrollView.addView(tipsView);
        layout.addView(titleView, paramT);
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

        // Set Title View
        String header = "Signs of Alcohol Poisoning";
        TextView titleView = new TextView(this);
        titleView.setText(header);
        titleView.setGravity(Gravity.CENTER_HORIZONTAL);
        titleView.setTextSize(34);

        RelativeLayout.LayoutParams paramT
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT );
        paramT.addRule( RelativeLayout.ALIGN_PARENT_TOP );
        paramT.addRule( RelativeLayout.CENTER_HORIZONTAL );
        paramT.setMargins( 0, 25, 0, 50 );

        TextView tipsView = new TextView(this);
        tipsView.setText("\n\n\n\n" +
                "\t • Breathing drastically slows\n" +
                "\t • Unresponsive audibly or physically\n" +
                "\t • Unconscious\n" +
                "\t • Blue lips or fingertips\n" +
                "\t • Dehydration (vomiting)\n" +
                "\t • Rapid pulse\n" +
                "\t • Cold clammy hands/feet\n");
        tipsView.setTextColor(Color.BLACK);
        paramT.setMargins(15,0,0,0);
        tipsView.setLayoutParams(paramT);
        tipsView.setTextSize(23);

        // create a back button
        Button backButton = new Button( this );
        backButton.setBackgroundColor(getResources().getColor(R.color.buttonColor));
        backButton.setText( "Back" );

        backButton.setOnClickListener( new View.OnClickListener( ) {
            public void onClick(View v) {
                setContentView(R.layout.activity_tips);
            }
        });

        scrollView.addView(tipsView);
        layout.addView(titleView, paramT);
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

    @Override
    public void onBackPressed() {
        if (extra != null){
            if (extra.equals("mainValue")){
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                finish();
            }else if(extra.equals("contactValue")){
                Intent intent = new Intent(this, ContactActivity.class);
                this.startActivity(intent);
                finish();
            }else if(extra.equals("settingsValue")){
                Intent intent = new Intent(this, SettingsActivity.class);
                this.startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                finish();
            }
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            finish();
        }
    }

} // end of class TipsActivity