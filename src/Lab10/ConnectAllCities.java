package Lab10;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ConnectAllCities {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static Vertex[] arr1, arr2;
    static int cnt = 0, total = 0, index = 0, cur = 0;
    static int[] init, dfn, low, stack, be, num, inde, outde;
    static boolean[] vis;

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        arr1 = new Vertex[240001];
        arr2 = new Vertex[240001];
        vis = new boolean[n + 1];
        be = new int[n + 1];
        num = new int[n + 1];
        dfn = new int[n + 1];
        low = new int[n + 1];
        init = new int[n + 1];
        stack = new int[240001];
        inde = new int[n + 1];
        outde = new int[n + 1];
        for (int i = 1; i <= 240000; i++) {
            arr1[i] = new Vertex(i);
            arr2[i] = new Vertex(i);
        }
        Arrays.fill(init, -1);
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            add(x, y, arr1);
        }
        for (int i = 1; i <= n; i++) if (dfn[i] == 0) Tarjan(i);
        if (cur == 1) {
            out.println(0);
            out.close();
            return;
        }
        Arrays.fill(init, -1);
        for (int i = 1; i <= m; i++) {
            int x = be[arr1[i].sta];
            int y = be[arr1[i].end];
            if (x != y) {
                add(x, y, arr2);
                inde[y]++;
                outde[x]++;
            }
        }
        int a = 0, b = 0;
        for (int i = 1; i <= cur; i++) {
            if (inde[i] == 0) a++;
            if (outde[i] == 0) b++;
        }
        out.println(Math.max(a, b));
        out.close();
    }

    static void Tarjan(int k) {
        low[k] = dfn[k] = ++total;
        vis[k] = true;
        stack[++index] = k;
        for (int i = init[k]; i != -1; i = arr1[i].next) {
            int end = arr1[i].end;
            if (dfn[end] == 0) {
                Tarjan(end);
                low[k] = Math.min(low[k], low[end]);
            } else if (vis[end]) low[k] = Math.min(low[k], dfn[end]);
        }
        if (low[k] == dfn[k]) {
            cur++;
            do {
//                System.out.print(stack[index] + " ");
                vis[stack[index]] = false;
                be[stack[index]] = cur;
                num[cur]++;
                index--;
            } while (k != stack[index + 1]);
//            System.out.println();
        }
    }

    static void add(int x, int y, Vertex[] arr) {
        arr[++cnt].next = init[x];
        arr[cnt].sta = x;
        arr[cnt].end = y;
        init[x] = cnt;
    }

    static class Vertex {
        int no;
        int sta;
        int end;
        int next;

        Vertex(int no) {
            this.no = no;
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

        public void println() {
            println("");
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
    }
}

/**************************************************************
 Problem: 1475
 User: 12013006
 Language: Java
 Result: Accepted
 Time:412 ms
 Memory:66520 kb
 ****************************************************************/
