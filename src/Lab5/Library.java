package Lab5;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Library {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static Deque[] array;

    public static void main(String[] args) {
        while (in.hasNext()) {
            int n = in.nextInt();
            int q = in.nextInt();
            array = new Deque[n];
            for (int i = 0;i < q;i++){
                int cho = in.nextInt();
                if (cho==1){
                    int u = in.nextInt();
                    int w = in.nextInt();
                    int val = in.nextInt();
                    if (array[u-1]==null) array[u-1] = new Deque();
                    if (w==0) array[u-1].addHead(val);
                    else array[u-1].addRear(val);
                } else if (cho==2){
                    int u = in.nextInt();
                    int w = in.nextInt();
                    if (array[u-1]==null || array[u-1].isEmpty()) out.println(-1);
                    else {
                        if (w==0) out.println(array[u-1].pollHead());
                        else out.println(array[u-1].pollRear());
                    }
                }else {
                    int u = in.nextInt();
                    int v = in.nextInt();
                    int w = in.nextInt();
                    if (array[v-1]==null||array[v-1].isEmpty()) continue;
                    if (array[u-1]==null) array[u-1] = new Deque();
                    int size = array[v-1].size();
                    if (w==0){
                        for (int j = 0;j < size;j++) array[u-1].addRear(array[v-1].pollHead());
                    }else {
                        for (int j = 0;j < size;j++) array[u-1].addRear(array[v-1].pollRear());
                    }
                }
            }
        }
        out.close();
    }

    static  class Deque {
        private LinkedList<Integer> list = new LinkedList<>();

        public int size() {
            return list.size();
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public void addHead(int val) {
            list.add(0, val);
        }

        public void addRear(int val) {
            list.add(val);
        }

        public int pollHead() {
            return list.remove(0);
        }

        public int pollRear() {
            return list.remove(list.size() - 1);
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
 Problem: 1342
 User: 12013006
 Language: Java
 Result: Accepted
 Time:644 ms
 Memory:125328 kb
 ****************************************************************/

