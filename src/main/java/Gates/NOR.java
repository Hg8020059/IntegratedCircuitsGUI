package Gates;
import Basics.*;

public class NOR extends Gate {
    //Frontend
    public static String path = "M 1 0 C 5 1 5 1 9 0 C 9 3 7 7 5 8 A 1 1 0 0 0 4 9 A 1 1 0 0 0 6 9 A 1 1 0 0 0 5 8 C 3 7 1 3 1 0";
    public static int numInputs = 2;

    //Backend
    public PMOS p1;
    public PMOS p2;
    public NMOS n1;
    public NMOS n2;

    //----------------------------------------- Constructors --------------------------------------------------
    public NOR(){
        super();

        p1 = null;
        p2 = null;
        n1 = null;
        n2 = null;
    }

    public NOR(Basic input1, Basic input2){
        super();
        init(new Basic[]{input1}, new Basic[]{input2});
    }

    public NOR(Basic[] input1, Basic[] input2){
        super();
        init(input1,input2);
    }
    //----------------------------------------- Mutators ------------------------------------------------------
    public void init(Basic[] input1, Basic[] input2){
        p1 = new PMOS(new Basic[]{vpp}, input1);
        p2 = new PMOS(new Basic[]{p1}, input2);

        n1 = new NMOS(new Basic[]{ground}, input1);
        n2 = new NMOS(new Basic[]{ground}, input2);
    }

    //----------------------------------------- Accessors -----------------------------------------------------


    //----------------------------------------- Testing -------------------------------------------------------
    public static void main(String[] args){
        Input Input1 = new Input(false);
        Input Input2 = new Input(false);

        NOR nor = new NOR(new Basic[]{Input1}, new Basic[]{Input2});
        Transistor out = new Output(new Basic[]{nor.p2, nor.n1, nor.n2});

        System.out.println(Util.truthTable(new Input[]{Input1,Input2}, out));
    }
}
