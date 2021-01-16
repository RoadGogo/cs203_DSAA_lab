import java.io.*;
import java.util.*;

public class lab8_solutionOfC {
    public static class minHeap {
        long[] array;
        int length;
        int maxlength;

        minHeap(int length) {
            array = new long[length + 1];
            this.length = 0;
            maxlength = length;
        }

        public void swap(int a, int b) {
            long temp = array[a];
            array[a] = array[b];
            array[b] = temp;
        }


        public long getMin() {
            return array[1];
        }

        public void replaceMin(long num) {
            array[1] = num;
            int index = 1;
            while (index * 2 <= length) {
                int i = index * 2;
                if (i + 1 <= length && array[i + 1] < array[i]) {
                    i++;
                }
                if (array[index] > array[i]) {
                    swap(index, i);
                } else {
                    break;
                }
                index = i;
            }
        }

        public void insert(long x) {
            length++;
            array[length] = x;
            int in = length;
            while (in > 1 && array[in >> 1] > array[in]) {
                swap(in, in >> 1);
                in >>= 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int casetimes = in.nextInt();
        for (int times = 0; times < casetimes; times++) {
            int size = in.nextInt();
            int[] powerLIst = new int[size];
            int[] timeLIst = new int[size];
            for (int i = 0; i < size; i++) powerLIst[i] = in.nextInt();
            for (int i = 0; i < size; i++) timeLIst[i] = in.nextInt();
            sort(timeLIst, powerLIst, 0, size - 1);
            minHeap minHeap = new minHeap(size);
            for (int i = 0; i < size; i++) {
                if (minHeap.length < timeLIst[i]) {
                    minHeap.insert(powerLIst[i]);
                } else {
                    int min = (int) minHeap.getMin();
                    if (min < powerLIst[i]) minHeap.replaceMin(powerLIst[i]);
                }
            }

            long ans = 0;
            for (int i = 0; i < size; i++) ans += minHeap.array[i];
            out.println(ans);

        }
        out.close();
    }

    public static void sort(int[] ArrLeft, int[] ArrRight, int Left, int Right) {
        if (Left < Right) {
            int start = Left;
            int end = Right;
            int temp1 = ArrLeft[Left];
            int temp2 = ArrRight[Left];

            while (start < end) {
                while (start < end && ArrLeft[end] >= temp1) {
                    end--;
                }
                ArrLeft[start] = ArrLeft[end];
                ArrRight[start] = ArrRight[end];
                while (start < end && ArrLeft[start] <= temp1) {
                    start++;
                }
                ArrLeft[end] = ArrLeft[start];
                ArrRight[end] = ArrRight[start];
            }

            ArrLeft[start] = temp1;
            ArrRight[start] = temp2;

            sort(ArrLeft, ArrRight, Left, start - 1);
            sort(ArrLeft, ArrRight, start + 1, Right);
        }
    }

    static class Reader {
        final private int BUFFER_length = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_length];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_length];
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
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_length);
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