package Lab7;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MinimalTime {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static Node[] array;
    static Node maxNode;
    static int max;

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int t = 0; t < test; t++) {
            int n = in.nextInt();//number of cities
            int k = in.nextInt();//number of people
            array = new Node[n];
            for (int i = 0; i < n; i++) array[i] = new Node(i + 1);
            for (int i = 0; i < n - 1; i++) {
                int num1 = in.nextInt();
                int num2 = in.nextInt();
                array[num1 - 1].list.add(array[num2 - 1]);
                array[num2 - 1].list.add(array[num1 - 1]);
            }
            Node temp = null;
            for (int i = 0; i < k; i++) {
                int index = in.nextInt() - 1;
                array[index].check = true;
                if (i == 0) temp = array[index];
            }
            if (k == 1) out.println(0);
            else if (n == 2) out.println(1);
            else {
                assert temp != null;
                findMax1(temp, 0);
                if (k == 2) {
                    max = max % 2 == 0 ? max / 2 : max / 2 + 1;
                    out.println(max);
                    max = 0;
                    continue;
                }
                findMax2(maxNode, 0);
                max = max % 2 == 0 ? max / 2 : max / 2 + 1;
                out.println(max);
                maxNode = null;
                max = 0;
            }
        }
        out.close();
    }

    static void findMax1(Node cur, int deep) {
        if (cur.check && max < deep) {
            max = deep;
            maxNode = cur;
        }
        cur.flag1 = true;
        ArrayList<Node> list = cur.list;
        for (Node node : list) {
            if (!node.flag1) findMax1(node, deep + 1);
        }
    }

    static void findMax2(Node cur, int deep) {
        if (cur.check && max < deep) {
            max = deep;
            maxNode = cur;
        }
        cur.flag2 = true;
        ArrayList<Node> list = cur.list;
        for (Node node : list) {
            if (!node.flag2) findMax2(node, deep + 1);
        }
    }

    static class Node {
        int val;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean check = false;
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
 Problem: 1458
 User: 12013006
 Language: Java
 Result: Accepted
 Time:940 ms
 Memory:121488 kb
 ****************************************************************/
