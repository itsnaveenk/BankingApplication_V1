package org.bankway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        final String URL = "jdbc:mysql://localhost:3306/bankwaydb";
        String username = "root";
        String password = "root";

        try {
            Connection connection;
            connection = DriverManager.getConnection(URL, username, password);
            // establish a connection to db.
            System.out.println(connection);

            Statement statement = connection.createStatement();

            Scanner sc = new Scanner(System.in);
            System.out.println("Choose options: \n1. Create new Account \n2. Show balance \n3. Deposits \n4. Withdraw \n5. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
//                    create new account


                    Operations.createNewAccount(statement);


                    break;
                }

                case 2: {
//                    show balance

                    Operations.showBalance(statement);
                    break;
                }
                case 3: {
//                    Deposits

                    Operations.deposit(statement);

                    break;
                }
                case 4: {
//                    Withdraw

                    Operations.withdraw(statement);

                    break;
                }
                case 5: {
//                    exit


                    System.out.println("\nThank you for using our services.\nIf you have any further inquiries or require assistance, please don't hesitate to contact our friendly customer support team.");


                    break;
                }

                default:
                    System.out.println("Invalid Choice!");
//                    break;
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
