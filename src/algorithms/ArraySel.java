package algorithms;

/**
 * Created by User on 23.04.2015.
 */
public class ArraySel {
    private long[] a;
    private int nElems;

    ArraySel(int size) {
        a = new long[size];
        nElems = 0;
    }

    public void insert(long value)
    {
        a[nElems] = value;
        nElems++;
    }

    private void swap(int one, int two)
    {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }

    public void selectionSort() {
        for (int i = 0; i < nElems - 1; i++) {
            int min = i;
            for (int j = i + 1; j < nElems; j++) {
                if (a[j] < a[min]) {
                    swap(j, min);
                    min = j;
                }
            }
        }

    }

    public void display()
    {
        for(int i = 0; i < nElems; i++)
            System.out.print(a[i] + " ");
        System.out.println(" ");
    }
}
