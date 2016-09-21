package com.abhinav.keepsafe.Utils;

/**
 * Created by abhinav.sharma on 9/21/2016.
 */
public class AccountModel {
    private String AccountName;
    private String AccountType;
    private String AccountPassword;
    private String AccountTranPassword;
    private String AccountID;

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public String getAccountPassword() {
        return AccountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        AccountPassword = accountPassword;
    }

    public String getAccountTranPassword() {
        return AccountTranPassword;
    }

    public void setAccountTranPassword(String accountTranPassword) {
        AccountTranPassword = accountTranPassword;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
    }
}
