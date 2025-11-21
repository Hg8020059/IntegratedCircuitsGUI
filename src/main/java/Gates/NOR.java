package Gates;
import Basics.*;
import javafx.scene.shape.Circle;

public class NOR extends Gate {
    //Frontend
    public static String path = "M 1 0 C 5 1 5 1 9 0 C 9 3 7 7 5 8 A 1 1 0 0 0 4 9 A 1 1 0 0 0 6 9 A 1 1 0 0 0 5 8 C 3 7 1 3 1 0";
    public static int numInputs = 2;

    //Backend
    public PMOS p1;
    public PMOS p2;
    public NMOS n1;
    public NMOS n2;

    public Wire W_input1;
    public Wire W_input2;
    //----------------------------------------- Constructors --------------------------------------------------
    public NOR(){
        super();
        W_input1 = null;
        W_input2 = null;

        p1 = null;
        p2 = null;
        n1 = null;
        n2 = null;
    }

    public NOR(Wire inputWire, Wire inputWire2){
        super();
        init(inputWire,inputWire2);
    }
    //----------------------------------------- Mutators ------------------------------------------------------
    public void init(Wire inputWire, Wire inputWire2){
        //sets the input wire that was passed in as W_input, then uses that while declaring the Basics.PMOS and Basics.NMOS as their controls
        // Set the input before setting things that depend on the input wire
        W_input1 = inputWire;
        W_input2 = inputWire2;

        Wire p1out = new Wire("Basics.PMOS 1 out");

        p1 = new PMOS(W_vpp, W_input1, p1out);
        p2 = new PMOS(p1out, W_input2, W_out);

        n1 = new NMOS(W_ground, W_input1, W_out);
        n2 = new NMOS(W_ground, W_input2, W_out);
    }

    //----------------------------------------- Accessors -----------------------------------------------------


    //----------------------------------------- Testing -------------------------------------------------------
    public static void main(String[] args){
        Wire Input_Wire = new Wire("In1");
        Wire Input_Wire2 = new Wire("In2");

        Input Input1 = new Input(Input_Wire, false, "In1");
        Input Input2 = new Input(Input_Wire2, false, "In2");

        NOR nor1 = new NOR(Input_Wire, Input_Wire2);

        System.out.println(Util.truthTable(new Input[]{Input1,Input2}, nor1.W_out));
    }
}
