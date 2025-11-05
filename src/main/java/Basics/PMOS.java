package Basics;

public class PMOS extends Transistor {
    public static int pmosNoNameCount = 1;

    //----------------------------------------- Constructors --------------------------------------------------

    public PMOS(Wire input, Wire control, Wire output) {
        super(input, control, output, "Basics.PMOS"+ pmosNoNameCount++);
    }

    public PMOS(Wire input, Wire control, Wire output, String name){
        super(input, control, output, name);
    }

    //----------------------------------------- Mutators ------------------------------------------------------

    //----------------------------------------- Accessors -----------------------------------------------------

    public Boolean getOut() {
        if(control.getOut() == true){
            return false;
        }
        else if (control.getOut() == false){
            return input.getOut();
        }
        else return null;
    }
}
