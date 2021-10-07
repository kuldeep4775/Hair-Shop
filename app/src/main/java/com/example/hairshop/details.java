package com.example.hairshop;

public class details {

        String firstname,mo,email2,Time,select;

    public details(String firstname, String mo, String email2, String Time,String select) {
        this.firstname = firstname;
        this.mo = mo;
        this.email2 = email2;
        this.Time = Time;
        this.select = select;
    }

    public details() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMo() {
        return mo;
    }

    public void setMo(String mo) {
        this.mo = mo;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }
}
