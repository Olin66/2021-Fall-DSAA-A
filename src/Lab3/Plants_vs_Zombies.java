package Lab3;

import java.io.*;
import java.util.*;

public class Plants_vs_Zombies {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        int sum = 0;
        HashMap<Integer, Long> map = new HashMap<>(n);
        long[] heights = new long[n];
        long[] strengths = new long[n];
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            heights[i] = in.nextLong();
            strengths[i] = in.nextLong();
            arr[i] = strengths[i];
        }
        if (q == 0) {
            out.println(Arrays.stream(strengths).sum());
            out.close();
            return;
        }
        for (int i = 0; i < n; i++) map.put(i, heights[i] - strengths[i]);
        List<Map.Entry<Integer, Long>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        for (Map.Entry<Integer, Long> mapping : list) if (mapping.getValue() > 0) sum++;
        if (sum == 0) {
            long pre = Arrays.stream(strengths).sum();
            long max = pre;
            for (int i = 0; i < n; i++) {
                long a = pre + doubleH(heights[i], p) - strengths[i];
                max = Math.max(a, max);
            }
            out.println(max);
            out.close();
            return;
        }
        int swap = Math.min(sum, q);
        for (int i = 0; i < swap; i++) {
            int index = list.get(i).getKey();
            strengths[index] = heights[index];
        }
        long pre = Arrays.stream(strengths).sum();
        long max = pre;
        for (int i = 0; i < list.size(); i++) {
            int index = list.get(i).getKey();
            long temp;
            if (i < swap) temp = doubleH(heights[index], p) - heights[index] + pre;
            else {
                if (q > sum) temp = pre + doubleH(heights[index], p) - arr[index];
                else {
                    int j = list.get(swap - 1).getKey();
                    temp = pre + arr[j] - heights[j] +
                            doubleH(heights[index], p) - arr[index];
                }
            }
            max = Math.max(temp, max);
        }
        out.println(max);
        out.close();
    }

    private static long doubleH(long height, int p) {
        for (int i = 0; i < p; i++) height *= 2;
        return height;
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

        public String nextLine() {
            tokenizer = new StringTokenizer("");
            return innerNextLine();
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
 Problem: 1433
 User: 12013006
 Language: Java
 Result: Accepted
 Time:964 ms
 Memory:84044 kb
 ****************************************************************/
