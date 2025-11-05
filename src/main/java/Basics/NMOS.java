package Basics;

public class NMOS extends Transistor {
    public static int nmosNoNameCount = 1;

    //----------------------------------------- Constructors --------------------------------------------------

    public NMOS(Wire input, Wire control, Wire output) {
        super(input, control, output, "Basics.NMOS"+ nmosNoNameCount++);
    }

    public NMOS(Wire input, Wire control, Wire output, String name){
        super(input, control, output, name);
    }

    //----------------------------------------- Mutators ------------------------------------------------------

    //----------------------------------------- Accessors -----------------------------------------------------


    public Boolean getOut() {
        if(control.getOut() == false){
            return false;
        }
        else if (control.getOut() == true){
            return input.getOut();
        }
        else return null;
    }
}
