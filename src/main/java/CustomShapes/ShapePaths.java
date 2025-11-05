package CustomShapes;

import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class ShapePaths {
    public static String NOR = "M 1 0 C 5 1 5 1 9 0 C 9 3 7 7 5 8 A 1 1 0 0 0 4 9 A 1 1 0 0 0 6 9 A 1 1 0 0 0 5 8 C 3 7 1 3 1 0";
    public static String OR = "M 1 0 C 5 1 5 1 9 0 C 9 3 7 7 5 8 C 3 7 1 3 1 0";
    public static String AND = "M 1 0 L 9 0 L 9 4 C 9 7 7 8 5 8 C 3 8 1 7 1 4 L 1 0";
    public static String NAND = "M 1 0 L 9 0 L 9 4 C 9 7 7 8 5 8 A 1 1 0 0 0 4 9 A 1 1 0 0 0 6 9 A 1 1 0 0 0 5 8 C 3 8 1 7 1 4 L 1 0";

}
