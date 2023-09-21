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

    public void insert(long value) {
        a[nElems] = value;
        nElems++;
    }

    public void insertionSort() {
        for (int i = 1; i < nElems; i++) {
            long temp = a[i];
            int in = i;

            while (in > 0 && a[in - 1] > temp) {
                a[in] = a[in - 1];
                in--;
            }

            a[in] = temp;
        }
    }

    public void shellSort() {
        int h = 1;

        while (h < nElems / 3)
            h = (h * 3) + 1;

        while (h > 0) {
            for (int outer = h; outer < nElems; outer++) {
                long temp = a[outer];
                int inner = outer;

                while (inner > h - 1 && temp <= a[inner - h]) {
                    a[inner] = a[inner - h];
                    inner -= h;
                }

                a[inner] = temp;
            }

            h = (h - 1) / 3;
        }
    }

    public void quickSort() {

        recQuickSort(0, nElems - 1);
    }

    private void recQuickSort(int left, int right) {
        if (right - left <= 0) return;

        long pivot = a[right];
        int partition = partitionIt(left, right, pivot);

        recQuickSort(left, partition - 1);
        recQuickSort(partition + 1, right);
    }

    private int partitionIt(int left, int right, long pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;

        while (true) {
            while (leftPtr < nElems - 1 && a[++leftPtr] <= pivot) ;
            while (rightPtr > 0 && a[--rightPtr] >= pivot) ;

            if (leftPtr >= rightPtr) break;

            swap(leftPtr, rightPtr);
        }

        swap(leftPtr, right);

        return leftPtr;
    }

    private void swap(int left, int right) {
        long temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

    public void display() {
        for (int i = 0; i < nElems; i++)
            System.out.print(a[i] + " ");
        System.out.println(" ");
    }
}
