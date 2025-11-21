package Gates;
import Basics.*;
import javafx.scene.shape.Circle;

public class OR extends Gate {
    //Frontend
    public static String path = "M 1 0 C 5 1 5 1 9 0 C 9 3 7 7 5 8 C 3 7 1 3 1 0";
    public static int numInputs = 2;

    //Backend
    public NOR nor;
    public NOT not;
    public Wire W_input1;
    public Wire W_input2;

    //----------------------------------------- Constructors --------------------------------------------------
    public OR(){
        super();
    }

    public OR(Wire inputWire, Wire inputWire2){
        super();
        init(inputWire,inputWire2);
    }

    //----------------------------------------- Mutators ------------------------------------------------------
    public void init(Wire inputWire, Wire inputWire2) {
        //sets the input wire that was passed in as W_input, then uses that while declaring the Basics.PMOS and Basics.NMOS as their controls
        // Set the input before setting things that depend on the input wire
        W_input1 = inputWire;
        W_input2 = inputWire2;

        nor = new NOR(inputWire, inputWire2);
        not = new NOT(nor.W_out);
        W_out = not.W_out;
    }

    public static void main(String[] args){
        Wire Input_Wire = new Wire("In1");
        Wire Input_Wire2 = new Wire("In2");

        Input Input1 = new Input(Input_Wire, false, "In1");
        Input Input2 = new Input(Input_Wire2, false, "In2");

        OR or1 = new OR(Input_Wire, Input_Wire2);

        System.out.println(Util.truthTable(new Input[]{Input1,Input2}, or1.W_out));
    }
}
