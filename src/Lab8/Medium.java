package Lab8;

import java.io.*;
import java.util.StringTokenizer;

public class Medium {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        int n = in.nextInt();
        long[] array = new long[n];
        long[] result = new long[n];
        Heap minHeap = new Heap(n, false);//小顶堆
        Heap maxHeap = new Heap(n, true);//大顶堆
        for (int i = 0; i < n; i++) array[i] = in.nextLong();
        long mid = array[0];
        result[0] = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i] > mid) minHeap.add(array[i]);
            else maxHeap.add(array[i]);
            if (Math.abs(maxHeap.size() - minHeap.size()) >= 2) {
                if (maxHeap.size() > minHeap.size()) {
                    minHeap.add(mid);
                    mid = maxHeap.poll();
                } else {
                    maxHeap.add(mid);
                    mid = minHeap.poll();
                }
            }
            if (i % 2 == 0) result[i] = mid;
            else result[i] = maxHeap.size() > minHeap.size() ? maxHeap.peak() : mid;
        }
        for (int i = 0; i < n; i++) {
            out.print(result[i]);
            if (i != n - 1) out.print(" ");
        }
        out.close();
    }

    static class Heap {
        int size;
        long[] array;
        boolean flag = false;//默认为小顶堆

        Heap(int n, boolean flag) {
            array = new long[n + 1];
            size = 0;
            this.flag = flag;
        }

        void add(long val) {
            array[++size] = val;
            siftUp(size);
        }

        long size() {
            return this.size;
        }

        long peak() {
            return array[1];
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
            if (flag) return a > b;
            else return a < b;
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
 Problem: 1464
 User: 12013006
 Language: Java
 Result: Accepted
 Time:588 ms
 Memory:110828 kb
 ****************************************************************/

