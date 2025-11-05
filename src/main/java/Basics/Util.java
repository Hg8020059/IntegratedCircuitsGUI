package Basics;

import Gates.AND;
import Gates.NOT;

import java.lang.Math;

public class Util {
    public static int toInt(Boolean val){
        return val ? 1 : 0;
    }

    //returns true if the number is anything other than 0
    public static boolean toBool(int val){
        return val != 0;
    }

    //Takes a number of inputs and generates an array of all the possible input combinations for that number of inputs
    //Mainly a helper function for truthTable atm
    public static int[][] testValues(int val){
        int numCombinations = (int) Math.pow(2,val);
        int[][] arr = new int[numCombinations][val];
        int pos = val - 1;

        for (int i = 0; i < numCombinations; i++) {
            for (int j = 0; j < val; j++) {
                arr[i][pos] = (i & (int) Math.pow(2,j)) >> j;
                pos--;
            }
            pos = val - 1;
        }
        return arr;
    }

    //Takes a given amount of inputs and an output, assumes that they are connected, then prints the output for all
    //possible combinations of inputs in the form of a truth table
    //This allows an easy way of seeing the truth table for any system, you just need to provide the inputs and outputs
    public static String truthTable(Input[] inputs, Wire output){
        String str = "";
        int numIn = inputs.length;
        int[][] arr = testValues(numIn);

        //Print the top of the truth table
        for (int i = 0; i < numIn; i++) {
            System.out.print("x" + i + " ");
        }
        System.out.println("| F");

        for (int i = 0; i < numIn; i++) {
            System.out.print("---");
        }
        System.out.print("|---");
        System.out.println();


        //Print Inputs and Outputs
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < numIn; j++) {
                inputs[j].setVal(toBool(arr[i][j]));
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println("| " + toInt(output.getOut()));
        }
        System.out.println();
        return str;
    }

    public static void main(String[] args){
        Wire w_in1 = new Wire();
        Wire w_in2 = new Wire();
        AND and = new AND(w_in1,w_in2);
        Input Input1 = new Input(w_in1, false);
        Input Input2 = new Input(w_in2, false);

        NOT not = new NOT(w_in1);

        Util.truthTable(new Input[]{Input1,Input2}, and.W_out);
        Util.truthTable(new Input[]{Input1}, not.W_out);

        not.init(and.W_out);

        Util.truthTable(new Input[]{Input1, Input2}, not.W_out);

    }
}
