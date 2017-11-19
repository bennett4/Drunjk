package edu.mountunion.csc330.drunjk;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String BAC_ARRAY_KEY = "edu.mountunion.csc330.drunjk.ARRAY_OF_BAC";
    private static final String EXTRA_INT = "edu.mountunion.csc330.drunjk.HOURS_ELAPSED";
    boolean properInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                return true;
            case R.id.action_contacts:
                Intent contactIntent = new Intent(this, ContactActivity.class);
                this.startActivity(contactIntent);
                finish();
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
        } // end of switch menu item selected
    } // end of method onOptionsItemSelected

    public void calculateBac(View view) {
        Double bac = 100.0;
        properInput = true;

        bac = (((getOunces() * getNumberOfDrinks() * getPercentAlcohol()) * 5.14) /
                (getWeight() * getPercentWater())) - (0.015 * getHoursElapsed());

        String bacString = bac + "";
        try {
            bacString = bacString.substring(0, 6);
        } // end try to display bac with 4 digits to right of decimal
        catch (StringIndexOutOfBoundsException ex) {
        } // end catch if already less than shortened length
        if (properInput) {
            ((TextView)findViewById(R.id.bacView)).setText(" " + bacString);
            Intent graphIntent = new Intent(this, GraphActivity.class);
            ArrayList<Double> bacArray = new ArrayList<>();
            for (int i = 1; i < 6; i++){
                Double interratedBAC = bac - (0.015 * (getHoursElapsed()*i));
                bacArray.add(interratedBAC);
            }
            graphIntent.putExtra(BAC_ARRAY_KEY, bacArray);
            graphIntent.putExtra(EXTRA_INT, getHoursElapsed() );
            this.startActivity(graphIntent);
        } // end if properInput
    } // end of method calculateBac

    public Double getPercentWater() {
        Double percentWater;
        Spinner sexSpinner = (Spinner)findViewById(R.id.sexSpinner);
        String sex = sexSpinner.getSelectedItem().toString();
        if (sex.equals("Male")) {
            percentWater = 0.73; // 58
        } // end if sex is male
        else if (sex.equals("Female")) {
            percentWater = 0.66; // 49
        } // end if sex is female
        else {
            properInput = false;
            percentWater = 1.0;
            Toast.makeText(MainActivity.this, "Please Select Male or Female",
                    Toast.LENGTH_SHORT).show();
        } // end else need to enter sex
        return percentWater;
    } // end of method calculatePercentWater

    public Double getWeight() {
        Double weight;
        EditText weightEditText = (EditText)findViewById(R.id.weight);
        Spinner weightSpinner = (Spinner)findViewById(R.id.weightSpinner);
        String weightUnit = weightSpinner.getSelectedItem().toString();
        if (weightEditText.getText().toString().isEmpty()) {
            weight = 1.0;
            properInput = false;
            Toast.makeText(MainActivity.this, "Please Enter a Valid Weight",
                    Toast.LENGTH_SHORT).show();
        } // end if no weight
        else {
            try {
                weight = Double.parseDouble(weightEditText.getText().toString());
                if (weightUnit.equals("lbs")) {
                    // leave weight the same
                } // end if using pounds
                else if (weightUnit.equals("kgs")) {
                    weight = weight * 2.20462;
                } // end else if using kilograms
            } // end try valid weight entered
            catch (NumberFormatException nfe) {
                weight = 1.0;
                properInput = false;
                Toast.makeText(MainActivity.this, "Please Enter a Valid Weight",
                        Toast.LENGTH_SHORT).show();
            } // end catch NumberFormatException
        } // end else weight is valid
        return weight;
    } // end of method calculateWeight

    public Integer getNumberOfDrinks() {
        Integer numDrinks;
        EditText numDrinksEditText = (EditText)findViewById(R.id.numDrinks);
        if (numDrinksEditText.getText().toString().isEmpty()) {
            numDrinks = 0;
            properInput = false;
            Toast.makeText(MainActivity.this, "Please Enter Number of Drinks Consumed",
                    Toast.LENGTH_SHORT).show();
        } // end if no number of drinks
        else {
            try {
                numDrinks = Integer.parseInt(numDrinksEditText.getText().toString());
            } // end try valid number of drinks
            catch (NumberFormatException nfe) {
                numDrinks = 0;
                properInput = false;
                Toast.makeText(MainActivity.this, "Please Enter Number of Drinks Consumed",
                        Toast.LENGTH_SHORT).show();
            } // end catch NumberFormatException
        } // end else number of drinks is valid
        return numDrinks;
    } // end of method getNumberOfDrinks

    public Double getPercentAlcohol() {
        Double percentAlcohol = 1.01;
        Spinner alcoholTypeSpinner = (Spinner)findViewById(R.id.alcoholTypeSpinner);
        String alcoholType = alcoholTypeSpinner.getSelectedItem().toString();
        EditText percentAlcoholEditText = (EditText)findViewById(R.id.alcoholPercentage);

        if (percentAlcoholEditText.getText().toString().isEmpty()) {
            if (alcoholType.equals("Beer")) {
                percentAlcohol = .045;
            } // end if type is beer
            else if (alcoholType.equals("Wine")) {
                percentAlcohol = 0.125;
            } // end if type is wine
            else if (alcoholType.equals("Liquor")) {
                percentAlcohol = 0.40;
            } // end if type is liquor
            else {
                percentAlcohol = 0.0;
                properInput = false;
            } // end else need to enter an alcohol type
        } // end if percentAlcohol is not entered, run through the default types for the percentage
        else {
            try {
                if (Double.parseDouble(percentAlcoholEditText.getText().toString()) < 0) {
                    properInput = false;
                    Toast.makeText(MainActivity.this, "Please Enter a Valid Alcohol Percentage",
                            Toast.LENGTH_SHORT).show();
                } // end if negative percent alcohol
                else if (Double.parseDouble(percentAlcoholEditText.getText().toString()) > 100) {
                    properInput = false;
                    Toast.makeText(MainActivity.this, "Please Enter a Valid Alcohol Percentage",
                            Toast.LENGTH_SHORT).show();
                } // end else if too large percent alcohol
                else if ((Double.parseDouble(percentAlcoholEditText.getText().toString()) > 0) &&
                        (Double.parseDouble(percentAlcoholEditText.getText().toString()) <= 100)) {
                    percentAlcohol = Double.parseDouble(percentAlcoholEditText.getText().toString()) * 0.01;
                } // end else if valid manually entered percent alcohol
            } catch (NumberFormatException nfe) {
                properInput = false;
                Toast.makeText(MainActivity.this, "Please Enter a Valid Alcohol Percentage",
                        Toast.LENGTH_SHORT).show();
            } // end catch NumberFormatException
        } // end else manually entered percent alcohol
        return percentAlcohol;
    } // end of method getPercentAlcohol

    public Double getOunces() {
        Double ounces;
        Spinner alcoholTypeSpinner = (Spinner)findViewById(R.id.alcoholTypeSpinner);
        String alcoholType = alcoholTypeSpinner.getSelectedItem().toString();
        if (alcoholType.equals("Beer")) {
            ounces = 12.0;
        } // end if type is beer
        else if (alcoholType.equals("Wine")) {
            ounces = 5.0;
        } // end if type is wine
        else if (alcoholType.equals("Liquor")) {
            ounces = 1.5;
        } // end if type is liquor
        else {
            ounces = 0.0;
            properInput = false;
            Toast.makeText(MainActivity.this, "Please Select a Type of Alcohol",
                    Toast.LENGTH_SHORT).show();
        } // end else need to enter an alcohol type
        return ounces;
    } // end of method getOunces

    public Double getHoursElapsed() {
        Double hoursElapsed;
        EditText timeElapsedEditText = (EditText)findViewById(R.id.timeElapsed);
        if (timeElapsedEditText.getText().toString().isEmpty()) {
            hoursElapsed = 0.0;
            properInput = false;
            Toast.makeText(MainActivity.this, "Please Enter the Time Elapsed",
                    Toast.LENGTH_SHORT).show();
        } // end if no time elapsed
        else {
            try {
                hoursElapsed = Double.parseDouble(timeElapsedEditText.getText().toString());
            } // end try valid time elapsed
            catch (NumberFormatException nfe) {
                properInput = false;
                hoursElapsed = 0.0;
                Toast.makeText(MainActivity.this, "Please Enter the Time Elapsed",
                        Toast.LENGTH_SHORT).show();
            } // end catch NumberFormatException
        } // end else time elapsed is valid
        return hoursElapsed;
    } // end of method getHoursElapsed

} // end of class MainActivity