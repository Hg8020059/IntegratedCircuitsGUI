package Gates;
import Basics.*;

//Gates just act as a wrapper of a bunch of pre-set transistors,
public abstract class Gate extends Basic{
    //Backend
    public Basic[] Inputs;
    public Basic[] outputs;

    public Boolean out;

    final Input vpp = new Input(true);
    final Input ground = new Input(false);

    //Have this just take the recursive out of the output transistors
    public Boolean recOut(){
        return false;
    }
}
