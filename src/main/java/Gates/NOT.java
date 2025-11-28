package Gates;
import Basics.*;
import javafx.scene.shape.Circle;

public class NOT extends Gate {
    //Frontend
    public static String path = "M 0 0 L 8 0 L 4 6 A 1 1 0 0 0 3 7 A 1 1 0 0 0 5 7 A 1 1 0 0 0 4 6 L 0 0";
    public static int numInputs = 1;

    //Backend
    // Setting up internal circuitry for the not gate
    public PMOS p1;
    public NMOS n1;

    //----------------------------------------- Constructors --------------------------------------------------
    public NOT(){
        super();
        p1 = null;
        n1 = null;
    }

    public NOT(Basic input){
        super();
        init(new Basic[]{input});
    }

    public NOT(Basic[] input){
        init(input);
    }
    //----------------------------------------- Mutators ------------------------------------------------------

    public void init(Basic[] input) {
        p1 = new PMOS(new Basic[]{vpp}, input);
        n1 = new NMOS(new Basic[]{ground}, input);
    }

    //----------------------------------------- Accessors -----------------------------------------------------

    //----------------------------------------- Testing -------------------------------------------------------

    public static void main(String[] args){
        Input input = new Input(true);
        NOT not = new NOT(new Basic[]{input});
        Transistor out = new Output(new Basic[]{not.n1, not.p1});

        System.out.println(Util.truthTable(new Input[]{input}, out));
    }
}
