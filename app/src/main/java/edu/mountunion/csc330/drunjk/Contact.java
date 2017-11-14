package edu.mountunion.csc330.drunjk;

/**
 * Created by bennetmj on 11/10/2017.
 */

public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String relation;

    public Contact( int newId, String newFirstName, String newLastName, String newPhoneNum, String newRelation ) {
        setId( newId );
        setFirstName( newFirstName );
        setLastName( newLastName );
        setPhoneNum ( newPhoneNum );
        setRelation ( newRelation );
    }

    public void setId( int newId ) {
        id = newId;
    }

    public void setFirstName( String newFirstName ) {
        firstName = newFirstName;
    }

    public void setLastName( String newLastName ) {
        lastName = newLastName;
    }

    public void setPhoneNum( String newPhoneNum ) {
        phoneNum = newPhoneNum;
    }

    public void setRelation( String newRelation ) {
        relation = newRelation;
    }

    public int getId( ) {
        return id;
    }

    public String getFirstName( ) {
        return firstName;
    }

    public String getLastName( ) {
        return lastName;
    }

    public String getPhoneNum( ) {
        return phoneNum;
    }

    public String getRelation( ) {
        return relation;
    }

    public String toString( ) {
        return id + "; " + firstName + "; " + lastName + "; " + phoneNum + "; " + relation;
    }
}