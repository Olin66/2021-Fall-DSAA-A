package Lab5;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class FencingHall {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static MaxPriorQueue max = new MaxPriorQueue();
    static MinPriorQueue min = new MinPriorQueue();

    public static void main(String[] args) {
        long k = in.nextLong();
        int n = in.nextInt();
        int j = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            long val = in.nextLong();
            min.add(val, i);
            max.add(val, i);
            long critical = Math.abs(max.peek() - min.peek());
            if (critical <= k) sum = Math.max(sum, i - j + 1);
            else {
                min.remove(j);
                max.remove(j);
                j++;
            }
        }
        out.println(sum);
        out.close();
    }

    static class MaxPriorQueue {
        LinkedList<Long> list = new LinkedList<>();
        private long max = 0L;

        public void add(long val, int index) {
            list.add(index, val);
            if (val > max) max = val;
        }

        public void remove(int index) {
            if (list.get(index) == max) {
                list.set(index, 0L);
                max = Collections.max(list);
            } else list.set(index, 0L);
        }

        public long peek() {
            return this.max;
        }
    }

    static class MinPriorQueue {
        LinkedList<Long> list = new LinkedList<>();
        private long min = 2000000000L;

        public void add(long val, int index) {
            list.add(index, val);
            if (val < min) min = val;
        }

        public void remove(int index) {
            if (list.get(index) == min) {
                list.set(index, 2000000000L);
                min = Collections.min(list);
            } else list.set(index, 2000000000L);
        }

        public long peek() {
            return this.min;
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
 Problem: 1376
 User: 12013006
 Language: Java
 Result: Accepted
 Time:2388 ms
 Memory:460500 kb
 ****************************************************************/
