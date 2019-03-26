/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Saju
 */
public class Transaction {
 
    private String type;
    private double amount;
    private String dateTime;
    private long accountNo;

    public Transaction() {
    }

    public Transaction(String type, double amount, String dateTime, long accountNo) {
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
        this.accountNo = accountNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }
    
    
    
    
}
