package com.example.demo1;

import Gates.AND;
import Gates.*;
import Gates.NAND;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class Controller {
    @FXML
    private Pane drawingPane;

    //Global Variables todo: look into getting rid of some of these
    public static ArrayList<Gate> components = new ArrayList<>(); //List holding all components
    public static Region moving = null;
    public static ArrayList<Line> lines = new ArrayList<>();
    public static boolean wireToggle = false;
    public static double[] wire_start = new double[2];
    public static Gate wireStartGate = null;

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
        gate.display.setOnMouseEntered(regionMouseEnter);
        gate.display.setOnMouseExited(regionMouseExit);
        gate.display.setOnMousePressed(regionMousePressed);
        drawingPane.getChildren().add(gate.display);
        System.out.println(components);
    }

    //------------------------------ event handlers---------------------------------------------

    //Start of region handlers
    EventHandler<MouseEvent> regionMouseEnter = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Region region = (Region) mouseEvent.getSource();
            region.setStyle("-fx-background-color: grey; -fx-border-color: black;");
        }
    };

    EventHandler<MouseEvent> regionMouseExit = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Region region = (Region) mouseEvent.getSource();
            if(moving != region){ //Keep it gray while its moving
                region.setStyle("-fx-background-color: transparent; -fx-border-color: black;");
            }
        }
    };

    EventHandler<MouseEvent> regionMousePressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Region region = (Region) mouseEvent.getSource();
            //If wire toggle is off
            if(!wireToggle){
                moving = region;
                region.setStyle("-fx-background-color: grey; -fx-border-color: black;");
            }
        }
    };

    //End of region handlers

    @FXML
    protected void onWireButtonClick(){
        wireToggle = !wireToggle;
    }

    @FXML
    protected void onPaneMousePressed(MouseEvent event){
        double x = event.getX();
        double y = event.getY();

        if(wireToggle){
            //Find a component to link it to
            for(Gate i:components){
                Region j = i.display;
                //If component is found
                if(j.contains(x-j.getLayoutX(),y-j.getLayoutY())){
                    wire_start[0] = j.getBoundsInParent().getCenterX();
                    wire_start[1] = j.getBoundsInParent().getCenterY();

                    wireStartGate = i;
                    break;
                }
            }
        }
    }

    @FXML
    protected void onPaneMouseRelease(MouseEvent event){
        double x = event.getX();
        double y = event.getY();

        // Component moving logic
        if(moving!=null){
            //Move to mouse
            double adjx = moving.getBoundsInLocal().getCenterX();
            double adjy = moving.getBoundsInLocal().getCenterY();
            moving.setLayoutX(x - adjx);
            moving.setLayoutY(y - adjy);
            moving = null;
        }

        //Logic for if we are creating wires
        if(wireToggle && wireStartGate != null){
            //Find a component to link it to
            for(Gate i:components){
                Region j = i.display;
                //If component is found
                if(j.contains(x-j.getLayoutX(),y-j.getLayoutY())){
                    //todo: create wire connections between found components

                    lines.add(new Line(wire_start[0], wire_start[1], j.getBoundsInParent().getCenterX(), j.getBoundsInParent().getCenterY()));
                    drawingPane.getChildren().add(lines.getLast());
                    break;
                }
            }
            wireStartGate = null;
        }

    }
}