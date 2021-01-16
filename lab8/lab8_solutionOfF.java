import java.io.*;
import java.math.*;
import java.util.*;

public class lab8_solutionOfF {
    static class AVLtree {
        private static class Node {
            int value;
            AVLtree.Node left;
            AVLtree.Node right;
            AVLtree.Node parent;
            int height;
            int size = 1;

            public Node(int value) {
                this.value = value;
            }
        }

        private AVLtree.Node root = null;

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

        private int closestValue(int value) {
            return closestValue(root, value);
        }

        private int size() {
            return size(root);
        }

        private static int height(AVLtree.Node node) {
            if (node != null) {
                return node.height;
            }
            return -1;
        }

        private static int size(AVLtree.Node node) {
            if (node != null) {
                return node.size;
            }
            return 0;
        }

        private static AVLtree.Node LL(AVLtree.Node root) {
            AVLtree.Node left = root.left;
            AVLtree.Node leftRight = left.right;

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

        private static AVLtree.Node RR(AVLtree.Node root) {
            AVLtree.Node right = root.right;
            AVLtree.Node rightLeft = right.left;

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

        private static AVLtree.Node LR(AVLtree.Node root) {
            AVLtree.Node left = root.left;
            AVLtree.Node newLeft = RR(left);

            newLeft.parent = root;
            root.left = newLeft;
            return LL(root);
        }

        private static AVLtree.Node RL(AVLtree.Node root) {
            AVLtree.Node right = root.right;
            AVLtree.Node newRight = LL(right);

            newRight.parent = root;
            root.right = newRight;
            return RR(root);
        }

        private static AVLtree.Node insert(AVLtree.Node root, int value) {
            if (root == null) {
                return new AVLtree.Node(value);
            }

            if (value < root.value) {
                AVLtree.Node newLeft = insert(root.left, value);
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
                AVLtree.Node newRight = insert(root.right, value);
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

        private static AVLtree.Node remove(AVLtree.Node root, int toDelete) {
            if (root == null) {
                return null;
            }

            if (toDelete < root.value) {
                AVLtree.Node newLeft = remove(root.left, toDelete);
                if (newLeft != null)
                    newLeft.parent = root;
                root.left = newLeft;

                if (height(root.left) - height(root.right) == -2) {
                    AVLtree.Node right = root.right;
                    if (height(right.left) > height(right.right)) {
                        root = RL(root);
                    } else {
                        root = RR(root);
                    }
                }
            } else if (toDelete > root.value) {
                AVLtree.Node newRight = remove(root.right, toDelete);
                if (newRight != null)
                    newRight.parent = root;
                root.right = newRight;

                if (height(root.right) - height(root.left) == -2) {
                    AVLtree.Node left = root.left;
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
                        AVLtree.Node newLeft = remove(root.left, max);

                        root.value = max;
                        if (newLeft != null)
                            newLeft.parent = root;
                        root.left = newLeft;
                    } else {
                        int min = minimum(root.right).value;
                        AVLtree.Node newRight = remove(root.right, min);

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


        private static AVLtree.Node maximum(AVLtree.Node tree) {
            if (tree == null)
                return null;

            while (tree.right != null)
                tree = tree.right;
            return tree;
        }


        private static AVLtree.Node minimum(AVLtree.Node tree) {
            if (tree == null)
                return null;

            while (tree.left != null)
                tree = tree.left;
            return tree;
        }

        private static void toArrayList(ArrayList<Integer> list, AVLtree.Node node) {
            if (node == null) {
                return;
            }
            toArrayList(list, node.left);
            list.add(node.value);
            toArrayList(list, node.right);
        }

        private static int closestValue(AVLtree.Node root, int value) {
            AVLtree.Node subtree = value < root.value ? root.left : root.right;
            if (subtree == null) {
                return root.value;
            }
            int closet = closestValue(subtree, value);

            int rootAbs = Math.abs(root.value - value);
            int subAbs = Math.abs(closet - value);
            if (rootAbs == subAbs) {
                return Math.min(root.value, closet);
            }
            return rootAbs < subAbs ? root.value : closet;
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
            int n = in.nextInt();
            AVLtree pets = new AVLtree();
            AVLtree adopters = new AVLtree();
            long res = 0;
            for (int i = 0; i < n; i++) {
                int type = in.nextInt();
                int num = in.nextInt();

                AVLtree toSearch;
                AVLtree toInsert;

                if (type == 0) {
                    toSearch = adopters;
                    toInsert = pets;

                } else {
                    toSearch = pets;
                    toInsert = adopters;
                }

                if (toSearch.size() == 0) {
                    toInsert.insert(num);
                }
                else {
                    int closest = toSearch.closestValue(num);
                    toSearch.remove(closest);
                    res += Math.abs(closest - num);
                }
            }
            out.println(res);
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
