import java.io.*;
import java.util.*;

public class labGame_solutionOfA {
    static Vector<Integer> v1 = new Vector<Integer>();

    // to stores ancestors of first given node
    static Vector<Integer> v2 = new Vector<Integer>();

    // normal binary search to find the element
    static int BinarySearch(int x) {
        int low = 0;
        int high = v2.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (v2.get(mid) == x)
                return mid;
            else if (v2.get(mid) > x)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }

    // function to make ancestors of first node
    static void MakeAncestorNode1(int x) {
        v1.clear();
        while (x > 0) {
            v1.add(x);
            x /= 2;
        }
        Collections.reverse(v1);
    }

    // function to make ancestors of second node
    static void MakeAncestorNode2(int x) {
        v2.clear();
        while (x > 0) {
            v2.add(x);
            x /= 2;
        }
        Collections.reverse(v2);
    }

    // function to find distance bewteen two nodes
    static int Distance() {
        for (int i = v1.size() - 1; i >= 0; i--) {
            int x = BinarySearch(v1.get(i));
            if (x != -1) {
                return v1.size() - 1 - i +
                        v2.size() - 1 - x;
            }
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int caseTimes = in.nextInt();

        for (int times = 0; times < caseTimes; times++) {
            int a = in.nextInt();
            int b = in.nextInt();

            // find ancestors
            MakeAncestorNode1(a);
            MakeAncestorNode2(b);
            out.println(Distance());
        }


        out.close();
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