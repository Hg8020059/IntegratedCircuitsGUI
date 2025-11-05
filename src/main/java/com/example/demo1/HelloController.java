package com.example.demo1;

import CustomShapes.ShapePaths;
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

public class HelloController {
    @FXML
    private Pane drawingPane;

    //Global Variables
    private ArrayList<Gates> components = new ArrayList<>(); //List holding all components
    private Region moving = null;
    private ArrayList<Line> lines = new ArrayList<>();
    private boolean wireToggle = false;
    private double[] wire_start = new double[2];

    //Create the region objects for each component
    public Region createPathRegion(String path){
        SVGPath svg = new SVGPath();
        svg.setContent(path);
        Region region = new Region();
        region.setShape(svg);
        region.setMinSize(50, 60);
        region.setPrefSize(50, 60);
        region.setMaxSize(50, 60);
        region.setStyle("-fx-background-color: transparent; -fx-border-color: black;");

        //Set events
        region.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //If wire toggle is on
                if(wireToggle){
                    //Get center of starting component
                    wire_start[0] = region.getBoundsInParent().getCenterX();
                    wire_start[1] = region.getBoundsInParent().getCenterY();
                }
                // Move the component if wire toggle is off
                else {
                    moving = region;
                    region.setStyle("-fx-background-color: grey; -fx-border-color: black;");
                }
            }
        });

        //Make gates grey when hovered over
        region.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                region.setStyle("-fx-background-color: grey; -fx-border-color: black;");
            }
        });
        region.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(moving != region){
                    region.setStyle("-fx-background-color: transparent; -fx-border-color: black;");
                }
            }
        });
        return region;
    }

    @FXML
    //Parses the text on the button to ensure that
    protected void onGateButtonClick(ActionEvent event) {
        Gates gate;
        //Get ID of button to make this event reusable and work for all component creation buttons
        Button button = (Button) event.getSource();
        String name = button.getId();
        //Switch case to find the proper gate to make
        switch (name) {
            case "OR":
                gate = new OR();
                gate.display = createPathRegion(OR.path);
                break;
            case "NOR":
                gate = new NOR();
                gate.display = createPathRegion(NOR.path);
                break;
            case "AND":
                gate = new AND();
                gate.display = createPathRegion(AND.path);
                break;
            case "NAND":
                gate = new NAND();
                gate.display = createPathRegion(NAND.path);
                break;
            default:
                throw new RuntimeException("Shape path not found");
        };

        //Create region containing the svg path, for ease of use
        //Region region = createPathRegion(path);

        components.add(gate);
        drawingPane.getChildren().add(components.getLast().display);
        System.out.println(components);
    }

    @FXML
    protected void onWireButtonClick(){
        wireToggle = !wireToggle;
    }

    @FXML
    protected void onPaneMouseRelease(MouseEvent event){
        double x = event.getX();
        double y = event.getY();
        System.out.println("(" + x + ", " + y + ")");

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
        if(wireToggle){
            //Find a component to link it to
            for(Gates i:components){
                Region j = i.display;
                //If wire is successfully created
                if(j.contains(x-j.getLayoutX(),y-j.getLayoutY())){
                    //System.out.println(wire_start[0] + ", " + wire_start[1]);
                    //System.out.println("Mouse release: " + x + ", " + y);
                    lines.add(new Line(wire_start[0], wire_start[1], j.getBoundsInParent().getCenterX(), j.getBoundsInParent().getCenterY()));
                    drawingPane.getChildren().add(lines.getLast());
                    break;
                }
            }
        }
    }
}