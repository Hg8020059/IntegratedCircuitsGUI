package Basics;

//Basics.Basic should be what's used as the V++, and ground values, as it has an unchangeable output value,
// and no input or control values
public abstract class Basic{
    public static int basicNoNameCount = 1;

    public Boolean out;
    public String name;
    public Boolean val = null;    //Output value, can only change val in Basics.Input type variables
    public Wire output;    //Basics.Wire that the output of this node is connected to

    //---------------------------------------------- Constructors -------------------------------------------------
    public Basic(Wire output){
        setOutput(output);
        this.name = "Basics.Basic"+ basicNoNameCount++;
    }

    public Basic(Wire output, String name){
        setOutput(output);
        this.name = name;
    }

    //---------------------------------------------- Mutators -------------------------------------------------

    public void setOutput(Wire output){
        this.output = output;
        output.addInput(this);
    }


    //---------------------------------------------- Accessors -------------------------------------------------

    public String toString(){
        return( "Name: " + name);
    }


    public abstract Boolean getOut();
}
