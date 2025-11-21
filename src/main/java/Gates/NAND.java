package Gates;
import Basics.*;
import javafx.scene.shape.Circle;

public class NAND extends Gate {
    //Frontend
    public static String path = "M 1 0 L 9 0 L 9 4 C 9 7 7 8 5 8 A 1 1 0 0 0 4 9 A 1 1 0 0 0 6 9 A 1 1 0 0 0 5 8 C 3 8 1 7 1 4 L 1 0";
    public static int numInputs = 2;

    //Backend
    public Wire[] w_inputs = new Wire[2];

    public PMOS p1;
    public PMOS p2;
    public NMOS n1;
    public NMOS n2;
    //----------------------------------------- Constructors --------------------------------------------------

    public NAND(){
        super();
        p1 = null;
        p2 = null;
        n1 = null;
        n2 = null;

        w_inputs[0] = null;
        w_inputs[1] = null;
    }

    public NAND(Wire inputWire, Wire inputWire2){
        super();
        w_inputs[0] = inputWire;
        w_inputs[1] = inputWire2;
        init();
    }
    //----------------------------------------- Mutators ------------------------------------------------------

    public void init(){
        //sets the input wire that was passed in as W_input, then uses that while declaring the PMOS and NMOS as their controls
        // Set the input before setting things that depend on the input wire
        for (Wire i : w_inputs) {
            if (i == null) {
                return;
            }
        }

        p1 = new PMOS(W_vpp, w_inputs[0], W_out);
        p2 = new PMOS(W_vpp, w_inputs[1], W_out);

        Wire n1out = new Wire("NMOS 1 out");

        n1 = new NMOS(W_ground, w_inputs[0], n1out);
        n2 = new NMOS(n1out, w_inputs[1], W_out);
    }


    public boolean addInput(Wire wire){
        for (int i = 0; i < w_inputs.length; i++) {
            // If there's an unused input
            if(w_inputs[i] == null){
                w_inputs[i] = wire;
                return true;
            }
        }
        return false;
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
