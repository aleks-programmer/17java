package algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

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
        for (i = 0; i < size; i++)
            System.out.println(arr.getElem(i));
        System.out.println("");

        int searchKey = 26;
        for (i = 0; i < size; i++)
            if (searchKey == arr.getElem(i))
                break;
        if (size == i)
            System.out.println("Cannot find searchKey = " + searchKey);
        else
            System.out.println("Found searchKey = " + searchKey);

        int searchDelete = 55;
        for (i = 0; i < size; i++)
            if (searchDelete == arr.getElem(i))
                break;
        for (int k = i; k < size; k++)
            arr.setElem(k, arr.getElem(k + 1));
        size--;
        for (i = 0; i < size; i++)
            System.out.print(arr.getElem(i) + " ");
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
        if (findOK)
            System.out.println("Found search key = " + searchKeyHA);
        else
            System.out.println("Cannot find search key = " + searchKeyHA);
        long maxEl = arrHigh.getMax();
        if (maxEl == -1)
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
        while (true) {
            long maxElSort = arrHigh.getMax();
            if (maxElSort == -1) {
                System.out.println("Array is empty");
                break;
            } else {
                sortedArray[arrHigh.getSize() - 1] = maxElSort;
                arrHigh.removeMax();
            }
        }
        arrHigh.display();
        for (int g = 0; g < sortedArray.length; g++)
            System.out.print(sortedArray[g] + " ");
        System.out.println(" sorted array");
        int deleteKey = 55;
        boolean deleteOK = arrHigh.delete(deleteKey);
        if (deleteOK)
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
        if (foundIndex != orderedArray.getSize())
            System.out.println("Found key = " + foundIndex + " for key value = " + findKey);
        else
            System.out.println("Cannot find key for key value = " + findKey);
        int deleteKeyForOrderedArr = 16;
        boolean deleteIDInOrderedArrOK = orderedArray.delete(deleteKeyForOrderedArr);
        if (deleteIDInOrderedArrOK)
            System.out.println("Deleted key value = " + deleteKeyForOrderedArr);
        else
            System.out.println("Cannot delete. Key value = " + deleteKeyForOrderedArr + " is not found");
        orderedArray.display();
        System.out.println("begin 2 arrays");
        long[] arr1 = new long[3];
        long[] arr2 = new long[3];
        for (int m = 0; m < arr1.length; m++) {
            while (true) {
                long temp = (long) (Math.random() * 100);
                if (!checkForDuplicate(arr1, temp)) {
                    arr1[m] = temp;
                    break;
                }
            }

            System.out.print(arr1[m] + " ");
        }
        System.out.println(" ");
        for (int n = 0; n < arr2.length; n++) {
            while (true) {
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
        arrayBub.insert(19);
        arrayBub.insert(66);
        arrayBub.insert(3);
        arrayBub.insert(68);
        arrayBub.insert(7);
        arrayBub.display();
        arrayBub.bubbleSort();
        arrayBub.display();
        System.out.println("end BubbleSort app");

        System.out.println("begin SelSort app");
        ArraySel arraySel = new ArraySel(10);
        arraySel.insert(19);
        arraySel.insert(66);
        arraySel.insert(3);
        arraySel.insert(68);
        arraySel.insert(7);
        arraySel.display();
        arraySel.selectionSort();
        arraySel.display();
        System.out.println("end SelSort app");

        System.out.println("begin CharWord app");
        char[] arrCh1 = new char[10];
        char[] arrCh2 = new char[10];
        arrCh1[0] = 'c';
        arrCh1[1] = 'g';
        arrCh1[2] = 'c';
        arrCh1[3] = 'a';
        arrCh1[4] = 'b';
        arrCh1[5] = 'c';
        arrCh1[6] = 'd';
        arrCh2[0] = 'b';
        arrCh2[1] = 'a';
        arrCh2[2] = 'c';
        arrCh2[3] = 'c';
        arrCh2[4] = 'c';
        arrCh2[5] = 'g';
        boolean isWordValid = CharWord.isWordValid(arrCh2, arrCh1);
        if (isWordValid)
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

        System.out.println("begin mergesort");
        int[] a = {1, 8, 9};
        int[] b = {10, 88, 99};
        int[] c = new int[6];
        merge(a, a.length, b, b.length, c);
        for (int i1 : c) {
            System.out.println(i1);
        }

        System.out.println("end mergesort");

        System.out.println(1 + 1);


        class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }
        class ListNode {
            int val;
            ListNode next;

            ListNode(int x) {
                val = x;
                next = null;
            }
        }

        class Solution {
            public boolean isBalanced(TreeNode root) {
                if (root == null) return true;

                TreeNode l = root.left;
                TreeNode r = root.right;

                int lDepth = traverse(l);
                int rDepth = traverse(r);
                int differ = lDepth - rDepth;
                return Math.abs(differ) <= 1 && isBalanced(l) && isBalanced(r);
            }

            public int traverse(TreeNode root) {
                if (root == null) return 0;

                return 1 + Math.max(traverse(root.left), traverse(root.right));
            }

            public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
                ListNode prevA = null;
                ListNode prevB = null;
                boolean end = false;

                while (true) {
                    if (end) {
                        if (headA == headB) {
                            if (prevA == null && prevB == null) return headA;
                            else if (prevA == null) return headB;
                            else if (prevB == null) return headA;
                            else {
                                headA = prevA;
                                headB = prevB;
                            }


                        }
                    } else {
                        if (headA.next == null && headB.next == null) {
                            end = true;
                            if (headA == headB) {
                                headA = prevA;
                                headB = prevB;

                            } else return null;
                        } else if (headA.next == null) {
                            prevB = headB;
                            headB = headB.next;
                        } else if (headB.next == null) {
                            prevA = headA;
                            headA = headA.next;
                        } else {
                            prevA = headA;
                            headA = headA.next;
                            prevB = headB;
                            headB = headB.next;
                        }
                    }


                }
            }

            public int[] twoSum(int[] numbers, int target) {
                if (numbers.length == 0) return new int[0];
                if (numbers.length == 1) return new int[0];

                int start = 0;
                int end = numbers.length - 1;

                while (true) {
                    int median = (start + end) / 2;

                    if (start == numbers.length) return new int[0];
                    if (end == -1) return new int[0];

                    if (start > end) {
                        start = 0;
                        if (end == 0) return new int[0];
                        else break;
                    }

                    if (numbers[median] == target) {
                        start = 0;
                        end = median - 1;
                        if (end == 0) return new int[0];
                        else break;
                    } else if (numbers[median] < target) {
                        start = median + 1;
                    } else if (numbers[median] > target) {
                        end = median - 1;
                    }
                }

                int[] result = new int[2];

                int prev = numbers[start];


                for (int i = start + 1; i <= end; i++) {
                    int sum = prev + numbers[i];

                    if (sum == target) {
                        result[0] = i;
                        result[1] = i + 1;

                        return result;
                    }

                    if (prev > 0) {
                        int offset = i - 2;
                        while (offset >= 0) {
                            sum = numbers[offset] + numbers[i];

                            if (sum == target) {
                                result[0] = i;
                                result[1] = i + 1;

                                return result;
                            }
                            offset--;
                        }
                    }

                    prev = numbers[i];
                }

                return new int[0];
            }

            public String convertToTitle(int n) {
                StringBuilder sb = new StringBuilder();
                do {
                    int bit1 = n % 26;
                    int bit2 = n / 26;
                    if (bit1 == 0) {
                        bit1 += 26;
                        bit2--;
                    }
                    sb.append((char) (bit1 + 64));
                    n = bit2;
                } while (n > 0);
                return sb.reverse().toString();
            }

        }
        class MinStack {

            /** initialize your data structure here. */
            LinkedList<Integer> stack = new LinkedList();
            int min;

            public MinStack() {

            }

            public void push(int x) {
                if (stack.size() == 0) min = x;
                stack.addFirst(x);
                if (x < min) min = x;

            }

            public void pop() {
                int removed = stack.removeFirst();
                if (removed == min) {
                    ListIterator<Integer> iterator = stack.listIterator();
                    min = iterator.hasNext() ? iterator.next() : 0;

                    while (iterator.hasNext()) {
                        Integer element = iterator.next();
                        if (element < min) min = element;
                    }
                }
            }

            public int top() {
                return stack.getFirst();
            }

            public int getMin() {
                return min;
            }
        }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);


        Solution solution = new Solution();
        boolean balanced = solution.isBalanced(root);
        System.out.println(balanced);
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.top();
        minStack.getMin();
        minStack.pop();

        ListNode listNode1 = new ListNode(4);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(8);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(5);
        ListNode listNode7 = new ListNode(0);
        ListNode listNode8 = new ListNode(1);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode3;
