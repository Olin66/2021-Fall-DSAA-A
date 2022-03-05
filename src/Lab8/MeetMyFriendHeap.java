package Lab8;

import java.io.*;
import java.util.StringTokenizer;

public class MeetMyFriendHeap {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        int n = in.nextInt();
        long[] array = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = in.nextInt();
            if (i == 1) {
                out.print(0);
                if (i != n) out.print(" ");
                continue;
            }
            int sum = 0, j = i;
            while (j != 1) {
                if (j % 2 == 0 && array[j] > array[j / 2]) {
                    long temp = array[j];
                    array[j] = array[j / 2];
                    array[j / 2] = temp;
                    j /= 2;
                    sum++;
                } else if (j % 2 == 1 && array[j] > array[(j - 1) / 2]) {
                    long temp = array[j];
                    array[j] = array[(j - 1) / 2];
                    array[(j - 1) / 2] = temp;
                    j = (j - 1) / 2;
                    sum++;
                } else break;
            }
            out.print(sum);
            if (i != n) out.print(" ");
        }
        out.close();
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
 Problem: 1460
 User: 12013006
 Language: Java
 Result: Accepted
 Time:488 ms
 Memory:58056 kb
 ****************************************************************/

