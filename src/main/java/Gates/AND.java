package Gates;
import Basics.*;

public class AND extends Gate {
    //Frontend
    public static String path = "M 1 0 L 9 0 L 9 4 C 9 7 7 8 5 8 C 3 8 1 7 1 4 L 1 0";

    //Backend components
    public NAND nand;
    public NOT not;
    public Wire W_input1;
    public Wire W_input2;

    public AND(){
        W_input1 = null;
        W_input2 = null;

        nand = new NAND();
        not = new NOT();
    }

    public AND(Wire inputWire, Wire inputWire2){
        init(inputWire,inputWire2);
    }

    public void init(Wire inputWire, Wire inputWire2) {
        //sets the input wire that was passed in as W_input, then uses that while declaring the Basics.PMOS and Basics.NMOS as their controls
        // Set the input before setting things that depend on the input wire
        W_input1 = inputWire;
        W_input2 = inputWire2;

        nand = new NAND(inputWire, inputWire2);
        not = new NOT(nand.W_out);
        W_out = not.W_out;
    }

    public static void main(String[] args){
        Wire Input_Wire = new Wire("In1");
        Wire Input_Wire2 = new Wire("In2");

        Input Input1 = new Input(Input_Wire, false, "In1");
        Input Input2 = new Input(Input_Wire2, false, "In2");

        AND and1 = new AND(Input_Wire, Input_Wire2);

        System.out.println(Util.truthTable(new Input[]{Input1,Input2}, and1.W_out));
    }
}
