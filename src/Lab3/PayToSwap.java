package Lab3;

import java.io.*;
import java.util.StringTokenizer;

public class PayToSwap {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static long[] temp;

    public static void main(String[] args) {
        int n = in.nextInt();
        temp = new long[n];
        long[] array = new long[n];
        for (int i = 0; i < n; i++) array[i] = in.nextLong();
        out.println(mergeSort(array, 0, n - 1));
        out.close();
    }

    private static long mergeSort(long[] array, int left, int right) {
        long result = 0;
        if (left < right) {
            int mid = (right - left) / 2 + left;
            long r1 = mergeSort(array, left, mid);
            long r2 = mergeSort(array, mid + 1, right);
            long r3 = merge(array, left, mid, right);
            result += (r1 + r2 + r3);
        }
        return result;
    }

    private static long merge(long[] array, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int t = left;
        long sum = 0;
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) temp[t] = array[i++];
            else {
                sum += (j - t) * array[j];
                temp[t] = array[j++];
            }
            t++;
        }
        while (j <= right) temp[t++] = array[j++];
        while (i <= mid) temp[t++] = array[i++];
        t = left;
        int tempL = left;
        while (tempL <= right) array[tempL++] = temp[t++];
        return sum;
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

        public String nextLine() {
            tokenizer = new StringTokenizer("");
            return innerNextLine();
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
 Problem: 1432
 User: 12013006
 Language: Java
 Result: Accepted
 Time:320 ms
 Memory:35664 kb
 ****************************************************************/
