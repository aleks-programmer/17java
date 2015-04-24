package algorithms;

/**
 * Created by User on 22.04.2015.
 */
public class OrderedArray {
    private long[] arr;
    private int elNum;

    public OrderedArray(int size) {
        arr = new long[size];
        elNum = 0;
    }

    public void insert(long value) {
        int lowerBound = 0;
        int upperBound = elNum - 1;

        for(int k = 0; k < elNum; k++)
            if(arr[k] == value) {
                insert((long) (Math.random() * 100));
                return;
            }
        while(true) {
            int checkMidIndex = (lowerBound + upperBound) / 2;
            if(lowerBound > upperBound) {
                for(int i  = elNum; i > lowerBound; i--)
                    arr[i] = arr[i-1];
                arr[lowerBound] = value;
                break;
            }
            else {
                if(arr[checkMidIndex] < value)
                    lowerBound = checkMidIndex + 1;
                else
                    upperBound = checkMidIndex - 1;
            }
        }
        elNum++;
    }

    public int find(long value) {
        int lowerBound = 0;
        int upperBound = elNum - 1;

        while(true) {
            int checkMidIndex = (lowerBound + upperBound) / 2;
            if(arr[checkMidIndex] == value)
                return checkMidIndex;
            else if(lowerBound > upperBound)
                return elNum;
            else {
                if(arr[checkMidIndex] < value)
                    lowerBound = checkMidIndex + 1;
                else
                    upperBound = checkMidIndex - 1;
            }
        }
    }

    public boolean delete(long value) {
        int i;
        int curElem = find(value);
        if(curElem == elNum)
            return false;
        for(i = curElem; i < elNum - 1; i++)
            arr[i] = arr[i + 1];
        elNum--;
        return true;
    }

    public void display() {
        for(int i = 0; i < elNum; i++)
            System.out.print(arr[i] + " ");
        System.out.println(" ");
    }

    public int getSize() {
        return elNum;
    }

    public void merge(long[] arr1, long[] arr2) {
        for(int i = 0; i < arr1.length; i++)
            insert(arr1[i]);
        for (int j = 0; j < arr2.length; j++)
            insert(arr2[j]);
    }
}
