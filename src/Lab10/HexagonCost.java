package Lab10;

import java.io.*;
import java.util.*;

public class HexagonCost {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static long MAX = Long.MAX_VALUE;
    static ArrayList<Vertex>[] vertices;
    static Graph graph;

    public static void main(String[] args) {
        int n = in.nextInt();
        vertices = new ArrayList[2 * n + 2];
        graph = new Graph(n);
        for (int i = 1; i <= 2 * n + 1; i++) {
            vertices[i] = new ArrayList<>();
            vertices[i].add(new Vertex());
            for (int j = 1; j <= 2 * n + 1 - Math.abs(i - n - 1); j++) {
                long val = in.nextLong();
                Vertex v = new Vertex(i, j, val);
                vertices[i].add(v);
                graph.vertexList.add(v);
                graph.map.put(v, new ArrayList<>());
            }
        }
        graph.init();
        out.println(graph.prim());
        out.close();
    }

    static class Graph {
        List<Vertex> vertexList;
        Map<Vertex, List<Edge>> map;
        PriorityQueue queue;
        int n;

        Graph(int n) {
            this.vertexList = new ArrayList<>();
            vertexList.add(new Vertex());
            this.map = new HashMap<>();
            queue = new PriorityQueue(3 * n * n + 3 * n + 1);
            this.n = n;
        }

        void init() {
            for (int i = 1; i <= 3 * n * n + 3 * n + 1; i++) {
                Vertex v = vertexList.get(i);
                if (i == 1) v.key = 0;
                addEdge(v);
                queue.add(v);
            }
        }

        long prim() {
            long sum = 0;
            while (!queue.isEmpty()) {
                Vertex top = queue.poll();
                for (Edge edge : map.get(top)) {
                    if (edge.end.inQueue) queue.update(edge);
                }
                sum += top.key;
            }
            return sum;
        }

        void addEdge(Vertex v) {
            int curR = v.row;
            int curL = v.col;
            addEdge(v, curR, curL - 1);
            addEdge(v, curR, curL + 1);
            if (curR <= n) {
                addEdge(v, curR - 1, curL - 1);
                addEdge(v, curR - 1, curL);
                addEdge(v, curR + 1, curL);
                addEdge(v, curR + 1, curL + 1);
            } else if (curR == n + 1) {
                addEdge(v, curR - 1, curL - 1);
                addEdge(v, curR - 1, curL);
                addEdge(v, curR + 1, curL - 1);
                addEdge(v, curR + 1, curL);
            } else {
                addEdge(v, curR - 1, curL);
                addEdge(v, curR - 1, curL + 1);
                addEdge(v, curR + 1, curL - 1);
                addEdge(v, curR + 1, curL);
            }
        }

        void addEdge(Vertex v, int row, int col) {
            if (limitRow(row) && limitCol(row, col)) {
                Vertex tar = vertices[row].get(col);
                long val = v.val * tar.val;
                Edge edge = new Edge(v, tar, val);
                map.get(v).add(edge);
            }
        }

        boolean limitRow(int row) {
            return 1 <= row && row <= 2 * n + 1;
        }

        boolean limitCol(int row, int col) {
            int max = 2 * n + 1 - Math.abs(row - n - 1);
            return 1 <= col && col <= max;
        }
    }

    static class PriorityQueue {
        Vertex[] array;
        int size;

        public PriorityQueue(int n) {
            this.array = new Vertex[n + 1];
            this.size = 0;
        }

        public Vertex poll() {
            Vertex temp = array[1];
            array[1] = array[size];
            array[size--] = array[1];
            siftDown();
            temp.inQueue = false;
            return temp;
        }

        boolean isEmpty() {
            return size == 0;
        }

        void siftUp(int n) {
            while (n > 1 && compare(array[n], array[n >> 1])) {
                Vertex temp = array[n];
                array[n] = array[n >> 1];
                array[n].index = n;
                array[n >> 1] = temp;
                array[n >> 1].index = n >> 1;
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
                    array[n].index = n;
                    array[k] = temp;
                    array[k].index = k;
                    n = k;
                } else break;
            }
        }

        boolean compare(Vertex a, Vertex b) {
            return a.key < b.key;
        }

        void add(Vertex val) {
            array[++size] = val;
            val.index = size;
            siftUp(size);
        }

        void update(Edge edge) {
            if (edge.weight < edge.end.key) {
                edge.end.key = edge.weight;
                siftUp(edge.end.index);
            }
        }
    }

    static class Vertex {
        int row;
        int col;
        int index;
        long val;
        long key = MAX;
        boolean inQueue = true;

        Vertex() {
        }

        Vertex(int row, int col, long val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    static class Edge {
        Vertex start;
        Vertex end;
        long weight;

        Edge(Vertex start, Vertex end, long weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
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
 Problem: 1474
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1216 ms
 Memory:110100 kb
 ****************************************************************/
