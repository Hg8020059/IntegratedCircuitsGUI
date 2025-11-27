package Basics;

public class Input extends Basic{
    //---------------------------------------------- Constructors -------------------------------------------------

    public Input(Boolean val){
        super();
        setVal(val);
    }

    //---------------------------------------------- Mutators -------------------------------------------------

    public void setVal(Boolean val){
        this.val = val;
    }

    //---------------------------------------------- Accessors -------------------------------------------------

    public String toString() {
        return (super.toString() + "\nOutput Value: " + val.toString());
    }

    //Update all nodes leading to this one, for non looping circuits
    //This is the base case of getOut
    public Boolean getOut(){
        return val;
    }
}
