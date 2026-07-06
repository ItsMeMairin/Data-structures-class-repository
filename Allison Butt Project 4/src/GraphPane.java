import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import java.util.HashMap;
import java.util.Map;

/*
The third class is should be an extension of the javafx Pane class that 
visually displays the
graph. It should contain an event handler that responds to mouse clicks 
that creates new vertices
and a method that is called to draw edges

Draw circles
Draw labels
Draw lines
Handle mouse clicks


event handler
    get x + y coordinates from mouse click
    pass click into virtex definer 

use methods from graph definer to display graph 

plots virtexes in alphabetical order 

draws edge between appropriate vertices
*/

//import javafx pane class to be an extension
import javafx.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class GraphPane extends Pane {

    private final Map<Vertex, Circle> vertexMarkers = new HashMap<>();

   
    /**
     * Adds a vertex to the pane at specific coordinates.
     */
    public void addVertex(String id, double x, double y) {
        if (vertexMarkers.containsKey(id)) return;

        Circle circle = new Circle(x, y, 15, Color.LIGHTBLUE);
        circle.setStroke(Color.BLUE);
        circle.setStrokeWidth(2);

        Text label = new Text(id);
        // Center the text inside the circle
        label.xProperty().bind(circle.centerXProperty().subtract(label.getLayoutBounds().getWidth() / 2));
        label.yProperty().bind(circle.centerYProperty().add(label.getLayoutBounds().getHeight() / 4));

        // Enable drag-and-drop movement for the vertex
        //enableDrag(circle);

        vertexMarkers.put(id, circle);
        this.getChildren().addAll(circle, label);
    }

    /**
     * Connects two existing vertices with a line.
     */
    public void addEdge(String sourceId, String targetId) {
        Circle source = vertexMarkers.get(sourceId);
        Circle target = vertexMarkers.get(targetId);

        if (source == null || target == null) return;

        Line edge = new Line();
        // Bind edge positions to circle center positions
        edge.startXProperty().bind(source.centerXProperty());
        edge.startYProperty().bind(source.centerYProperty());
        edge.endXProperty().bind(target.centerXProperty());
        edge.endYProperty().bind(target.centerYProperty());
        
        edge.setStroke(Color.GRAY);
        edge.setStrokeWidth(2);

        // Send edges to the back so they don't cover text/circles
        this.getChildren().add(0, edge);
    }

   

    // Helper class for mouse tracking
    private static class Delta { double x, y; }
}



 /*public graphPane() {
        // Apply styling or sizing constraints if needed
        this.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #cccccc;");
    }



    /* 
    rootPane.setOnMouseClicked(event -> {
        double sceneX = event.getSceneX(); // Window-relative coordinate
        double nodeX = event.getX();       // Node-relative coordinate
    
        System.out.println("Graph canvas clicked at X: " + nodeX + " Y: " + event.getY());
    });

    public graphDisplay() {

        //configure container
        this.setPrefSize(430, 430);

        //initialize UI children
        this.getChildren().addAll();
    }
    */


