package algorithms;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by User on 22.04.2015.
 */
public class MainClass {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    interface Node {
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    static class StringListNode {
        char val;
        StringListNode next;

        StringListNode() {
        }

        StringListNode(char x) {
            val = x;
            next = null;
        }
    }

    static class GraphNode {
        List<GraphNode> children = new ArrayList<>();
        int val;
        boolean visited;

        GraphNode(int i) {
            this.val = i;
        }
    }

    static class SodaBottle {
        boolean poisoned;

        SodaBottle(boolean poisoned) {
            this.poisoned = poisoned;
        }
    }

    static class JigsawPuzzle {
        private JigsawPuzzleEdge[][] edges;
        private int edgesSize;

        public JigsawPuzzle(int edgesSize) {
            edges = new JigsawPuzzleEdge[edgesSize][edgesSize];
            this.edgesSize = edgesSize;
        }

        public void addEdgeToPuzzle(JigsawPuzzleEdge edge, int posX, int posY) {
            if (posX < 0 || posX > edgesSize) throw new IllegalArgumentException("Pos X has wrong value!");
            if (posY < 0 || posY > edgesSize) throw new IllegalArgumentException("Pos Y has wrong value!");

            edges[posX][posY] = edge;
            edge.setPosX(posX);
            edge.setPosY(posY);
        }

        public boolean fitsWidth(JigsawPuzzleEdge edge1, JigsawPuzzleEdge edge2) {
            int edge1PosX = edge1.getPosX();
            int edge1PosY = edge1.getPosY();
            int edge2PosX = edge2.getPosX();
            int edge2PosY = edge2.getPosY();

            if (edge1PosX == edge2PosX) {
                int negationAbs = Math.abs(edge1PosY - edge2PosY);
                if (negationAbs == 1) {
                    return true;
                }
            }

            if (edge1PosY == edge2PosY) {
                int negationAbs = Math.abs(edge1PosX - edge2PosX);
                if (negationAbs == 1) {
                    return true;
                }
            }

            return false;
        }
    }

    static class JigsawPuzzleEdge {
        private int id;
        private int posX;
        private int posY;

        public JigsawPuzzleEdge(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public int getPosX() {
            return posX;
        }

        public void setPosX(int posX) {
            this.posX = posX;
        }

        public int getPosY() {
            return posY;
        }

        public void setPosY(int posY) {
            this.posY = posY;
        }
    }

    static class CircularArray<T> extends AbstractQueue<T> {
        private final Object[] array;
        private int size = 10;
        private int firstPos = 0;
        private int lastPos = -1;
        private int count = 0;

        public CircularArray() {
            this.array = new Object[size];
        }

        @Override
        public Iterator<T> iterator() {
            return new Itr<>();
        }

        @Override
        public int size() {
            return count;
        }

        public CircularArray(int size) {
            this.size = size;
            this.array = new Object[size];
        }


        @Override
        public boolean offer(T t) {
            int newPos;
            if (lastPos == (size - 1)) {
                newPos = 0;
            } else {
                newPos = lastPos + 1;
            }
            if (array[newPos] == null) {
                array[newPos] = t;
                lastPos = newPos;
                count++;
                return true;
            }

            return false;
        }

        @Override
        public T poll() {
            if (array[firstPos] != null) {
                T removed = (T) array[firstPos];
                array[firstPos] = null;
                if (array[firstPos + 1] != null) {
                    firstPos++;
                }
                count--;

                return removed;
            }

            return null;
        }

        @Override
        public T peek() {
            return (T) array[firstPos];
        }

        private class Itr<T> implements Iterator<T> {
            private int next = firstPos;

            @Override
            public boolean hasNext() {
                return array[next] != null;
            }

            @Override
            public T next() {
                if (array[next] == null) {
                    throw new NoSuchElementException();
                }

                T nextElement = (T) array[next];
                next = next + 1;

                if (next == size) {
                    next = 0;
                }

                return nextElement;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }

    static class Minesweeper {
        private final MinesweeperCell[][] array;
        private final int size;

        public Minesweeper(int size) {
            this.size = size;
            array = new MinesweeperCell[size][size];
        }

        public void play() {
            int cellCount = (int) Math.pow(size, 2);
            int offset = new Random().nextInt(cellCount - 1);
            int minesCount = new Random().nextInt(cellCount);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (minesCount > 0 && offset-- == 0) {
                        array[i][j] = new MinesweeperCell(true, i, j);
                        offset = new Random().nextInt(cellCount - (i > 0 ? (((i - 1) * size) + j) : j)) - 1;
                    } else {
                        array[i][j] = new MinesweeperCell(i, j);
                    }
                }
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    MinesweeperCell minesweeperCell = array[i][j];
                    if (!minesweeperCell.isMine()) {
                        int xAdj;
                        int yAdj;
                        int minesCellCount = 0;
                        for (int w = 0; w < 8; w++) {
                            if (w == 0) {
                                xAdj = i - 1;
                                yAdj = j;
                            } else if (w == 1) {
                                xAdj = i - 1;
                                yAdj = j + 1;
                            } else if (w == 2) {
                                xAdj = i;
                                yAdj = j + 1;
                            } else if (w == 3) {
                                xAdj = i + 1;
                                yAdj = j + 1;
                            } else if (w == 4) {
                                xAdj = i + 1;
                                yAdj = j;
                            } else if (w == 5) {
                                xAdj = i + 1;
                                yAdj = j - 1;
                            } else if (w == 6) {
                                xAdj = i;
                                yAdj = j - 1;
                            } else {
                                xAdj = i - 1;
                                yAdj = j - 1;
                            }
                            if (xAdj >= 0 && yAdj >= 0 && xAdj < size && yAdj < size && array[xAdj][yAdj].isMine()) {
                                minesCellCount++;
                            }
                        }

                        if (minesCellCount > 0) {
                            minesweeperCell.setMinesCount(minesCellCount);
                        }
                    }
                }
            }
        }

        public Set<MinesweeperCell> expose(int x, int y) {
            Set<MinesweeperCell> result = new HashSet<>();
            Stack<MinesweeperCell> temp = new Stack<>();

            MinesweeperCell minesweeperCell = array[x][y];
            if (minesweeperCell.isMine()) {
                throw new RuntimeException("Game lost");
            }

            if (minesweeperCell.getMinesCount() > 0) {
                return Collections.singleton(minesweeperCell);
            } else {
                temp.push(array[x][y]);
                while (!temp.isEmpty()) {
                    MinesweeperCell pop = temp.pop();
                    int xTemp = pop.x;
                    int yTemp = pop.y;
                    for (int w = 0; w < 4; w++) {
                        int xAdj;
                        int yAdj;
                        if (w == 0) {
                            xAdj = x - 1;
                            yAdj = yTemp;
                        } else if (w == 1) {
                            xAdj = xTemp;
                            yAdj = yTemp + 1;
                        } else if (w == 2) {
                            xAdj = xTemp + 1;
                            yAdj = yTemp;
                        } else {
                            xAdj = xTemp - 1;
                            yAdj = yTemp - 1;
                        }

                        MinesweeperCell adjacentCell = array[xAdj][yAdj];
                        if (!adjacentCell.isMine() && adjacentCell.getMinesCount() == 0 && !result.contains(adjacentCell)) {
                            temp.push(adjacentCell);
                            result.add(adjacentCell);
                        }
                    }
                }
            }

            return result;
        }
    }

    static class MinesweeperCell {
        private boolean mine;
        private int minesCount;
        private int x;
        private int y;

        public MinesweeperCell(boolean mine, int x, int y) {
            this.mine = mine;
            this.x = x;
            this.y = y;
        }

        public MinesweeperCell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isMine() {
            return mine;
        }

        public int getMinesCount() {
            return minesCount;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setMinesCount(int minesCount) {
            this.minesCount = minesCount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MinesweeperCell)) return false;
            MinesweeperCell that = (MinesweeperCell) o;
            return mine == that.mine && minesCount == that.minesCount && x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(mine, minesCount, x, y);
        }
    }

    static class FileSystem {
        private List<FSItem> items = new ArrayList<>();

        public List<FSItem> search(String name) {
            LinkedList<FSItem> ll = new LinkedList<>();
            List<FSItem> result = new ArrayList<>();

            if (items.isEmpty()) return items;

            for (FSItem item : items) {
                ll.offer(item);
            }

            while (!ll.isEmpty()) {
                FSItem poll = ll.poll();
                if (name.equals(poll.getName())) {
                    result.add(poll);
                }
                if (poll instanceof Dir) {
                    for (FSItem item : ((Dir) poll).items) {
                        ll.offer(item);
                    }
                }
            }

            return result;
        }

        public List<FSItem> getItems() {
            return items;
        }

        public void setItems(List<FSItem> items) {
            this.items = items;
        }
    }

    static class Dir implements FSItem {
        private List<FSItem> items = new ArrayList<>();
        private String name;

