package Module_3;

import java.util.Scanner;

public class Assignment3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double currentBalance = 0.0;

        while(true){
            System.out.println("Welcome to the Simple Console ATM!");
            System.out.println("Please select an option :");
            System.out.println("1.Check Balance");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. Exit");

            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Your current balance is : "+ currentBalance);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();

                    if (depositAmount > 0) {
                        currentBalance += depositAmount;
                        System.out.println("Deposit successful!");
                    } else {
                        System.out.println("Invalid deposit amount! Amount must be positive.");
                    }
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();

                    if (withdrawAmount <= 0) {
                        System.out.println("Invalid withdrawal amount!");
                    }
                    else if (withdrawAmount > 100) {
                        System.out.println("You can withdraw a maximum of 100 taka per transaction.");
                    }
                    else if (withdrawAmount > currentBalance) {
                        System.out.println("Insufficient balance!");
                    }
                    else {
                        currentBalance -= withdrawAmount;
                        System.out.println("Withdrawal successful. Please collect your cash.");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM! Goodbye.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");

            }
        }
    }
}
