import java.util.ArrayDeque;
import java.util.Scanner;
//wrong reason:fin
public class lab8_solutionOfA {
    public static class tree {
        tree parent;
        tree left;
        tree right;
        int num;
        int value;

        public tree(int num) {
            this.num = num;
            parent = null;
            left = null;
            right = null;
        }

        public boolean isMin() {
            if (left != fin && right != fin) {
                return value <= left.value && value <= right.value && left.isMin() && right.isMin();
            } else if (left != fin) {
                return value <= left.value && left.isMin();
            } else {
                return true;
            }
        }

        public boolean isMax() {
            if (left != fin && right != fin) {
                return value >= left.value && value >= right.value && left.isMax() && right.isMax();
            } else if (left != fin) {
                return value >= left.value && left.isMax();
            } else {
                return true;
            }
        }
    }
    private static final tree fin = new tree(-1);
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseTimes = in.nextInt();
        for (int times = 1; times <= caseTimes; times++) {
            if (judge(in)) System.out.printf("Case #%d: YES", times);
            else System.out.printf("Case #%d: NO", times);
            System.out.println();
        }
    }

    private static boolean judge(Scanner in) {
        int length = in.nextInt();
        tree[] treelist = new tree[length + 1];
        for (int i = 1; i <= length; i++) {
            treelist[i] = new tree(i);
            treelist[i].value = in.nextInt();
            treelist[i].right = fin;
            treelist[i].left = fin;
        }
        for (int i = 1; i < length; i++) {
            int parent = in.nextInt();
            int child = in.nextInt();
            tree pa = treelist[parent];
            tree ch = treelist[child];
            if (pa.left == fin && pa.right == fin) {
                pa.left = ch;
            } else if (pa.right == fin) {
                pa.right = ch;
            } else {
                return false;
            }
            ch.parent = pa;
        }

        tree root = null;
        for (int i = 1; i <= length; i++) {
            if (treelist[i].parent == null) {
                root = treelist[i];
            }
        }
        ArrayDeque<tree> deque = new ArrayDeque<>();
        deque.add(root);
        while (deque.peek() != fin) {
            tree tree = deque.poll();
            deque.addLast(tree.left);
            deque.addLast(tree.right);
        }
        while (!deque.isEmpty()) {
            if (deque.poll() != fin) return false;
        }
        boolean flag1 = root.isMax();
        boolean flag2 = root.isMin();
        boolean boo = flag1 || flag2;

        return boo;
    }
}
