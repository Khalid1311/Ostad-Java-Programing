package Module_4;

import java.util.Scanner;

public class Assignment4 {

    public static double valueInput(String message){
        Scanner sc = new Scanner(System.in);
        double value;

        while(true){
            System.out.print("Enter the "+message+" : " );
            value = sc.nextDouble();

            if(value > 0) return value;
            else {
                System.out.println("Invalid input! Please enter a  positive number");
            }
        }
    }

    public static double calculateRectangle(double length ,double width ,String message){
            if(message.equals("area")){
                return length * width;
            }
            if(message.equals("perimeter")){
                return 2 * (length + width);
            }
            return 0;
    }

    public  static void displayDetails(double length ,double width ,double area ,double perimeter){
        System.out.println("------- Rectangle Details --------");
        System.out.println("Length     : " + length);
        System.out.println("Width      : " + width);
        System.out.println("Area       : " + area);
        System.out.println("Perimeter  : " + perimeter);
    }

    public static void main(String[] args) {

        double length = valueInput("Length");
        double width = valueInput("Width");

        double Area = calculateRectangle(length ,width ,"area");
        double Perimeter = calculateRectangle(length ,width ,"perimeter");

        displayDetails(length ,width ,Area ,Perimeter);
    }
}