        public Dir(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        public List<FSItem> getItems() {
            return items;
        }

        public void setItems(List<FSItem> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "Dir{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class File implements FSItem {
        private String name;

        public File(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "File{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    interface FSItem {
        String getName();
    }

    static class HashTable<K, V> {
        private final float loadFactor;
        private Node<K, V>[] hashArray;
        private int size;
        private int capacity;

        public HashTable() {
            capacity = 10;
            hashArray = (Node<K, V>[]) new Node[capacity];
            loadFactor = 0.75f;
        }

        public void put(K key, V value) {
            int hash = key.hashCode();

            if (((double) size / capacity) >= loadFactor) {
                int newCapacity = (int) (capacity * 1.5);
                Node<K, V>[] newHashArrayTemp = (Node<K, V>[]) new Node[newCapacity];
                System.arraycopy(hashArray, 0, newHashArrayTemp, 0, size);
                hashArray = newHashArrayTemp;
                capacity = newCapacity;
            }

            int bucketIndex = (capacity - 1) & hash;
            Node<K, V> kvNode = hashArray[bucketIndex];
            if (kvNode == null) {
                hashArray[bucketIndex] = new Node<>(hash, key, value);
                size++;
            } else {
                while (kvNode != null) {
                    if (kvNode.next == null) {
                        kvNode.next = new Node<>(hash, key, value);
                        size++;
                        return;
                    }
                    kvNode = kvNode.next;
                }
            }
        }

        public void remove(K key) {
            int hash = key.hashCode();
            Node<K, V> kvNode = getNode(hash);
            Node<K, V> kvNodeTemp = kvNode;
            Node<K, V> kvNodePrev = null;

            if (kvNodeTemp != null && kvNodeTemp.next == null && kvNodeTemp.key == key) {
                hashArray[(capacity - 1) & hash] = null;
                size--;
                return;
            }

            while (kvNodeTemp != null) {
                if (kvNodeTemp.key == key) {
                    if (kvNodePrev != null) {
                        kvNodePrev.next = kvNodeTemp.next;
                        size--;
                        return;
                    } else {
                        hashArray[(capacity - 1) & hash] = kvNodeTemp.next;
                        size--;
                        return;
                    }
                }
                kvNodePrev = kvNodeTemp;
                kvNodeTemp = kvNodeTemp.next;
            }
        }

        public V get(K key) {
            int hash = key.hashCode();
            Node<K, V> kvNode = getNode(hash);
            Node<K, V> kvNodeTemp = kvNode;
            while (kvNodeTemp != null) {
                if (kvNodeTemp.key == key) {
                    return kvNodeTemp.value;
                }
                kvNodeTemp = kvNodeTemp.next;
            }

            return null;
        }

        private Node<K, V> getNode(int hash) {
            return hashArray[(capacity - 1) & hash];
        }

        private static class Node<K, V> {
            private int hash;
            private K key;
            private V value;
            private Node<K, V> next;

            public Node() {
            }

            public Node(int hash, K key, V value) {
                this.hash = hash;
                this.key = key;
                this.value = value;
            }

            public int getHash() {
                return hash;
            }

            public void setHash(int hash) {
                this.hash = hash;
            }

            public K getKey() {
                return key;
            }

            public void setKey(K key) {
                this.key = key;
            }

            public V getValue() {
                return value;
            }

            public void setValue(V value) {
                this.value = value;
            }

            public Node<K, V> getNext() {
                return next;
            }

            public void setNext(Node<K, V> next) {
                this.next = next;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "hash=" + hash +
                        ", key=" + key +
                        ", value=" + value +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "HashTable{" +
                    "hashArray=" + Arrays.toString(hashArray) +
                    '}';
        }

        public int size() {
            return size;
        }
    }

    static int tripleStep(int steps) {
        return tripleStepRecursive(steps, new int[steps + 1]);
    }

    static int tripleStepRecursive(int n, int[] mem) {
        if (n < 0) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        if (mem[n] == 0) {
            mem[n] = tripleStepRecursive(n - 1, mem) + tripleStepRecursive(n - 2, mem) + tripleStepRecursive(n - 3,
                    mem);
        }

        return mem[n];
    }

    static int magicIndex(int[] arr) {
        for (int i = 0, arrLength = arr.length; i < arrLength; i++) {
            if (arr[i] == i) {
                return i;
            }
        }

        return -1;
    }

    static List<List<Integer>> powerSet(List<Integer> list) {
        List<List<Integer>> powerSet;
        if (list.size() > 1) {
            powerSet = powerSetRec(list, null);
        } else {
            powerSet = new ArrayList<>();
        }

        powerSet.add(Collections.<Integer>emptyList());
        for (Integer integer : list) {
            powerSet.add(Collections.singletonList(integer));
        }

        return powerSet;
    }

    static List<List<Integer>> powerSetRec(List<Integer> subList, Integer prev) {
        List<Integer> subListTemp = new ArrayList<>(subList);

        if (subList.size() == 2) {
            List<List<Integer>> result = new ArrayList<>();
            if (prev != null) {
                for (Integer integer : subList) {
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(integer);
                    integers.add(prev);
                    result.add(integers);
                }

                result.add(subListTemp);
            }

            return result;
        }

        List<List<Integer>> powerSet = powerSetRec(subList.subList(0, subList.size() - 1),
                subList.get(subList.size() - 1));

        if (prev != null) {
            List<List<Integer>> powerSetTemp = new ArrayList<>();
            for (List<Integer> powerSetList : powerSet) {
                List<Integer> subListTemp1 = new ArrayList<>(powerSetList);
                subListTemp1.add(prev);
                powerSetTemp.add(subListTemp1);
            }

            powerSet.addAll(powerSetTemp);
        }

        return powerSet;
    }

    public static int recursiveMultiply(int a, int b) {
        int smaller = a < b ? a : b;
        int bigger = a >= b ? a : b;

        return recursiveMultiplyRec(smaller, bigger);
    }

    public static int recursiveMultiplyRec(int smaller, int bigger) {
        if (smaller == 0) {
            return 0;
        } else if (smaller == 1) {
            return bigger;
        }

        int d = smaller >> 1;
        int half = recursiveMultiplyRec(d, bigger);

        if (smaller % 2 == 1) {
            return half + half + bigger;
        } else {
            return half + half;
        }
    }

    public static void towersOfHanoi(Stack<Integer> source, Stack<Integer> destination, Stack<Integer> buffer,
     int topN) {
        if (topN == 1) {
            destination.push(source.pop());
            return;
        } else if (topN == 2) {
            towersOfHanoi(source, buffer, null, topN - 1);
            towersOfHanoi(source, destination, null, topN - 1);
            towersOfHanoi(buffer, destination, null, topN - 1);
            return;
        }

        towersOfHanoi(source, buffer, destination, topN - 1);
        towersOfHanoi(source, destination, null, 1);
        towersOfHanoi(buffer, destination, source, topN - 1);
    }

    public static int permutationsWithoutDups(String str) {
        if (str == null || str.length() == 0) return 0;
        if (str.length() == 1) return 1;

        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (Character character : set) {
            stringBuilder.append(character);
        }

        String s = stringBuilder.toString();

        return permutationsRec(s, s.length()).size();
    }

    public static int permutationsWithDups(String str) {
        if (str == null || str.length() == 0) return 0;
        if (str.length() == 1) return 1;

        return permutationsRec(str, str.length()).size();
    }

    public static Set<String> permutationsRec(String str, int count) {
        Set<String> result = new HashSet<>();

        if (count == 2) {
            result.add(str.substring(0, 2));
            result.add("" + str.charAt(1) + str.charAt(0));

            return result;
        }

        Set<String> strings = permutationsRec(str, count - 1);
        for (String string : strings) {
            char[] charArray = string.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                result.add(string.substring(0, i) + str.charAt(count - 1) + string.substring(i));

                if (i == (charArray.length - 1)) {
                    result.add(string + str.charAt(count - 1));
                }
            }
        }

        return result;
    }

    public static Set<String> parens(int n) {
        if (n == 0) {
            return new HashSet<>();
        }

        if (n == 1) {
            Set<String> strs = new HashSet<>();
            strs.add("()");

            return strs;
        }

        Set<String> result = new HashSet<>();

        Set<String> parens = parens(n - 1);
        for (String p : parens) {
            int prev = -1;
            for (int i = 0; i < p.length(); i++) {
                if (prev >= 0 && p.charAt(prev) == '(' && p.charAt(i) == ')') {
                    result.add(p.substring(0, i + 1) + "()" + p.substring(i + 1));
                    result.add(p.substring(0, prev) + "(" + p.substring(prev, i + 1) + ")" + p.substring(i + 1));
                }

                prev = i;
            }
        }

        return result;
    }

    public static void paintFill(String[][] colors, String newColor, Point point) {
        paintFillRec(colors, newColor, point, 0);
    }

    public static void paintFillRec(String[][] colors, String newColor, Point point, int xIndex) {
        if (xIndex == colors.length) {
            return;
        }

        for (int i = 0; i < colors[xIndex].length; i++) {
            if (point.x != xIndex || point.y != i) {
                colors[xIndex][i] = newColor;
            }
        }

        paintFillRec(colors, newColor, point, xIndex + 1);
    }

    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static int coins(int n) {
        if (n <= 0) return 0;

        return coinsRec(n, new int[n + 1]);
    }

    public static int coinsRec(int n, int[] mem) {
        if (n < 0) return 0;
        if (n == 0) return 1;

        if (mem[n] == 0) {
            mem[n] = coinsRec(n - 1, mem) + coinsRec(n - 5, mem) + coinsRec(n - 10, mem) + coinsRec(n - 25, mem);
        }

        return mem[n];
    }

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
            private LinkedList<Integer> linkedList = new LinkedList<>();
            private LinkedList<Integer> minList = new LinkedList<>();

            public void push(Integer i) {
                if (minList.isEmpty()) {
                    minList.push(i);
                } else {
                    Integer peek = minList.peek();
                    if (i < peek) {
                        minList.push(i);
                    }
                }
                linkedList.push(i);
            }

            public Integer pop() {
                Integer pop = linkedList.pop();
                Integer peek = minList.peek();
                if (pop.equals(peek)) {
                    minList.pop();
                }
                return pop;
            }

            public Integer min() {
                return minList.peek();
            }
        }

        class SetOfStacks {
            private Map<Integer, Integer[]> map = new HashMap<>();
            private Map<Integer, Integer> countMap = new HashMap<>();
            private int stackCount;
            private int stackCapacity = 10;

            public void push(Integer i) {
                if (map.isEmpty()) {
                    stackCount++;
                    countMap.put(stackCount, 0);
                }
                if (countMap.get(stackCount) == stackCapacity) {
                    countMap.put(++stackCount, 0);
                }
                Integer[] integers = map.get(stackCount);
                if (integers == null) {
                    integers = new Integer[stackCapacity];
                }
                Integer countInStack = countMap.get(stackCount);

                integers[countInStack++] = i;
                countMap.put(stackCount, countInStack);
                map.put(stackCount, integers);
            }

            public Integer pop() {
                if (map.isEmpty()) {
                    return null;
                }

                if (!map.containsKey(stackCount)) {
                    int i = stackCount - 1;
                    while (i >= 0) {
                        if (map.containsKey(i)) {
                            stackCount = i;
                            break;
                        }
                        i--;
                    }
                }

                Integer countInStack = countMap.get(stackCount);
                Integer integer = map.get(stackCount)[--countInStack];
                map.get(stackCount)[countInStack] = null;

                if (countInStack == 0) {
                    countMap.remove(stackCount);
                    map.remove(stackCount--);
                } else {
                    countMap.put(stackCount, countInStack);
                }

                return integer;
            }

            public Integer popAt(int index) {
                if (map.isEmpty()) {
                    return null;
                }

                if (!map.containsKey(index)) {
                    int i = index + 1;
                    while (i <= stackCount) {
                        if (map.containsKey(i)) {
                            index = i;
                            break;
                        }
                        i++;
                    }
                }

                Integer countInStack = countMap.get(index);
                if (countInStack == null) return null;

                Integer integer = map.get(index)[--countInStack];
                map.get(index)[countInStack] = null;
                if (countInStack == 0) {
                    countMap.remove(index);
                    map.remove(index);
                } else {
                    countMap.put(index, countInStack);
                }

                return integer;
            }
        }


        class ThreeInOneStack {
            private int size = 10;
            private int[] arr = new int[size];
            private int count = 0;
            private int stackCount = 3;
            private int[] top = new int[stackCount];
            private int[] countInStack = new int[stackCount];

            public ThreeInOneStack() {
                top[0] = 0;
                top[1] = size / stackCount;
                top[2] = (2 * size) / stackCount;
            }

            public int pop(int stackNumber) {
                if (isEmpty(stackNumber)) throw new EmptyStackException();

                int indexTop = top[stackNumber - 1];
                int index = countInStack[stackNumber - 1] - 1;
                index = indexTop + index;
                if (index > 9) {
                    index = index - 10;
                }
                int value = arr[index];


                arr[index] = 0;
                count--;
                countInStack[stackNumber - 1]--;
                return value;
            }

            public void push(int i, int stackNumber) {
                if (isFull()) throw new StackOverflowError();

                int indexTop = top[stackNumber - 1];
                int index = countInStack[stackNumber - 1];
                int indexToAdd = indexTop + index;
                if (indexToAdd > 9) {
                    indexToAdd = indexToAdd - 10;
                }

                int indexTo = indexToAdd;

                for (int i2 = 0; i2 < top.length; i2++) {
                    if (i2 == stackNumber - 1) break;
                    int i1 = top[i2];
                    if (i1 == indexTo) {
                        top[i2]++;
                        int i3 = countInStack[i2];
                        if (i3 > 0) {
                            indexTo = i2 + i3;
                        } else break;
                    }
                }

                if (indexTo > indexToAdd) {
                    System.arraycopy(arr, indexToAdd, arr, indexToAdd + 1, indexTo - indexToAdd);
                }


                arr[indexToAdd] = i;
                count++;
                countInStack[stackNumber - 1]++;
            }

            public int peek(int stackNumber) {
                int indexTop = top[stackNumber - 1];
                int index = countInStack[stackNumber - 1] - 1;
                index = indexTop + index;
                if (index > 9) {
                    index = index - 10;
                }
                return arr[index];
            }

            public boolean isEmpty(int stackNumber) {
                return countInStack[stackNumber - 1] == 0;
            }

            public boolean isFull() {
                return count == size;
            }
        }


        class MyQueue {
            private Stack<Integer> stack = new Stack<>();
            private Stack<Integer> queue = new Stack<>();

            public boolean offer(Integer e) {
                stack.push(e);
                return true;
            }

            public Integer poll() {
                if (stack.isEmpty() && queue.isEmpty()) return null;

                if (queue.isEmpty()) {
                    while (stack.size() > 1) {
                        queue.push(stack.pop());
                    }
                    return stack.pop();
                } else {
                    return queue.pop();
                }
            }
        }

        class SortedStack {
            private LinkedList<Integer> linkedList = new LinkedList<>();
            private LinkedList<Integer> sortedLinkedList = new LinkedList<>();

            public void push(Integer i) {
                linkedList.push(i);
            }

            public Integer pop() {
                if (linkedList.isEmpty() && sortedLinkedList.isEmpty()) return null;

                if (sortedLinkedList.isEmpty()) {
                    if (linkedList.size() == 1) return linkedList.pop();
                    sortedLinkedList.push(linkedList.pop());
                    while (linkedList.size() > 0) {
                        Integer pop = linkedList.pop();
                        int sortedPeekMoveCount = 0;
                        while (!sortedLinkedList.isEmpty()) {
                            Integer sortedPeek = sortedLinkedList.peek();
                            if (pop <= sortedPeek) {
                                if (linkedList.isEmpty()) return pop;
                                sortedLinkedList.push(pop);
                                while (sortedPeekMoveCount-- > 0) {
                                    sortedLinkedList.push(linkedList.pop());
                                }
                                break;
                            } else {
                                linkedList.push(sortedLinkedList.pop());
                                sortedPeekMoveCount++;
                            }
                        }
                    }
                    return linkedList.pop();
                } else {
                    return sortedLinkedList.pop();
                }
            }

            public Integer peek() {
                if (linkedList.isEmpty() && sortedLinkedList.isEmpty()) return null;

                if (sortedLinkedList.isEmpty()) {
                    if (linkedList.size() == 1) return linkedList.peek();
                    sortedLinkedList.push(linkedList.pop());
                    while (linkedList.size() > 0) {
                        Integer pop = linkedList.pop();
                        int sortedPeekMoveCount = 0;
                        while (!sortedLinkedList.isEmpty()) {
                            Integer sortedPeek = sortedLinkedList.peek();
                            if (pop <= sortedPeek) {
                                sortedLinkedList.push(pop);
                                while (sortedPeekMoveCount-- > 0) {
                                    sortedLinkedList.push(linkedList.pop());
                                }
                                break;
                            } else {
                                linkedList.push(sortedLinkedList.pop());
                                sortedPeekMoveCount++;
                            }
                        }
                    }
                    return sortedLinkedList.peek();
                } else {
                    return sortedLinkedList.peek();
                }
            }

            public boolean isEmpty() {
                return linkedList.isEmpty();
            }
        }

        class AnimalShelterDequeue {
            private LinkedList<Animal> linkedList = new LinkedList<>();

            public void enqueue(Animal animal) {
                linkedList.offer(animal);
            }

            public Animal dequeueAny() {
                return linkedList.poll();
            }

            public Dog dequeueDog() {
                Iterator<Animal> iterator = linkedList.iterator();
                while (iterator.hasNext()) {
                    Animal next = iterator.next();
                    if (next instanceof Dog) {
                        iterator.remove();
                        return (Dog) next;
                    }
                }

                return null;
            }

            public Cat dequeueCat() {
                Iterator<Animal> iterator = linkedList.iterator();
                while (iterator.hasNext()) {
                    Animal next = iterator.next();
                    if (next instanceof Cat) {
                        iterator.remove();
                        return (Cat) next;
                    }
                }

                return null;
            }
        }


        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);


