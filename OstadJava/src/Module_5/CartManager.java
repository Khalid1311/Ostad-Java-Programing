package Module_5;

import java.util.Scanner;

public class CartManager {

    static void SelectItem(Scanner sc, CartItem egg, CartItem milk, CartItem noodles, String msg) {
        System.out.println("Select item : 1.Egg 2.Milk 3.Noodles ");
        System.out.print("Enter your Item : ");
        int item = sc.nextInt();

        CartItem selected = (item == 1) ? egg : (item == 2) ? milk : noodles;
        if (msg.equals("Increment"))
            selected.increaseQty();
        else
            selected.decreaseQty();
    }

    static void printCart(CartItem egg, CartItem milk, CartItem noodles) {
        System.out.println("--- CART ITEMS ---");

        if (egg.isAddedToCart()) {
            egg.printItem();
        }

        if (milk.isAddedToCart()) {
            milk.printItem();
        }

        if (noodles.isAddedToCart()) {
            noodles.printItem();
        }
    }

    static void printTotal(CartItem egg, CartItem milk, CartItem noodles) {
        double total = 0;

        if (egg.isAddedToCart()) {
            total += egg.getTotalPrice();
        }

        if (milk.isAddedToCart()) {
            total += milk.getTotalPrice();
        }

        if (noodles.isAddedToCart()) {
            total += noodles.getTotalPrice();
        }

        if (total == 0) {
            System.out.println("Cart is empty");
        } else {
            System.out.println("Total Payable Amount: " + total);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CartItem egg = new CartItem("Egg", 10);
        CartItem milk = new CartItem("Milk", 20);
        CartItem noodles = new CartItem("Noodles", 30);

        while(true){
            System.out.println("\n-------------------------------------------");
            System.out.println("1.Add Egg   2.Add Milk   3.Add Noodles");
            System.out.println("4.Remove Egg  5.Remove Milk  6.Remove Noodles");
            System.out.println("7.Increase Quality  8.Decrease Quality");
            System.out.println("9.Show Cart  10.Total Bill");
            System.out.println("0.Exit");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    egg.addToCart();
                    break;
                case 2:
                    milk.addToCart();
                    break;
                case 3:
                    noodles.addToCart();
                    break;
                case 4:
                    egg.removeFromCart();
                    break;
                case 5:
                    milk.removeFromCart();
                    break;
                case 6:
                    noodles.removeFromCart();
                    break;
                case 7:
                    SelectItem(sc, egg, milk, noodles, "Increment");
                    break;
                case 8:
                    SelectItem(sc, egg, milk, noodles, "Decrement");
                    break;
                case 9:
                    printCart(egg, milk, noodles);
                    break;
                case 10:
                    printTotal(egg, milk, noodles);
                    break;
                case 0:
                    System.out.println("Thank you for shopping!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
