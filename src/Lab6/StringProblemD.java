package Lab6;

import java.io.*;
import java.util.StringTokenizer;

public class StringProblemD {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        String text = in.next();
        String p = in.next();
        if (search(text, p)) out.println("Yes");
        else out.println("No");
        out.close();
    }

    private static boolean search(String text, String p) {
        if (text.length() != p.length()) return false;
        for (int k = 0; k < p.length(); k++) {
            int i = 0, j = k;
            while (i < text.length() && j < p.length() + k) {
                if (j == -1 || (j < p.length() && text.charAt(i) == p.charAt(j))
                        || (j >= p.length() && text.charAt(i) == p.charAt(j - p.length()))) {
                    i++;
                    j++;
                } else break;
            }
            if (j >= p.length() + k) return true;
        }
        return false;
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
 Problem: 1449
 User: 12013006
 Language: Java
 Result: Accepted
 Time:332 ms
 Memory:27532 kb
 ****************************************************************/
