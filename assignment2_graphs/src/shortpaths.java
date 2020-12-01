import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class shortpaths {

    /**
     * Main function that will get the file name as input and print the required results
     * @param args
     * @throws Exception if the file name or path entered is not found
     */
    public static void main(String[] args) throws Exception{

        System.out.println("Please enter file name:");

        Scanner input = new Scanner(System.in);

        adjListGraph test = readTestFile(input.next());
        //adjListGraph test = readTestFile("testGraph.txt");

        //Printing adjacency matrix------------------------------------------------
        double[][] matrix = adjMatrixConversion(test);

        System.out.println("Adjacency Matrix");

        for(int a = 0; a < matrix.length; a++){

            System.out.print("\t" + a + "\t");

        }

        System.out.println();

        for(int i= 0; i<matrix.length; i++){

            System.out.print(i + "\t");

            for(int j = 0; j<matrix.length; j++){

                if(matrix[i][j] ==0){

                    System.out.print("." + "\t" + "\t");

                }else {

                    System.out.print(matrix[i][j] + " \t");
                }

            }

            System.out.println();

        }

        System.out.println();

        //Printing results of Dijkstra's algorithm-------------------------------------
        dijkstra(test);

        System.out.println("Dijkstra's Algorithm");
        System.out.println("Shortest Paths from source:");

        for(Vertex v: test.getVertices()){

            System.out.print("0 --> " + v.getVertexNum() + " : ");
            System.out.print(v.getDistance());
            System.out.println();

        }

        System.out.println();


        //Printing results of Floyd-Warshall algorithm------------------------------
        double[][] floydWarshallM = floydWarshall(matrix);

        System.out.println("Floyd-Warshall Algorithm");

        for(int a = 0;a<floydWarshallM.length;a++){

            System.out.print("\t" + a + "\t");

        }

        System.out.println();

        for(int i= 0; i<floydWarshallM.length; i++){

            System.out.print(i + "\t");

            for(int j = 0; j<floydWarshallM.length; j++){

                System.out.print(floydWarshallM[i][j] + " \t");

            }

            System.out.println();

        }

    }

    /**
     * Function to read the text file and generate an adjacency list graph
     * @param filePath name of the text file
     * @return adjacency list graph
     * @throws Exception if file is not found
     */
    private static adjListGraph readTestFile (String filePath) throws Exception{

            File f = new File(filePath);
            Scanner scanner = new Scanner(f);

            String s;

            adjListGraph graph = new adjListGraph();    //creating new adjacency list graph

            while (scanner.hasNext()){

                s = scanner.next();

                String temp, temp1;

                temp = s.replace("(","");   //replacing all brackets

                temp1 = temp.replace(")"," ");


                String[] vertices = temp1.split(" ");

                ArrayList<Integer> verticesCheck = new ArrayList<>();

                ArrayList<Integer> finalVertices = new ArrayList<>();

                String[] data = null;

                //splitting the contents of each bracket
                for(String u : vertices){

                    data = u.split(",");

                    for(int i = 0; i < data.length; i = i+3){

                        int v1 = Integer.parseInt(data[i]);
                        int v2 = Integer.parseInt(data[i+1]);

                        verticesCheck.add(v1);
                        verticesCheck.add(v2);

                    }

                }

                //getting the number of vertices that will be present on the graph
                for(int q: verticesCheck){

                    if(!finalVertices.contains(q)){

                        finalVertices.add(q);

                    }

                }

                //ordering the list of vertices from smallest to highest
                Collections.sort(finalVertices);

                ArrayList<Vertex> fVertices = new ArrayList<>();

                for(int q: finalVertices){

                    Vertex v = new Vertex(q);
                    fVertices.add(v);

                }

                //splitting the numbers in each bracket separated by a ','
                for(String s1: vertices){

                    data = s1.split(",");

                    for(int i = 0; i < data.length; i = i+3){

                        int a = Integer.parseInt(data[i]);
                        int b = Integer.parseInt(data[i + 1]);
                        double c = Double.parseDouble(data[i + 2]);

                        //generating edges that are present between the vertices
                        for(Vertex v: fVertices){

                            if(a == v.getVertexNum()){

                                for(Vertex v1: fVertices){

                                    if(b == v1.getVertexNum()){

                                        v.addEdge(new Edge(v,v1,c));

                                    }

                                }

                            }

                        }


                    }

                }

                graph.setVertices(fVertices);

            }

            return graph;
    }

    /**
     * Method to convert an adjacency list graph to an adjacency matrix graph
     * @param graph adjacency list graph
     * @return double[][] matrix
     */
    private static double[][] adjMatrixConversion (adjListGraph graph){

        int vertices = graph.getVertices().size();

        double[][] matrix = new double[vertices][vertices];

        for(Vertex v: graph.getVertices()){

            for(Edge e: v.getAdjList()){

                int s = e.getSource().getVertexNum();
                int t = e.getTarget().getVertexNum();

                matrix[s][t] = e.getWeight();

            }

        }

        return matrix;
    }


    /**
     * Function to compute Dijkstra's algorithm on an adjacency list graph
     * Updates the distance variables for each vertex object
     * @param graph adjacency list graph
     */
    private static void dijkstra(adjListGraph graph) {

            Vertex source = graph.getVertices().get(0);

            source.setDistance(0);      //setting distance of the source of the graph to 0

            //gives priority based on the vertex with the smallest distance value
            PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();

            priorityQueue.add(source);

            while (!priorityQueue.isEmpty()) {

                Vertex vertex = priorityQueue.peek();       //extract min from priority queue

                for (Edge edge : vertex.getAdjList()) {

                    Vertex v = edge.getTarget();

                    double distance = vertex.getDistance() + edge.getWeight();

                    if(distance < v.getDistance()){

                        priorityQueue.remove(v);
                        v.setDistance(distance);
                        priorityQueue.add(v);

                    }

                }

                priorityQueue.remove(vertex);

            }

    }

    /**
     * Method to compute the Floyd-Warshall algorithm
     * Takes as input an adjacency matrix graph and gives the result in a double[][]
     * @param w adjacency matrix
     * @return double[][] containing the shortest path between each vertex
     */
    private static double[][] floydWarshall(double[][] w){

        int n = w.length;

        int max = Integer.MAX_VALUE;

        //creating a new matrix that will contain the path values
        double[][] matrix = new double[n][n];

        int i = 0;
        int j = 0;
        int k = 0;

        //assigning the new matrix to the input matrix initially
        for(i = 0; i < n; i++){

            for(j = 0; j < n; j++){

                if(i == j){

                    //removing the edges starting on one node and ending on that node
                    matrix[i][j] = 0;

                }else if(w[i][j] == 0 && i!=j) {

                    //replacing all weights that are 0 to infinity
                    matrix[i][j] = max;

                }else{

                    matrix[i][j] = w[i][j];
                }

            }

        }

        for(k = 0; k < n; k++){

            for(i = 0; i < n; i++){

                for(j = 0; j < n; j++){

                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);

                }

            }

        }

        return matrix;

    }

}
