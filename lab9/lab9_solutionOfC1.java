import java.io.*;
import java.util.*;
import java.math.*;

public class lab9_solutionOfC1 {
    static class PQueue {
        int value;
        PQueue parentNode;
        PQueue left;
        PQueue right;
        PQueue(int value) {
            this.value = value;
            parentNode=null;
            left=null;
            right=null;
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

            int numOfElement = in.nextInt();
            int numOfEdge = in.nextInt();

            PQueue root = new PQueue(0);
            int[] inDegree = new int[numOfElement + 1]; //入度
            ArrayList<Integer>[] graph = new ArrayList[numOfElement + 1];

            for (int i = 0; i < numOfElement + 1; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < numOfEdge; i++) {
                int dad = in.nextInt();
                int son = in.nextInt();
                inDegree[son]++;
                graph[dad].add(son);
            }

            int currentIndex = 0;
            for (int i = 1; i < numOfElement + 1; i++) {
                if (inDegree[i] == 0) {
                    insert(root, new PQueue(i), currentIndex);
                    currentIndex++;
                }
            }

            while (root.value != 0) {
                int now = root.value;
                out.print(root.value + " ");
                deleteMin(root, currentIndex);
                currentIndex--;

                for (int i = 0; i < graph[now].size(); i++) {
                    inDegree[graph[now].get(i)]--;
                    if (inDegree[graph[now].get(i)] == 0) {
                        insert(root, new PQueue(graph[now].get(i)), currentIndex);
                        currentIndex++;
                    }
                }
            }

        }
    }
    public static void deleteMin(PQueue root, int currentCount) {
        if (currentCount == 1) {
            root.value = 0;
            return;
        }

        PQueue keyNode = findCorrectPosition(root, currentCount - 1);
        PQueue deleteNode;

        if (keyNode.right == null) {
            deleteNode = keyNode.left;
            keyNode.left = null;
        } else {
            deleteNode = keyNode.right;
            keyNode.right = null;
        }
        deleteNode.parentNode = null;
        root.value = deleteNode.value;
        PQueue temp = root;

        while (temp.left != null && (temp.value > temp.left.value || temp.right == null || temp.value > temp.right.value)) {
            if (temp.value <= temp.left.value && temp.right == null) break;
            else {
                int tempValue = temp.value;
                if (temp.right == null || temp.left.value < temp.right.value) {
                    temp.value = temp.left.value;
                    temp.left.value = tempValue;
                    temp = temp.left;
                } else {
                    temp.value = temp.right.value;
                    temp.right.value = tempValue;
                    temp = temp.right;
                }
            }
        }
    }
    public static void insert(PQueue root, PQueue newNode, int currentIndex) {
        if (root.value == 0) {
            root.value = newNode.value;
            return;
        }

        PQueue keyNode = findCorrectPosition(root, currentIndex);
        newNode.parentNode = keyNode;

        if (keyNode.left == null) keyNode.left = newNode;
        else keyNode.right = newNode;

        PQueue temp = newNode;
        while (temp != root && temp.parentNode.value > temp.value) {
            int tempValue = temp.parentNode.value;
            temp.parentNode.value = temp.value;
            temp.value = tempValue;
            temp = temp.parentNode;
        }
    }
    public static PQueue findCorrectPosition(PQueue root, int currentNode) {
        PQueue result = root;
        String temp = getBinaryForm(currentNode + 1);
        for (int i = 1; i < temp.length() - 1; i++) {
            if (temp.charAt(i) == '1') result = result.right;
            else result = result.left;
        }
        return result;
    }
    public static String getBinaryForm(int currentNode) {
        String binaryForm = "";
        while (currentNode != 0) {
            binaryForm = currentNode % 2 + binaryForm;
            currentNode >>=1 ;
        }
        return binaryForm;
    }
    
    public static class InputReader {
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


    }
}
