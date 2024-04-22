package org.example;

import java.io.IOException;
import java.util.Scanner;

public class ArithmeticApp {
    public static String[] get_array_from_expression(String exp)
    {
        exp = exp.replaceAll("\\s", "");
        String[] parts = exp.split("(?<=[+\\-*/])|(?=[+\\-*/])");
        return parts;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter base (2/8/10/16):");
        int base =sc.nextInt();
        sc.nextLine();
        while (base != 2 && base != 8 && base != 10 && base != 16) {
            System.out.println("Error – this base isn’t supported. Please enter a base (2/8/10/16):");
            base =sc.nextInt();
            sc.nextLine();
        }

        System.out.println("Enter expression:");

        String exp =sc.nextLine();
        String[] parts = get_array_from_expression(exp);
        while (parts == null)
        {
            System.out.println("Error: invalid expression:");
            exp =sc.next();
            parts = get_array_from_expression(exp);
        }




    }
}
