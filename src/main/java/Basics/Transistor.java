package Basics;

import java.util.ArrayList;
import java.util.Arrays;

import static Basics.Util.truthTable;

public abstract class Transistor extends Basic{
    public ArrayList<Basic> inputs;
    public ArrayList<Basic> controls;

    //----------------------------------------- Constructors --------------------------------------------------

    public Transistor(){
        inputs = new ArrayList<>();
        controls = new ArrayList<>();
    }

    //outputs of this transistor are controlled by other nodes adding it as an input
    public Transistor(Basic[] inputs, Basic[] controls){
        this.inputs = new ArrayList<>();
        this.controls = new ArrayList<>();
        this.add(inputs, controls);
    }

    //----------------------------------------- Mutators ------------------------------------------------------
    //add all nodes in the arrays to the inputs and outputs od this node
    public void add(Basic[] inputs, Basic[] controls){
        //Add specified inputs and outputs to appropriate places
        this.inputs.addAll(Arrays.asList(inputs));
        this.controls.addAll(Arrays.asList(controls));
        //Add this Transistor to the input components output lists
        for(Basic input : inputs) {
            input.outputs.add(this);
        }
        for(Basic control : controls) {
            control.outputs.add(this);
        }
    }


    //----------------------------------------- Accessors -----------------------------------------------------

    //returns true if any of the input values are true
    protected boolean calcIn(){
        for(Basic input : inputs){
            if(input.val){
                return true;
            }
        }
        return false;
    }

    //returns true if any of the control values are true
    protected boolean calcCon(){
        for(Basic control : controls){
            if(control.val){
                return true;
            }
        }
        return false;
    }

    //Sets val to the output given current inputs
    public abstract Boolean calcOut();
    //----------------------------------------- Testing -------------------------------------------------------

    public static void main(String[] args){
        Input input = new Input(true);
        Input control = new Input(true);

        Transistor nmos = new NMOS(new Input[]{input}, new Input[]{control});
        Transistor pmos = new PMOS(new Input[]{input}, new Input[]{control});
        System.out.println("nmos: " + nmos.val);
        System.out.println("pmos: " + pmos.val);
        nmos.calcOut();
        pmos.calcOut();
        System.out.println("nmos: " + nmos.val);
        System.out.println("pmos: " + pmos.val);

        truthTable(new Input[]{input, control}, nmos);
        truthTable(new Input[]{input, control}, pmos);
    }
}
