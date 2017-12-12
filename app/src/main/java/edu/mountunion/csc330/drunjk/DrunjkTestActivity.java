package edu.mountunion.csc330.drunjk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class DrunjkTestActivity extends AppCompatActivity {

    private Context cont = this;
    private static final String TEST = "The quick brown fox jumps over the lazy dog!";
    private EditText editText;
    TextView tipsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ////////////////
        RelativeLayout layout = new RelativeLayout( this );
        ScrollView scrollView = new ScrollView( this );
        // Set Content View
        tipsView = new TextView(this);
        tipsView.setText(TEST);
        tipsView.setPadding(50,80,0,0);
        tipsView.setTextSize(15);

        editText = new EditText(this);
        editText.addTextChangedListener(new TextListener());
        editText.setPadding(50,180,0,0);
        //Add the textView to layout
        layout.addView(tipsView);

        layout.addView( editText );

        scrollView.addView( layout );

        setContentView( scrollView );

        ////////////////
    }

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
                Intent eggIntent = new Intent(this, ContactActivity.class);
                this.startActivity(eggIntent);
                return true;
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                this.startActivity(settingsIntent);
                finish();
                return true;
            case R.id.action_tips:
                Intent tipsIntent = new Intent(this, TipsActivity.class);
                this.startActivity(tipsIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        } // end switch getting selected menu item
    } // end method onOptionsItemSelected


    private class TextListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String checkString = editText.getText().toString();
            int checkNum = editText.getText().length();
            for (int j = 0; j < checkNum; j++){
                if (!(checkString.substring(j,j+1)).equals(TEST.substring(j,j+1))){
                    tipsView.setText("WRONG");
                    return;
                }
            }
            tipsView.setText(TEST);
            if(checkNum == TEST.length()){
                tipsView.setText("Right!");
                editText.setText("");
                openApp(cont);
            }
        }


    }
    public static void openApp(Context context) {
        // you can also use BuildConfig.APPLICATION_ID
        String appId = "com.picolo.android";
        Intent rateIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("market://details?id=" + appId));
        boolean marketFound = false;

        // find all applications able to handle our rateIntent
        final List<ResolveInfo> otherApps = context.getPackageManager()
                .queryIntentActivities(rateIntent, 0);
        for (ResolveInfo otherApp: otherApps) {
            // look for Google Play application
            if (otherApp.activityInfo.applicationInfo.packageName
                    .equals("com.android.vending")) {

                ActivityInfo otherAppActivity = otherApp.activityInfo;
                ComponentName componentName = new ComponentName(
                        otherAppActivity.applicationInfo.packageName,
                        otherAppActivity.name
                );
                // make sure it does NOT open in the stack of your activity
                rateIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // task reparenting if needed
                rateIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                // if the Google Play was already open in a search result
                //  this make sure it still go to the app page you requested
                rateIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // this make sure only the Google Play app is allowed to
                // intercept the intent
                rateIntent.setComponent(componentName);
                context.startActivity(rateIntent);
                marketFound = true;
                break;

            }
        }

        // if GP not present on device, open web browser
        if (!marketFound) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id="+appId));
            context.startActivity(webIntent);
        }
    }
}

