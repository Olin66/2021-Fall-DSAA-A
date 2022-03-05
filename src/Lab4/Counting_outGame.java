package Lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Counting_outGame {
    static QReader in = new QReader();
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        int n = in.nextInt();
        Student std = new Student(0);
        Student temp = std;
        for (int i = 1; i <= n; i++) {
            Student student = new Student(i);
            temp.next = student;
            student.pre = temp;
            temp = temp.next;
        }
        ArrayList<Integer> list = new ArrayList<>(n);
        int m = in.nextInt();
        while (std.next != null) {
            int len = std.len();
            temp = std.next;
            int index = 1;
            while (true) {
                if (temp.next != null) {
                    list.add(temp.no);
                    temp.pre.next = temp.next;
                    temp.next.pre = temp.pre;
                } else {
                    list.add(temp.no);
                    temp.pre.next = null;
                    break;
                }
                index += m;
                if (index <= len) {
                    for (int i = 0; i < m; i++) temp = temp.next;
                } else break;
            }
        }
        for (int i =0;i < list.size();i++){
            out.print(list.get(i));
            if (i != list.size()-1) out.print(" ");
        }
        out.println("");
        out.flush();
    }

    static class Student {
        int no;
        Student next;
        Student pre;

        public Student(int no) {
            this.no = no;
        }

        public int len() {
            int sum = 0;
            Student temp = next;
            while (temp != null) {
                sum++;
                temp = temp.next;
            }
            return sum;
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
 Problem: 1436
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1596 ms
 Memory:189640 kb
 ****************************************************************/