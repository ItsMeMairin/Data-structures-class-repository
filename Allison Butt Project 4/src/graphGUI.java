

/*
The fourth class should contain the main method and should create the GUI including all the
buttons and text fields. It should include event handlers for each of the buttons.

required elements:title, Stage, Scene, graph, 
 scene, vertex 1 label, vertex 1 box, virtex 2 label, virtex 2 box, 
 connected button, cycle button, depth search button,
  breadth search button, response textbox

*/


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphGUI extends Application {

    private TextField vertex1Field;
    private TextField vertex2Field;
    private TextField responseField;
    private Button addEdgeButton;
    private Button connectedButton;
    private Button cycleButton;
    private Button dfsButton;
    private Button bfsButton;
    GraphDefiner graph = new GraphDefiner();
    private GraphPane graphPane;
    /**
     * instantiates the GUI
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * sets parameters for outer window
     * 
     * builds window size, scene, and initial labels
     * 
     * @param Stage
     */
    @Override
    public void start(Stage primaryStage) {
        //Label label = new Label("JavaFX Works!");

        //scene builder
        Scene scene = new Scene(createContents(), 500, 500);

        //scene
        primaryStage.setScene(scene);

        //title
        primaryStage.setTitle("Allison Butt Project 4");

        //Stage
        primaryStage.show();

        //graph scene
        //this gets outsourced to our graph display

        

    }
    /**
     * 
     * @return
     */
    private Region createContents() {

        VBox contents = new VBox(addEdgeSections(), addGraphSection(), addSearchSections());
        contents.setAlignment(Pos.CENTER);
        contents.setPadding(new Insets(20,0,0,10));
        return contents;
        
    }
    
    //cluster add edge sections on top of UI
    private HBox addEdgeSections() {

        HBox results = new HBox(0, edgeButton(), createVertex1(), createVertex2());
        results.setSpacing(0);
        //results.setPadding(new Insets(0,0,0,1));
        results.setAlignment(Pos.CENTER);
        return results;           

    }

    //cluster add search section on bottom of UI
    private VBox addSearchSections() {
        HBox hbox = new HBox(0, connectedButton(), cycleButton(), depthButton(), breadthButton());
        hbox.setSpacing(0);
        //results.setPadding(new Insets(0,0,0,1));
        hbox.setAlignment(Pos.BOTTOM_CENTER);

        VBox searchSection = new VBox(10, hbox, addResponseTextbox());
        searchSection.setAlignment(Pos.BOTTOM_CENTER);
        return searchSection;           

    }
    
    private Node addGraphSection() {
        graphPane = new GraphDefiner();
        return graphPane;
    }
    

    //cluster add last response textbox    
    private VBox addResponseTextbox() {
        VBox results = new VBox(0, responseTextbox());
        results.setSpacing(0);
        //results.setPadding(new Insets(0,0,0,1));
        results.setAlignment(Pos.BOTTOM_CENTER);
        return results;           

    }

    //add edge button 
    private HBox edgeButton() {
        HBox edgeButton = new HBox();
        Button button = new Button("Add Edge");
        button.setOnAction( e -> {
            graph.addEdge( 
                vertex1Field.getText(),
                vertex2Field.getText()
                );
            });
        graphPane.addEdge(
            vertex1Field.getText(),
            vertex2Field.getText()
);
        //edgeButton.setOnAction();
        edgeButton.setMinWidth(10);
        edgeButton.setPadding(new Insets(20, 0, 0 ,10));
        edgeButton.getChildren().add(button);
        return edgeButton;
    }

    //virtex 2 hbox
    private HBox createVertex1() {
        HBox vertex1 = new HBox();
        Label label = new Label("Vertex 1");
        label.setMinWidth(50);
        vertex1Field = new TextField();
        vertex1Field.setMinWidth(20);
        vertex1Field.setMaxWidth(30);
        vertex1.setPadding(new Insets(20, 0, 0 ,10));
        vertex1.getChildren().addAll(label, vertex1Field);
        //vertex1.setAlignment(Pos.TOP_CENTER);
        return vertex1;
    }

    //virtex 2 hbox
    private HBox createVertex2() {
        HBox vertex2 = new HBox();
        Label label = new Label("Vertex 2");
        label.setMinWidth(50);
        vertex2Field = new TextField();
        vertex2Field.setMinWidth(20);
        vertex2Field.setMaxWidth(30);
        vertex2.setPadding(new Insets(20, 0, 0 ,10));
        vertex2.getChildren().addAll(label, vertex2Field);
        //vertex2.setAlignment(Pos.TOP_CENTER);
        return vertex2;
    }

    //connected button
    private HBox connectedButton() {
        HBox connectedButton = new HBox();
        Button button = new Button("Is Connected?");
        button.setOnAction( e -> {
            if(graph.isConnected())  {
                responseField.setText("Graph is connected");
            }
            else {
                responseField.setText("Graph is not connected");
            }
            
            });
        
        connectedButton.setMinWidth(20);
        connectedButton.setPadding(new Insets(20, 0, 0 ,2));
        connectedButton.getChildren().add(button);
        return connectedButton;
    }

    //cycle button
    private HBox cycleButton() {
        HBox cycleButton = new HBox();
        Button button = new Button("Has Cycles?");
        button.setOnAction( e -> {
            graph.hasCycles();
            //returns List<Vertex>
            //GUI converts to A B D E C 
        });
        cycleButton.setMinWidth(20);
        cycleButton.setPadding(new Insets(20, 0, 0 ,10));
        cycleButton.getChildren().add(button);
        return cycleButton;
    }

    //depth search button
    private HBox depthButton() {
        HBox depthButton = new HBox();
        Button button = new Button("Depth First Search");
        button.setOnAction( e -> {
            graph.depthFirstSearch();
            //returns List<Vertex>
            //GUI converts to A B D E C 
        });
        depthButton.setMinWidth(20);
        depthButton.setPadding(new Insets(20, 0, 0 ,10));
        depthButton.getChildren().add(button);
        return depthButton;
    }

    //breadth search button
    private HBox breadthButton() {
        HBox breadthButton = new HBox();
        Button button = new Button("Breadth First Search");
        button.setOnAction( e -> {
            graph.breadthFirstSearch();
            //returns List<Vertex>
            //GUI converts to A B D E C 
        });
        breadthButton.setMinWidth(20);
        breadthButton.setPadding(new Insets(20, 0, 0 ,10));
        breadthButton.getChildren().add(button);
        return breadthButton;
    }

    //response textbox
    private TextField responseTextbox() {
        responseField = new TextField();
        responseField.setPadding(new Insets(5, 0, 5 ,10));
        responseField.setMinWidth(20);
        responseField.setMaxWidth(435);
        return responseField;
    }
    
    
       
        
    

    
}

