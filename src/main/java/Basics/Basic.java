package Basics;

import java.util.ArrayList;
import java.util.Arrays;

//Basics.Basic should be what's used as the V++, and ground values, as it has an unchangeable output value,
// and no input or control values
public abstract class Basic{
    public Boolean out;
    public Boolean val = null;                  //Output value, can only change val in Input type variables
    public ArrayList<Transistor> outputs;      //Basics.Wire that the output of this node is connected to

    //---------------------------------------------- Constructors -------------------------------------------------
    public Basic(){
        outputs = new ArrayList<>();
    }

    //---------------------------------------------- Mutators -------------------------------------------------

    //---------------------------------------------- Accessors -------------------------------------------------

}
