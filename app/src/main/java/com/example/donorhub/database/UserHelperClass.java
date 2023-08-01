package com.example.donorhub.database;

public class UserHelperClass {

    String fullName, userName, email, phoneNo, password, _user;

    public UserHelperClass(){}

    public UserHelperClass(String fullName, String userName, String email, String phoneNo, String password, String user) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this._user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String get_user() {
        return _user;
    }

    public void set_user(String _user) {
        this._user = _user;
    }


}
