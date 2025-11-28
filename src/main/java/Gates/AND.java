package Gates;
import Basics.*;
import javafx.scene.shape.Circle;

public class AND extends Gate {
    //Frontend
    public static String path = "M 1 0 L 9 0 L 9 4 C 9 7 7 8 5 8 C 3 8 1 7 1 4 L 1 0";

    //Backend components
    public NAND nand;
    public NOT not;

    public AND(){
        super();
        nand = new NAND();
        not = new NOT();
    }

    public AND(Basic input1, Basic input2){
        super();
        init(new Basic[]{input1}, new Basic[]{input2});
    }

    public AND(Basic[] input1, Basic[] input2){
        super();
        init(input1, input2);
    }

    //Assuming that w_inputs are all declared
    public void init(Basic[] input1, Basic[] input2) {
        nand = new NAND(input1, input2);
        not = new NOT(new Basic[]{nand.p1, nand.p2, nand.n2});
        outputs = new Basic[]{not.p1, not.n1};
    }

    public static void main(String[] args){
        Input Input1 = new Input(false);
        Input Input2 = new Input(false);
        AND and = new AND(Input1, Input2);

        Transistor out = new Output(and.outputs);

        System.out.println(Util.truthTable(new Input[]{Input1, Input2}, out));

    }
}
