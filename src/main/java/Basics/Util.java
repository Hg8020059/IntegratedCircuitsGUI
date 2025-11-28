package Basics;

import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;

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
    public static String truthTable(Input[] inputs, Transistor output){
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
            System.out.println("| " + toInt(output.recOut()));
        }
        System.out.println();
        return str;
    }

    //create an svg path enclosed by a region
    public static Region createPathRegion(String path) {
        SVGPath svg = new SVGPath();
        svg.setContent(path);
        Region region = new Region();
        region.setShape(svg);
        region.setMinSize(50, 60);
        region.setPrefSize(50, 60);
        region.setMaxSize(50, 60);
        region.setStyle("-fx-background-color: transparent; -fx-border-color: black;");
        return region;
    }

    public static void main(String[] args){

    }
}
