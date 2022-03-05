package Lab10;

import java.io.*;
import java.util.*;

public class Travel {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static long MAX = 10000000000000000L;

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
        long result = graph.dijkstra() == MAX ? -1 : graph.dijkstra();
        out.print(result);
        out.close();
    }


    static class Graph {
        List<Vertex> vertexList;
        Map<Vertex, List<Edge>> map;
        int n;

        Graph(int n) {
            this.vertexList = new ArrayList<>();
            this.map = new HashMap<>();
            this.n = n;
            initVertex();
        }

        void initVertex() {
            vertexList.add(new Vertex());
            for (int i = 1; i <= n; i++) {
                Vertex v = new Vertex(i);
                vertexList.add(v);
                map.put(v, new ArrayList<>());
            }
        }

        void addEdge(int num1, int num2, long weight) {
            Vertex v1 = vertexList.get(num1);
            Vertex v2 = vertexList.get(num2);
            Edge edge = new Edge(v1, v2, weight);
            map.get(v1).add(edge);
        }

        long dijkstra() {
            PriorityQueue queue = new PriorityQueue(n);
            vertexList.get(1).dist = 0;
            queue.add(vertexList.get(1));
            vertexList.get(1).inQueue = true;
            while (!queue.isEmpty()) {
                Vertex min = queue.poll();
                if (min.no == n) break;
                for (int i = 0; i < map.get(min).size(); i++) {
                    Edge e = map.get(min).get(i);
                    Vertex next = e.end;
                    if (min.dist + e.weight < next.dist) {
                        next.dist = min.dist + e.weight;
                        if (next.inQueue) queue.update(next);
                        else {
                            queue.add(next);
                            next.inQueue = true;
                        }
                    }
                }
            }
            return vertexList.get(n).dist;
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
            long dist;
            boolean inQueue = false;

            Vertex() {
                this.dist = MAX;
            }

            Vertex(int no) {
                this.no = no;
                this.dist = MAX;
            }
        }

        static class PriorityQueue {
            Vertex[] array;
            int size;

            public PriorityQueue(int v) {
                this.array = new Vertex[v + 1];
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

            boolean compare(Vertex a, Vertex b){
                return a.dist < b.dist;
            }

            void add(Vertex val) {
                array[++size] = val;
                siftUp(size);
            }

            void update(Vertex vertex){
                for (int i = 1; i <= size; i++) {
                    if (array[i].no == vertex.no) {
                        array[i] = vertex;
                        siftUp(i);
                        break;
                    }
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
 Problem: 1470
 User: 12013006
 Language: Java
 Result: Accepted
 Time:656 ms
 Memory:56024 kb
 ****************************************************************/

