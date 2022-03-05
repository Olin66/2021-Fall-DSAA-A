package Lab10;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RoadConstruction {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static Vertex[] arr;
    static int cnt, total, index, cur;
    static int[] init, dfn, low, stack, num, num1, num2;
    static boolean[] vis;

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        num1 = new int[m + 1];
        num2 = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            num1[i] = in.nextInt();
            num2[i] = in.nextInt();
        }
        int l = 1;
        int r = m;
        while (l < r) {
            stack = new int[250000];
            cnt = 0;
            total = 0;
            index = 0;
            cur = 0;
            int mid = (l + r) / 2;
            arr = new Vertex[250001];
            vis = new boolean[n + 1];
            num = new int[n + 1];
            dfn = new int[n + 1];
            low = new int[n + 1];
            init = new int[n + 1];
            Arrays.fill(init, -1);
            for (int i = 1; i <= 250000; i++) arr[i] = new Vertex(i);
            for (int i = 1; i <= mid; i++) add(num1[i], num2[i], arr);
            for (int i = 1; i <= n; i++) if (dfn[i] == 0) Tarjan(i);
            if (cur == 1) r = mid;
            else l = mid + 1;
        }
        stack = new int[250000];
        cnt = 0;
        total = 0;
        index = 0;
        cur = 0;
        arr = new Vertex[250001];
        vis = new boolean[n + 1];
        num = new int[n + 1];
        dfn = new int[n + 1];
        low = new int[n + 1];
        init = new int[n + 1];
        Arrays.fill(init, -1);
        for (int i = 1; i <= 250000; i++) arr[i] = new Vertex(i);
        for (int i = 1; i <= l; i++) add(num1[i], num2[i], arr);
        for (int i = 1; i <= n; i++) if (dfn[i] == 0) Tarjan(i);
        if (cur != 1) out.println(-1);
        else out.println(l);
        out.close();
    }

    static void Tarjan(int k) {
        low[k] = dfn[k] = ++total;
        vis[k] = true;
        stack[++index] = k;
        for (int i = init[k]; i != -1; i = arr[i].next) {
            int end = arr[i].end;
            if (dfn[end] == 0) {
                Tarjan(end);
                low[k] = Math.min(low[k], low[end]);
            } else if (vis[end]) low[k] = Math.min(low[k], dfn[end]);
        }
        if (low[k] == dfn[k]) {
            cur++;
            do {
                vis[stack[index]] = false;
                num[cur]++;
                index--;
            } while (k != stack[index + 1]);
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
 Problem: 1471
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1040 ms
 Memory:481816 kb
 ****************************************************************/
