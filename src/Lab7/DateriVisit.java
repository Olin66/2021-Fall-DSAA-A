package Lab7;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class DateriVisit {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static Node[] array;
    static int N;
    static ArrayList<Node> leaves = new ArrayList<>();
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        N = in.nextInt();
        array = new Node[N];
        for (int i = 0; i < N; i++) array[i] = new Node(i + 1);
        for (int i = 0; i < N - 1; i++) {
            int num1 = in.nextInt();
            int num2 = in.nextInt();
            array[num1 - 1].list.add(array[num2 - 1]);
            array[num2 - 1].list.add(array[num1 - 1]);
        }
        int k = 0;
        if (N == 2) {
            in.nextInt();
            out.print("1 2 1");
            out.close();
            return;
        }
        for (int i = 0; i < N; i++) if (i != 0 && array[i].list.size() == 1) k++;
        for (int i = 0; i < k; i++) leaves.add(array[in.nextInt() - 1]);
        LinkedList<Integer> stack = new LinkedList<>();
        findNode(array[0], leaves.get(0), stack);
        stack.poll();
        Collections.reverse(stack);
        result.addAll(stack);
        for (int j = 0; j < N; j++) array[j].flag = false;
        stack.clear();
        for (int i = 0; i < k - 1; i++) {
            findNode(leaves.get(i), leaves.get(i + 1), stack);
            stack.poll();
            Collections.reverse(stack);
            result.addAll(stack);
            for (int j = 0; j < N; j++) array[j].flag = false;
            stack.clear();
        }
        findNode(leaves.get(k - 1), array[0], stack);
        Collections.reverse(stack);
        result.addAll(stack);
        if (result.size() != 2 * N - 1) out.println(-1);
        else {
            for (int i = 0; i < 2 * N - 1; i++) {
                out.print(result.get(i));
                if (i != 2 * N - 2) out.print(" ");
            }
        }
        out.close();
    }

    static boolean findNode(Node cur, Node tar, LinkedList<Integer> stack) {
        cur.flag = true;
        stack.push(cur.val);
        ArrayList<Node> list = cur.list;
        if (cur == tar) return true;
        for (Node node : list) {
            if (!node.flag && findNode(node, tar, stack)) return true;
        }
        stack.poll();
        return false;
    }

    static class Node {
        int val;
        boolean flag = false;
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
 Problem: 1457
 User: 12013006
 Language: Java
 Result: Accepted
 Time:756 ms
 Memory:182920 kb
 ****************************************************************/
