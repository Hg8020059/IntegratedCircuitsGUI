//package Basics;
//
//import Gates.Gate;
//import javafx.scene.shape.Line;
//
//import java.util.ArrayList;
//
//public class Wire {
//    //Frontend
//    public Line display;
//
//    //Backend
//    public ArrayList<Basic> inputs;
//    public ArrayList<Transistor> outputs = new ArrayList<>();
//    public String name;
//    public static int wireNoNameCount = 0;
//
//    //----------------------------------------- Constructors -----------------------------------------------------
//
//    public Wire(){
//        inputs = new ArrayList<>();
//        name = "Wire" + wireNoNameCount++;
//    }
//
//    public Wire(String name){
//        inputs = new ArrayList<>();
//        this.name = name;
//    }
//
//    //----------------------------------------- Mutators -----------------------------------------------------
//
//    public void addInput(Basic input){
//        this.inputs.add(input); // We want to reference the original, not create a copy of it
//    }
//
//    public void addOutput(Transistor output){
//        this.outputs.add(output); // We want to reference the original, not create a copy of it
//    }
//
//    public Boolean getOut(){
//        Boolean out = null;
//        for (Basic i : inputs){
//            if (i.recOut()){
//                out = true;
//                return out;
//            }
//            out = false;
//        }
//        return out;
//    }
//
//    //----------------------------------------- Accessors -----------------------------------------------------
//
//    //----------------------------------------- Testing -------------------------------------------------------
//    public static void main(String[] args){
//
//    }
//}
