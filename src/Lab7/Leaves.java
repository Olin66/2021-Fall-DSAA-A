package Lab7;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Leaves {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = in.nextInt();
        Node[] nodes = new Node[1000000];
        for (int i = 0; i < n - 1; i++) {
            int num1 = in.nextInt();
            int num2 = in.nextInt();
            if (nodes[num1 - 1] == null) nodes[num1 - 1] = new Node();
            else nodes[num1 - 1].isLeaf = false;
            if (nodes[num2 - 1] == null) nodes[num2 - 1] = new Node();
            else nodes[num2 - 1].isLeaf = false;
        }
        for (int i = 1; i < n; i++) if (nodes[i].isLeaf) result.add(i + 1);
        for (int i = 0; i < result.size(); i++) {
            if (i != result.size() - 1) out.print(result.get(i) + " ");
            else out.print(result.get(i));
        }
        out.close();
    }

    static class Node {
        boolean isLeaf = true;
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
 Problem: 1454
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1240 ms
 Memory:134284 kb
 ****************************************************************/
