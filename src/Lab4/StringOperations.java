package Lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class StringOperations {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static int square;
    static Block head;

    public static void main(String[] args) {
        String ss = in.next();
        square = (int) Math.ceil(Math.pow(ss.length(), 0.5));
        Block block = new Block();
        head = block;
        Block temp = block;
        for (int i = 0; i < ss.length(); i++) {
            if (i != 0 && i % square == 0) {
                block = new Block();
                temp.next = block;
                block.pre = temp;
                temp = block;
            }
            temp.add(ss.charAt(i));
        }
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int cho = in.nextInt();
            if (cho == 1) {
                char val = in.next().charAt(0);
                int p = in.nextInt();
                insert(val, p);
            } else if (cho == 2) out.println(findNode(in.nextInt()).val);
            else {
                int l = in.nextInt();
                int r = in.nextInt();
                transform(l, r);
            }
        }
        out.close();
    }

    public static Node findNode(int p) {
        Block temp = head;
        int sum = 0;
        while (p - sum > temp.len()) {
            sum += temp.len();
            temp = temp.next;
        }
        if (temp.isTransform) temp.getNode(p - sum - 1).isTransform = true;
        return temp.getNode(p - sum - 1).transform();
    }

    public static void insert(char val, int p) {
        Block temp = head;
        int sum = 0;
        while (p - sum > temp.len()) {
            sum += temp.len();
            temp = temp.next;
        }
        temp.add(p - sum - 1, val);
        if (temp.len() == 2 * square) {
            Block block = new Block();
            block.next = temp.next;
            if (temp.next != null) temp.next.pre = block;
            block.pre = temp;
            temp.next = block;
            while (temp.len() != square) block.add(temp.remove(square));
        }
    }

    public static void transform(int l, int r) {
        int sum = 0;
        Block temp = head;
        while (l - sum > temp.len()) {
            sum += temp.len();
            temp = temp.next;
        }
        int start = l - sum - 1;
        if ((r - sum - 1) >= temp.len()) {
            do {
                temp.getNode(start).isTransform = !temp.getNode(start).isTransform;
            } while (++start != temp.len());
            while (r - sum > temp.len()) {
                sum += temp.len();
                temp = temp.next;
                if (r - sum > temp.len()) temp.isTransform = true;
            }
            start = 0;
        }
        do {
            temp.getNode(start).isTransform = !temp.getNode(start).isTransform;
        } while (++start != r - sum);
    }

    static class Block {
        ArrayList<Node> list = new ArrayList<>();
        Block next;
        Block pre;
        boolean isTransform = false;

        public void add(Node node) {
            list.add(node);
        }

        public void add(char val) {
            Node node = new Node(val);
            list.add(node);
        }

        public void add(int index, char val) {
            Node node = new Node(val);
            list.add(index, node);
        }

        public int len() {
            return list.size();
        }

        public Node getNode(int p) {
            return list.get(p);
        }

        public Node remove(int p) {
            return list.remove(p);
        }
    }

    static class Node {
        char val;
        boolean isTransform = false;

        public Node(char val) {
            this.val = val;
        }

        public Node transform() {
            char temp = this.val;
            if (isTransform) this.val = (char) ('a' + 'z' - temp);
            return this;
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
 Problem: 1438
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1676 ms
 Memory:123000 kb
 ****************************************************************/

