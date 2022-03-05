package Lab9;

import java.io.*;
import java.util.*;

public class TheElves {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static Queue<Graph.Vertex> queue;
    static Graph graph;
    static long[] arr1;
    static long[] arr2;
    static long N = 1000000007;

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            long sum = 0;
            int n = in.nextInt();
            int m = in.nextInt();
            graph = new Graph(n);
            queue = new LinkedList<>();
            arr1 = new long[n];
            arr2 = new long[n];
            for (int j = 0; j < n; j++) {
                arr1[j] = in.nextLong() % N;
                arr2[j] = in.nextLong() % N;
                graph.list.get(j).func = arr2[j];
            }
            for (int j = 0; j < m; j++) graph.addEdge(in.nextInt(), in.nextInt());
            queue.addAll(graph.degreeMap.keySet());
            while (!queue.isEmpty()) {
                Graph.Vertex top = queue.poll();
                for (Graph.Edge edge : graph.map.get(top)) {
                    edge.start.func += top.func;
                    edge.start.func = edge.start.func % N;
                    if (--edge.start.degree == 0) queue.add(edge.start);
                }
            }
            for (int j = 0; j < graph.list.size(); j++) {
                sum = sum + arr1[j] * (graph.list.get(j).func - arr2[j]);
                sum %= N;
            }
            out.println(sum);
        }
        out.close();
    }

    static class Graph {
        List<Vertex> list = new ArrayList<>();
        Map<Vertex, List<Edge>> map = new HashMap<>();
        HashMap<Vertex, Boolean> degreeMap = new HashMap<>();

        Graph(int n) {
            this.init(n);
        }

        void init(int n) {
            for (int i = 1; i <= n; i++) {
                Vertex v = new Vertex(i);
                list.add(v);
                map.put(v, new ArrayList<>());
                degreeMap.put(v, true);
            }
        }

        void addEdge(int num1, int num2) {
            Vertex v1 = list.get(num1 - 1);
            Vertex v2 = list.get(num2 - 1);
            Edge edge = new Edge(v1, v2);
            map.get(v2).add(edge);
            v1.degree++;
            degreeMap.remove(v1);
        }

        static class Vertex {
            int degree = 0;
            long val;
            long func;

            Vertex(int val) {
                this.val = val;
            }
        }

        static class Edge {
            Vertex start;
            Vertex end;

            Edge(Vertex v1, Vertex v2) {
                this.start = v1;
                this.end = v2;
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
 Problem: 1291
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1048 ms
 Memory:138852 kb
 ****************************************************************/
