/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparse;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BankAccount;
import model.Member;
import model.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Saju
 */
public class JsonModel {

    public static Member parseJSONAndGetMember(String accountNoStr) throws Exception {
        // parsing file "JSONExample.json" 

        Object obj = new JSONParser().parse(new FileReader("src/files/member_" + accountNoStr + ".json"));
//        Object obj = new JSONParser().parse(new FileReader("JSONExample.json")); 

        // typecasting obj to JSONObject 
        JSONObject jo = (JSONObject) obj;

        // getting firstName and lastName 
        String name = (String) jo.get("name");
        long accountNo = (long) jo.get("accountno");
        String email = (String) jo.get("email");
        String password = (String) jo.get("password");
        String division = (String) jo.get("division");
        String gender = (String) jo.get("gender");
        double balance = (double) jo.get("balance");

        JSONArray ja = (JSONArray) jo.get("transactions");
        Iterator itr2 = ja.iterator();
        Iterator<Map.Entry> itr1;
        List<Transaction> transactions = new ArrayList<>();

        while (itr2.hasNext()) {
            itr1 = ((Map) itr2.next()).entrySet().iterator();
            Transaction t = new Transaction();

            t.setAmount((double) itr1.next().getValue());
            t.setDateTime((String) itr1.next().getValue());
            t.setAccountNo((long) itr1.next().getValue());
            t.setType((String) itr1.next().getValue());
            transactions.add(t);

        }

        transactions.forEach((t) -> {
            System.out.println(t.getAccountNo() + " " + t.getAmount());
        });

        Member member = new Member(name, accountNo, password, gender, email, division, balance, transactions);

        return member;

    }

    public static void generateJSONWithMember(Member member) {
        // creating JSONObject 
        JSONObject jo = new JSONObject();

        // putting data to JSONObject
        jo.put("name", member.getName());
        jo.put("accountno", member.getAccountNo());
        jo.put("password", member.getPassword());
        jo.put("gender", member.getGender());
        jo.put("division", member.getDivision());
        jo.put("email", member.getEmail());
        jo.put("balance", member.getBalance());

        Map m;
        JSONArray jsonarr = new JSONArray();

        for (Transaction tr : member.getTransactions()) {
            m = new LinkedHashMap(4);
            m.put("type", tr.getType());
            m.put("amount", tr.getAmount());
            m.put("datetime", tr.getDateTime());
            m.put("accountno", tr.getAccountNo());
            jsonarr.add(m);
        }
       
        jo.put("transactions", jsonarr);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("src/files/member_" + member.getAccountNo() + ".json");
            pw.write(jo.toJSONString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BankAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

        pw.flush();
        pw.close();

    }

    public static void addTransaction(String accountNo, Transaction t) {

        try {
            Object obj = new JSONParser().parse(new FileReader("src/files/member_" + accountNo + ".json"));
//        Object obj = new JSONParser().parse(new FileReader("JSONExample.json")); 

            // typecasting obj to JSONObject 
            JSONObject jo = (JSONObject) obj;
            JSONArray transactions = (JSONArray) jo.get("transactions");
            Map m = new LinkedHashMap(4);
            m.put("type", t.getType());
            m.put("amount", t.getAmount());
            m.put("datetime", t.getDateTime());
            m.put("accountno", t.getAccountNo());
            transactions.add(m);
//            System.out.println(transactions.toJSONString());

            PrintWriter pw = null;
            try {
                pw = new PrintWriter("src/files/member_" + accountNo + ".json");
                pw.write(jo.toJSONString());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BankAccount.class.getName()).log(Level.SEVERE, null, ex);
            }

            pw.flush();
            pw.close();
//         JSONArray transaction = jo.getJSONArray("results");

        } catch (Exception e) {
            System.out.println("exception occured");
        }

    }

    public static void updateBalance(String accountNo, double amount) {
        try {
            Object obj = new JSONParser().parse(new FileReader("src/files/member_" + accountNo + ".json"));
//        Object obj = new JSONParser().parse(new FileReader("JSONExample.json")); 

            // typecasting obj to JSONObject 
            JSONObject jo = (JSONObject) obj;
            jo.put("balance", amount);
//         JSONArray transaction = jo.getJSONArray("results");

            PrintWriter pw = null;
            try {
                pw = new PrintWriter("member_" + accountNo + ".json");
                pw.write(jo.toJSONString());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BankAccount.class.getName()).log(Level.SEVERE, null, ex);
            }

            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("exception occured");
        }
    }

    
    public static double getBalance(String accountNo) {
        Object obj = null;
        double amount = -1;
        try {
            obj = new JSONParser().parse(new FileReader("src/files/member_" + accountNo + ".json"));
            JSONObject jo = (JSONObject) obj;

            // getting firstName and lastName 
            String name = (String) jo.get("name");
            System.out.println("Name: " + name);
            amount = (double) jo.get("balance");
            System.out.println("Amount: " + amount);
        } catch (Exception e) {
            System.out.println("Error Occured");
        }

//        Object obj = new JSONParser().parse(new FileReader("JSONExample.json")); 
        // typecasting obj to JSONObject 
        return amount;
    }

    public static void main(String[] args) {
        getBalance("1553552519085");
        updateBalance("1553552519085", 2000);
    }
}
