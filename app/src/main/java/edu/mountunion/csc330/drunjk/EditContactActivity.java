package edu.mountunion.csc330.drunjk;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;

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

        if ((firstName.trim().equals("")) && (lastName.trim().equals(""))) {
            Toast.makeText(EditContactActivity.this, "Please Enter a Name",
                    Toast.LENGTH_SHORT).show();
            return;
        } // end if names are both empty

        if (phoneNum.length() != 10) {
            Toast.makeText(EditContactActivity.this, "Please Enter 10 Digit Phone Number",
                    Toast.LENGTH_SHORT).show();
            return;
        } // end if incomplete phone number

        database.updateById(id, firstName, lastName, phoneNum, relation);

        Intent intent = new Intent(this, ContactActivity.class);
        this.startActivity(intent);
        finish();
        Toast.makeText(EditContactActivity.this, "Contact Updated",
                Toast.LENGTH_LONG).show();
    } // end of method editContact

    public void deleteClicked(View v) {
        showDeleteConfirmationDialog(this);
    } // end of method deleteClicked

    public void showDeleteConfirmationDialog(Context context) {
        Contact contact = database.selectById(contactId);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Are you sure you want to delete " + contact.getFirstName() + " "
                       + contact.getLastName() + " from your contacts?");
        PlayDialog deleteDialog = new PlayDialog();
        alert.setPositiveButton("YES", deleteDialog);
        alert.setNegativeButton("NO", deleteDialog);
        alert.show();
    } // end of method showCallConfirmationDialog

    private class PlayDialog implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int id) {
            deleteContact(id);
        }
    } // end of class PlayDialog

    public void deleteContact(int id) {
        if (id == -1)  /* YES */  {
            database.deleteById(contactId);
            Intent intent = new Intent(this, ContactActivity.class);
            this.startActivity(intent);
            finish();
            Toast.makeText(EditContactActivity.this, "Contact Deleted",
                    Toast.LENGTH_LONG).show();
        } // end if yes selected
        else if (id == -2) /* NO */  {
            // do nothing, leave in case we want to add something here later
        } // end else if no selected
    } // end of method phoneAFriend

} // end of class EditContactActivity