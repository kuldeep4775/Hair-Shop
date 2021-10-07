package com.example.hairshop;

public class Users {

    String  Firstname,Mot,Email1,Pass1;

    public Users(String firstname, String mo, String email, String pass1) {
        Firstname = firstname;
        Mot = mo;
        Email1 = email;
        Pass1 = pass1;
    }

    public Users() {
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getMo() {
        return Mot;
    }

    public void setMo(String mo) {
        Mot = mo;
    }

    public String getEmail() {
        return Email1;
    }

    public void setEmail(String email) {
        Email1 = email;
    }

    public String getPass1() {
        return Pass1;
    }

    public void setPass(String pass1) {
        Pass1 = pass1;
    }

}
