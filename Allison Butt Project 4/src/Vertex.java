/**
 * @author Allison Butt
 * @version CMSC 315 6380 - 6 July 2026
 * 
 * Vertex
 * This class creates a Vertex object
 * 
 * This class defines Vertex variables and contains getter methods to retrieve elements from a variable.
 * This class also includes override methods to ensure data formatting is proper for the program. 
 * @param name name of vertex
 * @param x x-coordinate 
 * @param y y-coordinate
 * 
 */

public class Vertex {

    //create variables
    private final String name;
    private final double x;
    private final double y;

    /**Constructs a new Vertex
     * 
     * Vertex object contains three variables and ensures null values are handled
     * 
     * @param name name of the vertex 
     * @param x x-coordinate of the vertex
     * @param y y-coordinate of the vertex
     * @throws IllegalArgumentException to ensure data is valid
     */
    public Vertex(String name, double x, double y) {

        //checks for null values
        if (name == null) {
            throw new IllegalArgumentException("Vertex name cannot be null");
        }
        //creates object
        this.name = name;
        this.x = x;
        this.y = y; 
    }

    /**
     * returns name
     * 
     * This method is a public getter method for the name variable.
     * @return Vertex name
     */
    public String getName() {
        return name;
    }

    /**
     * returns x coordinate
     * 
     * This method is a public getter method for the x coordinate variable.
     * @return Vertex x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * returns y coordinate
     * 
     * This method is a public getter method for the y coordinate variable.
     * @return Vertex y coordinate
     */
    public double getY() {
        return y;
    }
    
    /**
     * Overrides native toString() method
     * 
     * This method ensures that the name data is passed in the appropriate format
     * and prevents conflicts of data types
     * @return name string
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Overrides native equals() method
     * 
     * This method ensures that the data is passed in the appropriate format
     * and prevents conflicts of data types 
     * @param o Object to be compared 
     * @return boolean equality verification
     */
    @Override
    public boolean equals(Object o) {

        //checks native object properties
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        
        //checks equality through data type shift
        Vertex v = (Vertex) o;
        return name.equals(v.name);
    }

    /**
     * Overrides native hashCode() method
     * 
     * This method ensures that our HashSet objects are able to interact with Vertex objects 
     * appropriately. 
     * @return hashcode of name variable for use in HashSet data structures
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

}