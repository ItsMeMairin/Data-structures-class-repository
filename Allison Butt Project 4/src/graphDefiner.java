import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
The second class should be a class that defines the graph. You may use any graph representation 
that allows an unlimited number of vertices and edges. It should have the following public 
methods  
 A method to add a vertex 
 A method to add an edge 
 A method that checks whether the graph has cycles 
 A method to check whether the graph is connected 
 A method that returns a list of vertices resulting from a depth-first graph search 
 A method that returns a list of vertices resulting from a breadth-first graph search 
*/

/*
essentially this class handles all of the number crunching for the graph
should we use helper methods for each user call? may be unnecessary depending on recursion

*/

public class GraphDefiner {

    private Map<Vertex, Set<Vertex>> adjacencyList;
    private Map<String, Vertex> vertexCollection;

    public GraphDefiner() {
    adjacencyList = new HashMap<>();
    vertexCollection = new HashMap<>();
}
    
    //method to add vertex
    public boolean addVertex(Vertex v) {
        if (vertexCollection.containsValue(v.getName())) {
            return false;
        }
        //store vertex
        vertexCollection.put((v.key, v.value)); 

        //create empty neighbor list
        adjacencyList.put(v, x);

        //return true
        return true;
    }

    private Vertex findVertex(String name) {
        return vertexCollection.get(name);
    }

    //method to add edge
    public boolean addEdge(String v1, String v2){
        // if either are missing
        if ((!v1.findVertex()) || (!v2.findVertex())) {
            //return false
            return false;
        }
            
        //connect A -> B
        adjacencyList.add(v1, <v1, v2>);

        //connect B -> A
        adjacencyList.add(v2, <v2, v1>);
        //return true
        return true;
    }

    //method that checks whether there's cycles
    public boolean hasCycles() {
        //for every vertex
        for (Vertex v : vertexCollection.getValues()) {
            //if not visited
            if (v != adjacencyList.get(v)) {
                //run DFS
                depthFirstSearch();
                //if DFS finds a cycle
                //return true
            }
        }
            
        return false;
    }

    //method to check whether graph is connected
    public boolean isConnected() {
        //if graph has no verticies
            
        if (vertexCollection == null) {
            //return true
            return true;
        }
        
        //pick first vertex
        Map first = vertexCollection[0];

        //run DFS
        depthFirstSearch();
        //did DFS visit every vertex?
        //yes -> connected, T
        //no -> disconnected, F
    }

    //method that returns depth-first list
    public List<Vertex> depthFirstSearch() {
        //find vertex A
        //perform DFS
        //return list
    }

    private boolean dfsCycle() {
        //mark current visited
        Vertex = vertexCollection;

        //for every neighbor
            //if neighbor has not been visited

                //recursively search neighbor

                //if recursion found cycle
                    //return true 
        //else if neighbor is NOT parent
            //return true
        //return false
    }

    private void dfsTraversal(Vertex v, Set s, List l) {
        //needs current vertex, visited set, result list
        //dfsTraversal(current)
        //mark current as visited
        //add current to result list
        //for everyneighbor of current
            //if neighbor has not been visited
                //recursively call dfsTraversal(neighbor)
    }

    //method that returns breadth-first list
    public List<Vertex> breadthFirstSearch() {
        //find A
        //perform BFS
        //return list
    }
}