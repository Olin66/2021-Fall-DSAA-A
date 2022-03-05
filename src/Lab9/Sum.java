package Lab9;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sum {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static long[][] matrix;
    static ArrayList<String[]> list = new ArrayList<>();
    static int n, m;
    static String[] states;
    static String[] choices;
    static long max = 0;

    static void init() {
        String[] arr1 = {"1", "0"};
        String[] arr2 = {"10", "01", "00"};
        String[] arr3 = {"100", "010", "001", "101", "000"};
        String[] arr4 = {"1000", "0100", "0010", "0001", "1010", "0101", "1001", "0000"};
        String[] arr5 = {"10000", "01000", "00100", "00010", "00001", "10100", "10010", "10001", "01001", "01010", "00101", "10101", "00000"};
        String[] arr6 = {"100000", "010000", "001000", "000100", "000010", "000001", "101000", "100100", "100010",
                "100001", "010100", "010010", "010001", "001010", "001001", "000101", "101010", "101001", "100101", "010101", "000000"};
        list.add(arr1);
        list.add(arr2);
        list.add(arr3);
        list.add(arr4);
        list.add(arr5);
        list.add(arr6);
    }

    static void init(int n, int m) {
        choices = list.get(m - 1);
        states = new String[n];
        for (int i = 0; i < n; i++) {
            states[i] = choices[choices.length - 1];
        }
    }

    public static void main(String[] args) {
        init();
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            n = in.nextInt();
            m = in.nextInt();
            init(n, m);
            matrix = new long[n][m];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    matrix[j][k] = in.nextLong();
                }
            }
            check(0);
            out.println(max);
            max = 0;
        }
        out.close();
    }

    static void check(int num) {
        if (num == n) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < m; i++) {
                    if (states[j].charAt(i) == '1') sum += matrix[j][i];
                }
            }
            max = sum > max ? sum : max;
            return;
        }
        for (String choice : choices) {
            states[num] = choice;
            if (judge(num)) check(num + 1);
        }
    }

    static boolean judge(int num) {
        for (int i = 0; i < m; i++) {
            if (states[num].charAt(i) != '1') continue;
            if (isValidRow(num - 1)) {
                if (isValidCol(i - 1)) {
                    if (isChoose(num - 1, i - 1)) return false;
                }
                if (isValidCol(i + 1)) {
                    if (isChoose(num - 1, i + 1)) return false;
                }
                if (isChoose(num - 1, i)) return false;
            }
            if (isValidRow(num + 1)) {
                if (isValidCol(i - 1)) {
                    if (isChoose(num + 1, i - 1)) return false;
                }
                if (isValidCol(i + 1)) {
                    if (isChoose(num + 1, i + 1)) return false;
                }
                if (isChoose(num + 1, i)) return false;
            }
            if (isValidCol(i - 1)) if (isChoose(num, i - 1)) return false;
            if (isValidCol(i + 1)) if (isChoose(num, i + 1)) return false;
        }
        return true;
    }

    static boolean isChoose(int row, int col) {
        return states[row].charAt(col) == '1';
    }

    static boolean isValidRow(int num) {
        return num >= 0 && num < n;
    }

    static boolean isValidCol(int num) {
        return num >= 0 && num < m;
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
 Problem: 1468
 User: 12013006
 Language: Java
 Result: Accepted
 Time:796 ms
 Memory:29108 kb
 ****************************************************************/

