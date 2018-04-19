package com.example.chunyili.mylogin;

/**
 * Created by chunyili on 2018/4/14.
 */

public class Account {
    private int id;
    private String account;
    private String pswd;

    public Account(String account, String pswd) {
        this.account = account;
        this.pswd = pswd;
    }

    public Account(int id, String account, String pswd) {
        this.id = id;
        this.account = account;
        this.pswd = pswd;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
}
