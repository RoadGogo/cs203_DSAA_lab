import java.io.*;
import java.util.*;

public class lab8_solutionOfD {

    private static class MinPQ {

        private long[] arr;
        private int size;

        static MinPQ create(int size) {
            MinPQ p = new MinPQ();
            p.arr = new long[size + 1];
            p.size = 0;
            return p;
        }

        private void swim(int index) {
            while (index > 1 && arr[index] < arr[index >>1]) {
                swap(index, index >>1);
                index >>= 1;
            }
        }

        private void sink(int index) {
            while (index <<1 <= size) {
                int j = index <<1;
                if (j + 1 <= size && arr[j + 1] < arr[j]) {
                    j++;
                }
                if (arr[index] > arr[j]) {
                    swap(index, j);
                } else {
                    break;
                }
                index = j;
            }
        }

        private long peekMin() {
            return arr[1];
        }

        private void replaceMin(long num) {
            arr[1] = num;
            sink(1);
        }

        private void insert(long x) {
            size++;
            arr[size] = x;
            swim(size);
        }

        private void swap(int index1, int index2) {
            long temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;
        }

    }

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        int k = in.nextInt();
        long last_ans = in.nextInt();
        MinPQ minPQ = MinPQ.create(k);
        for (int i = 1; i <= t; i++) {
            if (minPQ.size < k) {
                minPQ.insert(mod(i + last_ans));
            } else {
                long min = minPQ.peekMin();
                long num = mod(i + last_ans);
                if (num > min) {
                    minPQ.replaceMin(num);
                }
            }
            if (i % 100 == 0) {
                long ans = minPQ.peekMin();
                out.print(ans + " ");
                last_ans = ans;
            }
        }
        out.close();
    }

    private static long mod(long n) {
        long ans = n;
        while (n != 0) {
            ans += n % 10;
            n /= 10;
        }
        return ans;
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