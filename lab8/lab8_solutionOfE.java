import java.io.*;
import java.math.*;
import java.util.*;

public class lab8_solutionOfE {

    private static class AVL {
        private static class Node {
            int value;
            AVL.Node left;
            AVL.Node right;
            AVL.Node parent;
            int height;
            int size = 1;

            public Node(int value) {
                this.value = value;
            }
        }
        private AVL.Node root = null;

        public void insert(int value) {
            root = insert(root, value);
        }

        public void remove(int value) {
            root = remove(root, value);
        }

        public int max() {
            return maximum(root).value;
        }

        public int min() {
            return minimum(root).value;
        }

        public ArrayList<Integer> toArrayList() {
            ArrayList<Integer> list = new ArrayList<>();
            toArrayList(list, root);
            return list;
        }

        public int findKth(int k) {
            return findKth(root, k);
        }

        private static int height(AVL.Node node) {
            if (node != null) {
                return node.height;
            }
            return -1;
        }

        private static int size(AVL.Node node) {
            if (node != null) {
                return node.size;
            }
            return 0;
        }

        private static AVL.Node LL(AVL.Node root) {
            AVL.Node left = root.left;
            AVL.Node leftRight = left.right;

            root.left = leftRight;
            if (leftRight != null)
                leftRight.parent = root;
            left.right = root;
            root.parent = left;

            root.height = Math.max(height(root.left), height(root.right)) + 1;
            left.height = Math.max(height(left.left), height(left.right)) + 1;
            root.size = size(root.left) + size(root.right) + 1;
            left.size = size(left.left) + size(left.right) + 1;

            left.parent = null;
            return left;
        }

        private static AVL.Node RR(AVL.Node root) {
            AVL.Node right = root.right;
            AVL.Node rightLeft = right.left;

            root.right = rightLeft;
            if (rightLeft != null)
                rightLeft.parent = root;
            right.left = root;
            root.parent = right;

            root.height = Math.max(height(root.left), height(root.right)) + 1;
            right.height = Math.max(height(right.left), height(right.right)) + 1;
            root.size = size(root.left) + size(root.right) + 1;
            right.size = size(right.left) + size(right.right) + 1;

            right.parent = null;
            return right;
        }

        private static AVL.Node LR(AVL.Node root) {
            AVL.Node left = root.left;
            AVL.Node newLeft = RR(left);

            newLeft.parent = root;
            root.left = newLeft;
            return LL(root);
        }

        private static AVL.Node RL(AVL.Node root) {
            AVL.Node right = root.right;
            AVL.Node newRight = LL(right);

            newRight.parent = root;
            root.right = newRight;
            return RR(root);
        }

        private static AVL.Node insert(AVL.Node root, int value) {
            if (root == null) {
                return new AVL.Node(value);
            }

            if (value < root.value) {
                AVL.Node newLeft = insert(root.left, value);
                newLeft.parent = root;
                root.left = newLeft;

                if (height(root.left) - height(root.right) == 2) {
                    if (value < root.left.value) {
                        root = LL(root);
                    } else {
                        root = LR(root);
                    }
                }
            } else {
                AVL.Node newRight = insert(root.right, value);
                newRight.parent = root;
                root.right = newRight;

                if (height(root.right) - height(root.left) == 2) {
                    if (value < root.right.value) {
                        root = RL(root);
                    } else {
                        root = RR(root);
                    }
                }
            }
            root.height = Math.max(height(root.left), height(root.right)) + 1;
            root.size = size(root.left) + size(root.right) + 1;
            return root;
        }

        private static AVL.Node remove(AVL.Node root, int toDelete) {
            if (root == null) {
                return null;
            }

            if (toDelete < root.value) {
                AVL.Node newLeft = remove(root.left, toDelete);
                if (newLeft != null)
                    newLeft.parent = root;
                root.left = newLeft;

                if (height(root.left) - height(root.right) == -2) {
                    AVL.Node right = root.right;
                    if (height(right.left) > height(right.right)) {
                        root = RL(root);
                    } else {
                        root = RR(root);
                    }
                }
            } else if (toDelete > root.value) {
                AVL.Node newRight = remove(root.right, toDelete);
                if (newRight != null)
                    newRight.parent = root;
                root.right = newRight;

                if (height(root.right) - height(root.left) == -2) {
                    AVL.Node left = root.left;
                    if (height(left.left) > height(left.right)) {
                        root = LL(root);
                    } else {
                        root = LR(root);
                    }
                }
            } else {
                if (root.left == null && root.right == null) {
                    return null;
                }
                if (root.left == null) {
                    root = root.right;
                    root.parent = null;
                } else if (root.right == null) {
                    root = root.left;
                    root.parent = null;
                } else {
                    if (root.left.height > root.right.height) {
                        int max = maximum(root.left).value;
                        AVL.Node newLeft = remove(root.left, max);

                        root.value = max;
                        if (newLeft != null)
                            newLeft.parent = root;
                        root.left = newLeft;
                    } else {
                        int min = minimum(root.right).value;
                        AVL.Node newRight = remove(root.right, min);

                        root.value = min;
                        if (newRight != null)
                            newRight.parent = root;
                        root.right = newRight;
                    }
                }
            }
            root.height = Math.max(height(root.left), height(root.right)) + 1;
            root.size = size(root.left) + size(root.right) + 1;
            return root;
        }

        private static AVL.Node maximum(AVL.Node tree) {
            if (tree == null)
                return null;

            while (tree.right != null)
                tree = tree.right;
            return tree;
        }

        private static AVL.Node minimum(AVL.Node tree) {
            if (tree == null)
                return null;

            while (tree.left != null)
                tree = tree.left;
            return tree;
        }

        private static void toArrayList(ArrayList<Integer> list, AVL.Node node) {
            if (node == null) {
                return;
            }
            toArrayList(list, node.left);
            list.add(node.value);
            toArrayList(list, node.right);
        }

        private static int findKth(AVL.Node node, int k) {
            int lessSize = size(node.left);
            if (k <= lessSize) {
                return findKth(node.left, k);
            } else if (k == lessSize + 1) {
                return node.value;
            } else {
                return findKth(node.right, k - (lessSize + 1));
            }

        }
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            int m = in.nextInt();
            int k = in.nextInt();

            int[] array = new int[m];
            for (int i = 0; i < m; i++) {
                array[i] = in.nextInt();
            }

            AVL avl = new AVL();
            for (int i = 0; i < k; i++) {
                avl.insert(array[i]);
            }
            int ni = in.nextInt();
            out.println(avl.findKth(ni));

            for (int i = k; i < m; i++) {
                avl.remove(array[i - k]);
                avl.insert(array[i]);
                ni = in.nextInt();
                out.println(avl.findKth(ni));
            }

        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecimal() {
            return new BigDecimal(next());
        }
    }
}
