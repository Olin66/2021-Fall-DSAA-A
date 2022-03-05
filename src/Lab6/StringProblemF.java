package Lab6;

import java.io.*;
import java.util.StringTokenizer;

public class StringProblemF {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static char[] array = new char[26];

    public static void main(String[] args) {
        for (int i = 0; i < 26; i++) array[i] = in.next().charAt(0);
        String s = in.next();
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char temp = array[s.charAt(i) - 'a'];
            sb.append(temp);
        }
        String text = sb.toString();
        out.println(len - search(text, s));
        out.close();
    }

    private static int search(String text, String p) {
        int[] next = findNext(p);
        int i = 0, j = 0;
        while (i < text.length() && j < p.length()) {
            if (j == -1 || text.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else j = next[j];
        }
        return j;
    }

    private static int[] findNext(String p) {
        int i = 0, k = -1;
        int[] next = new int[p.length()];
        next[0] = -1;
        while (i < p.length() - 1) {
            if (k == -1 || p.charAt(i) == p.charAt(k))
                next[++i] = ++k;
            else k = next[k];
        }
        return next;
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
 Problem: 1452
 User: 12013006
 Language: Java
 Result: Accepted
 Time:308 ms
 Memory:27356 kb
 ****************************************************************/

