package com.example.mobilebank;

import java.util.Arrays;

// A Blueprint for all Bank Accounts to follow, Utilizes Encapsulation:
class Account {
    // The Encapsulated Data:
    private String username, password, fName, bankId;
    private float balance;

    // The Constructor for the Account:
    public Account(String in1, String in2, String in3, String in4, float in5) {
        this.username = in1;
        this.password = in2;
        this.fName = in3;
        this.bankId = in4;
        this.balance = in5;
    }

    // Getters and Setters for the Encapsulated Data:
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFName() {
        return fName;
    }

    public String getBankId() {
        return bankId;
    }

    public float getBalance() {
        return balance;
    }

    // The Methods responsible for Increasing and Decreasing the Balance:
    public void increaseBalance(float amount) {
        this.balance += amount;
    }

    public void decreaseBalance(float amount) {
        this.balance -= amount;
    }

}

// Main Object Created in other Classes, Inherits from the Abstract Bank Class:
class BankExternal extends Bank {
    // A 2D String Array needed for the Log In Method below it:
    private String[][] accountInfo = getAccountInfo();

    // A Method that enables the Log In Feature:
    public boolean login(String user, String password) {
        if (Arrays.asList(accountInfo[0]).contains(user) || Arrays.asList(accountInfo[2]).contains(user)) {
            // If there is a match in the Username or ID, return true or false depending on the inputted password:
            return accountInfo[1][getId()].equals(password);
        }
        // Else, return False:
        return false;
    }
}

// Inherits from the MainActivity Class, Utilizes Inheritance, Encapsulation, & Abstraction:
abstract class Bank extends MainActivity{
    private static Account[] account = new Account[] {
            new Account("Zeke", "Villasurda", "Ezekiel Villasurda", "20230001", 20000),
            new Account("Raden", "Alvarez", "Raden Alvarez", "20230003", 20000),
            new Account("Mozes", "Garcia", "John Mark Garcia", "20230005", 20000),
            new Account("Joseph", "GarciaJr", "Jude Joseph Garcia Jr.", "20230007", 20000),
            new Account("Jay", "Malinis", "Jermaine Jared Malinis", "20230007", 20000),
            new Account("Heisenberg", "White", "Walter White", "20230011", 5000000)
            // Can add multiple accounts here that all can use the Balance Transfer Feature.
    };

    // Declare Needed variables for the Bank:
    private static String[][] accountInfo = new String[3][account.length];

    private static int id;

    // Method to gather information for the Login and Transfer Process:
    private static void setAccountInfo() {
        for (int i = 0; i < account.length; i++) {
            accountInfo[0][i] = account[i].getUsername();
            accountInfo[1][i] = account[i].getPassword();
            accountInfo[2][i] = account[i].getBankId();
        }
    }


    // Initializes the Bank's Account Information and ID gathering:
    public static void init() {
        setAccountInfo();
        id = setId(input[0]);
    }


    // Find the index of the current user on the Account Info Array:
    public static int setId(String user) {
        int i = Arrays.asList(accountInfo[0]).indexOf(user);
        if (i == -1) return Arrays.asList(accountInfo[2]).indexOf(user);
        return i;
    }

    // Public Methods to be used to get or set the Encapsulated Values:
    public int getId() {
        return id;
    }

    public String[][] getAccountInfo() {
        return accountInfo;
    }


    public String getUsername() {
        return account[id].getUsername();
    }

    public String geFName() {
        return  account[id].getFName();
    }

    public String getBankId() {
        return  account[id].getBankId();
    }

    public float getBalance() {
        return account[id].getBalance();
    }

    // The only method that can change the values of balance:
    public void balanceTransfer(int transferee, float amount) {
        account[transferee].increaseBalance(amount);
        account[id].decreaseBalance(amount);
    }
}
