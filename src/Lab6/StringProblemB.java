package Lab6;

import java.io.*;
import java.util.StringTokenizer;

public class StringProblemB {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static int[][] array;
    static String p;

    public static void main(String[] args) {
        p = in.next();
        array = new int[p.length()][26];
        findTransition();
        for (int[] ints : array) {
            for (int anInt : ints) out.print(anInt + " ");
            out.println("");
        }
        out.close();
    }

    private static void findTransition() {
        int m = p.length(), X = 0;
        array[0][p.charAt(0) - 'a'] = 1;
        for (int j = 1; j <= m - 1; j++) {
            for (int a = 0; a < 26; a++) {
                char c = (char) (a + 'a');
                if (p.charAt(j) == c) array[j][a] = j + 1;
                else array[j][a] = array[X][a];
            }
            X = array[X][p.charAt(j) -'a'];
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
 Problem: 1447
 User: 12013006
 Language: Java
 Result: Accepted
 Time:488 ms
 Memory:142272 kb
 ****************************************************************/

