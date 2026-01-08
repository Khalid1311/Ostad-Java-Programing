package Module_2;

import java.util.Scanner;

public class Assignment2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Selling Price & Units Sold");

        System.out.print("Potato selling price per kg: ");
        double potatoSellPrice = sc.nextDouble();
        System.out.print("Potato sold (kg): ");
        double potatoSold = sc.nextDouble();

        System.out.print("Onion selling price per kg: ");
        double onionSellPrice = sc.nextDouble();
        System.out.print("Onion sold (kg): ");
        double onionSold = sc.nextDouble();

        System.out.print("Oil selling price per liter: ");
        double oilSellPrice = sc.nextDouble();
        System.out.print("Oil sold (liter): ");
        double oilSold = sc.nextDouble();

        double potatoRevenue = potatoSellPrice * potatoSold;
        double onionRevenue = onionSellPrice * onionSold;
        double oilRevenue = oilSellPrice * oilSold;

        double totalSoldPrice = potatoRevenue + onionRevenue + oilRevenue;

        System.out.println("\n=== Enter Purchasing Price & Units Purchased ===");

        System.out.print("Potato purchasing price per kg: ");
        double potatoBuyPrice = sc.nextDouble();
        System.out.print("Potato purchased (kg): ");
        double potatoPurchased = sc.nextDouble();

        System.out.print("Onion purchasing price per kg: ");
        double onionBuyPrice = sc.nextDouble();
        System.out.print("Onion purchased (kg): ");
        double onionPurchased = sc.nextDouble();

        System.out.print("Oil purchasing price per liter: ");
        double oilBuyPrice = sc.nextDouble();
        System.out.print("Oil purchased (liter): ");
        double oilPurchased = sc.nextDouble();

        System.out.print("Total Transportation Cost: ");
        double transportationCost = sc.nextDouble();

        double potatoPurchaseCost = potatoBuyPrice * potatoPurchased;
        double onionPurchaseCost = onionBuyPrice * onionPurchased;
        double oilPurchaseCost = oilBuyPrice * oilPurchased;

        double totalPurchaseCost = potatoPurchaseCost + onionPurchaseCost + oilPurchaseCost;

        double netIncome = totalSoldPrice - (totalPurchaseCost + transportationCost);

        System.out.println("Grocery Store Net Income Calculator");

        System.out.println("Sales Data");
        System.out.println("Potato Revenue: " + potatoRevenue);
        System.out.println("Onion Revenue: " + onionRevenue);
        System.out.println("Oil Revenue: " + oilRevenue);
        System.out.println("Total Sold Price (Revenue): " + totalSoldPrice);

        System.out.println("Cost Data");
        System.out.println("Potato Purchase Cost: " + potatoPurchaseCost);
        System.out.println("Onion Purchase Cost: " + onionPurchaseCost);
        System.out.println("Oil Purchase Cost: " + oilPurchaseCost);
        System.out.println("Total Purchase Cost (COGS): " + totalPurchaseCost);
        System.out.println("Total Transportation Cost: " + transportationCost);

        System.out.println("Final Result");
        System.out.println("Net Income: " + netIncome);

        sc.close();
    }
}
