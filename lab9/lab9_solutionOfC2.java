import java.io.*;
import java.util.*;

public class lab9_solutionOfC2 {
    static class node {
        int value;
        node parentNode;
        node left;
        node right;

        node(int value) {
            this.value = value;
            parentNode = null;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int elementNum = in.nextInt();
        int edgeNum = in.nextInt();

        node root = new node(0);
        int[] inDegree = new int[elementNum + 1];
        ArrayList<Integer>[] graph = new ArrayList[elementNum + 1];

        for (int i = 0; i < elementNum + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgeNum; i++) {
            int dad = in.nextInt();
            int son = in.nextInt();
            inDegree[son]++;
            graph[dad].add(son);
        }

        int currentIndex = 0;
        for (int i = 1; i < elementNum + 1; i++) {
            if (inDegree[i] == 0) {
                insert(root, new node(i), currentIndex);
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
                    insert(root, new node(graph[now].get(i)), currentIndex);
                    currentIndex++;
                }
            }
        }
        out.close();
    }

    public static String getBinaryForm(int currentNode) {
        String binaryForm = "";
        while (currentNode != 0) {
            binaryForm = currentNode % 2 + binaryForm;
            currentNode >>= 1;
        }
        return binaryForm;
    }

    public static node findCorrectPosition(node root, int currentNode) {
        node result = root;
        String temp = getBinaryForm(currentNode + 1);
        for (int i = 1; i < temp.length() - 1; i++) {
            if (temp.charAt(i) == '1') result = result.right;
            else result = result.left;
        }
        return result;
    }

    public static void deleteMin(node root, int currentCount) {
        if (currentCount == 1) {
            root.value = 0;
            return;
        }

        node keyNode = findCorrectPosition(root, currentCount - 1);
        node deleteNode;

        if (keyNode.right == null) {
            deleteNode = keyNode.left;
            keyNode.left = null;
        } else {
            deleteNode = keyNode.right;
            keyNode.right = null;
        }
        deleteNode.parentNode = null;
        root.value = deleteNode.value;
        node temp = root;

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

    public static void insert(node root, node newNode, int currentIndex) {
        if (root.value == 0) {
            root.value = newNode.value;
            return;
        }

        node keyNode = findCorrectPosition(root, currentIndex);
        newNode.parentNode = keyNode;

        if (keyNode.left == null) {
            keyNode.left = newNode;
        } else {
            keyNode.right = newNode;
        }

        node temp = newNode;
        while (temp != root && temp.parentNode.value > temp.value) {
            int tempValue = temp.parentNode.value;
            temp.parentNode.value = temp.value;
            temp.value = tempValue;
            temp = temp.parentNode;
        }
    }


    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
