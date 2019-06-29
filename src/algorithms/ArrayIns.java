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

    public void shellSort()
    {
        int inner, outer;
        long temp;
        int h = 1;
        while (h <= nElems/3)
            h = h*3 + 1;
        while (h >0) {
            for (outer = h; outer < nElems; outer++) {
                temp = a[outer];
                inner = outer;

                while (inner > h - 1 && a[inner - h] >= temp) {
                    a[inner] = a[inner - h];
                    inner -= h;
                }
                a[inner] = temp;
            }
            h = (h - 1) / 3;
        }

    }

    public void quickSort()
    {

        recQuickSort(0, nElems - 1);
    }

    public void recQuickSort(int left, int right)
    {

        if (right - left <= 0) return;
        else {
            long pivot = a[right];
            int partition = partitionIt(left, right, pivot);
            recQuickSort(left, partition - 1);
            recQuickSort(partition + 1, right);
        }
    }

    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left - 1;
        int rightPtr = right + 1;

        while (true) {
            while (leftPtr < right && a[++leftPtr] < pivot) ;
            while (rightPtr > left && a[--rightPtr] > pivot) ;

            if (leftPtr >= rightPtr) break;
            else {
                long temp = a[leftPtr];
                a[leftPtr] = a[rightPtr];
                a[rightPtr] = temp;
            }

        }

        return leftPtr;
    }

    public void display()
    {
        for(int i = 0; i < nElems; i++)
            System.out.print(a[i] + " ");
        System.out.println(" ");
    }
}
