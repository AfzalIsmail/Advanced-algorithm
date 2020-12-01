import java.util.ArrayList;
import java.util.List;

public class Vertex  implements Comparable<Vertex>{

    private int vertexNum;

    private List<Edge> adjList;

    private double distance = Double.MAX_VALUE;

    /**
     * Constructor for the vertex object
     * @param vertexNum number representing the vertex
     */
    public Vertex(int vertexNum){

        this.vertexNum = vertexNum;

        this.adjList = new ArrayList<>();

    }

    /**
     * add an Edge to a vertex
     * @param edge
     */
    public void addEdge(Edge edge){

        this.adjList.add(edge);

    }

    /**
     * get the number assigned to the vertex
     * @return vertex number
     */
    public int getVertexNum() {
        return vertexNum;
    }

    /**
     * set the vertex number
     * @param vertexNum
     */
    public void setVertexNum(int vertexNum) {
        this.vertexNum = vertexNum;
    }

    /**
     * returns the list of edges originating from that vertex
     * @return List
     */
    public List<Edge> getAdjList() {
        return adjList;
    }

    /**
     * sets the list of edges
     * @param adjList
     */
    public void setAdjList(ArrayList<Edge> adjList) {
        this.adjList = adjList;
    }

    /**
     * gets the distance from the from the vertex to the source vertex
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * sets the distance
     * @param distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * compares the distance between vertex
     * returns the smaller one
     * used by the priority queue to assign priorities
     * @param v vertex to be compared with
     * @return the smaller distance
     */
    @Override
    public int compareTo(Vertex v){

        return Double.compare(this.distance,v.getDistance());

    }

}
