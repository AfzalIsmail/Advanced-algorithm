/**
 * Fibonacci heap object class
 */
public class FibonacciHeap{

    public FibHeapNode head;

    public FibHeapNode end;

    public int numNodes;

    public boolean empty;

    /**
     * Constructor for the FibonacciHeap object
     * head: the head of the wheel
     * end: the last node of the wheel
     * numNodes: the number of nodes in the wheel
     */
    public FibonacciHeap(){

        head = null;

        end = null;

        numNodes =0;

    }

    /**
     * Function to check if the heap is empty
     * @return
     */
    public boolean isEmptyH() {

        if(head == null){

            return true;

        }else {

            return false;

        }
    }

    /**
     * Method to insert a new node into the heap
     * @param x Takes a string as input and a new node is created, holding as value the string
     * @return the new node
     */
    public FibHeapNode insertH (String x) {

        //creates a new node
        FibHeapNode node = new FibHeapNode(x);
        //sets the degree of the node to 0
        node.degree = 0;

        //check if heap is empty
        if (isEmptyH()) {

            head = node;

            node.next = node;
            node.prev = node;
            end = head;

        } else {

            node.next = head;
            node.prev = head.prev;
            head.prev = node;
            node.prev.next = node;

            //Compares the value of the new node to that of the head
            //If the new node has a smaller value, it becomes the head
            if(node.data.compareTo(head.data) < 0){

                //System.out.println(compareTo(node.data,head.data));

                head = node;

            }

        }

        numNodes++;

        return node;
    }

    /**
     * Method to get the head of the heap
     * @return the head node
     */
    public FibHeapNode minimumH(){

        return head;
    }



    /**
     * Function to merge a child wheel with the main wheel
     * @param a the head node of the main wheel
     * @param b the node who's children are being merged to the main wheel
     */
    private void merge(FibHeapNode a, FibHeapNode b){

        FibHeapNode end = a.prev;
        FibHeapNode childEnd = b.child.prev;

        a.prev = childEnd;
        childEnd.next = a;
        a.child.prev = end;
        end.next = a.child;

    }

    /**
     * Method to extract the head of the main wheel
     * @return the head node
     */
    public FibHeapNode extractH(){

        FibHeapNode z = head;

        try {

            if (z.child != null) {

                z.child.parent = null;

                //set the parent for each child to null
                for (FibHeapNode x = z.child.next; x != z.child; x = x.next) {

                    x.parent = null;

                }

                // merge the children nodes to the root wheel
               merge(head,z);

            }

            // remove head of wheel
            z.prev.next = z.next;
            z.next.prev = z.prev;

            if (z == z.next) {

                head = null;

            } else {

                head = z.next;
                consolidate();

            }

        }catch (Exception e){

        }
        numNodes--;
        return z;

    }

    /**
     * Function to consolidate the structure of the heap when a node is extracted from the heap
     */
    private void consolidate() {

        FibHeapNode[] A = new FibHeapNode[100];

        FibHeapNode start = head;
        FibHeapNode w = head;

        try {

            //for each node in the main wheel
           do{
                FibHeapNode x = w;

                FibHeapNode wNext = w.next;

                int d = x.degree;

                while (A[d] != null) {

                    FibHeapNode y = A[d];

                    //exchange the node x with y
                    if (x.data.compareTo(y.data) > 0) {

                        FibHeapNode temp = y;
                        y = x;
                        x = temp;

                    }

                    //remove y from the main wheel and make it a child of x
                    fibHeapLink(x,y);

                    A[d] = null;

                    d++;

                }

                A[d] = x;

                w = wNext;

            }while (w != start);

            for (FibHeapNode a : A) {

                if (a != null) {

                    if(head == null){

                        head = a;

                    }else if(a.data.compareTo(head.data) < 0){

                        head = a;

                    }


                }
            }

        }catch (Exception e){

        }
    }

    /**
     * Removes the node from the main wheel and makes it a child to the parent node
     * @param parent node
     * @param currentNode node that will become the child of the parent node
     */
    private void fibHeapLink(FibHeapNode parent,FibHeapNode currentNode) {

        currentNode.prev.next = currentNode.next;
        currentNode.next.prev = currentNode.prev;

        //makes the current node a child of the parent node
        currentNode.parent = parent;

        //checks if the parent has a child
        if (parent.child == null) {

            parent.child = currentNode;
            currentNode.next = currentNode;
            currentNode.prev = currentNode;

            //adds this node to the child wheel of the parent node
        } else {

            currentNode.prev = parent.child;
            currentNode.next = parent.child.next;
            currentNode.parent.child.next = currentNode;
            currentNode.next.prev = currentNode;

        }

        //increase the degree of the parent
        parent.degree++;

        currentNode.mark = false;
    }

}
