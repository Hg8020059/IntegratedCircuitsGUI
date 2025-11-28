package com.example.demo1;

import Gates.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class Controller {
    @FXML
    public Pane drawingPane;

    //Global Variables todo: look into getting rid of some of these
    public static ArrayList<Gate> components = new ArrayList<>(); //List holding all components
    public static ArrayList<Circle> display = new ArrayList<>();
    public static ArrayList<Line> lines = new ArrayList<>();
    public static boolean wireToggle = false;
    public static Circle lineStartObject = null;
    public static Gate wireStartGate = null;

    //------------------------------ mutators --------------------------------------------------
    @FXML
    public Circle newGateDisplay(){
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

        //Add to Canvas
        drawingPane.getChildren().add(circle);
        return circle;
    }

    //------------------------------ event handlers---------------------------------------------
    @FXML
    protected void onGateButtonClick(ActionEvent event) {
        Gate gate;
        //Get ID of button to make this event reusable and work for all component creation buttons
        Button button = (Button) event.getSource();
        String name = button.getId();
        //Switch case to find the proper gate to make
        switch (name) {
            case "OR":
                gate = new OR();
                break;
            case "NOR":
                gate = new NOR();
                break;
            case "AND":
                gate = new AND();
                break;
            case "NAND":
                gate = new NAND();
                break;
            case "NOT":
                gate = new NOT();
                break;
            default:
                throw new RuntimeException("Shape path not found");
        };

        //Add Gate to list of gates at same index as displays
        components.add(gate);
        //Add to list of displays
        display.add(newGateDisplay());
        System.out.println(components);
    }

    //--------------------------------Event handlers-----------------------------------------
    // Component display handlers
    EventHandler<MouseEvent> componentMouseClick = e ->  {
        Circle circle = (Circle) e.getSource();
        if(wireToggle){
            if(lineStartObject == null){
                lineStartObject = circle;
                wireStartGate = components.get(display.indexOf(circle));
            }
            else{
                //Create connection between associated gates (need to set up graph representation first eugh)


                wireStartGate = null;

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
        if(display.getFirst().getRadius() + e.getDeltaY() < 1){
            newRadius = 1;
        }
        else if(display.getFirst().getRadius() + e.getDeltaY() > 50){
            newRadius = 50;
        }
        else{
            newRadius = display.getFirst().getRadius() + e.getDeltaY();
        }
        for (Circle curr : display) {
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