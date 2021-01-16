import java.io.*;
import java.util.*;

public class lab8_solutionOfB{
    static class node {
        int value;
        int index;

        node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    static class maxHeap {
        node[] array;
        int size;
        int maxSize;

        maxHeap(int size) {
            array = new node[size + 1];
            this.size = 0;
            maxSize = size;
            array[0] = new node(0,0);
        }
        public void swap(int a, int b) {
            node temp = array[a];
            array[a] = array[b];
            array[b] = temp;
        }
        public void insert(node x) {
            size+=1;
            array[size] = x;
            int in = size;
            while (in > 1 && array[in >> 1].value < array[in].value) {
                swap(in, in >> 1);
                in >>= 1;
            }
        }
        public int getHeight(int N) {
            return (int) Math.ceil(Math.log(N + 1) / Math.log(2)) ;
        }
        public int toFindIndex(node node) {
            for(int i =0;  i<= maxSize;i++){
                if(array[i].value==node.value&&array[i].index==node.index) {
                    return i;
                }
            }
            return -100;
        }
    }

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int caseTimes = in.nextInt();
        for (int times = 0; times < caseTimes; times++) {
            int length = in.nextInt();
            node[] arr = new node[length + 1];
            for (int i = 1; i <= length; i++) {
                arr[i] = new node(in.nextInt(), i);
            }
            maxHeap heap = new maxHeap(length);
            for (int i = 1; i <= length; i++) {
                heap.insert(arr[i]);
            }
            int arrIndex = in.nextInt();
            int allIndex = heap.toFindIndex(arr[arrIndex]);
            int ansLevel = heap.getHeight(allIndex);
            int ansIndex = (int)(allIndex-Math.pow(2,ansLevel-1)+1);
            out.println(ansLevel +" "+ansIndex);

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