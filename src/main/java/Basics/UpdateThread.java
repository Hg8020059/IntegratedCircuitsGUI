package Basics;

public class UpdateThread extends Thread{
    // Need to make a function that will loop through every component and get its value, starting from where a change in inputs occurs
    // Have Input.setVal call update.run on itself, so update will continuously update all wires and components after it
    // Breadth first search??? I might kill myself
    // Tree traversal might work
    Input input;

    public UpdateThread(Input input){
        this.input = input;
    }

    private void Update(Wire wire){
        for (Basic i : wire.outputs){
            i.out = i.getOut();
        }
    }

    public void run(){
        // Only update if an output wire from the given input exists
        if (this.input != null){
            Update(input.output);
        }
    }
}
