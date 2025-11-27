//package Gates;
//import Basics.*;
//import javafx.scene.shape.Circle;
//
//public class NOT extends Gate {
//    //Frontend
//    public static String path = "M 0 0 L 8 0 L 4 6 A 1 1 0 0 0 3 7 A 1 1 0 0 0 5 7 A 1 1 0 0 0 4 6 L 0 0";
//    public static int numInputs = 1;
//
//    //Backend
//    // Setting up internal circuitry for the not gate
//    public PMOS p1;
//    public NMOS n1;
//    public Wire W_input1;
//
//    //----------------------------------------- Constructors --------------------------------------------------
//    public NOT(){
//        super();
//        p1 = null;
//        n1 = null;
//        W_input1 = null;
//    }
//
//
//    public NOT(Wire inputWire){
//        super();
//        init(inputWire);
//    }
//    //----------------------------------------- Mutators ------------------------------------------------------
//
//    public void init(Wire inputWire) {
//        //sets the input wire that was passed in as W_input, then uses that while declaring the Basics.PMOS and Basics.NMOS as their controls
//        // Set the input before setting things that depend on the input wire
//        W_input1 = inputWire;
//        p1 = new PMOS(W_vpp, W_input1, W_out);
//        n1 = new NMOS(W_ground, W_input1, W_out);
//    }
//
//    //----------------------------------------- Accessors -----------------------------------------------------
//
//    //----------------------------------------- Testing -------------------------------------------------------
//
//    public static void main(String[] args){
//        Wire w_in = new Wire("NOT_In");
//        Input input = new Input(w_in, true, "NOT_In");
//        NOT not = new NOT(w_in);
//
//        System.out.println(Util.truthTable(new Input[]{input}, not.W_out));
//    }
//}