        Solution solution = new Solution();
        boolean balanced = solution.isBalanced(root);
        System.out.println(balanced);
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        System.out.println(minStack.min());

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
//        listNode4.next = listNode5;
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
        System.out.println("quickSort");
        quickSort();
        shellSOrt();
        System.out.println(11 % 4);
        System.out.println(isUnique("abcdefg"));
        System.out.println(checkPermutation("asjhhjko", "sjhkohja"));
        char[] charArray = new char[5];
        charArray[0] = 's';
        charArray[1] = ' ';
        charArray[2] = 'a';
        System.out.println(URLify(charArray));
        System.out.println(palindromePermutation("Tact Coa "));
        System.out.println(oneAway("pale", "bake"));
        System.out.println(stringCompression("aaaaaaaaccccdeeeeeeeeee"));
        int[][] arr111 = new int[3][3];
        arr111[0][0] = 1;
        arr111[0][1] = 2;
        arr111[0][2] = 3;
        arr111[1][0] = 4;
        arr111[1][1] = 5;
        arr111[1][2] = 6;
        arr111[2][0] = 7;
        arr111[2][1] = 8;
        arr111[2][2] = 9;

        rotate(arr111);

        for (int k = 0; k < arr111.length; k++) {
            for (int l = 0; l < arr111.length; l++) {
                System.out.print(arr111[k][l]);
            }
            System.out.println();
        }

        arr111[1][0] = 0;
        zeroMatrix(arr111);

        for (int k = 0; k < arr111.length; k++) {
            for (int l = 0; l < arr111.length; l++) {
                System.out.print(arr111[k][l]);
            }
            System.out.println();
        }

        System.out.println(stringRotation("waterbottle", "erbottlewat"));

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(2);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;

        ListNode head = node1;

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

        ListNode listNode = removeDups(node1);

        head = listNode;

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

        head = node1;

        ListNode kthToLast = kthToLast(head, 5);

        head = kthToLast;

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

