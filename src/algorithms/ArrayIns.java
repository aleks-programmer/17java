package algorithms;

/**
 * Created by User on 23.04.2015.
 */
public class ArrayIns {
    private long[] a;
    private int nElems;

    ArrayIns(int size) {
        a = new long[size];
        nElems = 0;
    }

    public void insert(long value)
    {
        a[nElems] = value;
        nElems++;
    }

    public void insertionSort()
    {
        int i, in;
        for(i = 1; i < nElems; i++) {
            int out = i;
            in = out;
            long temp = a[out];
            while(in > 0 && a[i-1] >= temp) {
                a[i] = a[i-1];
                --i;
            }
            a[i] = temp;
        }

    }

    public void display()
    {
        for(int i = 0; i < nElems; i++)
            System.out.print(a[i] + " ");
        System.out.println(" ");
    }
}
