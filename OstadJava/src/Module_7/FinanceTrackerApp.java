package Module_7;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FinanceTrackerApp {

    private static void AddTransaction(Scanner sc,ArrayList<Transaction> transactions){
        String id = String.valueOf(transactions.size());

        String type;
        while (true){
            System.out.print("Enter type (INCOME/EXPENSE) : ");
            type = sc.nextLine();
            if(type.equalsIgnoreCase("INCOME") || type.equalsIgnoreCase("EXPENSE"))break;
            else System.out.println("Type must be INCOME or EXPENSE");
        }

        double amount;
        while (true){
            System.out.print("Enter the amount : ");
            amount = sc.nextDouble();
            if (amount > 0)break;
            else System.out.println("Amount must be positive!");
        }
        System.out.print("Enter the Description: ");
        String description = sc.nextLine();

        String date;
        System.out.print("Use today's date? (y/n): ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            date = LocalDate.now().toString();
        } else {
            System.out.print("Enter date (YYYY-MM-DD): ");
            date = sc.nextLine();
        }

        Transaction t = new Transaction(id,type,amount,description,date);
        transactions.add(t);
        System.out.println("Transaction added successfully!");
    }

    private static void ReadTransaction(ArrayList<Transaction> transactions){
        if (transactions.isEmpty()){
            System.out.println("No transactions found!");
            return;
        }
        else {
            System.out.println("\nID | TYPE | AMOUNT | DESCRIPTION | DATE");
            for (Transaction t : transactions){
                System.out.print(t.getID() + " | " + t.getType() + " | " + t.getAmount() + " | "+ t.getDescription() + " | " +
                        t.getDate());
            }
        }
    }

    private static void UpdateTransaction(Scanner sc, ArrayList<Transaction> transactions){
        System.out.print("Enter ID to update: ");
        String id = sc.nextLine();
        for(Transaction t : transactions){
            if(t.getID().equals(id)){
                System.out.print("New type (INCOME/EXPENSE): ");
                t.setType(sc.nextLine());

                double amount;
                while (true) {
                    System.out.print("New amount: ");
                    amount = sc.nextDouble();
                    sc.nextLine();
                    if (amount > 0) break;
                    System.out.println("Amount must be positive!");
                }
                t.setAmount(amount);

                System.out.print("New description: ");
                t.setDescription(sc.nextLine());

                String date;
                System.out.print("Use today's date? (y/n): ");
                String choice = sc.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    date = LocalDate.now().toString();
                } else {
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    date = sc.nextLine();
                }
                t.setDate(date);
                System.out.println("Transaction updated!");
            }
        }
    }

    private static void DeleteTransaction(Scanner sc, ArrayList<Transaction> transactions){
        System.out.print("Enter ID to Delete: ");
        String id = sc.nextLine();
        for (Transaction t:transactions){
            if(t.getID().equals(id)){
                transactions.remove(t);
                System.out.println("Transaction deleted!");
            }
        }
    }

    private static void CalculateBalance(ArrayList<Transaction> transactions) {
        double income = 0, expense = 0;
        for (Transaction t : transactions) {
            if (t.getType().equals("INCOME")) {
                income += t.getAmount();
            } else {
                expense += t.getAmount();
            }
        }
        System.out.println("Total Income : " + income);
        System.out.println("Total Expense: " + expense);
        System.out.println("Balance      : " + (income - expense));
    }

    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("""
                    \n-----Personal Finance Tracker-----
                    1. Add Transaction
                    2. Read Transaction
                    3. Update Transaction
                    4. Delete Transaction
                    0. Exit                 
                    """);
            System.out.print("Enter the choice : ");
            int choice = sc.nextInt();

            switch (choice){

                case 1:
                    AddTransaction(sc,transactions); break;
                case 2:
                    ReadTransaction(transactions); break;
                case 3:
                    UpdateTransaction(sc,transactions); break;
                case 4:
                    DeleteTransaction(sc, transactions); break;
                case 5:
                    CalculateBalance(transactions); break;
                case 0:
                    System.out.println("Exiting... Thank you");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