        head = node1;
        deleteMiddle(head, 5);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

//        head = node1;
//        ListNode reversedHead = reverseList(head, null);
//        while (reversedHead != null) {
//            System.out.println(reversedHead.val);
//            reversedHead = reversedHead.next;
//        }

        head = node1;
        ListNode partition = partition(head, 5);
        while (partition != null) {
            System.out.println(partition.val);
            partition = partition.next;
        }

        ListNode sum = sumLists(node1, listNode1, false);
        head = sum;
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

        StringListNode stringListNode1 = new StringListNode('m');
        StringListNode stringListNode2 = new StringListNode('a');
        StringListNode stringListNode3 = new StringListNode('d');
        StringListNode stringListNode4 = new StringListNode('a');
        StringListNode stringListNode5 = new StringListNode('m');

        stringListNode1.next = stringListNode2;
        stringListNode2.next = stringListNode3;
        stringListNode3.next = stringListNode4;
        stringListNode4.next = stringListNode5;

        boolean palindrome = isPalindrome(stringListNode1);

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);

        n1.next = n2;
        n2.next = n3;

        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);

        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        ListNode intersect = intersect(n1, n4);
        ListNode loopDetection = loopDetection(n1);

        int[] arr4 = new int[10];
        System.out.println(arr4[5]);

        ThreeInOneStack stack = new ThreeInOneStack();
        stack.push(1, 1);
        stack.push(4, 1);
        stack.push(2, 2);
        stack.push(5, 2);
        stack.push(7, 2);
        stack.push(3, 3);
        stack.push(6, 3);
        stack.push(8, 3);
        stack.push(9, 3);
        stack.push(10, 3);
        stack.pop(3);
        stack.pop(3);
        stack.pop(3);
        stack.pop(3);
        stack.pop(3);
        stack.pop(2);
        stack.pop(2);
        stack.pop(2);
        stack.pop(1);
        stack.pop(1);
        System.out.println(stack.count);

        SetOfStacks setOfStacks = new SetOfStacks();
        for (int k = 0; k < 30; k++) {
            setOfStacks.push(k);
        }
        for (int k = 0; k < 30; k++) {
            System.out.println(setOfStacks.pop());
        }

        MyQueue myQueue = new MyQueue();
        for (int k = 0; k < 30; k++) {
            myQueue.offer(k);
        }
        for (int k = 0; k < 30; k++) {
            System.out.println(myQueue.poll());
        }

        SortedStack sortedStack = new SortedStack();
        for (int k = 0; k < 30; k++) {
            sortedStack.push(k);
        }
        for (int k = 0; k < 30; k++) {
            System.out.println(sortedStack.pop());
        }

        AnimalShelterDequeue animalShelterDequeue = new AnimalShelterDequeue();

        for (int k = 0; k < 30; k++) {
            if (k > 10) {
                animalShelterDequeue.enqueue(new Dog(String.valueOf(k)));
            }

            if (k > 15) {
                animalShelterDequeue.enqueue(new Cat(String.valueOf(k)));
            }
        }

        System.out.println(animalShelterDequeue.dequeueAny().getName());

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        map.put(4, 1);
        map.put(5, 1);
        map.put(1000, 1);

        GraphNode node1Graph = new GraphNode(1);
        GraphNode node2Graph = new GraphNode(2);
        GraphNode node3Graph = new GraphNode(3);
        GraphNode node4Graph = new GraphNode(4);
        GraphNode node5Graph = new GraphNode(5);
        GraphNode node6Graph = new GraphNode(6);

        node1Graph.children.add(node2Graph);
        node1Graph.children.add(node3Graph);
        node3Graph.children.add(node4Graph);
        node3Graph.children.add(node5Graph);

        boolean routeBetweenNodes = isRouteBetweenNodes(node1Graph, null);
        System.out.println(routeBetweenNodes);

        int[] ints1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        TreeNode treeNode = sortedArrayToBinarySearchTree(ints1);

        System.out.println("in order traversal");
        inOrderTraverseBinarySearchTree(treeNode);
        System.out.println("pre order traversal");
        preOrderTraverseBinarySearchTree(treeNode);
        System.out.println("post order traversal");
        postOrderTraverseBinarySearchTree(treeNode);

        Map<Integer, LinkedList<TreeNode>> integerLinkedListMap = linkedListsAtEachDepth(treeNode);
        for (Map.Entry<Integer, LinkedList<TreeNode>> node :
                integerLinkedListMap.entrySet()) {
            System.out.println("node number: " + node.getKey());
            for (TreeNode treeNode1 : node.getValue()) {
                System.out.println("tree number: " + treeNode1.val);
            }
        }

        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(5);

        root1.left = root2;
        root1.right = root3;
        root2.left = root4;
        root4.left = root5;
        boolean b1 = checkBalanced(root1);

        System.out.println(b1);

        boolean b2 = validateBST(treeNode);

        TreeNodeWithParent treeNodeWithParent = new TreeNodeWithParent(1);
        TreeNodeWithParent treeNodeWithParent1 = new TreeNodeWithParent(2);
        TreeNodeWithParent treeNodeWithParent2 = new TreeNodeWithParent(3);
        TreeNodeWithParent treeNodeWithParent3 = new TreeNodeWithParent(4);
        TreeNodeWithParent treeNodeWithParent4 = new TreeNodeWithParent(5);

        treeNodeWithParent.left = treeNodeWithParent1;
        treeNodeWithParent1.parent = treeNodeWithParent;
        treeNodeWithParent.right = treeNodeWithParent2;
        treeNodeWithParent2.parent = treeNodeWithParent;
        treeNodeWithParent1.left = treeNodeWithParent3;
        treeNodeWithParent3.parent = treeNodeWithParent1;
        treeNodeWithParent1.right = treeNodeWithParent4;
        treeNodeWithParent4.parent = treeNodeWithParent1;

        TreeNodeWithParent successor = successor(treeNodeWithParent3);

        GraphNode graphNode1 = new GraphNode(1);
        GraphNode graphNode2 = new GraphNode(2);
        GraphNode graphNode3 = new GraphNode(3);
        GraphNode graphNode4 = new GraphNode(4);
        GraphNode graphNode5 = new GraphNode(5);
        GraphNode graphNode6 = new GraphNode(6);
        List<GraphNode> projects = Arrays.asList(
                graphNode1,
                graphNode2,
                graphNode3,
                graphNode4,
                graphNode5,
                graphNode6
        );
        GraphNode[] d = {
                graphNode6, graphNode1
        };
        GraphNode[] d2 = {
                graphNode1, graphNode4
        };
        GraphNode[] d3 = {
                graphNode6, graphNode2
        };
        GraphNode[] d4 = {
                graphNode2, graphNode4
        };
        GraphNode[] d5 = {
                graphNode4, graphNode3
        };
        List<GraphNode[]> graphNodes = Arrays.asList(d, d2, d3, d4, d5);
        Set<GraphNode> graphNodes1 = buildOrder(projects, graphNodes);
        if (graphNodes1 != null) {
            for (GraphNode graphNode : graphNodes1) {
                System.out.println("graphNode " + graphNode.val);
            }
        } else System.out.println("Error");

        TreeNode treeNode1 = firstCommonAncestor(root1, root4, root5);

        ArrayList<LinkedList<Integer>> linkedLists = allSequences(root1);
        for (LinkedList<Integer> linkedList : linkedLists) {
            System.out.println("Variant");
            for (Integer integer : linkedList) {
                System.out.println("integer: " + integer);
            }
        }

        int sum1 = sum(5);
        System.out.println(sum1);

        ArrayList<LinkedList<Integer>> linkedLists1 = allSequences(root1);
        i = 1;
        for (LinkedList<Integer> integers : linkedLists1) {
            System.out.println("Variant: " + i);
            i++;
            for (Integer integer : integers) {
                System.out.println(integer);
            }
        }

        boolean[] booleans = {false};
        boolean b3 = checkBinaryTrees(root1, root, booleans);
        System.out.println(b3);

        TreeNode treeNode2 = randomNode(null);
        if (treeNode2 != null) {
            System.out.println(treeNode2.val);
        }

        List<TreeNode> treeNodes = generateTrees(10);
        System.out.println(treeNodes);

        int i1 = pathsWithSum(root, 3);
        System.out.println(i1);
        int insertion = insertion(1024, 19, 2, 6);
        System.out.println(insertion);

        String s = binaryToString(0.72, 32);
        System.out.println(s);

        int i2 = flipBitToWin(1775);
        System.out.println(i2);

        nextNumber(5);

        int conversion = conversion(29, 15);
        System.out.println("Conversion: " + conversion);

        int i3 = pairwiseSwap(2);
        System.out.println(i3);


        byte[] bytes = {0b00000000, 0b00000000, 0b00000000, 0b00000000};
        drawLine(bytes, 16, 1, 2, 1);

