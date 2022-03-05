package Lab8;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class WeOnlyWantTheSmallest {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        long n = in.nextLong();
        long m = in.nextLong();
        int k = in.nextInt();
        PriorityQueue queue = new PriorityQueue((int) Math.min(500000, n * m));
        ArrayList<Long> result = new ArrayList<>(k);
        Long[] array1 = new Long[(int) n];
        Long[] array2 = new Long[(int) m];
        int[] columns = new int[(int) m];
        for (int i = 0; i < n; i++) array1[i] = in.nextLong();
        for (int i = 0; i < m; i++) array2[i] = in.nextLong();
        shellSort(array1);
        shellSort(array2);
        for (int i = 0; i < m; i++) {
            Node node = new Node(i, array1[0] * array2[i]);
            queue.add(node);
            columns[i]++;
        }
        while (true) {
            Node node = queue.poll();
            if (columns[queue.col] >= array1.length) {
                boolean flag = false;
                queue.col++;
                while (true) {
                    if (queue.col < array2.length) {
                        if (columns[queue.col] < array1.length) break;
                        else queue.col++;
                    } else {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    result.add(node.val);
                    break;
                }
            }
            long val = array2[queue.col] * array1[columns[queue.col]++];
            queue.add(new Node(queue.col, val));
            result.add(node.val);
            if (result.size() >= k) break;
        }
        while (result.size() < k) result.add(queue.poll().val);
        for (int i = 0; i < k; i++) {
            out.print(result.get(i));
            if (i != k - 1) out.print(" ");
        }
        out.close();
    }

    static void shellSort(Long[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int j = i;
                long temp = array[j];
                while (j - gap >= 0 && temp < array[j - gap]) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = temp;
            }
        }
    }

    static class Node {
        int col;
        long val;

        Node(int col, long val) {
            this.col = col;
            this.val = val;
        }
    }

    static class PriorityQueue {
        int size;
        int capacity;
        int col;
        Node[] array;

        PriorityQueue(int n) {
            array = new Node[n + 1];
            size = 0;
            capacity = n;
        }

        void add(Node val) {
            array[++size] = val;
            siftUp(size);
        }

        Node poll() {
            Node temp = array[1];
            this.col = temp.col;
            array[1] = array[size];
            array[size--] = array[1];
            siftDown();
            return temp;
        }

        boolean compare(long a, long b) {
            return a < b;
        }

        void siftUp(int n) {
            while (n > 1 && compare(array[n].val, array[n >> 1].val)) {
                Node temp = array[n];
                array[n] = array[n >> 1];
                array[n >> 1] = temp;
                n >>= 1;
            }
        }

        void siftDown() {
            int n = 1;
            while (2 * n <= size) {
                int k = 2 * n;
                if (k < size && compare(array[k + 1].val, array[k].val)) k++;
                if (compare(array[k].val, array[n].val)) {
                    Node temp = array[n];
                    array[n] = array[k];
                    array[k] = temp;
                    n = k;
                } else break;
            }
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
 Problem: 1462
 User: 12013006
 Language: Java
 Result: Accepted
 Time:2144 ms
 Memory:334512 kb
 ****************************************************************/
