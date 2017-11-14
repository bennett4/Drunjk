package edu.mountunion.csc330.drunjk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contactDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CONTACTS = "contacts";
    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PHONE_NUM = "phoneNum";
    private static final String RELATION = "relation";

    public Database( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    public void onCreate( SQLiteDatabase db ) {
        // build sql create statement
        String sqlCreate = "create table " + TABLE_CONTACTS + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + FIRST_NAME;
        sqlCreate += " text, " + LAST_NAME + " text, " + PHONE_NUM;
        sqlCreate += " text, " + RELATION + " text )" ;
        db.execSQL( sqlCreate );
    }

    public void onUpgrade( SQLiteDatabase db,
                           int oldVersion, int newVersion ) {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_CONTACTS );
        // Re-create tables
        onCreate( db );
    }

    public void insert( Contact contacts ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " + TABLE_CONTACTS;
        sqlInsert += " values( null, '" + contacts.getFirstName( ) + "', '" + contacts.getLastName( );
        sqlInsert += "', '" + contacts.getPhoneNum( ) + "', '" + contacts.getRelation( ) + "' )";

        db.execSQL( sqlInsert );
        db.close( );
    }

    public void deleteById( int id ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " + TABLE_CONTACTS;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL( sqlDelete );
        db.close( );
    }

    public void updateById( int id, String firstName, String lastName, String phoneNum, String relation ) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_CONTACTS;
        sqlUpdate += " set " + FIRST_NAME + " = '" + firstName + "', ";
        sqlUpdate += LAST_NAME + " = '" + lastName + "'";
        sqlUpdate += PHONE_NUM + " = '" + phoneNum + "'";
        sqlUpdate += RELATION + " = '" + relation + "'";
        sqlUpdate += " where " + ID + " = " + id;

        db.execSQL( sqlUpdate );
        db.close( );
    }

    public ArrayList<Contact> selectAll( ) {
        String sqlQuery = "select * from " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<Contact> contacts = new ArrayList<Contact>( );
        while( cursor.moveToNext( ) ) {
            Contact currentContact
                    = new Contact( Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                                   cursor.getString(2), cursor.getString(3), cursor.getString(4) );
            contacts.add( currentContact );
        }
        db.close( );
        return contacts;
    }

    public Contact selectById( int id ) {
        String sqlQuery = "select * from " + TABLE_CONTACTS;
        sqlQuery += " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        Contact contact = null;
        if( cursor.moveToFirst( ) )
            contact = new Contact( Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4) );
        return contact;
    }
}
