import java.util.ArrayList;
import java.util.List;

public class adjListGraph {

    List<Vertex> vertices;

    /**
     * Constructor the adjacency list graph object
     */
    public adjListGraph(){

        this.vertices = new ArrayList<>();

    }

    /**
     * gets the list of vertices for the graph
     * @return List
     */
    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * sets the list for the vertices
     * @param vertices
     */
    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }
}
