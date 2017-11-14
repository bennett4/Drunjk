package edu.mountunion.csc330.drunjk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {

    private Contact contact;
    private int id = 0;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = new Database(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText phoneNumberFirstEditText = (EditText)findViewById(R.id.phoneNumberFirst);
        EditText phoneNumberSecondEditText = (EditText)findViewById(R.id.phoneNumberSecond);
        EditText phoneNumberThirdEditText = (EditText)findViewById(R.id.phoneNumberThird);
        TextChangeHandler tch = new TextChangeHandler();
        phoneNumberFirstEditText.addTextChangedListener(tch);
        phoneNumberSecondEditText.addTextChangedListener(tch);
        phoneNumberThirdEditText.addTextChangedListener(tch);
    } // end of method onCreate

    public void addContact(View v) {
        String firstName = ((EditText)findViewById(R.id.firstName)).getText().toString().trim();
        String lastName = ((EditText)findViewById(R.id.lastName)).getText().toString().trim();
        String phoneNum;
        String phoneNumFirst = ((EditText)findViewById(R.id.phoneNumberFirst)).getText().toString();
        String phoneNumSecond = ((EditText)findViewById(R.id.phoneNumberSecond)).getText().toString();
        String phoneNumThird = ((EditText)findViewById(R.id.phoneNumberThird)).getText().toString();
        phoneNum = phoneNumFirst + phoneNumSecond + phoneNumThird;
        String relation = ((EditText)findViewById(R.id.relation)).getText().toString().trim();
        contact = new Contact(id, firstName, lastName, phoneNum, relation);
        id++;
        database.insert(contact);
        Intent intent = new Intent(this, ContactActivity.class);
        this.startActivity(intent);
        finish();
    } // end of method addContact

    public void cancelAdd(View v) {
        Intent intent = new Intent(this, ContactActivity.class);
        this.startActivity(intent);
        finish();
    } // end of method cancelAdd

    private class TextChangeHandler implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override
        public void afterTextChanged(Editable editable) {

            if (((EditText)findViewById(R.id.phoneNumberFirst)).getText().toString().length() == 3) {
                findViewById(R.id.phoneNumberSecond).requestFocus();
            } // end if entered 3 characters into the first phone number section

            if (((EditText)findViewById(R.id.phoneNumberSecond)).getText().toString().length() == 3) {
                findViewById(R.id.phoneNumberThird).requestFocus();
            } // end if entered 3 characters into the middle phone number section

            if (((EditText)findViewById(R.id.phoneNumberThird)).getText().toString().length() == 4) {
                findViewById(R.id.relation).requestFocus();
            } // end if entered 4 characters into the final phone number section

        } // end of afterTextChanged

    } // end of class TextChangeHandler

} // end of class AddContactActivity
