public class adjMatrixGraph {

    int vertices;

    double adjMatrix[][];

    /**
     * Constructor for the adjacency list matrix graph
     * @param vertices
     */
    public adjMatrixGraph(int vertices){

        this.vertices = vertices;

        adjMatrix = new double[vertices][vertices];

    }

    public void addEdge(int source, int dest, double weight){

        adjMatrix[source][dest] = weight;

    }
}
