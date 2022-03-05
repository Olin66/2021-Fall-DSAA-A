package Lab9;

import java.io.*;
import java.util.*;

public class Knight {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        Graph graph = new Graph();
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            String s1 = in.next();
            String s2 = in.next();
            out.println(result(s1, s2, graph));
        }
        out.close();
    }

    static int result(String s1, String s2, Graph graph) {
        int col1 = s1.charAt(0) - 96;
        int row1 = Integer.parseInt(String.valueOf(s1.charAt(1)));
        int col2 = s2.charAt(0) - 96;
        int row2 = Integer.parseInt(String.valueOf(s2.charAt(1)));
        return graph.bfs(row1, col1, row2, col2);
    }

    static class Graph {
        ArrayList<Vertex> list = new ArrayList<>();
        Map<Vertex, List<Vertex>> map = new HashMap<>();
        Map<Vertex, Integer> disMap;

        Graph() {
            initVertex();
        }

        static class Vertex {
            int col;
            int row;

            Vertex(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }

        void initVertex() {
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    Vertex v = new Vertex(i, j);
                    map.put(v, new ArrayList<>());
                    list.add(v);
                }
            }
            for (int i = 1; i <= 64; i++) addEdge(i);
        }

        boolean isValid(int num) {
            return num >= 1 && num <= 8;
        }

        void addEdge(int i) {
            int row1 = i % 8 == 0 ? i / 8 : i / 8 + 1;
            int col1 = i % 8 == 0 ? 8 : i % 8;
            if (isValid(row1 - 2) && isValid(col1 - 1)) add(i, row1 - 2, col1 - 1);
            if (isValid(row1 - 2) && isValid(col1 + 1)) add(i, row1 - 2, col1 + 1);
            if (isValid(row1 - 1) && isValid(col1 + 2)) add(i, row1 - 1, col1 + 2);
            if (isValid(row1 + 1) && isValid(col1 + 2)) add(i, row1 + 1, col1 + 2);
            if (isValid(row1 + 2) && isValid(col1 + 1)) add(i, row1 + 2, col1 + 1);
            if (isValid(row1 + 2) && isValid(col1 - 1)) add(i, row1 + 2, col1 - 1);
            if (isValid(row1 + 1) && isValid(col1 - 2)) add(i, row1 + 1, col1 - 2);
            if (isValid(row1 - 1) && isValid(col1 - 2)) add(i, row1 - 1, col1 - 2);
        }

        void add(int i, int row, int col) {
            int num = (row - 1) * 8 + col;
            Vertex v1 = list.get(i - 1);
            Vertex v2 = list.get(num - 1);
            map.get(v1).add(v2);
        }

        int bfs(int row1, int col1, int row2, int col2) {
            disMap = new HashMap<>();
            int num1 = (row1 - 1) * 8 + col1;
            int num2 = (row2 - 1) * 8 + col2;
            Vertex v = list.get(num1 - 1);
            Vertex target = list.get(num2 - 1);
            Queue<Vertex> queue = new LinkedList<>();
            queue.add(v);
            disMap.put(v, 0);
            while (!queue.isEmpty()) {
                Vertex top = queue.poll();
                int d = disMap.get(top) + 1;
                for (Vertex vertex : map.get(top)) {
                    if (!disMap.containsKey(vertex)) {
                        disMap.put(vertex, d);
                        queue.add(vertex);
                    }
                }
            }
            return disMap.get(target);
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
 Problem: 1467
 User: 12013006
 Language: Java
 Result: Accepted
 Time:372 ms
 Memory:45164 kb
 ****************************************************************/
