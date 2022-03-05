package Lab6;

import java.io.*;
import java.util.StringTokenizer;

public class StringProblemE {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        String s = in.next();
        out.println(find(s));
        out.close();
    }

    private static int find(String s) {
        String ss = preProcess(s);
        int[] array = new int[ss.length()];
        int right = -1, mid = -1;
        for (int i = 0; i < ss.length(); i++) {
            int n = 1;
            if (i <= right) n = Math.min(array[mid] - i + mid, array[2 * mid - i]);
            while (i - n >= 0 && i + n < ss.length() && ss.charAt(i - n) == ss.charAt(i + n)) n++;
            if (n + i - 1 > right) {
                right = n + i - 1;
                mid = i;
            }
            array[i] = n;
        }
        int max = 0;
        for (int n : array) max = Math.max(max, n);
        return max - 1;
    }

    private static String preProcess(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append("*");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append("*");
        }
        return sb.toString();
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
 Problem: 1450
 User: 12013006
 Language: Java
 Result: Accepted
 Time:236 ms
 Memory:22996 kb
 ****************************************************************/

