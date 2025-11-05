package Gates;
import Basics.*;
import javafx.scene.layout.Region;

// Gates just act as a wrapper of a bunch of pre-set transistors,
public abstract class Gates{
    public Region display;

    //Backend
    public Boolean out;

    public int numInputs = 0;

    public Wire W_out = new Wire(this.getClass().getName() + " out");

    final Wire W_vpp = new Wire("Vpp");
    final Wire W_ground = new Wire("Ground");

    final Input vpp = new Input(W_vpp, true, "Vpp");
    final Input ground = new Input(W_ground, false, "Ground");

//    public Gates(){
//        //display = createPathRegion();
//    }

    public Boolean getOut(){
        return W_out.getOut();
    }

    public void init(){

    }
}
