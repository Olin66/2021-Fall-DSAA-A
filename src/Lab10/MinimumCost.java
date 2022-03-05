package Lab10;

import java.io.*;
import java.util.*;

public class MinimumCost {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static long MAX = Long.MAX_VALUE;

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        Graph graph = new Graph(n);
        for (int i = 0; i < m; i++) {
            int num1 = in.nextInt();
            int num2 = in.nextInt();
            long dist = in.nextLong();
            graph.addEdge(num1, num2, dist);
        }
        out.println(graph.prim());
        out.close();
    }

    static class Graph {
        List<Vertex> vertexList;
        Map<Vertex, List<Edge>> map;
        PriorityQueue queue;
        int n;
        Vertex first = null;

        Graph(int n) {
            this.vertexList = new ArrayList<>();
            this.map = new HashMap<>();
            queue = new PriorityQueue(n);
            this.n = n;
            initVertex();
        }

        void initVertex() {
            vertexList.add(new Vertex());
            for (int i = 1; i <= n; i++) {
                Vertex v = new Vertex(i);
                vertexList.add(v);
                map.put(v, new ArrayList<>());
                queue.add(v);
            }
        }

        void addEdge(int num1, int num2, long weight) {
            Vertex v1 = vertexList.get(num1);
            Vertex v2 = vertexList.get(num2);
            Edge edge1 = new Edge(v1, v2, weight);
            map.get(v1).add(edge1);
            if (first == null) {
                first = v1;
                first.key = 0;
                queue.siftUp(first.index);
            }
            Edge edge2 = new Edge(v2, v1, weight);
            map.get(v2).add(edge2);
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

        static class Vertex {
            int no;
            int index;
            long key = MAX;
            boolean inQueue = true;

            Vertex() {
            }

            Vertex(int no) {
                this.no = no;
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
 Problem: 1473
 User: 12013006
 Language: Java
 Result: Accepted
 Time:2140 ms
 Memory:151340 kb
 ****************************************************************/
