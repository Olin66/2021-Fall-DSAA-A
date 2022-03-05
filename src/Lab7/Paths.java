package Lab7;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Paths {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        int n = in.nextInt();
        Node[] nodes = new Node[n];
        long num = in.nextLong();
        for (int i = 0; i < n - 1; i++) {
            int num1 = in.nextInt();
            int num2 = in.nextInt();
            long weight = in.nextLong();
            if (nodes[num1 - 1] == null) nodes[num1 - 1] = new Node();
            if (nodes[num2 - 1] == null) nodes[num2 - 1] = new Node();
            nodes[num1 - 1].list.add(nodes[num2 - 1]);
            nodes[num1 - 1].weights.add(weight);
            nodes[num2 - 1].list.add(nodes[num1 - 1]);
            nodes[num2 - 1].weights.add(weight);
        }
        long sum = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes[0]);
        nodes[0].isVisit = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node root = queue.poll();
                assert root != null;
                root.isVisit = true;
                if (root.isLeaf()) {
                    if (root.weight == num) sum++;
                    continue;
                }
                int listSize = root.list.size();
                for (int j = 0; j < listSize; j++) {
                    Node node = root.list.get(j);
                    if (node.isVisit) continue;
                    node.weight = root.weight + root.weights.get(j);
                    queue.add(node);
                }
            }
        }
        out.println(sum);
        out.close();
    }

    static class Node {
        long weight;
        boolean isVisit = false;
        ArrayList<Node> list = new ArrayList<>();
        ArrayList<Long> weights = new ArrayList<>();

        boolean isLeaf() {
            return list.size() == 1 && list.get(0).isVisit;
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
 Problem: 1453
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1460 ms
 Memory:210644 kb
 ****************************************************************/
