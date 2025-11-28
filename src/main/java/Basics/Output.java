package Basics;

import java.util.ArrayList;
import java.util.Arrays;

//Should only be placed somewhere where we want to check the outputs, never as a part of a circuit, this is mainly for testing
public class Output extends Transistor{
    public ArrayList<Basic> inputs;

    public Output(Basic[] inputs){
        this.inputs = new ArrayList<>();
        //Add specified inputs and outputs to appropriate places
        this.inputs.addAll(Arrays.asList(inputs));
        //Add this Transistor to the input components output lists
        for(Basic input : inputs) {
            input.outputs.add(this);
        }
    }

    public Boolean calcOut() {
        val = calcIn();
        return val;
    }

    public Boolean recOut() {
        for(Basic input: inputs){
            if(input.recOut()){
                return true;
            }
        }
        return false;
    }
}
