package Basics;

public abstract class Transistor extends Basic{
    public Wire input;
    public Wire control;

    //----------------------------------------- Constructors --------------------------------------------------

//    Transistor(Wire input, Wire output, Wire control){
//        super(output); //in the nmos/pmos subclasses is when auto-naming should be done
//        setInput(input);
//        setControl(control);
//    }

    public Transistor(Wire input, Wire control, Wire output, String name){
        super(output, name);
        setInput(input);
        setControl(control);
    }

    //----------------------------------------- Mutators ------------------------------------------------------

    public void setInput(Wire input) {
        this.input = input;
        input.addOutput(this);
    }

    public void setControl(Wire control) {
        this.control = control;
        control.addOutput(this);
    }


    //----------------------------------------- Accessors -----------------------------------------------------

    public String toString(){
        return (super.toString() +
                "\nInput: " + input.name + ", Value: " + input.getOut() +
                "\nControl: " + control.name) + ", Value: " + control.getOut() +
                "\nOutput: " + output.name + ", Value: " + getOut() + "\n";
    }

    //abstract Boolean getOut();

    //----------------------------------------- Testing -------------------------------------------------------

    public static void main(String[] args){
        Wire t1in = new Wire("n1in");
        Wire t1con = new Wire("con");
        Wire t1out = new Wire("n1out");
        Wire t2out = new Wire("p1out");

        Input controlInput = new Input(t1con, true, "Control Input");

        Input Vpp = new Input(t1in, true, "Vpp");
        controlInput.setOutput(t1con);

        Transistor n1 = new NMOS(t1in,t1con,t1out);
        Transistor p1 = new PMOS(t1out,t1con,t2out);


        System.out.println(t1out);
        System.out.println(n1);
        System.out.println(p1);
        controlInput.setVal(false);
        System.out.println(n1);
        System.out.println(p1);
    }

}
