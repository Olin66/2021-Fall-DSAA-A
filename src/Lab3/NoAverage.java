package Lab3;

import java.io.*;
import java.util.StringTokenizer;

public class NoAverage {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        int n = in.nextInt();
        Long[] input = new Long[n];
        long[] array = new long[n];
        for (int i = 0; i < n; i++) input[i] = in.nextLong();
        shellSort(input);
        for (int i = 0, k1 = 0, k2 = n / 2; i < n; i++) {
            if (i % 2 == 0) array[i] = input[k1++];
            else array[i] = input[k2++];
        }
        for (int k = 0; k < n; k++) {
            out.print(array[k]);
            if (k != array.length - 1) out.print(" ");
        }
        out.print("\n");
        out.flush();
    }

    public static void shellSort(Long[] array) {
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
 Problem: 1431
 User: 12013006
 Language: Java
 Result: Accepted
 Time:2108 ms
 Memory:382640 kb
 ****************************************************************/
