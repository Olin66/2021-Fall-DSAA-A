package Lab9;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CyclesAreBad {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static Queue<Node> queue = new LinkedList<>();
    static int sum = 0;

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        Node[] array = new Node[n + 1];
        for (int i = 1; i <= n; i++) array[i] = new Node(i);
        for (int i = 0; i < m; i++) {
            int j = in.nextInt();
            int k = in.nextInt();
            array[j].degree++;
            array[j].list.add(array[k]);
            array[k].degree++;
            array[k].list.add(array[j]);
        }
        for (int i = 1; i <= n; i++) {
            if (array[i].degree <= 1) {
                queue.add(array[i]);
                array[i].isVisited = true;
                sum++;
            }
        }
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            for (Node node : temp.list) {
                if (--node.degree <= 1 && !node.isVisited) {
                    queue.add(node);
                    node.isVisited = true;
                    sum++;
                }
            }
        }
        if (sum == n) out.println("Good");
        else out.println("Bad");
        out.close();
    }

    static class Node {
        int val;
        int degree = 0;
        boolean isVisited = false;
        ArrayList<Node> list = new ArrayList<>();

        Node(int val) {
            this.val = val;
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
 Problem: 1388
 User: 12013006
 Language: Java
 Result: Accepted
 Time:556 ms
 Memory:62972 kb
 ****************************************************************/
