/**
 * Class for the node object used for the fibonacci heap
 */
public class FibHeapNode {

    public String data;

    public FibHeapNode parent = null;

    public FibHeapNode child = null;

    public FibHeapNode next;

    public FibHeapNode prev;

    public int degree = 0;

    public boolean mark = false;

    /**
     * Constructor for the FibHeapNode object
     * @param data
     */
    public FibHeapNode(String data){

        this.data = data;

        next = this;
        prev = this;

        degree = 0;


    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
