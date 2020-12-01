/**
 * Class for the node object
 */
public class Node {

    public int key;

    public Node next, prev;

    /**
     * Constructor for the node object
     * key: the value of the node
     * next: the next node connected to this node
     * prev: the previous node connected to this node
     */
    public Node(){

        key = 0;
        next = null;
        prev = null;

    }

    /**
     * Constructor for the node object
     * @param key the value of the node
     * @param next the next node connected to this node
     * @param prev the previous node connected to this node
     */
    public Node(int key, Node next, Node prev){

        this.key = key;
        this.next = next;
        this.prev = prev;

    }

    /**
     * Method to return the value of the node
     * @return key
     */
    public int getData() {
        return key;
    }

    /**
     * Sets the value for the node
     * @param data the numerical value
     */
    public void setData(int data) {
        this.key = data;
    }

    /**
     * Method to get the next node connected to this node
     * @return the next node
     */
    public Node getNext() {
        return next;
    }

    /**
     * Function to set the next node of this node
     * @param next node
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Method to get the previous node connected to this node
     * @return previous node
     */
    public Node getPrev() {
        return prev;
    }

    /**
     * Sets the previous node of this node
     * @param prev the previous node
     */
    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
