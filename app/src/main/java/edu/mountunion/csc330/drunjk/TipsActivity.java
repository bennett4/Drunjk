package edu.mountunion.csc330.drunjk;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
        // Can't use context as a parameter (XML command doClick doesn't like it).
        // Can try just updating complete screen with a TextView and just put a
        // back button to go back to the original screen.

//        AlertDialog.Builder alert = new AlertDialog.Builder(context);
//        alert.setTitle("Are you sure you want to delete " + contact.getFirstName() + " "
//                + contact.getLastName() + " from your contacts list?");
//        EditContactActivity.PlayDialog deleteDialog = new EditContactActivity.PlayDialog();
//        alert.setPositiveButton("YES", deleteDialog);
//        alert.setNegativeButton("NO", deleteDialog);
//        alert.show();

        TipsView.idADrunk(view);
    } // end of idADrunk

    public void helpADrunk(View view) {
        TipsView.helpADrunk(view);
    } // end of helpADrunk

    public void idAlcoholPoisoning(View view) {
        TipsView.idAlcoholPoisoning(view);
    } // end of idAlcoholPoisoning

} // end of class TipsActivity