//        System.out.println("Apocalypse");
//        apocalypse();
        System.out.println("Poison");
        poison();

        GraphNode node11 = new GraphNode(1);
        GraphNode node22 = new GraphNode(2);
        GraphNode node33 = new GraphNode(3);
        GraphNode node44 = new GraphNode(4);
        GraphNode node55 = new GraphNode(5);
        GraphNode node66 = new GraphNode(6);

        node11.children = Arrays.asList(node22, node33);
        node22.children = Arrays.asList(node44, node55);
        node44.children = Arrays.asList(node66);
        breadthFirstSearch(node11, node66);
        depthFirstSearch(node11, node33);
        List<List<Integer>> pathInBinaryTree = findPathInBinaryTree(root2, root1);
        System.out.println("Found path in binary tree: " + pathInBinaryTree);

        System.out.println("Jigsaw Puzzle start");
        JigsawPuzzle jigsawPuzzle = new JigsawPuzzle(3);
        JigsawPuzzleEdge edge1 = new JigsawPuzzleEdge(1);
        JigsawPuzzleEdge edge2 = new JigsawPuzzleEdge(2);
        JigsawPuzzleEdge edge3 = new JigsawPuzzleEdge(3);
        JigsawPuzzleEdge edge4 = new JigsawPuzzleEdge(4);
        JigsawPuzzleEdge edge5 = new JigsawPuzzleEdge(5);
        JigsawPuzzleEdge edge6 = new JigsawPuzzleEdge(6);
        JigsawPuzzleEdge edge7 = new JigsawPuzzleEdge(7);
        JigsawPuzzleEdge edge8 = new JigsawPuzzleEdge(8);
        JigsawPuzzleEdge edge9 = new JigsawPuzzleEdge(9);
        jigsawPuzzle.addEdgeToPuzzle(edge1, 0, 0);
        jigsawPuzzle.addEdgeToPuzzle(edge2, 0, 1);
        jigsawPuzzle.addEdgeToPuzzle(edge3, 0, 2);
        jigsawPuzzle.addEdgeToPuzzle(edge4, 1, 0);
        jigsawPuzzle.addEdgeToPuzzle(edge5, 1, 1);
        jigsawPuzzle.addEdgeToPuzzle(edge6, 1, 2);
        jigsawPuzzle.addEdgeToPuzzle(edge7, 2, 0);
        jigsawPuzzle.addEdgeToPuzzle(edge8, 2, 1);
        jigsawPuzzle.addEdgeToPuzzle(edge9, 2, 2);
        boolean fitsWidth = jigsawPuzzle.fitsWidth(edge4, edge5);
        System.out.println("Edge4 and edge5 fits width: " + fitsWidth);
        System.out.println("Jigsaw Puzzle end");
        System.out.println("Circular Array start");
        Queue<Integer> circularArray = new CircularArray<>();
        circularArray.offer(1);
        circularArray.offer(2);
        circularArray.offer(3);
        circularArray.offer(4);
        circularArray.offer(5);
        circularArray.offer(6);
        circularArray.offer(7);
        circularArray.offer(8);
        circularArray.offer(9);
        circularArray.poll();
        circularArray.offer(10);
        circularArray.poll();
        circularArray.poll();
        circularArray.poll();
        circularArray.poll();
        circularArray.poll();
        circularArray.offer(11);
        circularArray.offer(12);
        circularArray.offer(13);
        circularArray.offer(14);
        circularArray.offer(15);
        for (Integer integer :
                circularArray) {
            System.out.println("Circular array item: " + integer);
        }
        System.out.println("Circular Array end");
