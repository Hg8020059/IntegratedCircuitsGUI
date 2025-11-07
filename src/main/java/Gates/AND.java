package Gates;
import Basics.*;

public class AND extends Gate {
    //Frontend
    public static String path = "M 1 0 L 9 0 L 9 4 C 9 7 7 8 5 8 C 3 8 1 7 1 4 L 1 0";

    //Backend components
    public NAND nand;
    public NOT not;
    public Wire[] w_inputs = new Wire[2];

    public AND(){
        display = Util.createPathRegion(path);
        w_inputs[0] = null;
        w_inputs[1] = null;

        nand = new NAND();
        not = new NOT();
    }

    public AND(Wire inputWire, Wire inputWire2){
        display = Util.createPathRegion(path);
        w_inputs[0] = inputWire;
        w_inputs[1] = inputWire2;
        init();
    }

    //Assuming that w_inputs are all declared
    public void init() {
        for (Wire i : w_inputs) {
            if (i == null) {
                return;
            }
        }

        nand = new NAND(w_inputs[0], w_inputs[1]);
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
