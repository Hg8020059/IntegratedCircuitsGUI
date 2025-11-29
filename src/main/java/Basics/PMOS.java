package Basics;

public class PMOS extends Transistor {
    public static int pmosNoNameCount = 1;

    //----------------------------------------- Constructors --------------------------------------------------

    public PMOS(){
        super();
    }

    public PMOS(Basic[] inputs, Basic[] controls) {
        super(inputs, controls);
    }

    //----------------------------------------- Mutators ------------------------------------------------------

    //----------------------------------------- Accessors -----------------------------------------------------

    public Boolean calcOut() {
        if(calcCon()){
            val = false;
        }
        else if (!calcCon()){
            val = calcIn();
        }
        return val;
    }

    //Recursive Out
    public Boolean recOut(){
        Boolean out = null;
        if(recCon()){
            out = false;
        }
        else if(!recCon()){
            out = recIn();
        }
        return out;
    }
}
