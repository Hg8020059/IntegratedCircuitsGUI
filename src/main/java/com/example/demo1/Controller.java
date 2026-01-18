package com.example.demo1;

import Basics.*;
import Gates.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class Controller {
    @FXML
    public Pane drawingPane;

    //Global Variables todo: look into getting rid of some of these
    public static ArrayList<Basic> componentList = new ArrayList<>(); //List holding all components
    public static ArrayList<Circle> componentDisplayList = new ArrayList<>();
    public static ArrayList<Text> valTextList = new ArrayList<>();
    public static ArrayList<Line> lines = new ArrayList<>();
    public static boolean wireToggle = false;
    public static Circle lineStartObject = null;
    public static Basic wireStartComponent = null;

    //------------------------------ mutators --------------------------------------------------

    @FXML
    public Circle newGateDisplay(Text text){
        //Create circle
        Circle circle = new Circle(10,20,30);
        Text valText = new Text("False");
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

        valText.xProperty().bind(circle.centerXProperty());
        valText.yProperty().bind(circle.centerYProperty());
        valText.setTextAlignment(TextAlignment.CENTER);
        valText.setMouseTransparent(true);

        //Add to Canvas
        drawingPane.getChildren().add(circle);
        //drawingPane.getChildren().add(text);
        drawingPane.getChildren().add(valText);

        valTextList.add(valText);
        return circle;
    }

    public String getTransistorInputType(){
        String type = "input";
        Dialog<ButtonType> dialog = new Dialog<>();
        ButtonType inputButtonType = new ButtonType("Input", ButtonBar.ButtonData.OK_DONE);
        ButtonType controlButtonType = new ButtonType("Control", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(inputButtonType, controlButtonType);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == inputButtonType) {
            type = "input";
        }
        else if(result.isPresent() && result.get() == controlButtonType){
            type = "control";
        }

        return type;
    }

    public Boolean getInputValue(){
        boolean type = true;
        Dialog<ButtonType> dialog = new Dialog<>();
        ButtonType inputButtonType = new ButtonType("True", ButtonBar.ButtonData.OK_DONE);
        ButtonType controlButtonType = new ButtonType("False", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(inputButtonType, controlButtonType);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == inputButtonType) {
            type = true;
        }
        else if(result.isPresent() && result.get() == controlButtonType){
            type = false;
        }

        return type;
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
        if (!e.isStillSincePress()){
            return;
        }

        Circle circle = (Circle) e.getSource();
        Basic component = componentList.get(componentDisplayList.indexOf(circle));

        //Updates component when it is clicked on
        System.out.println(component.recOut());
        valTextList.get(componentDisplayList.indexOf(circle)).setText(String.valueOf(component.recOut()));

        if(wireToggle){
            if(lineStartObject == null){
                lineStartObject = circle;
                wireStartComponent = componentList.get(componentDisplayList.indexOf(circle));
            }
            else{
                //if the selected component is a transistor allow the user to select whether they are setting the control or input
                // if its a gate, need to check if all of its inputs have been used, if not add this input, if so, send an error
                //If component is a transistor
                if(component instanceof Transistor){
                    String type = getTransistorInputType();
                    if(Objects.equals(type, "input")){
                        ((Transistor) component).addInput(wireStartComponent);
                    }
                    else if(Objects.equals(type, "control")){
                        ((Transistor) component).addControl(wireStartComponent);
                    }
                }

                //If component is a gate
                else if(component instanceof Gate){
                    
                }
                //For components that dont take inputs just break out of the function
                else{
                    System.out.println("This component doesn't accept inputs");
                    return;
                }

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
        //Wire toggle off
        else{
            if(component instanceof Input){
                boolean val = getInputValue();
                ((Input) component).setVal(val);
                valTextList.get(componentDisplayList.indexOf(circle)).setText(String.valueOf(val));
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
        System.out.println("(" + circle.getCenterX() + "," + circle.getCenterY() + ")");
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