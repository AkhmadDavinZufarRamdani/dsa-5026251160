package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        
        LinkedList<String[]> transactions = new LinkedList<>();

        
        LinkedList<String[]> customers = new LinkedList<>();

        try {
Scanner scanner = new Scanner(
    new File("src/lw02/prelab/transactions.txt")
);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] data = line.split(" ");

                String name = data[0];
                String type = data[1];
                String amount = data[2];

                transactions.add(new String[]{name, type, amount});

                boolean customerExists = false;

                for (String[] customer : customers) {
                    if (customer[0].equals(name)) {
                        customerExists = true;
                        break;
                    }
                }

                if (!customerExists) {
                    customers.add(new String[]{name, "0"});
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt tidak ditemukan.");
            return;
        }

        Queue<String[]> transactionQueue = new LinkedList<>();

        while (!transactions.isEmpty()) {
            transactionQueue.add(transactions.removeFirst());
        }

        Stack<String[]> failedTransactions = new Stack<>();

        while (!transactionQueue.isEmpty()) {

            String[] transaction = transactionQueue.poll();

            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            for (String[] customer : customers) {

                if (customer[0].equals(name)) {

                    int balance = Integer.parseInt(customer[1]);

                    if (type.equals("DEPOSIT")) {
                        balance += amount;
                        customer[1] = String.valueOf(balance);
                    }

                    else if (type.equals("WITHDRAW")) {

                        if (amount > balance) {
                            failedTransactions.push(transaction);
                        }

                        else {
                            balance -= amount;
                            customer[1] = String.valueOf(balance);
                        }
                    }

                    break;
                }
            }
        }

        System.out.println("=== Final Balances ===");

        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println("=== Failed Transactions ===");

        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();

            System.out.println(
                failed[0] + " "
                + failed[1] + " "
                + failed[2]
            );
        }
    }
}