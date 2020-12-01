import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class searchtree {

    private Node root;

    private Node nil;

    private searchtree(){

        this.root = null;

        this.nil = new Node();

        nil.color = "black";
        nil.left = null;
        nil.right = null;

        root = nil;

    }

    /**
     * Main function
     * Gets user input from the command line to get the name or path of the text file
     * Creates a new searchtree object that will be used to test the different functions
     * @param args
     */
    public static void main (String args[]){

        //creates new searchtree object t
        searchtree t = new searchtree();

        System.out.println("Please enter the file name.");

        Scanner input = new Scanner(System.in);   //getting user input from command line

        t.test(input.next());


        //----------------- codes used for testing

        /*searchtree s = new searchtree();

        s.insert(1);
        s.insert(2);
        s.insert(3);
        s.insert(4);
        s.insert(5);
        s.insert(6);
        s.insert(8);
        s.insert(7);
        s.insert(8);

        Node d = new Node();
        d.key = 11;*/

        /*Node z = s.nil;

        z = s.searchTree(s.root,8);

        if(z.equals(s.nil)){

            System.out.println("Key not found");
        }else{

            System.out.println("Key" + z.key);
        }*/

        //System.out.println(s.search(4));

        //s.delete(s.root,3);

        //System.out.println(s.search(3));

        //s.inorderTreeWalk(s.root);

        //Node x = s.searchTree(s.root,5);

        //System.out.println(x.key);
        //Node x = s.searchTree(s.root,2);
        //System.out.println(x.key);

    }

    /**
     * Function to test the tree implementation
     * Reads the operations and integer values in the text file
     * Implements a tree by carrying out the operations in the text file
     * @param filePath file name or path that the user enters in the command line
     */
    private void test(String filePath){

        try {

            //To read the text file
            File file = new File(filePath);

            Scanner scanner = new Scanner(file);

            ArrayList<String> op = new ArrayList<>();   //arraylist which holds the strings in the text file

            ArrayList<Operation> arrayList = new ArrayList<>();  //arraylist that holds the operation objects

            //scanning the text file and adding each string to the arraylist
            while (scanner.hasNext()) {

                //System.out.println(scanner.next());
                op.add(scanner.next());

            }

            //reading the string arraylist and creating operation objects
            //Each object hold the name of the operation and an integer value
            for (int i = 0; i < op.size() - 1; i = i + 2) {

                Operation operation = new Operation();
                operation.setName(op.get(i));
                int k = Integer.parseInt(op.get(i + 1));
                operation.setKey(k);
                arrayList.add(operation);

            }

            //performing operations on the searchtree according to each operation name and value in the Operation arraylist
            for (Operation a : arrayList) {

                if (a.name.equals("insert")) {    //insert in searchtree

                    insert(a.getKey());


                } if (a.name.equals("search")) {    //search the search tree and print the result in the command line

                    System.out.println(search(a.getKey()));


                } if (a.name.equals("delete")) {    //deleting a node in the tree

                    delete(root, a.getKey());

                }

            }

        }catch (FileNotFoundException e){    //exception handling in case the file name entered does not exist

            System.out.println("File not found");

        }

    }

    /**
     * Function to print the RB tree in order of the smallest to highest node
     * @param n the node from which the subtrees will be printed
     */
    private void inorderTreeWalk(Node n){

        if(n != null){

            inorderTreeWalk(n.left);

            System.out.println(n.key);

            inorderTreeWalk(n.right);

        }

    }

    /**
     * Left rotate function used for insert and delete operations
     * @param current the node about which the rotation is made
     */
    private void leftRotate(Node current){

        //setting node n to be the current's node right
        Node n = current.right;

        current.right = n.left;    //n's subtree becomes current's subtree

        if(n.left != null){

            n.left.parent = current;

        }

        n.parent = current.parent;    //current's parents becomes n's parents

        if(current.parent == null){    //if current has no parent, n becomes current's parent

            this.root = n;

        }else if(current == current.parent.left){   //if current is the left child of its parents, make n the left child

            current.parent.left = n;

        }else{

            current.parent.right = n;

        }

        n.left = current;    //    make current the left child of n
        current.parent = n;

    }

    /**
     * Right rotate function used for insert and delete operations
     * Similar to the leftRotate() function, with left and right interchanged
     * @param current the node about which the rotation is made
     */
    private void rightRotate(Node current){

        Node n = current.left;

        current.left = n.right;

        if(n.right != nil){

            n.right.parent = current;

        }

        n.parent = current.parent;

        if(current.parent == null){

            root = n;

        }else if(current == current.parent.right){

            current.parent.right = n;

        }else{

            current.parent.left = n;

        }

        n.right = current;

        current.parent = n;

    }

    /**
     * Insert function
     * Takes an integer as a parameter to create a new node and insert it in the RB tree
     * @param key the value of the new node created
     */
    private void insert(int key){

        try {

            //Creating a new node
            //This node will be inserted as a leaf and will be red
            Node z = new Node();

            z.key = key;
            z.left = nil;
            z.right = nil;
            z.color = "red";

            Node y = null;

            Node x = this.root;

            //finding a place in the tree to insert the new node
            while (x != nil) {

                y = x;

                if (z.key < x.key) {

                    x = x.left;

                } else {

                    x = x.right;

                }

            }

            //setting z's parent to y
            z.parent = y;

            if (y == null) {      //if z is the first node in the tree, make it the root of the tree and set its color black

                z.color = "black";
                root = z;
                return;

            } else if (z.key < y.key) {        //else place z either on the left or right depending on its value

                y.left = z;

            } else {

                y.right = z;

            }

            insertFixup(z);     //calls the function to check if all the properties of the tree is satisfied when a new node is inserted

        }catch (NullPointerException e){

        }

    }

    /**
     * Function to fix the property of the RB tree
     * @param n the node from which the subtrees will be checked and fixed
     */
    private void insertFixup(Node n){

        Node y;

        try {

            while (n.parent.color.equals("red")) {

                if (n.parent.equals(n.parent.parent.left)) {

                    y = n.parent.parent.right;

                    if (y.color.equals("red")) {        //case 1: if a node is red, both of its children must be black

                        n.parent.color = "black";
                        y.color = "black";
                        n.parent.parent.color = "red";
                        n = n.parent.parent;

                    } else {

                        if (n.equals(n.parent.right)) { //case 2: rotate the node to make it a left child

                            n = n.parent;
                            leftRotate(n);

                        }

                        n.parent.color = "black";       //case 3: makes n's parent black, so if n becomes the root during
                        n.parent.parent.color = "red";  //the next iteration, the root's color is black
                        rightRotate(n.parent.parent);   //This will break the while loop

                    }

                } else {

                    y = n.parent.parent.left;

                    if (y.color.equals("red")) {

                        n.parent.color = "black";
                        y.color = "black";
                        n.parent.parent.color = "red";
                        n = n.parent.parent;

                    } else {

                        if (n.equals(n.parent.left)) {

                            n = n.parent;
                            rightRotate(n);

                        }

                        n.parent.color = "black";
                        n.parent.parent.color = "red";
                        leftRotate(n.parent.parent);

                    }

                }

            }

            root.color = "black";

        }catch (NullPointerException e){
            //System.out.println("err");

        }

    }

    /**
     * Function to move subtrees
     * @param u
     * @param v
     */
    private void transplant(Node u, Node v){

        if(u.parent.equals(nil)){

            root = v;

        }else if(u.equals(u.parent.left)){

            u.parent.left = v;

        }else{

            u.parent.right = v;

        }

        v.parent = u.parent;

    }

    /**
     * Method to return the smallest node in a tree or subtree
     * @param n node in the tree from which the smallest node is searched for
     * @return the smallest node found
     */
    private Node minimumTree(Node n){

        while(n.left != nil){

            n = n.left;
        }

        return n;
    }

    /**
     * Function to delete a node
     * @param n the root of the tree
     * @param key the value of the node to be deleted
     */
    private void delete(Node n, int key){

        Node z = nil;

        z = searchTree(root,key);       //Search the tree for the node

        if(z == nil){                   //returns nothing if the node is not found

            return;
        }

        Node y = z;

        Node x;

        String originColY = y.color;

        if(z.left.equals(nil)){

            x = z.right;
            transplant(z,z.right);

        }else if(z.right.equals(nil)){

            x = z.left;
            transplant(z,z.left);

        }else{

            y = minimumTree(z.right);
            originColY = y.color;
            x = y.right;

            if(y.parent.equals(z)){

                x.parent = y;

            }else{

                transplant(y,y.right);
                y.right = n.right;
                y.right.parent = y;

            }

            transplant(z,y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;

        }

        if(originColY.equals("black")){     //if the node deleted is black the properties of the tree might be violated
                                            //the function deleteFixup is called to check and maintain the properties of the tree
            deleteFixup(n);

        }

    }

    /**
     * Function to check and maintain the properties of tree if a black node is deleted
     * @param n the node that has been deleted
     */
    private void deleteFixup(Node n){

        Node w;

        while(!n.equals(root) && n.color.equals("black")){

            if(n.equals(n.parent.left)){

                w = n.parent.right;

                if(w.color.equals("red")){                                          //case 1: if the sibling of node n is red
                                                                                    //the color of the parent and the sibling are exchanged
                    w.color = "black";                                              //and a left rotation is done to make n the child of w
                    w.parent.color = "red";
                    leftRotate(n.parent);
                    w = n.parent.right;

                }if(w.left.color.equals("black") && w.right.color.equals("black")){

                    w.color = "red";                                                //case 2: when the sibling of n is black and has 2 black
                    n = n.parent;                                                   //children.

                }else {

                    if(w.right.color.equals("black")) {                             //case 3: when x's sibling w is black and has a red left child
                        w.left.color = "black";                                     //and a black right child. The color of w and its left child
                        w.color = "red";                                            //are exchanged. A right rotation is done without violating any
                        rightRotate(w);                                             //properties
                        w = n.parent.right;

                    }

                    w.color = n.parent.color;                                       //case 4: when x' sibling w is black and w's right child is
                    n.parent.color = "black";                                       //red.
                    w.right.color = "black";
                    leftRotate(n.parent);

                    n = root;
                }

            }else{                                                                  //Similar cases as to the ones mentioned before with right and left exchanged

                w = n.parent.left;

                if(w.color.equals("red")){

                    w.color = "black";
                    w.parent.color = "red";
                    rightRotate(n.parent);
                    w = n.parent.left;

                }if(w.right.color.equals("black") && w.left.color.equals("black")){

                    w.color = "red";
                    n = n.parent;

                }else{

                    if(w.left.color.equals("black")){

                        w.right.color = "black";
                        w.color = "red";
                        leftRotate(w);
                        w = n.parent.left;
                    }

                    w.color = n.parent.color;
                    n.parent.color = "black";
                    w.left.color = "black";
                    rightRotate(n.parent);

                    n = root;
                }


            }

        }

        n.color = "black";

    }

    /**
     * Method to search the tree and return the node with the value being searched
     * @param n root of the tree
     * @param key value to be searched
     * @return node with the same value as the key
     */
    private Node searchTree(Node n, int key){


        if(n == nil || key == n.key){

            //System.out.println(n.key);

            return n;

        }if(key < n.key){

            return searchTree(n.left,key);

        }else{

            return searchTree(n.right,key);
        }



    }

    /**
     * Method to know if a node with the key value has been found in the searchTree
     * @param key the value of the node to be searched
     * @return a string to inform if the value has been found or not
     */
    private String search(int key){

        Node z = nil;

        z = searchTree(root,key);

        if(z == nil){

            return "Searching for " + key + " - not found";

        }else{

            return "Searching for " + key + " - found";

        }

    }

}
