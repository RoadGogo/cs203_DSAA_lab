import java.util.HashMap;
import java.util.Scanner;

public class lab7_solutionOfD {
    static int preIndex = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseTimes = in.nextInt();
        for (int times = 0; times < caseTimes; times++) {
            int length = in.nextInt();
            int[] inorderArr = new int[length];
            int[] preorderArr = new int[length];
            for (int i = 0; i < length; i++) {
                preorderArr[i] = in.nextInt();
            }
            for (int i = 0; i < length; i++) {
                inorderArr[i] = in.nextInt();
            }
            lab7_solutionOfD tree = new lab7_solutionOfD();
            tree.PostTraversal(inorderArr, preorderArr, length);
            //print postorder
            System.out.println();
            preIndex=0;
        }
    }

    void PostTraversal(int[] inorderArr, int[] preorderArr, int length) {
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();

        for (int i = 0; i < length; i++) {
            hash.put(inorderArr[i], i);
        }
        Post(inorderArr, preorderArr, 0, length - 1, hash);
    }

    void Post(int[] inorderArr, int[] preorderArr, int inStart, int inEnd, HashMap<Integer, Integer> hash) {
        if (inStart > inEnd) return;
        // Find index of next item in preorder traversal in inorderArr
        int inIndex = hash.get(preorderArr[preIndex++]);
        // traverse left tree
        Post(inorderArr, preorderArr, inStart, inIndex - 1, hash);
        // traverse right tree
        Post(inorderArr, preorderArr, inIndex + 1, inEnd, hash);
        // print root node at the end of traversal
        System.out.print(inorderArr[inIndex] + " ");
    }

    //比较重要的一题
    //天真的方法是先构造树，然后使用简单的递归方法打印构造树的后序遍历。
    //我们无需构造树就可以打印订单遍历。
    // 这个想法是，root始终是预遍历中的第一项，它必须是后遍历中的最后一项。
    // 我们首先递归打印左子树，然后递归打印右子树。 最后，打印根目录。
    // 要查找pre []和in []中左右子树的边界，
    // 我们搜索in []的根，in []中root之前的所有元素都是左子树的元素，root之后的所有元素都是右子树的元素。
    // 在pre []中，in []中的根索引之后的所有元素都是右子树的元素。 索引之前的元素（包括索引处的元素，但不包括第一个元素）是左子树的元素。

}
