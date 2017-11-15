package edu.mountunion.csc330.drunjk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditContactActivity extends AppCompatActivity {

    Database database;
    int contactId;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        database = new Database(this);
        Intent intent = getIntent();
        contactId = Integer.parseInt(intent.getStringExtra(ContactActivity.EDIT_CONTACT_ID));
        contact = database.selectById(contactId);

        EditText firstNameET = (EditText)findViewById(R.id.editFirstName);
        firstNameET.setText(contact.getFirstName());

        EditText lastNameET = (EditText)findViewById(R.id.editLastName);
        lastNameET.setText(contact.getLastName());

        EditText firstPhoneNumET = (EditText)findViewById(R.id.editPhoneNumberFirst);
        firstPhoneNumET.setText(contact.getPhoneNum().substring(0, 3));

        EditText secondPhoneNumET = (EditText)findViewById(R.id.editPhoneNumberSecond);
        secondPhoneNumET.setText(contact.getPhoneNum().substring(3, 6));

        EditText thirdPhoneNumET = (EditText)findViewById(R.id.editPhoneNumberThird);
        thirdPhoneNumET.setText(contact.getPhoneNum().substring(6));

        EditText relationET = (EditText)findViewById(R.id.editRelation);
        relationET.setText(contact.getRelation());
    } // end of method onCreate

    public void cancelEdit(View v) {
        Intent intent = new Intent(this, ContactActivity.class);
        this.startActivity(intent);
        finish();
    } // end of method cancelAdd

    public void editContact(View v) {
        int id = contactId;
        String firstName = ((EditText)findViewById(R.id.editFirstName)).getText().toString().trim();
        String lastName = ((EditText)findViewById(R.id.editLastName)).getText().toString().trim();
        String phoneNum;
        String phoneNumFirst = ((EditText)findViewById(R.id.editPhoneNumberFirst)).getText().toString();
        String phoneNumSecond = ((EditText)findViewById(R.id.editPhoneNumberSecond)).getText().toString();
        String phoneNumThird = ((EditText)findViewById(R.id.editPhoneNumberThird)).getText().toString();
        phoneNum = phoneNumFirst + phoneNumSecond + phoneNumThird;
        String relation = ((EditText)findViewById(R.id.editRelation)).getText().toString().trim();
        database.updateById(id, firstName, lastName, phoneNum, relation);

        Intent intent = new Intent(this, ContactActivity.class);
        this.startActivity(intent);
        finish();
        Toast.makeText(EditContactActivity.this, "Contact Updated",
                Toast.LENGTH_LONG).show();
    } // end of method editContact

    public void deleteContact(View v) {



    } // end of method deleteContact

} // end of class EditContactActivity