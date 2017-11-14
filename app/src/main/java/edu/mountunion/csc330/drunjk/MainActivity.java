package edu.mountunion.csc330.drunjk;
//hi2
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

public class MainActivity extends AppCompatActivity {

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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_calculator:
                return true;
            case R.id.action_contacts:
                Intent intent = new Intent(this, ContactActivity.class);
                this.startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void calculateBac(View view) {
        Double bac = 100.0;
        //test
        bac = (((getOunces() * getNumberOfDrinks() * getPercentAlcohol()) * 5.14) /
                (getWeight() * getPercentWater())) - (0.015 * getHoursElapsed());

        String bacString = bac + "";
        bacString = bacString.substring(0, 6);
        ((TextView)findViewById(R.id.bacView)).setText(bacString);
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
            throw new Error("they didn't enter a sex, make sure they do (add red border making them select one, etc)");
        } // end else need to enter sex
        return percentWater;
    } // end of method calculatePercentWater

    public Double getWeight() {
        Double weight;
        EditText weightEditText = (EditText)findViewById(R.id.weight);
        if (weightEditText.getText().toString().isEmpty()) {
            throw new Error("they didn't enter a weight, make sure they do (add red border making them enter one, etc)");
        } // end if no weight
        else {
            weight = Double.parseDouble(weightEditText.getText().toString());
            // weight = weight * 0.453592;  // Converts to kgs, not needed in this formula
        } // end else weight is valid
        return weight;
    } // end of method calculateWeight

    public Integer getNumberOfDrinks() {
        Integer numDrinks;
        EditText numDrinksEditText = (EditText)findViewById(R.id.numDrinks);
        if (numDrinksEditText.getText().toString().isEmpty()) {
            throw new Error("they didn't enter a number of drinks, make sure they do (add red border making them enter an amount, etc)");
        } // end if no number of drinks
        else {
            numDrinks = Integer.parseInt(numDrinksEditText.getText().toString());
        } // end else number of drinks is valid
        return numDrinks;
    } // end of method getNumberOfDrinks

    public Double getPercentAlcohol() {
        Double percentAlcohol;
        Spinner alcoholTypeSpinner = (Spinner)findViewById(R.id.alcoholTypeSpinner);
        String alcoholType = alcoholTypeSpinner.getSelectedItem().toString();
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
            throw new Error("they didn't enter a alcohol type, make sure they do (add red border making them select one, etc)");
        } // end else need to enter an alcohol type
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
            throw new Error("they didn't enter a alcohol type, make sure they do (add red border making them select one, etc)");
        } // end else need to enter an alcohol type
        return ounces;
    } // end of method getOunces

    public Double getHoursElapsed() {
        Double hoursElapsed;
        EditText timeElapsedEditText = (EditText)findViewById(R.id.timeElapsed);
        if (timeElapsedEditText.getText().toString().isEmpty()) {
            throw new Error("they didn't enter time elapsed, make sure they do (add red border making them enter an amount, etc)");
        } // end if no time elapsed
        else {
            hoursElapsed = Double.parseDouble(timeElapsedEditText.getText().toString());
        } // end else time elapsed is valid
        return hoursElapsed;
    } // end of method getHoursElapsed

} // end of class MainActivity