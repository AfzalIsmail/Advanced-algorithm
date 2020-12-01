import java.util.ArrayList;

/**
 * Class for the wheels object
 */
public class Wheels {

    public Boolean empty;

    private Node nil;

    private Node end;

    /**
     * Constructor for the wheels object
     * Holds 2 variables
     * The head of the wheel: nil
     * the last node of the wheel: end
     *
     */
    public Wheels(){

        nil = null;

        end = null;


    }

    /**
     * function to determine if the wheel is empty or not
     * @return
     */
    public boolean isEmpty() {

        if(nil == null){

            return true;

        }else {

            return false;

        }
    }

    /**
     * sets the empty boolean value
     * @param empty
     */
    public void setEmpty(Boolean empty) {

        empty = empty;

    }

    /**
     * Method to return the head of the wheel
     * @return the node nil
     */
    public Node getNil() {

        return nil;

    }

    /**
     * sets a node as the head of the wheel
     * @param head
     */
    public void setNil(Node head) {

        this.nil = head;

    }

    /**
     * Function to search the wheel for a particular node
     * @param i the node value
     * @return the node with hte same value
     */
    public Node search(int i) {

        Node n = new Node();
        n.key = i;

        Node x = nil.next;

            while(x != nil && x.key != n.key){

                x = x.next;

            }

        return x;

    }

    /**
     * Inserts a new node into the wheel and makes this node the head of the wheel
     * @param n the new node
     */
    public void insertW(Node n){

        /*n.next = nil.next;
        nil.next.prev = n;
        nil.next = n;
        n.prev = nil;*/

        //if the wheel is empty this node becomes the head
        if(isEmpty()){

            n.next = n;
            n.prev = n;
            nil = n;
            end = nil;

        //else the node is inserted into the wheel and becomes the head
        }else{

            n.prev = end;
            end.next = n;
            nil.prev = n;
            n.next = nil;
            nil = n;

        }

    }

    /**
     * Rotates the wheel to the right, by one node, by making the next node from the head to be the new head
     */
    public void rightW(){

        Node n = nil;
        nil = n.next;

    }

    /**
     * Rotates the wheel to the left, by one node, by making the previous node from the head to be the new head
     */
    public void leftW(){

        Node n = nil;
        nil = n.prev;

    }

    /**
     * Method to get the value of the head of the wheel
     * @return i the value of the head node
     */
    public int headW(){

        int i = nil.key;


        return i;

    }

    /**
     * Deletes and returns the value of the head of the wheel
     * and makes the next node on the right the new head
     * @return i the value of the head
     */
    public int extractW(){

        int i = nil.key;

        Node n = nil;

        nil = n.next;

        n.prev.next = n.next;
        n.next.prev = n.prev;

        return i;

    }
}
