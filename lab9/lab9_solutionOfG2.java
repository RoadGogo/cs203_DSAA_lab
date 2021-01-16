import java.io.*;
import java.util.*;
//from github
public class lab9_solutionOfG2 {
    public static int n;
    public static long[] dp;
    public static LinkedList<Integer>[] arr;
    public static int[] a;
    public static int[] b;

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int times = in.nextInt();
        for (int time = 0; time < times; time++) {
            n = in.nextInt();
            int m = in.nextInt();
            arr = new LinkedList[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = new LinkedList<>();
            }
            dp = new long[n + 1];
            a = new int[n + 1];
            b = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = in.nextInt();
                b[i] = in.nextInt();
            }
            for (int i = 1; i <= m; i++) {
                int start = in.nextInt();
                int end = in.nextInt();
                arr[start].add(end);
            }

            long ans = 0;
            for (int i = 1; i <= n; i++) {
                ans = (ans + DP(i) * a[i] % 1000000007) % 1000000007;
            }
            out.println((1000000007 + ans) % 1000000007);
        }
        out.close();
    }

    public static long DP(int i) {
        if (dp[i] != 0) return dp[i];
        long ans = 0;
        for (Integer j : arr[i]) {
            long it = DP(j);
            if (!arr[j].isEmpty()) {
                ans = (ans + it) % 1000000007;
            }
            ans = (ans + b[j]) % 1000000007;
        }
        dp[i] = ans;
        return ans;
    }

    static class Reader {
        final public int BUFFER_SIZE = 1 << 16;
        public DataInputStream din;
        public byte[] buffer;
        public int bufferPointer, bytesRead;

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

        public void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        public byte read() throws IOException {
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