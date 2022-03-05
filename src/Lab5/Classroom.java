package Lab5;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Classroom {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static ArrayStack<Character> stack;

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            stack = new ArrayStack<>();
            int n = in.nextInt();
            boolean flag = true;
            String s = in.next();
            for (int j = 0;j < n;j++) {
                char c = s.charAt(j);
                if (c == '(' || c == '[' || c == '{') stack.push(c);
                else {
                    if (stack.isEmpty()){
                        flag = false;
                        break;
                    }
                    if (c == ')') {
                        if (stack.pop() != '(') flag = false;
                    } else if (c == ']') {
                        if (stack.pop() != '[') flag = false;
                    } else {
                        if (stack.pop() != '{') flag = false;
                    }
                }
            }
            if (!stack.isEmpty()) flag = false;
            if (flag) out.println("YES");
            else out.println("NO");
        }
        out.close();
    }

    static class ArrayStack<T> {
        //实现栈的数组
        private Object[] stack;
        //数组大小
        private int size;

        ArrayStack() {
            stack = new Object[30000];
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
 Problem: 1357
 User: 12013006
 Language: Java
 Result: Accepted
 Time:132 ms
 Memory:20224 kb
 ****************************************************************/