/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Saju
 */
public class Member {
    
    private String name;
    private long accountNo;
    private String password;
    private String gender;
    private String email;
    private String division;
    private double balance;
    
    
    
    private  List<Transaction> transactions = new ArrayList<>();
    
    
    public Member(String name, String gender, String email, String division) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.division = division;
        this.balance = 0.0;
        
    }

    public Member(String name, long accountNo, String password, String gender, String email, String division, double balance, List<Transaction> transaction) {
        this.name = name;
        this.accountNo = accountNo;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.division = division;
        this.balance = balance;
        this.transactions = transaction;
    }
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    
    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getAllWithDraw(){
        List<Transaction> withDraws = new ArrayList<>();
        
        transactions.stream().filter((t) -> (t.getType().equals("Withdraw"))).forEachOrdered((t) -> {
            withDraws.add(t);
        });
        
        return withDraws;
    }
    
    public List<Transaction> getAllDeposit(){
        List<Transaction> deposits = new ArrayList<>();
        
        transactions.stream().filter((t) -> (t.getType().equals("Deposit"))).forEachOrdered((t) -> {
            deposits.add(t);
        });
        
        return deposits;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
    
}
