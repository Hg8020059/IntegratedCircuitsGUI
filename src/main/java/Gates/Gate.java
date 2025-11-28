package Gates;
import Basics.*;

//todo: add lists of the multiple transistors that make up the output for each gate, that way I can just
// reference those instead of having to remake the list each time I want to connect a node
//Gates just act as a wrapper of a bunch of pre-set transistors,
public abstract class Gate{
    //Backend
    public Basic[] Inputs;
    public Basic[] outputs;

    public Boolean out;

    final Input vpp = new Input(true);
    final Input ground = new Input(false);

}
