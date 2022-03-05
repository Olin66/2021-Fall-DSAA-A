package Lab5;

import java.io.*;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Canteen {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        int time = in.nextInt();
        ArrayStack<String> stack = new ArrayStack<>();
        ArrayQueue<String> queue = new ArrayQueue<>();
        for (int i = 0; i < time; i++) {
            String ss = in.next();
            if (ss.equals("NewComer")) {
                queue.enQueue(in.next());
            } else if (ss.equals("NewFood")) stack.push(in.next());
            else {
                if (stack.isEmpty() || queue.isEmpty()) continue;
                String food = stack.peek();
                String student = queue.deQueue();
                if (food.equals(student)) stack.pop();
                else queue.enQueue(student);
            }
        }
        while (!queue.isEmpty() && !stack.isEmpty()) {
            //队列里的全部都不等于栈顶
            boolean flag = false;
            String topFood = stack.peek();
            for (int i = 0;i < queue.size();i++){
                String temp = queue.deQueue();
                if (temp.equals(topFood)) flag=true;
                queue.enQueue(temp);
            }
            if (!flag) break;
            String student = queue.deQueue();
            String food = stack.peek();
            if (food.equals(student)) stack.pop();
            else queue.enQueue(student);
        }
        if (queue.size() == 0) out.println("Qi Fei!");
        else out.println(queue.size());
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
            if (isEmpty())
                throw new NoSuchElementException();
            return (T) array[(front + 1) % array.length];
        }

        public void enQueue(T item) {
            if (isFull())
                throw new ArrayIndexOutOfBoundsException();
            rear = (rear + 1) % array.length;
            array[rear] = item;
        }

        public T deQueue() {
            if (isEmpty())
                throw new NoSuchElementException();
            front = (front + 1) % array.length;
            return (T) array[front];
        }
    }


    static class ArrayStack<T> {
        private Object[] stack;
        private int size;

        ArrayStack() {
            stack = new Object[10000];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public T peek() {
            T t = null;
            if (size > 0)
                t = (T) stack[size - 1];
            return t;
        }

        public void push(T t) {
            expandCapacity(size + 1);
            stack[size] = t;
            size++;
        }

        public T pop() {
            T t = peek();
            if (size > 0) {
                stack[size - 1] = null;
                size--;
            }
            return t;
        }

        public void expandCapacity(int size) {
            int len = stack.length;
            if (size > len) {
                size = size * 3 / 2 + 1;
                stack = Arrays.copyOf(stack, size);
            }
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
 Problem: 1443
 User: 12013006
 Language: Java
 Result: Accepted
 Time:904 ms
 Memory:28100 kb
 ****************************************************************/
