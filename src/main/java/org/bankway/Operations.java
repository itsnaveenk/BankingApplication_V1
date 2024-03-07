package org.bankway;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Operations {
    static Scanner sc = new Scanner(System.in);

    static void createNewAccount(Statement stmt) {
        System.out.println("*** Account opening wizard ***");
        System.out.println("Enter Account No: ");
        int acc_no = sc.nextInt();

        System.out.println("Enter Name: ");
        String name;
        name = sc.next();

        System.out.println("Enter DOB: ");
        String dob;
        dob = sc.next();

        System.out.println("Enter Phone No: ");
        String phone_no;
        phone_no = sc.next();

        System.out.println("Enter Email: ");
        String email = sc.next();

        String sqlQuery = "insert into holders (acc_no, name, dob, phone_no, email) values(" + acc_no + ",'" + name + "','" + dob + "','" + phone_no + "','" + email + "');";

        System.out.println(sqlQuery);
        int result = 0;
        try {
            result = stmt.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (result == 0) {
            System.out.println("Error code: " + result + " \nAccount not created");

        } else {
            System.out.println("Result code: " + result + " \nAccount created");

        }


    }

    static void showBalance(Statement stmt) {
        System.out.println("Enter Account No: ");
        int acc_no = sc.nextInt();
        String query = "SELECT balance FROM holders WHERE acc_no = " + acc_no + ";";

        try {
            ResultSet result = stmt.executeQuery(query);
            if (result.next()) {
                System.out.println("Your Account Balance is : " + result.getInt("balance"));
            } else {
                System.out.println("Account not found. Try again");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void deposit(Statement stmt) {

        System.out.println("Enter Your Account No: ");
        int acc_no = sc.nextInt();
        int currBal = 0;

        String query = "SELECT balance FROM holders WHERE acc_no = " + acc_no + ";";

        try {
            ResultSet result = stmt.executeQuery(query);
            if (result.next()) {

                currBal = result.getInt("balance");

                System.out.println("Enter ammount to deposit: ");
                int addBal = sc.nextInt();
                currBal += addBal;

                String addBalQuery = "update holders set balance = " + currBal + " where acc_no = " + acc_no + ";";
                try {
                    int flag = stmt.executeUpdate(addBalQuery);
                    if (flag == 1) {
                        System.out.println("Congratulations! Deposit Successful.");
                        System.out.println("Updated Balance: " + currBal);
                        System.out.println("If you have any further inquiries or require assistance, please don't hesitate to contact our friendly customer support team.");

                    } else {
                        System.out.println("Error occured during depositing balance");
                    }
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }

            } else {
                System.out.println("Account not found. Try again");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    static void withdraw(Statement stmt) {
        System.out.println("Enter Your Account No: ");
        int acc_no = sc.nextInt();
        int currBal = 0;

        String query = "SELECT balance FROM holders WHERE acc_no = " + acc_no + ";";

        try {
            ResultSet result = stmt.executeQuery(query);
            if (result.next()) {
                currBal = result.getInt("balance");

                System.out.println("Enter ammount to Withdraw: ");
                int subBal = sc.nextInt();
                currBal -= subBal;

                String addBalQuery = "update holders set balance = " + currBal + " where acc_no = " + acc_no + ";";
                try {
                    int flag = stmt.executeUpdate(addBalQuery);
                    if (flag == 1) {
                        System.out.println("Congratulations! Withdraw Successful.");
                        System.out.println("Updated Balance: " + currBal);

                        System.out.println("If you have any further inquiries or require assistance, please don't hesitate to contact our friendly customer support team.");
                    } else {
                        System.out.println("Error occured during Withdrawing balance");
                    }
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }

            } else {
                System.out.println("Account not found. Try again");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    static void allAccounts(Statement stmt) {
        System.out.println("Welcome to the administrator zone");
        System.out.println("Press 0 for viewing all accounts");
        int x = sc.nextInt();

        if (x == 0) {
            String query = "select * from holders";

            try {
                ResultSet result = stmt.executeQuery(query);

                System.out.println("List of accounts: ");
                while(result.next()){
                    System.out.println("Account No: "+result.getInt(1));
                    System.out.println("Name: "+result.getString(2));
                    System.out.println("Date of Birth: "+result.getString(3));
                    System.out.println("Phone No: "+result.getString(4));
                    System.out.println("Email: "+result.getString(5));
                    System.out.println("Balance: "+result.getInt(6));
                }
            } catch (Exception e) {
                e.getLocalizedMessage();
                e.getMessage();
                e.printStackTrace();
            }
        } else {
            System.out.println("Leaving administrator zone...");
        }
    }
}
