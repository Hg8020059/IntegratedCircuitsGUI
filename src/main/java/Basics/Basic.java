package Basics;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Basic{
    public Boolean out;
    public Boolean val = null;                  //Output value, can only change val in Input type variables
    public ArrayList<Transistor> outputs;      //Adjacency list

    //---------------------------------------------- Constructors -------------------------------------------------
    public Basic(){
        outputs = new ArrayList<>();
    }

    //---------------------------------------------- Mutators -------------------------------------------------

    //---------------------------------------------- Accessors -------------------------------------------------
    //Recursive out function. For a non looping circuit, return the proper output of a given Node
    public abstract Boolean recOut();

}
