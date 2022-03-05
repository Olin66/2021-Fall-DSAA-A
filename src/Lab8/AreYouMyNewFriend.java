package Lab8;

import java.io.*;
import java.util.StringTokenizer;

public class AreYouMyNewFriend {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static long[] array;
    static int n;

    public static void main(String[] args) {
        n = in.nextInt();
        array = new long[n + 1];
        int flag;// 1-max, 2-min
        for (int i = 1; i <= n; i++) array[i] = in.nextLong();
        if (n == 2) {
            if (array[1] > array[2]) out.println("Max");
            else out.println("Min");
            out.close();
            return;
        }
        if (array[1] > array[2] && array[1] > array[3]) flag = 1;
        else if (array[1] < array[2] && array[1] < array[3]) flag = 2;
        else {
            out.println("Neither");
            out.close();
            return;
        }
        if (flag == 1 && judgeMax(1)) out.println("Max");
        else if (flag == 2 && judgeMin(1)) out.println("Min");
        else out.println("Neither");
        out.close();
    }

    static boolean judgeMax(int i) {
        if (i > n) return true;
        if ((2 * i <= n && array[i] < array[2 * i]) ||
                (2 * i + 1 <= n && array[i] < array[2 * i + 1])) return false;
        boolean left = judgeMax(2 * i);
        boolean right = judgeMax(2 * i + 1);
        return left && right;
    }

    static boolean judgeMin(int i) {
        if (i > n) return true;
        if ((2 * i <= n && array[i] > array[2 * i]) ||
                (2 * i + 1 <= n && array[i] > array[2 * i + 1])) return false;
        boolean left = judgeMin(2 * i);
        boolean right = judgeMin(2 * i + 1);
        return left && right;
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
 Problem: 1461
 User: 12013006
 Language: Java
 Result: Accepted
 Time:440 ms
 Memory:117928 kb
 ****************************************************************/

