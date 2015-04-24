package algorithms;

/**
 * Created by User on 22.04.2015.
 */
public class LowArray {
    private long[] longArr;

    public LowArray(int size) {
        longArr = new long[size];
    }

    public long getElem(int index) {
        return longArr[index];
    }

    public void setElem(int index, long value) {
       longArr[index] = value;
    }
}
