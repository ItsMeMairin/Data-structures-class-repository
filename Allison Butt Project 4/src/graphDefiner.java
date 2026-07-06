import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author Allison Butt
 * @version CMSC 315 6380 - 6 July 2026
 * 
 * GraphDefiner
 * This class defines the graph to be used in our program.
 * 
 * This class provides an unlimited number of vertices and edges, handling all of the methods required to 
 * analyze and manipulate the graph in our program. It contains methods to manipulate the graph and 
 * manage data structures within the graph itself
 * @param adjacencyList - map used to store adjacency pair information between vertices
 * @param vertexCollection - map used to store all vertices produced by the program
 * 
 */

public class GraphDefiner {

    //data structures
    private final Map<Vertex, Set<Vertex>> adjacencyList;
    private final Map<String, Vertex> vertexCollection;

    //constructor
    public GraphDefiner() {
    adjacencyList = new HashMap<>();
    vertexCollection = new HashMap<>();
}
    
    /**
     * Adds a vertex to the dataset 
     * 
     * This method receives a vertex and compres it to the existing list. 
     * If it is a new vertex, it is added to both data structures for future use
     * @param v Vertex object to be processed
     * @return boolean result confirming the vertex has been added
     */
    public boolean addVertex(Vertex v) {
        //if it exists, return false
        if (vertexCollection.containsKey(v.getName())) {
            return false;
        }
        //store vertex
        vertexCollection.put(v.getName(), (v)); 

        //create empty neighbor list
        adjacencyList.put(v, new HashSet<>());

        //return
        return true;
    }

    /**
     * Fetches vertex from data structure
     * 
     * This method is a helper to easily fetch an existing vertex object via it's name
     * which is a key in the vertexCollection Map.
     * @param name String label of desired vertex
     * @return Vertex object associated with referenced name.
     */
    private Vertex findVertex(String name) {
        //retrieves associated vertex object
        return vertexCollection.get(name);
    }

    /**
     * Adds an edge association between two vertices on the graph
     * 
     * This method checks to ensure both vertex names exist
     * It then fetches those vertices with our helper method
     * Finally it adds the association between the vertices to each of their records 
     * within the adjacencyList Map.
     * @param v1 String label of the first vertex
     * @param v2 String label of the second vertex
     * @return boolean confirming the two vertexes were properly connected
     */
    public boolean addEdge(String v1, String v2){
        // if either are missing
        if (!vertexCollection.containsKey(v1) || (!vertexCollection.containsKey(v2)) 
                || (v1.equals(v2))) {
            //return false
            return false;
        }

        //use helper to fetch vertex objects
        Vertex vertex1 = findVertex(v1);
        Vertex vertex2 = findVertex(v2);
            
        //connect A -> B
        adjacencyList.get(vertex1).add(vertex2);

        //connect B -> A
        adjacencyList.get(vertex2).add(vertex1);
        //return 
        return true;
    }

    /**
     * Performs a Depth First Search (DFS) of the graph
     * 
     * This method utilizes a recursive helper method to visit each node on the graph via edges
     * Then returns a list of vertices in the order they were found.
     * @return List object of discovered Vertices.
     */
    public List<Vertex> depthFirstSearch() {
        //create empty result list
        List<Vertex> results = new ArrayList<>();
        //create empty visited list
        Set<Vertex> visited = new HashSet<>();

        //if A doesn't exist
        //find vertex A
        Vertex start = findVertex("A");
        if (start == null) return results;

        
        //call dfsTraversal(first, visited, result)
        dfsTraversal(start, visited, results);

        //return 
        return results;
    }

    /**
     * DFS helper method to provide recursion
     * 
     * This method performs a DFS search on all nodes, recursively adding them to the results list 
     * until all vertices are visited, at which point it returns the complete list of connected vertices 
     * @param current the vertex which is next to be checked in the DFS
     * @param visited the set of vertices which have already been checked
     * @param result the running list of connected vertices to be returned by depthFirstSearch()
     */
    private void dfsTraversal(Vertex current, Set<Vertex> visited, List<Vertex> result) {
        //needs current vertex, visited  list, result list

        //mark current vertex as visited
        visited.add(current);
        
        //add current vertex to result list
        result.add(current);
        
        //for every neighbor in A's adjacency set
        Set<Vertex> neighbors = adjacencyList.get(current);
        //if neighbors is populated
        if (neighbors != null) {
            //for every neighbor associated with current
            for (Vertex neighbor : neighbors) {
                //if that neighbor hasn't been visited
                if (!visited.contains(neighbor)) {
                    //recursively call dfsTraversal(neighbor, visited, result)
                    dfsTraversal(neighbor, visited, result);
                }   
            }
        }
    }


