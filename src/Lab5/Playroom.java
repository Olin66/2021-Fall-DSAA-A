package Lab5;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Playroom {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        String s = in.next();
        ArrayStack<Long> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') stack.push(0L);
            else {
                if (stack.peek() == 0L) {
                    stack.pop();
                    stack.push(1L);
                } else {
                    long sum = 0;
                    while (stack.peek() != 0) {
                        sum += stack.pop();
                        sum = sum % 514329;
                    }
                    stack.pop();
                    stack.push(sum * 2 % 514329);
                }
            }
        }
        long sum = stack.pop();
        while (!stack.isEmpty()) {
            sum += stack.pop();
            sum = sum % 514329;
        }
        out.println(sum);
        out.close();
    }

    static class ArrayStack<T> {
        private Object[] stack;
        private int size;

        ArrayStack() {
            stack = new Object[100000];
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
 Problem: 1445
 User: 12013006
 Language: Java
 Result: Accepted
 Time:196 ms
 Memory:20980 kb
 ****************************************************************/