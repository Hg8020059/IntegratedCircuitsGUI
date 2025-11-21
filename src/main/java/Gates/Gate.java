package Gates;
import Basics.*;
import com.example.demo1.Controller;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.SVGPath;

import static com.example.demo1.Controller.*;

// Gates just act as a wrapper of a bunch of pre-set transistors,
public abstract class Gate {
    //Frontend
    public Circle display;
    public Wire[] w_inputs = new Wire[2];

    public Gate(){
        //frontend
        display = new Circle(10,20,30);
        display.setOnMouseEntered(componentMouseEnter);
        display.setOnMouseExited(componentMouseExit);
        display.setOnMouseDragged(componentMouseDrag);
        display.setFill(Color.TRANSPARENT);
        display.setStroke(Color.BLACK);
        //backend
    }

    //Start of region handlers
    EventHandler<MouseEvent> componentMouseEnter = e -> {
        display.setFill(Color.GREY);
    };

    EventHandler<MouseEvent> componentMouseExit = e -> {
        display.setFill(Color.TRANSPARENT);
    };

    EventHandler<MouseEvent> componentMouseDrag = e -> {
        display.setCenterX(e.getX());
        display.setCenterY(e.getY());
    };

    //Backend
    public Boolean out;

    public int numInputs = 0;

    public Wire W_out = new Wire(this.getClass().getName() + " out");

    final Wire W_vpp = new Wire("Vpp");
    final Wire W_ground = new Wire("Ground");

    final Input vpp = new Input(W_vpp, true, "Vpp");
    final Input ground = new Input(W_ground, false, "Ground");

    public Boolean getOut(){
        return W_out.getOut();
    }

    //todo make abstract
    public void init(){
    }

    public boolean addInput(Wire wire){
        return false;
    }
}
