package Lab4;

import java.io.*;
import java.util.StringTokenizer;

public class PolynomialSummation {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        Node head = new Node((long) 1e9 + 1, (long) 1e9 + 1);
        Node tail = new Node((long) -1e9 - 1, (long) -1e9 - 1);
        Node temp = head;
        for (int i = 0; i < n; i++) {
            long coe = in.nextLong();
            long exp = in.nextLong();
            temp.next = new Node(coe, exp);
            temp = temp.next;
        }
        temp.next = tail;
        temp = head;
        for (int i = 0; i < m; i++) {
            long coe = in.nextLong();
            long exp = in.nextLong();
            while (exp < temp.next.exp) temp = temp.next;
            if (exp == temp.next.exp) temp.next.coe += coe;
            else {
                Node node = new Node(coe, exp);
                node.next = temp.next;
                temp.next = node;
            }
        }
        findNum(head, tail);
        showList(head, tail);
        out.close();
    }

    public static void findNum(Node head, Node tail) {
        long sum = 0;
        Node temp = head;
        while (true) {
            temp = temp.next;
            if (temp != tail && temp.coe != 0) sum++;
            else break;
        }
        out.println(sum);
    }

    public static void showList(Node head, Node tail) {
        Node temp = head;
        while (true) {
            temp = temp.next;
            if (temp != tail && temp.coe != 0)
                out.println(temp.coe + " " + temp.exp);
            else break;
        }
    }


    static class Node {
        long coe;
        long exp;
        Node next;

        public Node(long coe, long exp) {
            this.coe = coe;
            this.exp = exp;
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
 Problem: 1435
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1468 ms
 Memory:307180 kb
 ****************************************************************/
