package Lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ConnectedChain {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        Node[] array = new Node[n];
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long val = in.nextLong();
            array[i] = new Node(val);
        }
        for (int i = 0; i < p; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            array[a - 1].link(array[b - 1]);
        }
        for (int i = 0; i < n; i++) {
            if (array[i].isHead && array[i].len >= q) {
                list.add(array[i].findNode(q));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            out.print(list.get(i));
            if (i != list.size() - 1) out.print(" ");
        }
        out.flush();
    }

    static class Node {
        long val;
        Node next;
        Node tail = this;
        int len = 1;
        boolean isHead = false;

        public Node(long val) {
            this.val = val;
        }

        public void link(Node node) {
            this.isHead = true;
            node.isHead = false;
            this.tail.next = node;
            this.tail = node.tail;
            this.len += node.len;
        }

        public long findNode(int q) {
            Node temp = this;
            for (int i = 1; i < q; i++) {
                temp = temp.next;
            }
            return temp.val;
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
 Problem: 1442
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1116 ms
 Memory:198560 kb
 ****************************************************************/