//        System.out.println("Minesweeper start");
//        Minesweeper game = new Minesweeper(4);
//        game.play();
//        game.expose(1, 0);
//        System.out.println("Minesweeper end");
        System.out.println("File system start");
        FileSystem fs = new FileSystem();
        FSItem dir = new Dir("dir1");
        FSItem file = new File("file1");
        ((Dir) dir).setItems(Collections.singletonList(file));
        fs.setItems(Collections.singletonList(dir));
        List<FSItem> searchList = fs.search("file1");
        System.out.println("Search: " + searchList.toString());
        System.out.println("File system end");
        System.out.println("Hash table start");
        HashTable<Integer, Integer> hashTable = new HashTable<>();
        hashTable.put(1, 1);
        hashTable.put(2, 2);
        hashTable.put(3, 3);
        System.out.println("Hash table size after inserting: " + hashTable.size());
        hashTable.remove(1);
        hashTable.remove(2);
        hashTable.remove(3);
        System.out.println("Hash table size after removing: " + hashTable.size());
        System.out.println("Hash table end");
        System.out.println("Triple step start");
        System.out.println("Triple step ways: " + tripleStep(3));
        System.out.println("Triple step end");
        System.out.println("Magic index start");
        System.out.println("Magic index: " + magicIndex(new int[]{-1, 0, 2, 2}));
        System.out.println("Magic index end");
        System.out.println("Power set start");
        System.out.println(powerSet(Arrays.asList(1, 2, 3, 4, 5)));
        System.out.println("Power set end");
        System.out.println("Recursive multiply start");
        System.out.println("Recursive multiply: " + recursiveMultiply(7, 5));
        System.out.println("Recursive multiply end");
        System.out.println("Tower of Hanoi start");
        Stack<Integer> source = new Stack<>();
        source.push(1);
        source.push(2);
        source.push(3);
        source.push(4);
        source.push(5);
        Stack<Integer> destination = new Stack<>();
        Stack<Integer> buffer = new Stack<>();
        towersOfHanoi(source, destination, buffer, source.size());
        System.out.println(destination.toString());
        System.out.println("Tower of Hanoi end");
        System.out.println("Permutations without dups start");
        System.out.println("Permutations without dups: " + permutationsWithoutDups("abcdd"));
        System.out.println("Permutations without dups end");
        System.out.println("Permutations with dups start");
        System.out.println("Permutations with dups: " + permutationsWithDups("abca"));
        System.out.println("Permutations with dups end");
        System.out.println("Parens start");
        System.out.println("Parens: " + parens(3));
        System.out.println("Parens end");
        System.out.println("Paint fill start");
        String[][] strings = new String[3][3];
        for (int k = 0, stringsLength = strings.length; k < stringsLength; k++) {
            for (int i4 = 0, stringLength = strings[k].length; i4 < stringLength; i4++) {
                String red = "red";
                strings[k][i4] = red;
                System.out.print("x: " + k);
                System.out.print(", y: " + i4);
                System.out.println(", color: " + red);
            }
        }

        paintFill(strings, "blue", new Point(2, 2));
        for (int k = 0, stringsLength = strings.length; k < stringsLength; k++) {
            for (int i4 = 0, stringLength = strings[k].length; i4 < stringLength; i4++) {
                System.out.print("x: " + k);
                System.out.print(", y: " + i4);
                System.out.println(", color: " + strings[k][i4]);
            }
        }
        System.out.println("Paint fill end");
        System.out.println("Coins start");
        System.out.println("Coins: " + coins(7));
        System.out.println("Coins end");
        System.out.println("eight Queens start");
        List<Integer[]> eightQueens = eightQueens();
        for (int i4 = 0, eightQueensSize = eightQueens.size(); i4 < eightQueensSize; i4++) {
            Integer[] eightQueen = eightQueens.get(i4);
            System.out.println("Variant " + i4);
            for (int k = 0; k < eightQueen.length; k++) {
                Integer integer = eightQueen[k];
                System.out.println("row, col: " + k + ", " + integer);
            }
        }
        System.out.println("eight Queens end");
    }

    private static List<List<Integer>> findPathInBinaryTree(TreeNode find, TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        findPathInBinaryTreeTraverse(find, root, result, false);
        return result;
    }

    private static void findPathInBinaryTreeTraverse(TreeNode find, TreeNode root,
                                                     List<List<Integer>> result, boolean found) {
        if (root == null) return;

        boolean f = found;

        if (find == root) {
            f = true;
        }

        findPathInBinaryTreeTraverse(find, root.left, result, f);
        if (!f && !result.isEmpty()) {
            for (List<Integer> l :
                    result) {
                l.add(root.val);
            }
            return;
        }
        findPathInBinaryTreeTraverse(find, root.right, result, f);

        if (f) {
            if (root.left == null && root.right == null) {
                List<Integer> l1 = new ArrayList<>();
                l1.add(root.val);
                result.add(l1);
            } else {
                for (List<Integer> l :
                        result) {
                    l.add(root.val);
                }
            }
        }
    }

    private static boolean depthFirstSearch(GraphNode root, GraphNode end) {
        if (root == null) {
            return false;
        }

        root.visited = true;
        System.out.println("visited: " + root.val);

        if (root == end) {
            return true;
        }

        for (GraphNode child : root.children) {
            if (!child.visited) {
                boolean found = depthFirstSearch(child, end);
                if (found) {
                    return found;
                }
            }
        }

        return false;
    }

    private static void breadthFirstSearch(GraphNode start, GraphNode end) {
        LinkedList<GraphNode> queue = new LinkedList<>();
        start.visited = true;
        System.out.println("visited: " + start.val);
        queue.offer(start);
        while (!queue.isEmpty()) {
            GraphNode poll = queue.poll();
            for (GraphNode current :
                    poll.children) {
                if (!current.visited) {
                    current.visited = true;
                    System.out.println("visited: " + current.val);
                    if (end == current) {
                        return;
                    }
                    queue.offer(current);
                }
            }
        }
    }

    private static void poison() {
        final List<SodaBottle> bottles = new ArrayList<>();
        int bottlesCount = 1000;
        int testStrips = 10;
        for (int i = 0; i < bottlesCount; i++) {
            if (i == 999) {
                bottles.add(new SodaBottle(true));
            } else {
                bottles.add(new SodaBottle(false));
            }
        }

        int part = bottlesCount / testStrips;
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        final AtomicInteger days = new AtomicInteger();
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                days.incrementAndGet();
                if (countDownLatch.getCount() == 1) {
                    countDownLatch.countDown();
                }
            }
        });
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < testStrips; i++) {
            final int start = i * part;
            final int end = i * part + part - 1;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = start; j <= end; j++) {
                        SodaBottle sodaBottle = bottles.get(j);
                        if (sodaBottle.poisoned) {
                            countDownLatch.countDown();
                        }
                        try {
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        try {
            countDownLatch.await();
            executorService.shutdown();

            System.out.println("Days are passed: " + (days.get() * 7));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void apocalypse() {
        int families = 0;
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Map<Boolean, Integer> genderMap = new ConcurrentHashMap<>();
        genderMap.put(true, 0);
        genderMap.put(false, 0);

        while (families < 10) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    boolean girl = new Random().nextBoolean();
                    while (!girl) {
                        genderMap.put(girl, genderMap.get(girl) + 1);
                        girl = new Random().nextBoolean();
                    }
                    genderMap.put(girl, genderMap.get(girl) + 1);
                    countDownLatch.countDown();
                }
            });
            families++;
        }
        try {
            countDownLatch.await();
            executorService.shutdown();
            double boys = (double) genderMap.get(false);
            double girls = (double) genderMap.get(true);
            double sum = boys + girls;
            double boysRatio = (boys / sum) * 100;
            double girlsRatio = (girls / sum) * 100;

            System.out.println("Gender ratio. Boys: " + boysRatio + "%, girls: " + girlsRatio + "%");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void inOrderTraverseBinarySearchTree(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrderTraverseBinarySearchTree(root.left);
        System.out.println(root.val);
        inOrderTraverseBinarySearchTree(root.right);
    }

    private static void preOrderTraverseBinarySearchTree(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println(root.val);

        preOrderTraverseBinarySearchTree(root.left);
        preOrderTraverseBinarySearchTree(root.right);
    }

    private static void postOrderTraverseBinarySearchTree(TreeNode root) {
        if (root == null) {
            return;
        }

        postOrderTraverseBinarySearchTree(root.left);
        postOrderTraverseBinarySearchTree(root.right);

        System.out.println(root.val);
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
        recQuickSort(0, arr.length - 1, arr);

        System.out.println(Arrays.toString(arr));
    }

    private static void recQuickSort(int left, int right, int[] arr) {
        if ((right - left) <= 0) {
            return;
        } else {
            int pivot = arr[right];
            int partition = partitionIt(left, right, pivot, arr);
            recQuickSort(left, partition - 1, arr);
            recQuickSort(partition + 1, right, arr);
        }
    }

    private static int partitionIt(int left, int right, int pivot/* last value */, int[] arr) {
        int leftPtr = left - 1; // -1
        int rightPtr = right; // last

        while (true) {
            while (arr[++leftPtr] < pivot) { // until more than or equal pivot
            }

            while (rightPtr > 0 && arr[--rightPtr] > pivot) { // until first index or less than or equal pivot
            }

            if (leftPtr >= rightPtr) {
                break;
            } else {
                int temp = arr[leftPtr];
                arr[leftPtr] = arr[rightPtr];
                arr[rightPtr] = temp;
            }
        }
        int temp = arr[leftPtr];
        arr[leftPtr] = arr[right];
        arr[right] = temp;
        return leftPtr;
    }

    public static void shellSOrt() {
        int[] arr = {1, 5, 3, 4, -1, 100, 99, -500, -350, -520, 1000, 999, 630, 752, 777};
        int n = 1;
        while (n < (arr.length / 3)) {
            n = (n * 3) + 1;
        }
        int outer = 0;
        int inner = 0;
        while (n > 0) {
            for (outer = n; outer < arr.length; outer++) {
                int temp = arr[outer];
                inner = outer;
                while (inner > n - 1 && arr[inner - n] >= temp) {
                    arr[inner] = arr[inner - n];
                    inner -= n;
                }
                arr[inner] = temp;
            }
            n = (n - 1) / 3;
        }

        System.out.println(Arrays.toString(arr));
    }

    public static boolean isUnique(String s) {
        if (s == null || s.length() == 0) return false;
        if (s.length() == 1) return true;

        char[] chars = s.toCharArray();

        Arrays.sort(chars);

        char prev = chars[0];

        for (int i = 1; i < chars.length; i++) {
            char current = chars[i];
            if (current == prev) return false;

            prev = current;
        }

        return true;
    }

    public static boolean checkPermutation(String a, String b) {
        if (a == null || b == null || a.length() != b.length() || a.length() == 0) return false;

        List<Character> list = new LinkedList<>();

        for (int i = 0; i < a.length(); i++) {
            list.add(a.charAt(i));
        }
        for (int i = 0; i < b.length(); i++) {
            list.remove((Character) b.charAt(i));
        }

        if (list.isEmpty()) return true;

        return false;
    }

    public static String URLify(char[] charArray) {
        int actualSize = 0;
        for (int i = charArray.length - 1; i >= 0; i--) {
            if (actualSize == 0 && charArray[i] != '\u0000') actualSize = i + 1;
            if (charArray[i] == ' ') {
                System.arraycopy(charArray, i + 1, charArray, i + 3, actualSize - 1 - i);
                charArray[i] = '%';
                charArray[i + 1] = '2';
                charArray[i + 2] = '0';
            }
        }

        return String.valueOf(charArray);
    }

    public static boolean palindromePermutation(String s) {
        if (s == null || s.isEmpty()) return false;
        if (s.length() == 1) return true;

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                Integer integer = map.get(Character.toLowerCase(c));
                if (integer == null) {
                    map.put(Character.toLowerCase(c), 1);
                } else {
                    map.put(Character.toLowerCase(c), integer + 1);
                }
            }
        }

        boolean oddsSwitcher = false;

        for (Map.Entry<Character, Integer> entry :
                map.entrySet()) {
            if (oddsSwitcher) {
                return false;
            }

            if (entry.getValue() % 2 != 0) {
                oddsSwitcher = true;
            }
        }

        return true;
    }

    public static boolean oneAway(String s1, String s2) {
        if (s1 == null || s2 == null) return false;

        int l1 = s1.length();
        int l2 = s2.length();

        int a = 0;
        int b = 0;

        int total = l1 - l2;

        if (total < -1 || total > 1) {
            return false;
        }

        boolean different = false;

        while (a < l1 && b < l2) {
            char c1 = s1.charAt(a);
            char c2 = s2.charAt(b);

            if (c1 != c2) {
                if (different) return false;
                different = true;

                if (l1 > l2) {
                    a++;
                } else if (l1 < l2) {
                    b++;
                } else {
                    a++;
                    b++;
                }
            } else {
                a++;
                b++;
            }
        }

        return true;
    }

    public static String stringCompression(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) return s;

        StringBuilder result = new StringBuilder();

        int repeats = 1;
        char prev = s.charAt(0);

        for (int i = 1; i < s.length(); i++) {
            char current = s.charAt(i);

            if (current == prev) {
                repeats++;
            } else {
                result.append(prev).append(repeats);
                repeats = 1;
            }

            prev = current;
        }

        result.append(prev).append(repeats);

        return result.length() < s.length() ? result.toString() : s;
    }

    public static void rotate(int[][] arr) {
        int lastRow = arr.length - 1;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i; j < arr.length - i - 1; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[lastRow - j][i];
                arr[lastRow - j][i] = arr[lastRow - i][lastRow - j];
                arr[lastRow - i][lastRow - j] = arr[j][lastRow - i];
                arr[j][lastRow - i] = temp;
            }
        }
    }

    public static void zeroMatrix(int[][] arr) {
        int a = arr.length;

        int b = 0;
        while (b < 2) {
            for (int i = 0; i < a; i++) {
                int j = arr[i].length;

                for (int k = 0; k < j; k++) {
                    if (b == 1) {
                        if (arr[i][k] == Integer.MAX_VALUE) {
                            arr[i][k] = 0;
                        }
                    } else if (arr[i][k] == 0) {
                        for (int l = 0; l < a; l++) {
                            arr[i][l] = Integer.MAX_VALUE;
                        }
                        for (int l = 0; l < j; l++) {
                            arr[l][k] = Integer.MAX_VALUE;
                        }
                    }
                }
            }
            b++;
        }
    }

    public static boolean stringRotation(String s1, String s2) {
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) return false;

        String concat = s2.concat(s2);

        return concat.length() == s1.length() * 2 && concat.contains(s1);
    }

    public static ListNode removeDups(ListNode node) {
        ListNode result = null;
        Set<Integer> set = new HashSet<>();

        ListNode root = node;

        while (root != null) {
            set.add(root.val);
            root = root.next;
        }

        ListNode currentNode = null;
        for (Integer value :
                set) {
            if (result == null) {
                result = new ListNode(value);
                currentNode = result;
            } else {
                ListNode nextNode = new ListNode(value);
                currentNode.next = nextNode;
                currentNode = nextNode;
            }
        }

        return result;
    }

    public static ListNode kthToLast(ListNode root, int kth) {
        ListNode head = root;
        int i = 0;

        while (head != null) {
            if (++i == kth) {
                return head;
            }
            head = head.next;
        }

        return root;
    }

    public static void deleteMiddle(ListNode root, int find) {
        ListNode head = root;
        ListNode prev = null;

        if (head == null) return;

        while (head.next != null) {
            if (head.val == find && prev != null) {
                prev.next = head.next;
            } else {
                prev = head;
            }
            head = head.next;
        }
    }

    public static ListNode partition(ListNode root, int median) {
        if (root == null) return null;
        if (root.next == null) return root;

        ListNode leftHead = null;
        ListNode left = null;
        ListNode rightHead = null;
        ListNode right = null;
        ListNode head = root;

        while (head != null) {
            if (head.val < median) {
                if (left == null) {
                    left = head;
                    leftHead = left;
                } else {
                    left.next = head;
                    left = left.next;
                }
            } else {
                if (right == null) {
                    right = head;
                    rightHead = right;
                } else {
                    right.next = head;
                    right = right.next;
                }
            }
            head = head.next;
        }

        if (left == null) return root;

        left.next = rightHead;
        return leftHead;
    }

    private static ListNode reverseList(ListNode element, ListNode prev) {
        if (element.next == null) {
            element.next = prev;
            return element;
        }

        ListNode listNode = reverseList(element.next, element);
        element.next = prev;

        return listNode;
    }

    private static ListNode sumLists(ListNode a, ListNode b, boolean reverse) {
        if (a == null && b == null) return null;
        if (a == null) return b;
        if (b == null) return a;

        ListNode result = null;
        ListNode resultNode = null;
        int add = 0;

        if (reverse) {
            while (true) {
                if (a == null && b == null) {
                    if (add > 0) {
                        resultNode.next = new ListNode(add);
                    }
                    return result;
                }

                int aNum = a != null ? a.val : 0;
                int bNum = b != null ? b.val : 0;

                int sum = aNum + bNum + add;
                add = 0;
                if (sum >= 10) {
                    add = 1;
                    sum = sum % 10;
                }

                if (resultNode == null) {
                    resultNode = new ListNode(sum);
                    result = resultNode;
                } else {
                    resultNode.next = new ListNode(sum);
                    resultNode = resultNode.next;
                }

                if (a != null) {
                    a = a.next;
                }
                if (b != null) {
                    b = b.next;
                }
            }
        } else {
            ListNode aHead = a;
            ListNode bHead = b;
            int aCount = 0;
            int bCount = 0;
            int aNumber = 0;
            int bNumber = 0;

            while (aHead != null) {
                aCount++;
                aHead = aHead.next;
            }
            while (bHead != null) {
                bCount++;
                bHead = bHead.next;
            }

            aCount = aCount == 0 ? 0 : aCount - 1;
            bCount = bCount == 0 ? 0 : bCount - 1;

            aHead = a;
            bHead = b;
            while (aHead != null) {
                int i = aHead.val;
                aNumber = aNumber + (int) (i * Math.pow(10, aCount--));
                aHead = aHead.next;
            }
            while (bHead != null) {
                int i = bHead.val;
                bNumber = bNumber + (int) (i * Math.pow(10, bCount--));
                bHead = bHead.next;
            }

            int sum = aNumber + bNumber;
            ListNode listNode = recursiveNumberListProducing(sum, null);
            return listNode == null ? new ListNode(0) : listNode;
        }
    }

    private static ListNode recursiveNumberListProducing(int sum, ListNode prev) {
        if (sum == 0) {
            return null;
        }
        ListNode listNode1 = new ListNode(sum % 10);
        ListNode listNode = recursiveNumberListProducing(sum / 10, listNode1);
        if (listNode == null) {
            listNode = listNode1;
        }
        listNode1.next = prev;
        return listNode;
    }

    private static boolean isPalindrome(StringListNode root) {
        if (root == null) return false;
        if (root.next == null) return true;

        LinkedList<Character> linkedList = new LinkedList<>();

        StringListNode head = root;
        recursiveFillingQueue(head, linkedList);

        return linkedList.isEmpty();
    }

    private static void recursiveFillingQueue(StringListNode head, LinkedList<Character> queue) {
        if (head == null) {
            return;
        }

        queue.offer(head.val);
        recursiveFillingQueue(head.next, queue);
        Character first = queue.getFirst();
        if (first.equals(head.val)) {
            queue.poll();
        }
    }

    private static ListNode intersect(ListNode root, ListNode root1) {
        if (root == null || root1 == null) return null;

        Set<ListNode> set = new HashSet<>();

        ListNode a = root;
        ListNode b = root1;
        while (a != null) {
            set.add(a);
            a = a.next;
        }
        while (b != null) {
            if (set.contains(b)) {
                return b;
            }
            b = b.next;
        }

        return null;
    }

    private static ListNode loopDetection(ListNode root) {
        if (root == null) return null;
        if (root.next == null) return null;
        if (root.next == root) return root;

        ListNode slower = root.next;
        ListNode faster = root.next.next;

        while (faster != null && faster.next != null) {
            if (slower == faster) {
                slower = root;
                break;
            }

            slower = slower.next;
            faster = faster.next.next;
        }

        while (faster != null) {
            if (slower == faster) {
                return slower;
            }

            slower = slower.next;
            faster = faster.next;
        }

        return null;
    }

    private static boolean isRouteBetweenNodes(GraphNode a, GraphNode b) {
        if (a == null || b == null) return false;

        LinkedList<GraphNode> queue = new LinkedList<>();
        queue.offer(a);

        while (!queue.isEmpty()) {
            GraphNode poll = queue.poll();
            poll.visited = true;

            if (poll == b) {
                return true;
            }

            for (GraphNode child : poll.children) {
                if (!child.visited) {
                    queue.offer(child);
                }
            }
        }

        return false;
    }

    private static TreeNode sortedArrayToBinarySearchTree(int[] ints) {
        if (ints == null) return null;
        if (ints.length == 0) return null;
        if (ints.length == 1) return new TreeNode(ints[0]);


        return recursiveTraverseSortedArray(ints, 0, ints.length - 1);
    }

    private static TreeNode recursiveTraverseSortedArray(int[] ints, int start, int end) {
        if (start > end) {
            return null;
        }

        int median = (start + end) / 2;

        TreeNode root = new TreeNode(ints[median]);
        root.left = recursiveTraverseSortedArray(ints, start, median - 1);

        root.right = recursiveTraverseSortedArray(ints, median + 1, end);

        return root;
    }

    private static Map<Integer, LinkedList<TreeNode>> linkedListsAtEachDepth(TreeNode root) {
        if (root == null) return new HashMap<>();

        Map<Integer, LinkedList<TreeNode>> result = new HashMap<>();

        reclinkedListsAtEachDepth(root, result, 1);

        return result;
    }

    private static void reclinkedListsAtEachDepth(TreeNode root, Map<Integer, LinkedList<TreeNode>> map,
                                                  int level) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode> treeNodes = map.get(level);
        if (treeNodes == null) {
            treeNodes = new LinkedList<>();
        }

        treeNodes.add(root);
        map.put(level, treeNodes);

        int nextLevel = level + 1;

        reclinkedListsAtEachDepth(root.left, map, nextLevel);
        reclinkedListsAtEachDepth(root.right, map, nextLevel);
    }

    private static boolean checkBalanced(TreeNode root) {
        return recTraverseTree(root) != 0;
    }

    private static int recTraverseTree(TreeNode root) {
        if (root == null) {
            return 1;
        }

        int leftLevel = recTraverseTree(root.left);
        int rightLevel = recTraverseTree(root.right);

        int i = leftLevel - rightLevel;

        if (leftLevel == 0 || rightLevel == 0 || i < -1 || i > 1) {
            return 0;
        }

        return Math.max(leftLevel, rightLevel) + 1;
    }

    private static boolean validateBST(TreeNode root) {
        if (root == null) return false;

        TreeNode checkingNode = new TreeNode(1);
        recTraverseTree1(root, checkingNode);

        return checkingNode.val == 1;
    }

    private static int recTraverseTree1(TreeNode root, TreeNode checkingNode) {
        if (root.left == null && root.right == null) {
            return root.val;
        }

        int left = 0;
        int right = 0;
        int val = root.val;

        if (root.left != null) {
            left = recTraverseTree1(root.left, checkingNode);
            if (val <= left) {
                checkingNode.val = 0;
                return left;
            }
        }

        if (root.right != null) {
            right = recTraverseTree1(root.right, checkingNode);
            if (val >= right) {
                checkingNode.val = 0;
                return right;
            }
        }

        if (root.left != null && root.right != null) {
            int max = Math.max(left, right);

            return Math.max(max, root.val);
        } else if (root.left != null) {
            return Math.max(left, val);
        } else {
            return Math.max(val, right);
        }
    }

    private static TreeNodeWithParent successor(TreeNodeWithParent node) {
        if (node == null || (node.right == null && node.parent == null)) return null;

        if (node.right != null) {
            return node.right;
        } else {
            TreeNodeWithParent parent = node.parent;
            if (parent.left == node) return parent;
            else return parent.parent;
        }
    }

    private static Set<GraphNode> buildOrder(List<GraphNode> projects, List<GraphNode[]> dependencies) {
        for (GraphNode[] dependency : dependencies) {

            dependency[1].children.add(dependency[0]);
        }
        Set<GraphNode> result = new LinkedHashSet<>();
        for (GraphNode project : projects) {
            boolean b = recursiveDepthFirstSearch(project, result, new HashSet<GraphNode>());
            if (!b) return null;
        }
        return result;
    }

    private static boolean recursiveDepthFirstSearch(GraphNode root, Set<GraphNode> result,
                                                     Set<GraphNode> setHelper) {
        List<GraphNode> children = root.children;
        setHelper.add(root);
        boolean b = true;
        for (GraphNode child : children) {
            if (setHelper.contains(child)) {
                return false;
            }
            if (!child.visited) {

                b = recursiveDepthFirstSearch(child, result, setHelper);
                setHelper.remove(child);
                if (!b) {
                    break;
                }
            } else {
                return false;
            }
        }

        if (b) {
            result.add(root);
            return true;
        } else {
            return false;
        }
    }

    private static TreeNode firstCommonAncestor(TreeNode root, TreeNode o, TreeNode m) {
        TreeNode[] result = new TreeNode[1];

        recursiveTreeTraversal(root, o, m, result);

        return result[0];
    }

    private static int recursiveTreeTraversal(TreeNode root, TreeNode o, TreeNode m, TreeNode[] result) {
        if (root == null) {
            return 0;
        }

        int left = recursiveTreeTraversal(root.left, o, m, result);
        int right = recursiveTreeTraversal(root.right, o, m, result);

        if (root.left == o || root.left == m) {
            left++;
        }

        if (root.right == o || root.right == m) {
            right++;
        }

        int sum = left + right;
        if (sum == 2 && result[0] == null) {
            result[0] = root;
        }
        return sum;
    }

    private static ArrayList<LinkedList<Integer>> allSequences(TreeNode node) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();

        if (node == null) {
            result.add(new LinkedList<Integer>());
            return result;
        }

        LinkedList<Integer> prefix = new LinkedList<Integer>();
        prefix.add(node.val);

        /* Recurse on lef t and righ t subtrees. */
        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

        /* Weave together each lis t from the lef t and righ t sides. */
        for (LinkedList<Integer> left :
                leftSeq) {
            for (LinkedList<Integer> right :
                    rightSeq) {
                ArrayList<LinkedList<Integer>> weaved =
                        new ArrayList<>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }

        return result;
    }

    /* Weave list s together in al l possible ways. This algorithm works by removing the
     * head from one list , recursing, and then doing the same thing with the other
     * list . */
    static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second,
                           ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
        /* One lis t is empty. Add remainder to [a cloned] prefi x and store result . */
        if (first.size() == 0 || second.size() == 0) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(
                    second);
            results.add(result);
            return;
        }

        /* Recurse with head of firs t added to the prefix . Removing the head wil l damage
         * first , so we'l l need to put it back where we found it afterwards. */
        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveLists(
                first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        /* Do the same thin g with second, damaging and then restoring the list.*/
        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headSecond);
    }

    private static int sum(int num) {
        return recursiveSum(num, 1, num);
    }

    private static int recursiveSum(int num, int prev, int reduced) {
        if (reduced < 0) {
            return 0;
        }

        if (reduced == 0) {
            return 1;
        }

        int result = 0;

        for (int i = prev; i <= num; i++) {
            prev = i;
            result = result + recursiveSum(num, prev, reduced - i);
        }

        return result;
    }

    private static boolean checkBinaryTrees(TreeNode n1, TreeNode n2, boolean[] result) {
        if (n2 == null) return false;
        if (n1 == null) return false;

        if
        (n1 == n2) {
            boolean b = recursiveCheckBinaryTrees(n1, n2);
            result[0] = b;
            return b;
        }

        boolean left = checkBinaryTrees(n1.left, n2, result);
        boolean right = checkBinaryTrees(n1.right, n2, result);

        return result[0];
    }

    private static boolean recursiveCheckBinaryTrees(TreeNode n1, TreeNode n2) {
        if
        (n1 == null && n2 == null) {
            return true;
        } else if (n1 == null || n2 == null) {
            return false;
        }

        if (n1 != n2) return false;//''

        boolean left = recursiveCheckBinaryTrees(n1.left, n2.left);
        boolean right = recursiveCheckBinaryTrees(n1.right, n2.right);

        return left && right;
    }

    private static TreeNode randomNode(TreeNode n1) {
        if (n1 == null) return null;
        List<TreeNode> treeNodes = recursiveBinaryTreeTraverse(n1);
        int i = new Random().nextInt(treeNodes.size());
        return treeNodes.get(i);
    }

    private static List<TreeNode> recursiveBinaryTreeTraverse(TreeNode n1) {
        if
        (n1 == null) {
            return new ArrayList<>();
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(n1);

        List<TreeNode> left = recursiveBinaryTreeTraverse(n1.left);
        List<TreeNode> right = recursiveBinaryTreeTraverse(n1.right);

        treeNodes.addAll(left);
        treeNodes.addAll(right);

        return treeNodes;
    }

    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }

        return generate(1, n);
    }

    private static List<TreeNode> generate(int start, int end) {
        List<TreeNode> output = new ArrayList<>();
        if (start > end) {
            return Collections.singletonList(null);
        } else if (start == end) {
            return Collections.singletonList(new TreeNode(start));
        } else if (start + 1 == end) {
            TreeNode curr = new TreeNode(start);
            curr.right = new TreeNode(end);
            output.add(curr);

            curr = new TreeNode(end);
            curr.left = new TreeNode(start);
            output.add(curr);
        } else {
            for (int root = start; root <= end; root++) {
                List<TreeNode> left = generate(start, root - 1);
                List<TreeNode> right = generate(root + 1, end);
                for (int i = 0; i < left.size(); i++) {
                    for (int j = 0; j < right.size(); j++) {
                        TreeNode curr = new TreeNode(root);
                        curr.left = left.get(i);
                        curr.right = right.get(j);
                        output.add(curr);
                    }
                }
            }
        }

        return output;
    }

    private static int pathsWithSum(TreeNode root, int num) {
        if (root == null) return 0;

        return recursiveBinaryTreeTraverseForSum(root, null, num);
    }

    private static int recursiveBinaryTreeTraverseForSum(TreeNode n1, Integer sum, int num) {
        if
        (n1 == null) {
            return 0;
        }

        int result = 0;
        int i = n1.val + (sum == null ? 0 : sum);
        if (i == num || n1.val == num) {
            result++;
            sum = null;
        } else if (i > num) {
            sum = n1.val;
        } else {
            sum = i;
        }


        int left = recursiveBinaryTreeTraverseForSum(n1.left, sum, num);
        int right = recursiveBinaryTreeTraverseForSum(n1.right, sum, num);

        result += left + right;


        return result;
    }

    private static int insertion(int n, int m, int i, int j) {
        int num = j - i + 1;
        int number = (int) (Math.pow(2, num) - 1);
        int mask = ~(number << i);
        return (n & mask) | (m << i);
    }

    private static String binaryToString(double i, int k) {
        int maxFranctional = k - 2;
        StringBuilder result = new StringBuilder("0.");
        if (i == 0) return String.valueOf(i);
        if (i == 1) return String.valueOf(i);

        for (int i1 = 0; i1 < maxFranctional; i1++) {
            i = i * 2;
            int integralPart = (int) i;
            if (i >= 1) i = i - 1;
            result.append(integralPart);
            if (i == 0.0d) return result.toString();
        }

        return "Error";
    }

    private static int flipBitToWin(int number) {
        int max = 0;
        int count = 0;
        int prevStartPos = -1;
        int flipPosition = -1;
        for (int i = 0; i < 31; i++) {
            int temp = (1 << i) | number;
            if (temp != number) {


                count = i - prevStartPos - 1;

                prevStartPos = flipPosition;
                if (count > max) {
                    max = count;
                }
                flipPosition = i;
            }
        }

        count = 31 - prevStartPos - 1;
        if (count > max) {
            max = count;
        }

        return max;
    }

    private static void nextNumber(int number) {
        int less = number - 1;
        int more = number + 1;
        while (less >= 0) {
            int i = count1s(less);
            int i1 = count1s(more);
            if (i == i1) {
                System.out.println("Least number : " + less);
                System.out.println("Most number : " + more);
                return;
            }
            less--;
            if (more == Integer.MAX_VALUE) {
                break;
            }
            more++;
        }
    }

    private static int count1s(int number) {
        int result = 0;
        for (int i = 0; i < 31; i++) {
            int temp = ~(1 << i) & number;
            if (number != temp) result++;
        }

        return result;
    }

    private static int conversion(int a, int b) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int tempA = (1 << i) & a;
            int tempB = (1 << i) & b;
            if (tempA != tempB) result++;
        }

        return result;
    }

    private static int pairwiseSwap(int a) {
        int result = a;

        for (int i = 0; i < 32; i++) {
            if (i % 2 != 0) {
                int temp = (1 << i) & a;
                int prevv = 1 << (i - 1) & a;
                if (temp != prevv) {
                    int updatable = ~(1 << i) & a;
                    result = (prevv << 1) | updatable;
                    updatable = ~(1 << (i - 1)) & result;
                    result = (temp >> 1) | updatable;
                }
            }
        }

        return result;
    }

    private static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
        int countOfBits = 0;
        int length = screen.length * 8 / width;
        int currentRow = length;
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < 8; j++) {
                if ((x1 - 1 == countOfBits || x2 - 1 == countOfBits) && y == currentRow) {
                    System.out.print(",");
                } else {

                    System.out.print(".");
                }
                if (countOfBits++ == width - 1) {
                    System.out.println();
                    countOfBits = 0;
                    currentRow--;
                }
            }
        }
    }

    private static List<Integer[]> eightQueens() {
        List<Integer[]> result = new ArrayList<>();
        Integer[] cols = new Integer[8];

        for (int col = 0; col < 8; col++) {
            cols[7] = col;
            for (int row = 6; row >= 0; row--) {
                eightQueensRec(row, cols, result);
                cols = new Integer[8];
            }
        }

        return result;
    }

    private static void eightQueensRec(int curRow, Integer[] cols, List<Integer[]> result) {
        if (curRow < 0) {
            for (Integer i : cols) {
                if (i == null) {
                    return;
                }
            }

            result.add(cols.clone());

            return;
        }

        for (int col = 0; col < 8; col++) {
            if (checkValid(col, curRow, cols)) {
                cols[curRow] = col;
                eightQueensRec(curRow - 1, cols, result);
                cols[curRow] = null;
            }
        }
    }

    private static boolean checkValid(int curCol, int curRow, Integer[] cols) {
        for (int row = curRow; row < 8; row++) {
            Integer col = cols[row];

            if (col != null) {
                if (col == curCol) {
                    return false;
                }

                if (Math.abs(col - curCol) == (row - curRow)) {
                    return false;
                }
            }

        }

        return true;
    }

    static class TreeNodeWithParent {
        int val;
        TreeNodeWithParent left;
        TreeNodeWithParent right;
        TreeNodeWithParent parent;

        TreeNodeWithParent(int i) {
            this.val = i;
        }
    }

    interface Animal {
        String getName();
    }

    static class Dog implements Animal {
        private String name;

        public Dog(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    static class Cat implements Animal {
        private String name;

        public Cat(String name) {
            this.name = name;
        }


        @Override
        public String getName() {
            return name;
        }
    }


    static class Queen{
        private String x;
        private String y;

        public Queen(String x, String y) {
            this.x = x;
            this.y = y;
        }

        public String getX() {
            return x;
        }

        public String getY() {
            return y;
        }
    }
}
