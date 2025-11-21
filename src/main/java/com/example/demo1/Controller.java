package com.example.demo1;

import Gates.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class Controller {
    @FXML
    public Pane drawingPane;

    //Global Variables todo: look into getting rid of some of these
    public static ArrayList<Gate> components = new ArrayList<>(); //List holding all components
    public static ArrayList<Line> lines = new ArrayList<>();
    public static boolean wireToggle = false;
    public static Circle wireStartGate = null;

    //------------------------------ mutators --------------------------------------------------

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

        components.add(gate);
        components.getLast().display.setOnMouseClicked(componentMouseClick);
        drawingPane.getChildren().add(gate.display);
        System.out.println(components);
    }

    //--------------------------------FXML handlers-----------------------------------------
    EventHandler<MouseEvent> componentMouseClick = e ->  {
        Circle display = (Circle) e.getSource();
        if(wireToggle){
            if(wireStartGate == null){
                wireStartGate = display;
            }
            else{
                Line line = new Line();
                line.setMouseTransparent(true);
                line.startXProperty().bind(wireStartGate.centerXProperty());
                line.startYProperty().bind(wireStartGate.centerYProperty());
                line.endXProperty().bind(display.centerXProperty());
                line.endYProperty().bind(display.centerYProperty());
                wireStartGate = null;
                drawingPane.getChildren().add(line);
            }
        }
        System.out.println(wireStartGate);
    };

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