package algorithms;

/**
 * Created by User on 22.04.2015.
 */
public class HighArray {
    private long[] arr;
    private int size;

    public HighArray(int size) {
        arr = new long[size];
        this.size =0;
    }

    public boolean find(long value) {
        int i;
        for(i = 0; i< size; i++)
            if (value == arr[i])
                break;
        return size != i;
    }

    public void insert(long value) {
        arr[size] = value;
        size++;
    }

    public boolean delete(long value) {
        int i;
        for(i = 0; i< size; i++)
            if(value == arr[i])
                break;
        if(i == size)
            return false;
        for(int k = i; k < size; k++)
            arr[k] = arr[k+1];
        size--;
        return true;
    }

    public void display() {
        for(int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");
        System.out.println(" ");
    }

    public long getMax() {
        if(size == 0)
            return -1;
        else {
            int i;
            long maxEl = arr[0];
            for(i = 1; i < size; i++)
                if(arr[i] > maxEl)
                    maxEl = arr[i];
            return maxEl;
        }
    }

    public boolean removeMax() {
        long maxElem = getMax();
        if(maxElem == -1)
            return false;
        else
            delete(maxElem);
        return true;
    }

    public int getSize() {
        return size;
    }

    public void noDups() {
        for(int i = 0; i < size; i++)
            for(int j = size-1; j > i; j--)
                if(arr[j] == arr[i])
                    arr[j] = -90909L;

        for(int i = 0; i < size; i++)
            if(arr[i] == -90909L) {
                delete(arr[i]);
                i--;
            }
    }
}
