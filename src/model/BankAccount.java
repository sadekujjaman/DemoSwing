package model;

import filehandle.WriteAndReadFromFile;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import jsonparse.JsonModel;
import mailhandle.Mailer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Saju
 */
public class BankAccount {
    
    public boolean createBankAccount(Member member) {
        
        System.out.println(System.currentTimeMillis());
        long time = System.currentTimeMillis();
        long hashCode = member.getName().hashCode() + member.getEmail().hashCode() + member.getGender().hashCode() + member.getDivision().hashCode();
        String msg = "<h1>Hello " + member.getName() + "</h1>,\n\n";
        msg = msg + "<h3>Welcome To Juniper Bank...</h3>\n\n";
        msg = msg + "<p>Your Account No:  " + time + "</p>";
        msg = msg + "<p>Your Password:  " + (time + hashCode) + "</p>\n\n";
        
        try {
             Mailer.send("junipertest00@gmail.com", "junipertest00@#", member.getEmail(), "Account No and Password", msg);
       
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
//        Preferences prefs = Preferences.userRoot().node("saju/juniper");
//        prefs.put("accountNo", String.valueOf(time));
//        prefs.put("password", String.valueOf((time + hashCode)));
//
//        String accountNo = prefs.get("accountNo", "unknown");
//        String password = prefs.get("password", "unknown");

//        System.out.println(accountNo + " " + password);
//        member.setAccountNo(Long.parseLong(accountNo));
//        member.setPassword(password);
        member.setAccountNo(time);
        member.setPassword(String.valueOf((time + hashCode)));
        member.setBalance(1000.0);
        Transaction t = new Transaction("deposit_own", 1000.0, getTime(), time);
        List<Transaction>tList = new ArrayList<>();
        tList.add(t);
        member.setTransactions(tList);
        
        JsonModel.generateJSONWithMember(member);
        
        WriteAndReadFromFile.writeToFile("src/files/members.txt", String.valueOf(member.getAccountNo()), true);
        WriteAndReadFromFile.writeToFile("src/files/memberspass.txt", String.valueOf(member.getPassword()), true);
        
        return true;
    }
    
    
    public static void depositOwnAccount(String accountNo, double amount){
        double prev = JsonModel.getBalance(accountNo);
        JsonModel.updateBalance(accountNo,  amount + prev);
        JsonModel.addTransaction(accountNo, new Transaction("deposit_own", amount, getTime(), Long.parseLong(accountNo)));
    }
    
    public static void transferMoney(String ownAccount, String otherAccount, double amount){
        double prev = JsonModel.getBalance(ownAccount);
        if(prev < amount){
            JOptionPane.showMessageDialog(null, "Unsufficient balance", "Error", JOptionPane.ERROR_MESSAGE);
            return ;
        }
        
        JsonModel.updateBalance(ownAccount, prev - amount);
        JsonModel.addTransaction(ownAccount, new Transaction("transfer_to", amount, getTime(), Long.parseLong(otherAccount)));
    
        double prevOther = JsonModel.getBalance(otherAccount);
        JsonModel.updateBalance(otherAccount, prevOther + amount);
        JsonModel.addTransaction(otherAccount, new Transaction("deposit_from", amount, getTime(), Long.parseLong(ownAccount)));
    
    
    }
    
    private static String getTime(){
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
        String dateTime = dateFormat.format(date);
	System.out.println(dateFormat.format(date));
        return dateTime;
    }
//    public static void main(String[] args) throws Exception {
//        BankAccount bk = new BankAccount();
////        JsonModel.parseJSONAndGetMember();
////        Member member = new Member("Saju", "male", "abc@", "Dinajpur");
////        member.setAccountNo(6543);
////        member.setPassword("ana");
////        Member.transactions.add(new Transaction("with", 54399, "", 654));
////        Member.transactions.add(new Transaction("dept", 54399, "", 654));
////        Member.transactions.add(new Transaction("with", 549499, "", 54));
////        bk.generateJSON(member);
//    }
}
