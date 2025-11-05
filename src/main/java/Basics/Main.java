package Basics;// Should work on getting all toStrings to look good at some point

import Gates.NOT;

public class Main {
    public static void main(String[] args) {
        // Basics.Wire Testing
        System.out.println("--------- Basics.Wire Testing ---------");
        Wire.main(args);

        // Basics.Transistor Testing
        System.out.println("--------- Basics.Transistor Testing ---------");
        Transistor.main(args);

        // Not Gate Testing
        System.out.println("--------- Not Testing ---------");
        NOT.main(args);
    }
}