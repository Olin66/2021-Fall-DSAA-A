package Lab5;

import java.io.*;
import java.util.*;

public class Cinema {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static Queue<Integer> queue1 = new LinkedList<>();
    static Queue<Integer> queue2 = new LinkedList<>();

    public static void main(String[] args) {
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < p; i++) queue1.add(in.nextInt());
        for (int i = 0; i < q; i++) queue2.add(in.nextInt());
        int time = 0;
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            time++;
            int mate1, mate2;
            if (!queue1.isEmpty()) {
                mate1 = queue1.poll();
                array[mate1 - 1] = time;
            }
            if (!queue2.isEmpty()) {
                mate2 = queue2.poll();
                array[mate2 - 1] = time;
            }
            while (!queue1.isEmpty() && array[queue1.peek() - 1] != 0) queue1.poll();
            while (!queue2.isEmpty() && array[queue2.peek() - 1] != 0) queue2.poll();
        }
        for (int i = 0; i < array.length; i++) {
            out.print(array[i]);
            if (i != array.length - 1) out.print(" ");
        }
        out.close();
    }

    static class ArrayQueue<T> {
        private static final int maxSize = 10000;
        private int front;
        private int rear;
        private Object[] array;

        public ArrayQueue() {
            array = new Object[maxSize];
            rear = front = 0;
        }

        public void clear() {
            front = rear = 0;
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public boolean isFull() {
            return (rear + 1) % array.length == front;
        }

        public int size() {
            return (rear - front + array.length) % array.length;
        }

        public T peek() {
            return (T) array[(front + 1) % array.length];
        }

        public void add(T item) {
            rear = (rear + 1) % array.length;
            array[rear] = item;
        }

        public T poll() {
            front = (front + 1) % array.length;
            return (T) array[front];
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
 Problem: 1444
 User: 12013006
 Language: Java
 Result: Accepted
 Time:484 ms
 Memory:58148 kb
 ****************************************************************/