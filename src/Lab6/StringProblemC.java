package Lab6;

import java.io.*;
import java.util.StringTokenizer;

public class StringProblemC {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static int[] next;

    public static void main(String[] args) {
        String p = in.next();
        getNext1(p);
        for (int j = 1; j < next.length; j++) out.println(next[j]);
        out.close();
    }

    private static void getNext1(String p) {
        next = new int[p.length() + 1];
        int i = 0, k = -1;
        next[0] = -1;
        while (i <= p.length() - 1) {
            if (k == -1 || p.charAt(i) == p.charAt(k)) {
                i++;
                k++;
                next[i] = k;
            } else k = next[k];
        }
    }

    private static void getNext2(String p) {
        int m = p.length();
        next = new int[m];
        int k = 0;
        for (int q = 1; q < m; q++) {
            while (k > 0 && p.charAt(k) != p.charAt(q)) {
                k = next[--k];
            }
            if (p.charAt(k) == p.charAt(q)) k++;
            next[q] = k;
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
 Problem: 1448
 User: 12013006
 Language: Java
 Result: Accepted
 Time:412 ms
 Memory:67500 kb
 ****************************************************************/

