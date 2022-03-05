package Lab4;

import java.io.*;
import java.util.StringTokenizer;

public class MinimumDifference {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static Item[] temp;

    public static void main(String[] args) {
        int n = in.nextInt();
        temp = new Item[n];
        Item[] array = new Item[n];
        Item[] origin = new Item[n];
        for (int i = 0; i < n; i++) {
            long val = in.nextLong();
            Item item = new Item(i, val);
            array[i] = item;
            origin[i] = item;
        }
        mergeSort(array, 0, n - 1);
        Item head = new Item(-1, (long) -1e9);
        Item tail = new Item(n, (long)2e9);
        for (int i = 0; i < n - 1; i++) {
            array[i].next = array[i + 1];
            array[i + 1].pre = array[i];
        }
        head.next = array[0];
        array[0].pre = head;
        array[n-1].next = tail;
        tail.pre = array[n-1];
        int[] result = new int[n - 1];
        show(n, origin, result, out);
    }

    static void show(int n, Item[] origin, int[] result, QWriter out) {
        for (int i = 0; i < n - 1; i++) {
            long val1 = Math.abs(origin[i].val - origin[i].pre.val);
            long val2 = Math.abs(origin[i].val - origin[i].next.val);
            result[i] = (int) Math.min(val1, val2);
            out.print(result[i]);
            if (i != n - 2) out.print(" ");
            origin[i].pre.next = origin[i].next;
            origin[i].next.pre = origin[i].pre;
        }
        out.flush();
    }

    public static void mergeSort(Item[] array, int left, int right) {
        if (left < right) {
            int mid = (right - left) / 2 + left;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public static void merge(Item[] array, int left, int mid, int right) {
        int p1 = left, p2 = mid + 1, k = left;
        while (p1 <= mid && p2 <= right) {
            if (array[p1].val <= array[p2].val) temp[k++] = array[p1++];
            else temp[k++] = array[p2++];
        }
        while (p1 <= mid) temp[k++] = array[p1++];
        while (p2 <= right) temp[k++] = array[p2++];
        if (right + 1 - left >= 0) System.arraycopy(temp, left, array, left, right + 1 - left);
    }

    static class Item {
        int no;
        long val;
        Item next;
        Item pre;

        public Item(int no, long val) {
            this.no = no;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "no=" + no +
                    ", val=" + val +
                    '}';
        }
    }

    static class QReader {
        private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer tokenizer = new StringTokenizer("");

        private String innerNextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public boolean hasNext() {
            while (!tokenizer.hasMoreTokens()) {
                String nextLine = innerNextLine();
                if (nextLine == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(nextLine);
            }
            return true;
        }

        public String next() {
            hasNext();
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class QWriter implements Closeable {
        private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        public void print(Object object) {
            try {
                writer.write(object.toString());
            } catch (IOException ignored) {
            }
        }

        public void println(Object object) {
            try {
                writer.write(object.toString());
                writer.write("\n");
            } catch (IOException ignored) {
            }
        }

        @Override
        public void close() {
            try {
                writer.close();
            } catch (IOException ignored) {
            }
        }

        public void flush() {
            try {
                writer.flush();
            } catch (IOException ignored) {
            }
        }
    }
}



/**************************************************************
 Problem: 1439
 User: 12013006
 Language: Java
 Result: Accepted
 Time:4780 ms
 Memory:253244 kb
 ****************************************************************/
