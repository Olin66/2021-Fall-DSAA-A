package Lab8;

import java.io.*;
import java.util.StringTokenizer;

public class GirlsAndBunnies {
    static QReader in = new QReader();
    static QWriter out = new QWriter();
    static Tree.Node temp;
    static Tree tree;
    static long money;
    static long min = 10000000000L;

    public static void main(String[] args) {
        int days = in.nextInt();
        for (int i = 0; i < days; i++) {
            int type = in.nextInt();
            long val = in.nextLong();
            if (i == 0) {
                if (type == 0) tree = new Tree(0);
                else tree = new Tree(1);
                tree.insert(val);
                continue;
            }
            shop(type, val);
        }
        out.println(money);
        out.close();
    }

    static void shop(int type, long val) {
        if (tree.isEmpty()) {
            tree.insert(val);
            tree.type = type;
        } else {
            if (tree.type == type) tree.insert(val);
            else {
                money += tree.findNode(val);
                tree.remove(temp);
                update();
            }
        }
    }

    static void update() {
        min = 10000000000L;
        temp = null;
    }

    static class Tree {
        Node root;
        int type;

        static class Node {
            long val;
            int height;
            Node left;
            Node right;

            Node(long val) {
                this.val = val;
                this.height = 0;
            }

            long findNode(long target) {
                Node temp1 = findPredecessor(target);
                if (temp1 != null && Math.abs(target - temp1.val) <= 1) {
                    temp = temp1;
                    return Math.abs(target - temp1.val);
                }
                Node temp2 = findSuccessor(target);
                if (temp1 == null) {
                    temp = temp2;
                    return (min = Math.abs(temp2.val - target));
                }
                if (temp2 == null) {
                    temp = temp1;
                    return (min = Math.abs(temp1.val - target));
                }
                if (Math.abs(temp1.val - target) <= Math.abs(temp2.val - target)) {
                    temp = temp1;
                    min = Math.abs(temp1.val - target);
                } else {
                    temp = temp2;
                    min = Math.abs(temp2.val - target);
                }
                return min;
            }

            Node findPredecessor(long target) {
                Node u = this, predecessor = null;
                while (true) {
                    if (u == null) return predecessor;
                    if (u.val == target) return u;
                    else if (u.val > target) u = u.left;
                    else {
                        predecessor = u;
                        u = u.right;
                    }
                }
            }

            Node findSuccessor(long target) {
                Node u = this, successor = null;
                while (true) {
                    if (u == null) return successor;
                    if (u.val == target) return u;
                    else if (u.val < target) u = u.right;
                    else {
                        successor = u;
                        u = u.left;
                    }
                }
            }
        }

        Tree(int type) {
            root = null;
            this.type = type;
        }

        int height(Node tree) {
            if (tree != null) return tree.height;
            return 0;
        }

        int max(int a, int b) {
            return Math.max(a, b);
        }

        boolean isEmpty() {
            return root == null;
        }

        long findNode(long target) {
            return this.root.findNode(target);
        }

        Node minimum(Node min) {
            if (min == null) return null;
            while (min.left != null) min = min.left;
            return min;
        }

        Node maximum(Node max) {
            if (max == null) return null;
            while (max.right != null) max = max.right;
            return max;
        }

        Node llRotation(Node node) {
            Node node1;
            node1 = node.left;
            node.left = node1.right;
            node1.right = node;
            node.height = max(height(node.left), height(node.right)) + 1;
            node1.height = max(height(node1.left), node.height) + 1;
            return node1;
        }

        Node rrRotation(Node node) {
            Node node1;
            node1 = node.right;
            node.right = node1.left;
            node1.left = node;
            node.height = max(height(node.left), height(node.right)) + 1;
            node1.height = max(height(node1.right), node.height) + 1;
            return node1;
        }

        Node lrRotation(Node node) {
            node.left = rrRotation(node.left);
            return llRotation(node);
        }

        Node rlRotation(Node node) {
            node.right = llRotation(node.right);
            return rrRotation(node);
        }

        Node insert(Node tree, long val) {
            if (tree == null) tree = new Node(val);
            else {
                long cmp = val - tree.val;
                if (cmp < 0) {
                    tree.left = insert(tree.left, val);
                    if (height(tree.left) - height(tree.right) > 1) {
                        if (val - tree.left.val < 0) tree = llRotation(tree);
                        else tree = lrRotation(tree);
                    }
                } else {
                    tree.right = insert(tree.right, val);
                    if (height(tree.right) - height(tree.left) > 1) {
                        if (val - tree.right.val > 0) tree = rrRotation(tree);
                        else tree = rlRotation(tree);
                    }
                }
            }
            tree.height = max(height(tree.left), height(tree.right)) + 1;
            return tree;
        }

        void insert(long val) {
            root = insert(root, val);
        }

        Node remove(Node tree, Node target) {
            if (tree == null || target == null) return null;
            long cmp = target.val - tree.val;
            if (cmp < 0) {
                tree.left = remove(tree.left, target);
                if (height(tree.right) - height(tree.left) > 1) {
                    Node r = tree.right;
                    if (height(r.left) > height(r.right)) tree = rlRotation(tree);
                    else tree = rrRotation(tree);
                }
            } else if (cmp > 0) {
                tree.right = remove(tree.right, target);
                if (height(tree.left) - height(tree.right) > 1) {
                    Node l = tree.left;
                    if (height(l.right) > height(l.left)) tree = lrRotation(tree);
                    else tree = llRotation(tree);
                }
            } else {
                if ((tree.left != null) && (tree.right != null)) {
                    if (height(tree.left) > height(tree.right)) {
                        Node max = maximum(tree.left);
                        tree.val = max.val;
                        tree.left = remove(tree.left, max);
                    } else {
                        Node min = minimum(tree.right);
                        tree.val = min.val;
                        tree.right = remove(tree.right, min);
                    }
                } else tree = (tree.left != null) ? tree.left : tree.right;
            }
            return tree;
        }

        void remove(Node target) {
            root = remove(root, target);
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
 Problem: 1465
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1036 ms
 Memory:125852 kb
 ****************************************************************/