package Gates;
import Basics.*;
import javafx.scene.layout.Region;

public class NAND extends Gates{
    //Frontend
    public static String path = "M 1 0 L 9 0 L 9 4 C 9 7 7 8 5 8 A 1 1 0 0 0 4 9 A 1 1 0 0 0 6 9 A 1 1 0 0 0 5 8 C 3 8 1 7 1 4 L 1 0";
    public int numInputs = 2;

    //Backend
    public PMOS p1;
    public PMOS p2;
    public NMOS n1;
    public NMOS n2;

    public Wire W_input1;
    public Wire W_input2;
    //----------------------------------------- Constructors --------------------------------------------------

    public NAND(){
        p1 = null;
        p2 = null;
        n1 = null;
        n2 = null;

        W_input1 = null;
        W_input2 = null;
    }

    public NAND(Wire inputWire, Wire inputWire2){
        init(inputWire, inputWire2);
    }
    //----------------------------------------- Mutators ------------------------------------------------------

    public void init(Wire inputWire, Wire inputWire2){
        //sets the input wire that was passed in as W_input, then uses that while declaring the PMOS and NMOS as their controls
        // Set the input before setting things that depend on the input wire
        W_input1 = inputWire;
        W_input2 = inputWire2;

        p1 = new PMOS(W_vpp, W_input1, W_out);
        p2 = new PMOS(W_vpp, W_input2, W_out);

        Wire n1out = new Wire("NMOS 1 out");

        n1 = new NMOS(W_ground, W_input1, n1out);
        n2 = new NMOS(n1out, W_input2, W_out);
    }

    //----------------------------------------- Accessors -----------------------------------------------------


    //----------------------------------------- Testing -------------------------------------------------------
    public static void main(String[] args){
        Wire w_in1 = new Wire();
        Wire w_in2 = new Wire();
        NAND nand = new NAND(w_in1,w_in2);
        Input Input1 = new Input(w_in1, false);
        Input Input2 = new Input(w_in2, false);

        System.out.println(nand.p1);
        Util.truthTable(new Input[]{Input1,Input2}, nand.W_out);
    }
}