    /**
     * Performs a breadth-first search (BFS) of the graph
     * 
     * This method instantiates a series of data structures to conduct a BFS by 
     * repeatedly queueing vertices and cataloging all of their neighbors sequentially
     * in this queue.
     * @return vertex list of all visited vertices
     */
    public List<Vertex> breadthFirstSearch() {

        //create structures needed to manage BFS logic
        Queue<Vertex> queue = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>();
        List<Vertex> results = new ArrayList<>();

        //if A doesn't exist
        if (vertexCollection.isEmpty()) {
            //return empty result list
            return results;
        }
        //find A
        Vertex start = findVertex("A");
        if (start == null) {
            return results;
        }

        //enqueue A
        queue.add(start);
        //mark A visited
        visited.add(start);

        //while queue isn't empty
        while (!queue.isEmpty()) {
            //remove first vertex
            Vertex current = queue.remove();
            //add to result
            results.add(current);

            //formats the data into appropriate format
            Set<Vertex> neighbors = adjacencyList.get(current);
            //checks if empty
            if (neighbors != null) {
                //for each element
                for (Vertex neighbor : neighbors) {
                    //if we haven't visited it, add it to data
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }   
        }
        return results;
    }


    /**
     * Checks for complete graph connectedness
     * 
     * This method checks to ensure all documented vertices have at least one connected edge
     * If the graph contains a node that is not connected (aside from an empty graph or single node)
     * it will return false
     * @return boolean statement verifying wheter the graph is completely connected
     */
    public boolean isConnected() {
        //if graph has no verticies
        if (vertexCollection.isEmpty()) {
            //return true
            return true;
        }
        
        //run DFS to catalog all connected vertices
        List<Vertex> results = depthFirstSearch();
        //did DFS visit every stored vertex?
        return (results.size() == vertexCollection.size());
        //yes -> connected, T
        //no -> disconnected, F
    }


    /**
     * Checks for graph edge cycles
     * 
     * This method checks to see if any 3+ vertex connections loop back to one another
     * (example: A is connected to B and C, while B and C are also connected to eachother)
     * This means one could trace a loop between multiple verices.
     * The method contains a helper method to compare vertices to prior iterations. 
     * @return boolean confirming the graph has a cycle
     */
    public boolean hasCycles() {
        //create local visited data
        Set<Vertex> visited = new HashSet<>();
        //for every vertex
        for (Vertex v : vertexCollection.values()) {
            //if not visited
            if (!visited.contains(v)) {
                //if DFS finds a cycle
                if (dfsCycle(v, null, visited)) {
                    //return true
                    return true;
                }
                
            }
        }
            
        return false;
    }

    /**
     * Recursive helper method for hasCycles() performing DFS
     * 
     * This method checks each vertex to see if it has been encountered as a parent node in prior iterations
     * If it detects that a parent node has been detected before, it will confirm there is a cycle.
     * @param current cvertex urrent node of the recursion cycle. 
     * @param parent vertex prior node in the recursion cycle, will be null when beginning with first node
     * @param visited set of previously catologued nodes
     * @return boolean confirmation of a cycle.
     */
    private boolean dfsCycle(Vertex current, Vertex parent, Set<Vertex> visited) {
        //mark current visited
        visited.add(current);

        //populate local data with current node
        Set<Vertex> neighbors = adjacencyList.get(current);

        //if not empty
        if (neighbors != null) {
            //for each item
            for (Vertex neighbor : neighbors) {
                //if it has not been visited
                if (!visited.contains(neighbor)) {
                    //if dfsCycle returns true
                    if (dfsCycle(neighbor, current, visited)) {
                        return true;
                    }
                }
                //if not visited, and item is not the parent
                else if (!neighbor.equals(parent)) {
                    return true;
                }
            }
        }
        return false;
    }

}


   
