package Lab9;

import java.io.*;
import java.util.*;

public class ValentineDay {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        Graph graph = new Graph(n);
        for (int i = 0; i < m; i++) {
            int num1 = in.nextInt();
            int num2 = in.nextInt();
            long weight = in.nextLong();
            graph.addEdge(num1, num2, weight);
        }
        out.println(graph.bfs());
        out.close();
    }

    static class Graph {
        ArrayList<Vertex> list;
        Map<Vertex, List<Edge>> map;
        Map<Vertex, Long> disMap = new HashMap<>();
        int n;

        Graph(int n) {
            this.list = new ArrayList<>();
            this.map = new HashMap<>();
            this.n = n;
            initVertex();
        }

        void addEdge(int num1, int num2, long weight) {
            Vertex v1 = list.get(num1 - 1);
            Vertex v2 = list.get(num2 - 1);
            if (weight == 1) {
                Edge edge = new Edge(v1, v2);
                map.get(v1).add(edge);
            } else {
                Vertex mid = new Vertex();
                Edge edge1 = new Edge(v1, mid);
                Edge edge2 = new Edge(mid, v2);
                map.get(v1).add(edge1);
                map.put(mid, new ArrayList<>());
                map.get(mid).add(edge2);
            }
        }

        long bfs() {
            Vertex v = list.get(0);
            Vertex target = list.get(n - 1);
            Queue<Vertex> queue = new LinkedList<>();
            queue.add(v);
            disMap.put(v, 0L);
            while (!queue.isEmpty()) {
                Vertex top = queue.poll();
                List<Edge> edges = map.get(top);
                long d = disMap.get(top) + 1;
                for (Edge edge : edges) {
                    if (!disMap.containsKey(edge.end)) {
                        disMap.put(edge.end, d);
                        queue.add(edge.end);
                    }
                }
            }
            return disMap.containsKey(target) ? disMap.get(target) : -1;
        }

        void initVertex() {
            for (int i = 1; i <= n; i++) {
                Vertex v = new Vertex(i);
                list.add(v);
                map.put(v, new ArrayList<>());
            }
        }

        static class Vertex {
            int no;

            Vertex() {

            }

            Vertex(int no) {
                this.no = no;
            }
        }

        static class Edge {
            Vertex start;
            Vertex end;
            long weight = 1;

            Edge(Vertex start, Vertex end) {
                this.start = start;
                this.end = end;
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
 Problem: 1245
 User: 12013006
 Language: Java
 Result: Accepted
 Time:2396 ms
 Memory:222048 kb
 ****************************************************************/
