import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * @author Allison Butt
 * @version CMSC 315 6380 - 6 July 2026
 * 
 * GraphPane
 * This class creates the GraphPane object used by the GUI
 * 
 * This class manages the plotting and display of graph nodes which the user creates via clicking and adding edges between vertices. 
 * @param GraphDefiner graph object to store data
 * @param Map creates two maps, one for vertices and other for stored labels
 * @param char allows for the vertex labels to iterate with clicks
 * 
 */

public class GraphPane extends Pane {

    //create required objects and variables
    private GraphDefiner graph;

    private Map<String, Circle> vertexMap = new HashMap<>();
    private Map<String, Text> labelMap = new HashMap<>();
    private char nextLabel = 'A';


    /**
     * GraphPane
     * 
     * creates graph UI element
     * 
     * This method creates a GraphPane object which is used in the GUI and receives user input. 
     * It collects mouse action data and draws elements
     * @param graph GraphDefiner object
     */
    public GraphPane(GraphDefiner graph) {

        //copies graph object
        this.graph = graph;

        //set action
        this.setOnMouseClicked(e -> {

            //get mouse coordinates
            double x = e.getX();
            double y = e.getY();

            //iterate to the next label
            String name = String.valueOf(nextLabel++);

            //create Vertex object with coordinates and label
            Vertex v = new Vertex(name, x, y);

            // add to data
            graph.addVertex(v);

            // draw data on UI
            drawVertex(v);
        });
    }

    /**
     * Draws Vertex elements
     * 
     * This method draws a dot and label on the location the user clicks their mouse within the graph
     * 
     * @param v Vertex object created by mouseclick 
     */
    private void drawVertex(Vertex v) {
        //creates dot for plotting
        Circle circle = new Circle(v.getX(), v.getY(), 4, Color.BLACK);
        circle.setStroke(Color.BLACK);

        //name label for Vertex
        Text text = new Text(v.getName());

        // position label above the circle
        text.setX(v.getX() - 4);
        text.setY(v.getY() - 12);

        //adds elements to data for future use
        vertexMap.put(v.getName(), circle);
        labelMap.put(v.getName(), text);

        //applies drawing to GUI
        this.getChildren().addAll(circle, text);
    }

    /**
     * Draws edge elements
     * 
     * This method draws a line between two vertices as stipulated by the user
     * @param v1 first Vertex
     * @param v2 second Vertex
     */
    public void drawEdge(String v1, String v2) {

        //locates the relative circles for each vertex
        Circle c1 = vertexMap.get(v1);
        Circle c2 = vertexMap.get(v2);

        //breaks if null case
        if (c1 == null || c2 == null) return;

        //draws line between two circles
        Line line = new Line(
                c1.getCenterX(), c1.getCenterY(),
                c2.getCenterX(), c2.getCenterY()
        );

        //applies drawing to GUI
        this.getChildren().add(0, line);
    }

}
