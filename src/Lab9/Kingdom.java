package Lab9;

import java.io.*;
import java.util.StringTokenizer;

public class Kingdom {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0;i < test;i++){
            int n = in.nextInt();
            int m = in.nextInt();
            int[][] array = new int[n][n];
            for (int j = 0;j < m;j++){
                int ii = in.nextInt()-1;
                int jj = in.nextInt()-1;
                array[jj][ii]++;
            }
            for (int j = 0;j < n;j++){
                for (int k = 0;k < n;k++){
                    out.print(array[k][j]);
                    if (k != n-1) out.print(" ");
                    else out.println("");
                }
            }
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
 Problem: 1286
 User: 12013006
 Language: Java
 Result: Accepted
 Time:844 ms
 Memory:196976 kb
 ****************************************************************/

