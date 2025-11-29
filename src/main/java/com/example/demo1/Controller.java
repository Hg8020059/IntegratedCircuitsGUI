package com.example.demo1;

import Basics.Basic;
import Basics.Input;
import Basics.NMOS;
import Basics.PMOS;
import Gates.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

import java.util.ArrayList;

public class Controller {
    @FXML
    public Pane drawingPane;

    //Global Variables todo: look into getting rid of some of these
    public static ArrayList<Basic> componentList = new ArrayList<>(); //List holding all components
    public static ArrayList<Circle> componentDisplayList = new ArrayList<>();
    public static ArrayList<Line> lines = new ArrayList<>();
    public static boolean wireToggle = false;
    public static Circle lineStartObject = null;
    public static Basic wireStartComponent = null;

    //------------------------------ mutators --------------------------------------------------

    @FXML
    public Circle newGateDisplay(Text text){
        //Create circle
        Circle circle = new Circle(10,20,30);
        //Set Events
        circle.setOnMouseEntered(componentMouseEnter);
        circle.setOnMouseExited(componentMouseExit);
        circle.setOnMouseDragged(componentMouseDrag);
        circle.setOnMouseClicked(componentMouseClick);
        //Set Style
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        //Set Bindings
        text.xProperty().bind(circle.centerXProperty());
        text.yProperty().bind(circle.centerYProperty());
        text.setTextAlignment(TextAlignment.CENTER);
        text.setMouseTransparent(true);
        //Add Circle and Text objects to a stack pane. Problem is I cant then move the circle
//        text.setBoundsType(TextBoundsType.VISUAL);
//        StackPane stack = new StackPane(circle, text);

        //Add to Canvas
        drawingPane.getChildren().add(circle);
        drawingPane.getChildren().add(text);
//        drawingPane.getChildren().add(stack);
        return circle;
    }

    //------------------------------ event handlers---------------------------------------------
    @FXML
    protected void onGateButtonClick(ActionEvent event) {
        Basic gate;
        //Get ID of button to make this event reusable and work for all component creation buttons
        Button button = (Button) event.getSource();
        String name = button.getId();
        Text text = new Text(name);
        //Switch case to find the proper gate to make
        gate = switch (name) {
            case "Input" -> new Input(false);
            case "NMOS" -> new NMOS();
            case "PMOS" -> new PMOS();
            case "OR" -> new OR();
            case "NOR" -> new NOR();
            case "AND" -> new AND();
            case "NAND" -> new NAND();
            case "NOT" -> new NOT();
            default -> throw new RuntimeException("Shape path not found");
        };

        //Add Gate to list of gates at same index as displays
        componentList.add(gate);
        //Add to list of displays
        componentDisplayList.add(newGateDisplay(text));
//        System.out.println(gateList);
    }

    // Component display handlers
    EventHandler<MouseEvent> componentMouseClick = e ->  {
        Circle circle = (Circle) e.getSource();
        if(wireToggle){
            if(lineStartObject == null){
                lineStartObject = circle;
                wireStartComponent = componentList.get(componentDisplayList.indexOf(circle));
            }
            else{
                //Create connection between associated gates (need to set up graph representation first eugh)

                wireStartComponent = null;

                //Create Line between display objects
                Line line = new Line();
                line.setMouseTransparent(true);
                line.startXProperty().bind(lineStartObject.centerXProperty());
                line.startYProperty().bind(lineStartObject.centerYProperty());
                line.endXProperty().bind(circle.centerXProperty());
                line.endYProperty().bind(circle.centerYProperty());
                lineStartObject = null;
                drawingPane.getChildren().add(line);
            }
        }
    };

    EventHandler<MouseEvent> componentMouseEnter = e -> {
        Circle circle = (Circle) e.getSource();
        circle.setFill(Color.GREY);
    };

    EventHandler<MouseEvent> componentMouseExit = e -> {
        Circle circle = (Circle) e.getSource();
        circle.setFill(Color.TRANSPARENT);
    };

    EventHandler<MouseEvent> componentMouseDrag = e -> {
        Circle circle = (Circle) e.getSource();
        circle.setCenterX(e.getX());
        circle.setCenterY(e.getY());
    };

    // FXML Handlers
    //Proof of concept
    @FXML
    protected void onScroll(ScrollEvent e){
        double newRadius;
        System.out.println(e.getDeltaY());
        if(componentDisplayList.getFirst().getRadius() + e.getDeltaY() < 1){
            newRadius = 1;
        }
        else if(componentDisplayList.getFirst().getRadius() + e.getDeltaY() > 50){
            newRadius = 50;
        }
        else{
            newRadius = componentDisplayList.getFirst().getRadius() + e.getDeltaY();
        }
        for (Circle curr : componentDisplayList) {
            curr.setRadius(newRadius);
        }
    }

    @FXML
    protected void onWireButtonClick(){
        wireToggle = !wireToggle;
    }

    @FXML
    protected void onPaneMousePressed(MouseEvent event){
        double x = event.getX();
        double y = event.getY();

    }

    @FXML
    protected void onPaneMouseRelease(MouseEvent event){
        double x = event.getX();
        double y = event.getY();

    }
}