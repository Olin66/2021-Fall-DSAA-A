package Lab10;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Portal {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static long MAX = 10000000000000000L;
    static int n;
    static int m;
    static int p;
    static int k;

    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        p = in.nextInt();
        k = in.nextInt();
        Graph graph = new Graph();
        for (int i = 0; i < m; i++) {
            int num1 = in.nextInt();
            int num2 = in.nextInt();
            long val = in.nextLong();
            graph.addEdge(num1, num2, val);
        }
        for (int i = 0; i < p; i++) {
            int num1 = in.nextInt();
            int num2 = in.nextInt();
            graph.addEdge(num1, num2, 0L);
        }
        int from = in.nextInt();
        int to = in.nextInt();
        out.println(graph.dijkstra(from, to));
        out.close();
    }

    static class Graph {
        List<Vertex>[] lists = new List[k + 1];//点集
        List<Edge>[] adj = new List[n + 1];//邻接表

        Graph() {
            this.init();
        }

        void init() {
            for (int i = 0; i <= k; i++) {
                lists[i] = new ArrayList<>();
                lists[i].add(new Vertex());
                for (int j = 1; j <= n; j++) {
                    Vertex v = new Vertex(j, i);
                    lists[i].add(v);
                }
            }
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        }

        void addEdge(int v1, int v2, long val) {
            adj[v1].add(new Edge(v1, v2, val));
        }

        long dijkstra(int from, int to) {
            PriorityQueue queue = new PriorityQueue(n);
            Vertex v = lists[0].get(from);
            v.dis = 0L;
            queue.add(v);
            v.inQueue = true;
            while (!queue.isEmpty()) {
                Vertex min = queue.poll();
                for (int i = 0; i < adj[min.no].size(); i++) {
                    Edge e = adj[min.no].get(i);
                    Vertex next;
                    if (e.val == 0 && min.deep + 1 <= k) {
                        next = lists[min.deep + 1].get(e.end);
                    } else if (e.val == 0) continue;
                    else next = lists[min.deep].get(e.end);
                    if (min.dis + e.val < next.dis){
                        next.dis = min.dis + e.val;
                        if (next.inQueue) queue.update(next);
                        else {
                            queue.add(next);
                            next.inQueue = true;
                        }
                    }
                }
            }
            long min = MAX;
            for (int i = 0;i<=k;i++)
                if (lists[i].get(to).dis <min)
                    min = lists[i].get(to).dis;
            return min;
        }
    }

    static class Vertex {
        int no;
        int deep;
        Long dis;
        boolean inQueue = false;

        Vertex() {
        }

        Vertex(int no, int deep) {
            this.no = no;
            this.deep = deep;
            this.dis = MAX;
        }
    }

    static class Edge {
        int start;
        int end;
        long val;

        Edge(int v1, int v2, long val) {
            this.start = v1;
            this.end = v2;
            this.val = val;
        }
    }

    static class PriorityQueue {
        Vertex[] array;
        int size;

        public PriorityQueue(int v) {
            this.array = new Vertex[10 * v + 1];
            this.size = 0;
        }

        public Vertex poll() {
            Vertex temp = array[1];
            array[1] = array[size];
            array[size--] = array[1];
            siftDown();
            return temp;
        }

        boolean isEmpty() {
            return size == 0;
        }

        void siftUp(int n) {
            while (n > 1 && compare(array[n], array[n >> 1])) {
                Vertex temp = array[n];
                array[n] = array[n >> 1];
                array[n >> 1] = temp;
                n >>= 1;
            }
        }

        void siftDown() {
            int n = 1;
            while (2 * n <= size) {
                int k = 2 * n;
                if (k < size && compare(array[k + 1], array[k])) k++;
                if (compare(array[k], array[n])) {
                    Vertex temp = array[n];
                    array[n] = array[k];
                    array[k] = temp;
                    n = k;
                } else break;
            }
        }

        boolean compare(Vertex a, Vertex b) {
            return a.dis < b.dis;
        }

        void add(Vertex val) {
            array[++size] = val;
            siftUp(size);
        }

        void update(Vertex v) {
            for (int i = 1; i <= size; i++) {
                if (array[i].no == v.no && array[i].deep == v.deep) {
                    array[i] = v;
                    siftUp(i);
                    break;
                }
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
 Problem: 1294
 User: 12013006
 Language: Java
 Result: Accepted
 Time:924 ms
 Memory:64124 kb
 ****************************************************************/
