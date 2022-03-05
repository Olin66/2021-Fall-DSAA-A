package Lab8;

import java.io.*;
import java.util.StringTokenizer;

public class Huffman {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static PriorityQueue queue;

    public static void main(String[] args) {
        int n = in.nextInt();
        long sum = 0;
        queue = new PriorityQueue(n);
        for (int i = 0; i < n; i++) queue.add(in.nextLong());
        while (queue.size() != 1) {
            assert queue.isEmpty();
            long num1 = queue.poll();
            assert queue.isEmpty();
            long num2 = queue.poll();
            long num = num1 + num2;
            sum += num;
            queue.add(num);
        }
        out.println(sum);
        out.close();
    }

    static class PriorityQueue {
        int size;
        int capacity;
        long[] array;

        PriorityQueue(int n) {
            array = new long[n + 1];
            size = 0;
            capacity = n;
        }

        int size() {
            return this.size;
        }

        void add(long val) {
            size++;
            array[size] = val;
            siftUp(size);
        }

        long poll() {
            long temp = array[1];
            array[1] = array[size];
            array[size--] = array[1];
            siftDown();
            return temp;
        }

        boolean isEmpty() {
            return size == 0;
        }

        boolean compare(long a, long b) {
            return a < b;
        }

        void siftUp(int n) {
            while (n > 1 && compare(array[n], array[n >> 1])) {
                long temp = array[n];
                array[n] = array[n >> 1];
                array[n >> 1] = temp;
                n >>= 1;
            }
        }

        void siftDown() {
            int n = 1;
            while (2 * n <= size) {
                int k = 2 * n;
                if (k < size && compare(array[k + 1], array[k])) k++;
                if (compare(array[k], array[n])) {
                    long temp = array[n];
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
 Problem: 1463
 User: 12013006
 Language: Java
 Result: Accepted
 Time:524 ms
 Memory:63400 kb
 ****************************************************************/

