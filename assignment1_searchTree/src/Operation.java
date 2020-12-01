public class Operation {

    String name;

    int key;

    /**
     * The operation object has 2 variables
     * 1. name of the type of operation to be performed
     * 2. the value with which the operation is being performed
     */
    public Operation(){

        this.name = null;

        this.key = 0;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
