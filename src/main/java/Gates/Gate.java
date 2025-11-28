package Gates;
import Basics.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.SVGPath;

// Gates just act as a wrapper of a bunch of pre-set transistors,
public abstract class Gate{
    //Backend
    public Boolean out;

    final Input vpp = new Input(true);
    final Input ground = new Input(false);

    //public abstract void init();
}
