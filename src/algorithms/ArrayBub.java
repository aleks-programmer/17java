package algorithms;

/**
 * Created by User on 23.04.2015.
 */
public class ArrayBub {
    private long[] a;
    private int nElems;

    public ArrayBub(int max)
    {
        a = new long[max];
        nElems = 0;
    }

    public void insert(long value)
    {
        a[nElems] = value;
        nElems++;
    }

    public void display()
    {
        for(int i = 0; i < nElems; i++)
            System.out.print(a[i] + " ");
        System.out.println(" ");
    }

    private void swap(int one, int two)
    {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }

    public void bubbleSort()
    {
        for(int i=0; i < nElems; i++)
            for(int j = nElems-1; j > i; j--)
                if(a[j] < a[j-1])
                    swap(j, j-1);
    }

}
