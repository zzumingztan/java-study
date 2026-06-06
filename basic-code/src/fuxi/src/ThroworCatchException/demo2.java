package jichengtest.ThroworCatchException;

import java.util.Scanner;

public class demo2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the first number");
        int number1 = scanner.nextInt();
        
        System.out.println("Enter the second number");
        int number2 = scanner.nextInt();
        
        System.out.print(number1 + "/" + number2 + "=");


        try {
            int result = number1 / number2;
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("\n❌ 捕获到异常！");
            System.out.println(e.getMessage());

            throw new RuntimeException(e);
            // throw new Exception("自定义异常");
        }


        scanner.close();
    }
}
