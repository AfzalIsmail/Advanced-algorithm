import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class heapRun {

    /**
     * Main runnable function
     * @param args
     * @throws Exception
     */
    public static void main (String[] args)throws Exception{

        //-----------------------------------------------------------------codes used for testing
        /*Wheels w = new Wheels();

        Node a = new Node();
        a.key = 2;

        Node b = new Node();
        b.key = 5;

        Node c = new Node();
        c.key = 3;

        Node d = new Node();
        d.key = 9;

        Node e = new Node();
        e.key = 1;

        w.insertW(a);
        w.insertW(b);
        w.insertW(c);
        w.insertW(d);
        w.insertW(e);*/

       /* FibonacciHeap f = new FibonacciHeap();

        f.insertH("8");
        f.insertH("9");
        f.insertH("1");
        f.insertH("7");
        f.insertH("5");

        FibHeapNode s = f.minumumH();

        System.out.println(s.data);

        System.out.println(f.numNodes);

        f.extractH();

        System.out.println(f.minumumH().data);

        System.out.println(f.numNodes);*/

        /*System.out.println(w.getNil().key);
        System.out.println(w.getNil().next.key);
        System.out.println(w.getNil().prev.key);

        System.out.println(w.headW());*/


        /*w.leftW();

        System.out.println(w.getNil().key);
        System.out.println(w.getNil().next.key);
        System.out.println(w.getNil().prev.key);

        System.out.println(w.headW());*/

        //System.out.println(w.extractW());

        //System.out.println(w.getNil().key);
        //System.out.println(w.getNil().next.key);
        //System.out.println(w.getNil().next.next.key);
        //System.out.println(w.getNil().prev.key);


        //Node s = w.search(2);

            //System.out.println(s.key);
            //System.out.println(s.next.key);
            //System.out.println(s.prev.key);
            //System.out.println(w.getNil().key);
            //System.out.println(w.getNil().prev.key);

        System.out.println("Please enter the file name or path:");

        Scanner input = new Scanner(System.in);

        String[] test = readTestFile(input.next());

        //Creates a new fibonacci heap for the data in the text file
        FibonacciHeap fibonacciHeap = new FibonacciHeap();

        for(int i = 0; i < test.length; i++){

            if(test[i].equals("insert")){

                fibonacciHeap.insertH(test[i+1]);

            }if (test[i].equals("extract")){

                fibonacciHeap.extractH();

            }if(test[i].equals("minimum")){

                try {

                    System.out.println("minimum: " + fibonacciHeap.minimumH().data);

                }catch (Exception e){

                }



            }

        }



    }

    /**
     * Method to read the text file
     * @param testFile name of the text file
     * @return an array containing the operations to be carried out
     * @throws Exception File not found exception
     */
    public static String[] readTestFile(String testFile) throws Exception{

        FileReader fileReader = new FileReader(new File(testFile));

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String[] s = null;

        String line;

        //reads the text file and splits it where there is a space(" ")
        while((line = bufferedReader.readLine()) != null){

            s = line.split(" ");

        }

        return s;

    }
}
