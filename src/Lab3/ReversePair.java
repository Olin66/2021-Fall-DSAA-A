package Lab3;

import java.io.*;
import java.util.StringTokenizer;

public class ReversePair {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            long n = in.nextLong();
            long m = in.nextLong();
            long[] arr1 = new long[(int) n];
            long[] arr2 = new long[(int) m];
            for (int k = 0; k < n; k++) arr1[k] = in.nextLong();
            for (int k = 0; k < m; k++) arr2[k] = in.nextLong();
            long[] array = new long[(int) (n + m)];
            int aI = 0, bI = 0, index = 0;
            long sum = 0;
            while (true) {
                if (arr1[aI] > arr2[bI]) {
                    array[index] = arr2[bI];
                    bI++;
                } else {
                    sum += bI;
                    array[index] = arr1[aI];
                    aI++;
                }
                index++;
                if (aI == n) {
                    System.out.println(sum);
                    do {
                        array[index] = arr2[bI];
                        bI++;
                        index++;
                    } while (index != n + m);
                    break;
                }
                if (bI == m) {
                    sum += (n - aI) * m;
                    System.out.println(sum);
                    do {
                        array[index] = arr1[aI];
                        aI++;
                        index++;
                    } while (index != n + m);
                    break;
                }
            }
            for (int k = 0; k < array.length; k++) {
                out.print(array[k]);
                if (k != array.length - 1) out.print(" ");
            }
            out.print("\n");
            out.flush();
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
 Problem: 1428
 User: 12013006
 Language: Java
 Result: Accepted
 Time:960 ms
 Memory:232560 kb
 ****************************************************************/