//        ListNode intersectionNode = solution.getIntersectionNode(listNode1, listNode6);
//        System.out.println(intersectionNode.val);

        int[] arr3 = new int[2];
        int target = -1;

        arr3[0] = -1;
        arr3[1] = 0;

        int[] ints = solution.twoSum(arr3, target);
        System.out.println("ints: " + Arrays.toString(ints));

        System.out.println(solution.convertToTitle(27));

        char[] chars = "BBB".toCharArray();
        int n = 0;
        int j = 0;
        for (int i1 = chars.length - 1; i1 >= 0; i1--) {
            n += Math.pow(26, i1) * (chars[j] - 64);
            j++;
        }

        System.out.println(n);

        quickSort();
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

    public static void merge(int[] arrayA, int sizeA, int[] arrayB, int sizeB, int[] arrayC) {
        int aDex = 0, bDex = 0, cDex = 0;

        while (aDex < sizeA && bDex < sizeB) {
            if (arrayA[aDex] < arrayB[bDex]) {
                arrayC[cDex++] = arrayA[aDex++];
            } else {
                arrayC[cDex++] = arrayB[bDex++];
            }
        }
        while (aDex < sizeA) {

            arrayC[cDex++] = arrayA[aDex++];

        }
        while (bDex < sizeB) {

            arrayC[cDex++] = arrayB[bDex++];

        }


    }

    public static void quickSort() {
        int[] arr = {1, 5, 3, 4, -1, 100, 99, -500, -350, -520, 1000, 999, 630, 752, 777};
        recQuickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void recQuickSort(int[] arr, int start, int end) {
        if ((end - start) <= 0) {
            return;
        } else {
            int median = arr[end];
            int i = partitionIt(arr, start, end, median);
            recQuickSort(arr, start, i - 1);
            recQuickSort(arr, i + 1, end);
        }
    }

    private static int partitionIt(int[] arr, int start, int end, int median) {
        int i = start - 1;
        int j = end;

        while (true) {
            while (arr[++i] < median) {
            }
            while (--j >= 0 && arr[j] > median) {
            }

            if (i >= j) {
                int temp = arr[end];
                arr[end] = arr[i];
                arr[i] = temp;
                return i;
            }

            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
    }
}
