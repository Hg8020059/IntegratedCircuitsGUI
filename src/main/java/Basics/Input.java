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

    //Base case of recOut
    public Boolean recOut(){
        return val;
    }
}
