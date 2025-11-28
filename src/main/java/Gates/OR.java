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

    //----------------------------------------- Constructors --------------------------------------------------
    public OR(){
        super();
    }

    public OR(Basic input1, Basic input2){
        super();
        init(new Basic[]{input1}, new Basic[]{input2});
    }

    public OR(Basic[] input1, Basic[] input2){
        super();
        init(input1, input2);
    }

    //----------------------------------------- Mutators ------------------------------------------------------
    public void init(Basic[] input1, Basic[] input2) {
        nor = new NOR(input1, input2);
        not = new NOT(new Basic[]{nor.p2, nor.n1, nor.n2});
        outputs = new Basic[]{not.p1, not.n1};
    }

    public static void main(String[] args){
        Input Input1 = new Input(false);
        Input Input2 = new Input(false);

        OR or = new OR(Input1, Input2);
        Transistor out = new Output(or.outputs);

        System.out.println(Util.truthTable(new Input[]{Input1, Input2}, out));    }
}
