package Lab4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Non_decreasingSequence {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static Queue<Node> queue = new LinkedList<>();
    static Node head;

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            head = new Node(0);
            Node temp = head;
            int n = in.nextInt();
            for (int j = 0; j < n; j++) {
                Node node = new Node(in.nextInt());
                temp.next = node;
                node.pre = temp;
                temp = temp.next;
            }
            Node tail = temp.next = new Node(100001);
            tail.pre = temp;
            temp = head.next;
            while (temp != tail) {
                if (temp.val > temp.next.val) {
                    queue.add(temp.pre);
                    temp.pre.critical = true;
                    Node cursor = temp;
                    while (cursor.val > cursor.next.val) cursor = cursor.next;
                    cursor.next.pre = temp.pre;
                    temp.pre.next = cursor.next;
                    temp = cursor.next;
                } else temp = temp.next;
            }
            while (!queue.isEmpty()) {
                if (!queue.peek().critical) {
                    queue.poll();
                    continue;
                }
                if (queue.peek().val > queue.peek().next.val) {
                    Node peek = queue.peek();
                    peek.critical = false;
                    while (peek.val > peek.next.val) {
                        peek = peek.next;
                        peek.critical = false;
                    }
                    if (queue.peek().pre != null) queue.peek().pre.next = peek.next;
                    peek.next.pre = queue.peek().pre;
                    queue.peek().pre.critical = true;
                    queue.add(queue.peek().pre);
                }
                queue.poll();
            }
            temp = head.next;
            while (temp!=tail){
                out.print(temp.val+" ");
                temp = temp.next;
            }
            out.println("");
        }
        out.close();
    }

    static class Node {
        int val;
        Node next;
        Node pre;
        boolean critical = false;

        public Node(int val) {
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
 Problem: 1440
 User: 12013006
 Language: Java
 Result: Accepted
 Time:516 ms
 Memory:65884 kb
 ****************************************************************/
