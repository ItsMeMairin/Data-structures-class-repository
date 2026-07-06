
/**
 This class should be an immutable class that
defines a vertex of the graph and contains the x and y coordinates of the vertex along with its
name. It should have a constructor and three getter methods.


3 getters are used by other classes to grab private variables
    we use these getters to pass vertex name, x, and y coordinates
 */

public class Vertex {

    private final String name;
    private final double x;
    private final double y;

    /**Constructs a new Vertex
     * 
     * Vertex object contains three variables which are declared by the class
     * this.vertexLetter etc. 
     * 
     * @param name name of the vertex 
     * @param x x-coordinate of the vertex
     * @param y y-coordinate of the vertex
     */
    public Vertex(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y; 
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}