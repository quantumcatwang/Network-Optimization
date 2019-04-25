public class MaxHeap {

    // use array to store the tree
    private int[] name;        // store vertex name
    private int[] value;       // store vertex bandwidth
    private Integer[] nameToIndex; // a mapping from name to index
    private int size;

    // constructor
    public MaxHeap(int totSize) {
        name  = new int[totSize + 1];
        value = new int[totSize + 1];
        nameToIndex = new Integer[totSize + 1];
        size  = 0;
    }

    // return the max element (name)
    public int maximum() {
        return name[1];
    }

    // insert certain element and re-construct the heap
    public void insert(int v, int bw) {
        size++;
        name[size] = v;
        value[v] = bw;
        heapfy(size);
    }

    // delete element by index
    public void delete(int index) {
        if (index > size || index < 1) {
            throw new IndexOutOfBoundsException("Heap out of bound");
        }
        nameToIndex[name[index]] = null;
        name[index] = name[size];
        nameToIndex[name[index]] = index;
        size--;
        heapfy(index);
    }

    // delete element by name
    public void deleteVertex(int vertex) {
        int index = nameToIndex[vertex];
        delete(index);
    }

    // to see if heap is empty
    public boolean isEmpty() {
        return (size == 0);
    }

    // procedure for construct heap
    private void heapfy(int k) {
        int left  = 2 * k;
        int right = 2 * k + 1;
        int large = k;
        int tempLarge = k;

        if (left <= size && value[left] > value[k]) {
            large = left;
        }
        if (right <= size && value[right] > value[k]) {
            tempLarge = right;
        }
        if (value[large] < value[tempLarge]) {
            large = tempLarge;
        }

        if (large != k) {
            swap(large, k);
            heapfy(large);
        }
    }

    // helper function
    private void swap(int i, int j) {
        int temp;
        temp = name[i];
        name[i] = name[j];
        name[j] = temp;

        nameToIndex[name[i]] = i;
        nameToIndex[name[j]] = j;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("The heap: \n");
        for (int i = 1; i <= size; i++) {
            s.append(name[i] + " (" + value[i] + "), ");
        }
        s.append("\n");

        return s.toString();
    }
}