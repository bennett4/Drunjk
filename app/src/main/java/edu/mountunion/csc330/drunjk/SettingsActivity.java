package edu.mountunion.csc330.drunjk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class SettingsActivity extends AppCompatActivity{
    private String extra;
    private static final String STRING_NAME = "edu.mountunion.csc330.drunjk.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Bundle extras = getIntent().getExtras();
            extra = extras.getString("edu.mountunion.csc330.drunjk.NAME");
        }catch (Exception ex) {}
        setContentView(R.layout.activity_settings);
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
                calculatorIntent.putExtra(STRING_NAME, "settingsValue");
                this.startActivity(calculatorIntent);
                finish();
                return true;
            case R.id.action_contacts:
                Intent contactIntent = new Intent(this, ContactActivity.class);
                contactIntent.putExtra(STRING_NAME, "settingsValue");
                this.startActivity(contactIntent);
                finish();
                return true;
            case R.id.action_settings:
                return true;
            case R.id.action_tips:
                Intent tipsIntent = new Intent(this, TipsActivity.class);
                tipsIntent.putExtra(STRING_NAME, "settingsValue");
                this.startActivity(tipsIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        } // end switch getting selected menu item
    } // end method onOptionsItemSelected

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
            }else if(extra.equals("tipsValue")){
                Intent intent = new Intent(this, TipsActivity.class);
                this.startActivity(intent);
                finish();
            }else {
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                finish();
            }
            }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            finish();
        }
    }

} // end of class SettingsActivity