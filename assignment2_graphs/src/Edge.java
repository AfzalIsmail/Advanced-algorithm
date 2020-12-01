public class Edge {

    private Vertex source;
    private Vertex target;
    private double weight;

    /**
     * Constructor for the Edge object
     * @param source vertex
     * @param target vertex
     * @param weight of the vertex
     */
    public Edge (Vertex source, Vertex target, double weight){

        this.source = source;
        this.target =  target;
        this.weight = weight;

    }

    /**
     *
     * @return the source vertex
     */
    public Vertex getSource() {
        return source;
    }

    /**
     * sets the source of an edge
     * @param source
     */
    public void setSource(Vertex source) {
        this.source = source;
    }

    /**
     *
     * @return the target vertex
     */
    public Vertex getTarget() {
        return target;
    }

    /**
     * sets the target vertex
     * @param dest
     */
    public void setTarget(Vertex dest) {
        this.target = dest;
    }

    /**
     *
     * @return the weight of the edge
     */
    public double getWeight() {
        return weight;
    }

    /**
     * sets the weight of the edge
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
