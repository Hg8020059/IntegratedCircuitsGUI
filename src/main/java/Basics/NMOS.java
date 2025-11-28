package Basics;

public class NMOS extends Transistor {
    //----------------------------------------- Constructors --------------------------------------------------

    public NMOS(Basic[] inputs, Basic[] controls) {
        super(inputs, controls);
    }

    //----------------------------------------- Mutators ------------------------------------------------------

    //----------------------------------------- Accessors -----------------------------------------------------
    //Update all nodes leading to this one, for non looping circuits

    public Boolean calcOut() {
        if(!calcCon()){
            val = false;
        }
        else if (calcCon()){
            val = calcIn();
        }
        return val;
    }

    //Recursive Out
    public Boolean recOut(){
        Boolean out = null;
        if(!recCon()){
            out = false;
        }
        else if(recCon()){
            out = recIn();
        }
        return out;
    }
}
