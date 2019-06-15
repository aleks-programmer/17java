package algorithms;

/**
 * Created by User on 22.04.2015.
 */
public class MainClass {
    public static void main(String[] args) {
        System.out.println("begin LowArray app");
        LowArray arr;
        arr = new LowArray(100);
        int size = 0;
        int i;
        arr.setElem(0, 77);
        arr.setElem(1, 99);
        arr.setElem(2, 44);
        arr.setElem(3, 55);
        arr.setElem(4, 22);
        arr.setElem(5, 88);
        arr.setElem(6, 11);
        arr.setElem(7, 00);
        arr.setElem(8, 66);
        arr.setElem(9, 33);
        size = 10;
        for(i = 0; i < size; i++)
            System.out.println(arr.getElem(i));
        System.out.println("");

        int searchKey = 26;
        for(i = 0; i < size; i++)
            if(searchKey == arr.getElem(i))
                break;
        if(size == i)
            System.out.println("Cannot find searchKey = " + searchKey);
        else
            System.out.println("Found searchKey = " + searchKey);

        int searchDelete = 55;
        for(i = 0; i < size; i++)
            if(searchDelete == arr.getElem(i))
                break;
        for(int k = i; k < size; k++)
            arr.setElem(k, arr.getElem(k + 1));
        size--;
        for(i = 0; i < size; i++)
            System.out.print( arr.getElem(i) + " ");
        System.out.println("end LowArray app");

        System.out.println("begin HighArray app");
        HighArray arrHigh;
        arrHigh = new HighArray(100);
        arrHigh.insert(10);
        arrHigh.insert(25);
        arrHigh.insert(78);
        arrHigh.insert(56);
        arrHigh.insert(56);
        arrHigh.insert(55);
        arrHigh.insert(78);
        arrHigh.display();
        int searchKeyHA = 26;
        boolean findOK = arrHigh.find(searchKeyHA);
        if(findOK)
            System.out.println("Found search key = " + searchKeyHA);
        else
            System.out.println("Cannot find search key = " + searchKeyHA);
        long maxEl = arrHigh.getMax();
        if(maxEl == -1)
            System.out.println("Array is empty");
        else
            System.out.println("Max element for array is " + maxEl);
        arrHigh.noDups();
        arrHigh.display();
//        boolean removeOK = arrHigh.removeMax();
//        if(removeOK)
//            System.out.println("Max element = " + maxEl + " is deleted for array");
//        else
//            System.out.println("Array is empty");
        long[] sortedArray = new long[arrHigh.getSize()];
        while(true) {
            long maxElSort = arrHigh.getMax();
            if (maxElSort == -1) {
                System.out.println("Array is empty");
                break;
            }
            else {
                sortedArray[arrHigh.getSize() - 1] = maxElSort;
                arrHigh.removeMax();
            }
        }
        arrHigh.display();
        for(int g = 0; g < sortedArray.length; g++)
            System.out.print(sortedArray[g] + " ");
        System.out.println(" sorted array");
        int deleteKey = 55;
        boolean deleteOK = arrHigh.delete(deleteKey);
        if(deleteOK)
            System.out.println("Deleted 'delete key' = " + deleteKey);
        else
            System.out.println("Cannot find 'delete key' = " + searchKeyHA);
        arrHigh.display();
        System.out.println("end HighArray app");

        System.out.println("begin OrderedArray app");
        OrderedArray orderedArray = new OrderedArray(20);
        orderedArray.insert(2);
        orderedArray.insert(3);
        orderedArray.insert(5);
        orderedArray.insert(4);
        orderedArray.display();
        int findKey = 16;
        int foundIndex = orderedArray.find(findKey);
        if(foundIndex != orderedArray.getSize())
            System.out.println("Found key = " + foundIndex + " for key value = " + findKey);
        else
            System.out.println("Cannot find key for key value = " + findKey);
        int deleteKeyForOrderedArr = 16;
        boolean deleteIDInOrderedArrOK = orderedArray.delete(deleteKeyForOrderedArr);
        if(deleteIDInOrderedArrOK)
            System.out.println("Deleted key value = " + deleteKeyForOrderedArr);
        else
            System.out.println("Cannot delete. Key value = " + deleteKeyForOrderedArr + " is not found");
        orderedArray.display();
        System.out.println("begin 2 arrays");
        long[] arr1 = new long[3];
        long[] arr2 = new long[3];
        for(int m = 0; m <arr1.length; m++) {
            while(true) {
                long temp = (long) (Math.random() * 100);
                if (!checkForDuplicate(arr1, temp)) {
                    arr1[m] = temp;
                    break;
                }
            }

            System.out.print(arr1[m] + " ");
        }
        System.out.println(" ");
        for(int n = 0; n <arr2.length; n++) {
            while(true) {
                long temp = (long) (Math.random() * 100);
                if (!checkForDuplicate(arr2, temp)) {
                    arr2[n] = temp;
                    break;
                }
            }

            System.out.print(arr2[n] + " ");
        }
        System.out.println(" ");
        OrderedArray resultArray = new OrderedArray(arr1.length + arr2.length);
        resultArray.merge(arr1, arr2);
        resultArray.display();
        System.out.println("end 2 arrays");
        System.out.println("end OrderedArray app");

        System.out.println("begin BubbleSort app");
        ArrayBub arrayBub = new ArrayBub(10);
        arrayBub.insert(19); arrayBub.insert(66); arrayBub.insert(3); arrayBub.insert(68); arrayBub.insert(7);
        arrayBub.display();
        arrayBub.bubbleSort();
        arrayBub.display();
        System.out.println("end BubbleSort app");

        System.out.println("begin SelSort app");
        ArraySel arraySel = new ArraySel(10);
        arraySel.insert(19); arraySel.insert(66); arraySel.insert(3); arraySel.insert(68); arraySel.insert(7);
        arraySel.display();
        arraySel.selectionSort();
        arraySel.display();
        System.out.println("end SelSort app");

        System.out.println("begin CharWord app");
        char[] arrCh1 = new char[10];
        char[] arrCh2 = new char[10];
        arrCh1[0] = 'c'; arrCh1[1] = 'g'; arrCh1[2] = 'c'; arrCh1[3] = 'a'; arrCh1[4] = 'b'; arrCh1[5] = 'c'; arrCh1[6] = 'd';
        arrCh2[0] = 'b'; arrCh2[1] = 'a'; arrCh2[2] = 'c'; arrCh2[3] = 'c'; arrCh2[4] = 'c'; arrCh2[5] = 'g';
        boolean isWordValid = CharWord.isWordValid(arrCh2,arrCh1);
        if(isWordValid)
            System.out.println("Word Valid");
        else
            System.out.println("Word Not Valid");
        System.out.println("end CharWord app");

        System.out.println("begin ArrInsObj app");
        ArrayInsObject arrayInsObject = new ArrayInsObject(10);
        arrayInsObject.insert("Vasya", "Pupkin", 25);
        arrayInsObject.insert("Vasya", "Koshechkin", 30);
        arrayInsObject.insert("Vasya", "Gromov", 28);
        arrayInsObject.insert("Vasya", "Petrov", 45);
        arrayInsObject.insert("Vasya", "Smirnov", 20);
        arrayInsObject.display();
        arrayInsObject.insertionSort();
        arrayInsObject.display();
        System.out.println("end ArrInsObj app");

        System.out.println("begin towers of Hanoi app");
        doTowers(3, 'A', 'B', 'C');
        System.out.println("end towers of Hanoi app");

    }

    private static boolean checkForDuplicate(long[] array, long value) {
        for (long anArray : array)
            if (anArray == value)
                return true;
        return false;
    }

    public static void doTowers(int topN, char from, char inter, char to) {
        if (topN == 1) {
            System.out.println("Disc 1 from " + from + " to " + to);
        } else {
            doTowers(topN - 1, from, to, inter); // from -> inter
            System.out.println("Disc " + topN + " from " + from + " to " + to);
            doTowers(topN - 1, inter, from, to); // inter -> to
        }
    }
}
