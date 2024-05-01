package org.example;

import java.io.IOException;
import java.util.Scanner;

public class ArithmeticApp {
    public static boolean isInteger(String str,int base) {
        try {
            Integer.parseInt(str,base);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String[] get_array_from_expression(String exp,int base)
    {

        exp = exp.replaceAll("\\s+", " ");
        /**String temp = "";
        for (i =0; i<exp.length();i++)
        {
            if(exp.charAt(i) == '+' || exp.charAt(i) == '-')
            {
                if (i == 0 || exp.charAt(i-1)=='(')
                    temp = temp.concat("0"+exp.charAt(i));
                else
                    temp = temp.concat(""+exp.charAt(i));
            }
            else if(exp.charAt(i) == '(')
            {
                if (i==0 ||exp.charAt(i-1)=='(')
                    temp = temp.concat("1*(");
                else if(isInteger(exp.charAt(i-1)+"",base))
                {
                    temp = temp.concat("*(");
                }
                else
                    temp = temp.concat(exp.charAt(i)+"");

            }
            else if(exp.charAt(i) == ')')
            {
                if (i==exp.length()-1 ||exp.charAt(i+1)==')')
                    temp = temp.concat(")*1");
                else if(isInteger(exp.charAt(i+1)+"",base)||exp.charAt(i+1)=='(')
                {
                    temp = temp.concat(")*");
                }
                else
                    temp = temp.concat(exp.charAt(i)+"");

            }
            else
                temp = temp.concat(exp.charAt(i)+"");

        }
        exp = temp;**/

        String[] parts = exp.split("\\s|(?<=[+\\-*/])|(?=[+\\-*/])");
        int real_length = parts.length;
        for (int i =0 ; i < parts.length ; i++)
        {
            parts[i] =  parts[i].trim();
            if (parts[i].isEmpty())
            {
                real_length--;
            }
        }
        String[] result = new String[real_length];
        int j =0 ;
        for (int i =0 ; i < parts.length ; i++)
        {
            if (!parts[i].isEmpty())
            {
                result[j] = parts[i];
                j++;
            }
        }
        return result;
    }
    public static boolean is_vaild(String[] exp , int base)
    {
        
        boolean the_previous_is_a_number = false;
        for (int i= 0; i < exp.length; i++)
        {
            //this is not a valid expression if the part is not a number or arthmitical sign
            if(!isInteger(exp[i], base) && !exp[i].equals("+") && !exp[i].equals("-") && !exp[i].equals("*") && !exp[i].equals("/"))
            {
                return false;
            }

            if (i==0 || i==exp.length-1)
            {

                /*if the first or the last part is not a number then this means that this is not a valid expression*/
                if (exp[i].equals("/") || exp[i].equals("*")|| exp[i].equals("-")|| exp[i].equals("+"))
                {
                    return false;
                }
                if (i==0)
                {
                    the_previous_is_a_number = true;
                    continue;
                }
            }

            // if we have 2 numbers or 2 arithmetical signs following each other that means invalidity

            boolean is_a_number = isInteger(exp[i], base);
            if (the_previous_is_a_number && is_a_number){
                return false;
            }
            else if(!the_previous_is_a_number && !is_a_number)
                return false;
            the_previous_is_a_number = is_a_number;


        }
        return true;
    }
    public static int answer_expression_with_out_parentheses(String[] exp , int base)
    {
        for(int i = 0 ;i < exp.length;i++)
        {
            //convert each number to base 10
            if (isInteger(exp[i], base)){
                double current_int = Integer.parseInt(exp[i],base);
                exp[i] = current_int +"";
            }
        }
        String[] new_exp = new String[exp.length];
        int j = 0;
        int i = 0;
        while (i < exp.length){
            // if we have / or *  then we don't include the first number of the / and the * in the new_exp but we include it in the
            //calculation
            if (i+1 < exp.length&&(exp[i+1].equals("/") || exp[i+1].equals("*")))
            {
                i++;
            }
            double current_result = 0;
            boolean first = true;
            // solve the current "/" and "*" expression
            while (i < exp.length&&(exp[i].equals("/") || exp[i].equals("*")))
            {
                // save the first number
                if (first)
                {
                    current_result = Double.parseDouble(exp[i-1]);
                    first = false;
                }
                if (exp[i].equals("/"))
                {
                    if (Double.parseDouble(exp[i+1]) ==0){
                        System.out.println("Error: trying to divide by 0 (evaluated: \"0\")");
                        return -1;
                    }
                    else{
                        double second_int = Double.parseDouble(exp[i+1]);
                        current_result = current_result  / second_int;

                    }
                }
                else if(exp[i].equals("*"))
                {
                    double second_int = Double.parseDouble(exp[i+1]);
                    current_result = current_result  * second_int;
                }
                i+=2;
            }

            if (!first){
                // if you have caluclated expression save it
                String result = Double.toString(current_result);
                new_exp[j] = result;
                j++;
            }
            if (i >= exp.length)
                continue;
            new_exp[j] = exp[i];
            j++;
            i++;
        }
        i = 1;
        double final_result = Double.parseDouble(new_exp[0]);
        while ( i < new_exp.length && new_exp[i]!=null ){
            if (new_exp[i].equals("-"))
            {
                double second_int = Double.parseDouble(new_exp[i+1]);
                final_result = final_result - second_int;
            }
            else if(new_exp[i].equals("+"))
            {
                double second_int = Double.parseDouble(new_exp[i+1]);
                final_result = final_result + second_int;
            }
            i+=2;
        }

        return (int)final_result;
    }
    public static int answer_expression(String[] exp , int base){

        boolean has_parentheses = false;

        for (int i =0 ; i < exp.length;i++)
        {
            if (exp[i].equals("(")){
                has_parentheses = true;
                break;
            }
        }
        if (!has_parentheses)
        {
            return answer_expression_with_out_parentheses(exp,base);
        }
        String[] new_exp = new String[exp.length];
        String[] current_exp = exp.clone();

        while (new_exp.length!=1) {
            int open_parentheses = 0;
            boolean need_rec = false;
            int j = 0;
            int i = 0;
            int re_i = 0;
            String[] re_exp = new String[current_exp.length];
            while (i < current_exp.length) {
                if (current_exp[i].equals("(")) {
                    if (open_parentheses ==0 )
                        i++;
                    open_parentheses++;
                    need_rec = true;
                } else if (current_exp[i].equals(")")) {

                    open_parentheses--;
                }
                if (open_parentheses == 0 && !need_rec) {
                    new_exp[j] = current_exp[i];
                    j++;

                } else if (open_parentheses == 0 && need_rec) {
                    need_rec = false;
                    int m = 0;
                    while (m < re_exp.length && re_exp[m] != null)
                        m++;
                    String[] real_re_exp = new String[m];
                    for (m = 0; m < real_re_exp.length; m++)
                        real_re_exp[m] = re_exp[m];
                    re_exp = new String[current_exp.length];
                    re_i =0;
                    int current_par_result = answer_expression(real_re_exp, base);
                    new_exp[j] = current_par_result + "";
                    j++;
                } else {
                    re_exp[re_i] = current_exp[i];
                    re_i++;
                }
                i++;
            }
            int k =0 ;
            while (k<new_exp.length && new_exp[k]!=null)
            {
                k++;
            }
            current_exp = new String[k];
            for (k = 0 ; k<current_exp.length;k++)
            {
                current_exp[k] = new_exp[k];
            }
            return answer_expression(current_exp,base);
        }
        return Integer.parseInt(current_exp[0]);

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
        String[] parts = get_array_from_expression(exp,base);
        boolean valid = is_vaild(parts,base);

        if (!valid)
        {
            System.out.println("Error: invalid expression: \"\"");
            /**exp =sc.next();
            parts = get_array_from_expression(exp,base);
            valid = is_vaild(parts,base);**/
        }
        else {


            int answer = answer_expression(parts, base);
            if (answer != -1) {
                //System.out.println("The value of the expression in base 10" + exp + " is: " + answer);
                String final_answer = Integer.toString(answer, base).toUpperCase();
                System.out.println("The value of expression " + exp + " is: " + final_answer);
            }
        }

    }
}
