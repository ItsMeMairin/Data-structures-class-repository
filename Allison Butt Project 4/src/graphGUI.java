
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

/**
 * @author Allison Butt
 * @version CMSC 315 6380 - 6 July 2026
 * 
 * GraphGUI
 * This class creates the program GUI and extends Application
 * 
 * This class creates and organizes the interface elements of our program, including text boxes,
 * buttons, and the graph display. All elements are organized and rigged to respond to user inputs
 * @param TextField 3 separate fields to allow us to overwrite existing text or retrieve text from users
 * @param GraphPane creates a GraphPane object to be integrated into the GUI
 * 
 */
public class GraphGUI extends Application {

    //create persistent variables
    private TextField vertex1Field;
    private TextField vertex2Field;
    private TextField responseField;
    private GraphPane graphPane;
    
    //create a GraphDefiner object
    GraphDefiner graph = new GraphDefiner();

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
     * This method builds window size, scene, and initial labels
     * the shell of the program
     * 
     * @param Stage container used to house initial scene
     */
    @Override
    public void start(Stage primaryStage) {

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
     * pieces together all contents 
     * 
     * This method constrains all program elements in a VBox and aligns them for their final 
     * screen display
     * 
     * @return Region screen elements
     */
    private Region createContents() {

        VBox contents = new VBox(addEdgeSections(), addGraphSection(), addSearchSections());
        contents.setAlignment(Pos.CENTER);
        contents.setPadding(new Insets(20,0,20,10));
        return contents;
        
    }
    
    /**
     * Combines all top-row edge control elements
     * 
     * This method constrains add edge button, and vertex manipulation sections into one HBox
     * aligning them properly
     * @return HBox single element to be used by createContents()
     */
    private HBox addEdgeSections() {

        //combine all elements
        HBox results = new HBox(10, edgeButton(), createVertex1(), createVertex2());

        //ensure all elements are aligned
        results.setAlignment(Pos.BASELINE_CENTER); 
        results.setSpacing(10);

        return results;
    }

    /**
     * combines bottom section of display
     * 
     * This method connects the response textbox as well as all of the prompted buttons into one VBox format.
     * It first gathers all buttons and wraps them, then repeats with textbox.
     * @return VBox single element used by createContents()
     */

    private VBox addSearchSections() {
        //combine buttons and format
        HBox hbox = new HBox(0, connectedButton(), cycleButton(), depthButton(), breadthButton());
        hbox.setSpacing(0);
        hbox.setAlignment(Pos.BOTTOM_CENTER);

        //repeat, but make it a VBox with textbox
        VBox searchSection = new VBox(10, hbox, addResponseTextbox());
        searchSection.setAlignment(Pos.BOTTOM_CENTER);
        
        return searchSection;           

    }
    
    /**
     * Creates window for graph display
     * 
     * This method attaches a GraphPane object and wraps it in a VBox to be pieced together in createContents()
     * It also formats the box to have a minimum size and format
     * @return Node object used by createContents()
     */
    private Node addGraphSection() {
        //create graph
        graphPane = new GraphPane(graph);
        //define size
        graphPane.setPrefSize(400,300);

        //prioritize the graph in the display
        VBox.setVgrow(graphPane, javafx.scene.layout.Priority.ALWAYS);

        return graphPane;
    }
    
    /**
     * creates response textbox
     * 
     * @return VBox building block
     */ 
    private VBox addResponseTextbox() {
        //create vbox
        VBox results = new VBox(0, responseTextbox());

        //format vbox
        results.setSpacing(0);
        results.setAlignment(Pos.BOTTOM_CENTER);

        return results;           

    }

    /**
     * Creates "Add Edge" button
     * 
     * This method creates the button object for use in our GUI and also configures
     * the button to interact with the GraphPane and draw an edge between the two given vertices
     * @return HBox building block
     */
    private HBox edgeButton() {
        //create button and label
        HBox edgeButton = new HBox();
        Button button = new Button("Add Edge");

        //on button clock
        button.setOnAction(e -> {

            //make sure textfield is cleared
            responseField.clear();
        
            //retrieve user input variables
            String v1 = vertex1Field.getText();
            String v2 = vertex2Field.getText();

            //validate
            if (v1.isEmpty() || v2.isEmpty()) {
                responseField.setText("Enter both vertices");
                return;
            }

            //connect vertices if able
            if (graph.addEdge(v1, v2)) {
                graphPane.drawEdge(v1, v2);
                responseField.setText("Edge added: " + v1 + "-" + v2);
            } else {
                responseField.setText("Invalid edge");
            }
        });

        //format button
        edgeButton.setMinWidth(10);
        edgeButton.setPadding(new Insets(20, 0, 0 ,10));
        edgeButton.getChildren().add(button);

        return edgeButton;
    }

    /**
     * Creates Vertex 1 GUI elements
     * 
     * This method creates and packages both a label "Vertex 1" and textbox for user input
     * It also links the textbox with a variable for data transfer.
     * @return HBox building block
     */
    private HBox createVertex1() {

        //create elements
        HBox vertex1 = new HBox();
        Label label = new Label("Vertex 1");
        label.setMinWidth(50);

        //format text field
        vertex1Field = new TextField();
        vertex1Field.setMinWidth(20);
        vertex1Field.setMaxWidth(30);

        //format HBox elements
        vertex1.setPadding(new Insets(20, 0, 0 ,10));
        vertex1.getChildren().addAll(label, vertex1Field);
        vertex1.setAlignment(Pos.CENTER);

        return vertex1;
    }

    /**
     * Creates Vertex 2 GUI elements
     * 
     * This method creates and packages both a label "Vertex 2" and textbox for user input
     * It also links the textbox with a variable for data transfer.
     * @return HBox building block
     */
    private HBox createVertex2() {

        //create elements
        HBox vertex2 = new HBox();
        Label label = new Label("Vertex 2");
        label.setMinWidth(50);

        //format text field
        vertex2Field = new TextField();
        vertex2Field.setMinWidth(20);
        vertex2Field.setMaxWidth(30);

        //format HBox elements
        vertex2.setPadding(new Insets(20, 0, 0 ,10));
        vertex2.getChildren().addAll(label, vertex2Field);
        vertex2.setAlignment(Pos.CENTER);

        return vertex2;
    }

    /**
     * creates "Is Connected?" button
     * 
     * This method creates a button and assigns logic to button click
     * This button will verify graph connectivity and respond via the response texbox
     * @return HBox building block
     */
    private HBox connectedButton() {

        //create HBox and button
        HBox connectedButton = new HBox();
        Button button = new Button("Is Connected?");
        
        //format action
        button.setOnAction( e -> {
            //clear textbox
            responseField.clear();

            //check connectivity, provide response
            if(graph.isConnected())  {
                responseField.setText("Graph is connected");
            }
            else {
                responseField.setText("Graph is not connected");
            }
            
            });
        
        //format HBox
        connectedButton.setMinWidth(20);
        connectedButton.setPadding(new Insets(20, 0, 0 ,2));
        connectedButton.getChildren().add(button);

        return connectedButton;
    }

    /**
     * creates "Has Cycles" button
     * 
     * This method creates a button and assigns logic to button click
     * This button will verify any graph cycles and respond via the response texbox
     * @return HBox building block
     */
    private HBox cycleButton() {

        //create HBox and button
        HBox cycleButton = new HBox();
        Button button = new Button("Has Cycles?");

        //format action
        button.setOnAction(e -> {
            //clear textbox
            responseField.clear();
            //check cycle presence
            boolean hasCycle = graph.hasCycles();
            responseField.setText(hasCycle ? "Graph has cycles" : "No cycles detected");
        });

        //format HBpx
        cycleButton.setMinWidth(20);
        cycleButton.setPadding(new Insets(20, 0, 0 ,10));
        cycleButton.getChildren().add(button);
        return cycleButton;
    }

    /**
     * creates DFS button
     * 
     * This method creates a button and assigns logic to button click
     * This button will perform a DFS and respond via the response texbox
     * @return HBox building block
     */
    private HBox depthButton() {

        //create HBox and button
        HBox depthButton = new HBox();
        Button button = new Button("Depth First Search");

        //format action
        button.setOnAction(e -> {
            //clear textbox
            responseField.clear();
            //triggers DFS and displays response
            responseField.setText(
                String.join(" ",
                    graph.depthFirstSearch()
                        .stream()
                        .map(Vertex::getName)
                        .toList()
                )
            );
        });
        //format HBox
        depthButton.setMinWidth(20);
        depthButton.setPadding(new Insets(20, 0, 0 ,10));
        depthButton.getChildren().add(button);

        return depthButton;
    }

    /**
     * creates BFS button
     * 
     * This method creates a button and assigns logic to button click
     * This button will perform a BFS and respond via the response texbox
     * @return HBox building block
     */
    private HBox breadthButton() {
        //create HBox and button
        HBox breadthButton = new HBox();
        Button button = new Button("Breadth First Search");
        //format action
        button.setOnAction(e -> {
            //clear textbox
            responseField.clear();
            //triggers BFS and displays response
            responseField.setText(
                String.join(" ",
                    graph.breadthFirstSearch()
                        .stream()
                        .map(Vertex::getName)
                        .toList()
                )
            );
        });
        //format HBox
        breadthButton.setMinWidth(20);
        breadthButton.setPadding(new Insets(20, 0, 0 ,10));
        breadthButton.getChildren().add(button);

        return breadthButton;
    }

    /**
     * creates response field
     * 
     * This method creates a textbox which displays program results
     * This field will be updated with new button clicks or display error messages
     * @return TextField object
     */
    private TextField responseTextbox() {
        //create field
        responseField = new TextField();
        //format field
        responseField.setPadding(new Insets(5, 0, 5 ,10));
        responseField.setMinWidth(20);
        responseField.setMaxWidth(435);
        
        return responseField;
    }
    
    
       
        
    

    
}

