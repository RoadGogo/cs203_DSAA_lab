import java.io.*;
import java.util.*;

public class lab3_solutionOfG {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] array= new long[n];
        long[] lab= new long[n-1];
        for(int i = 0; i < n; i++){
            array[i] = in.nextLong();
        }
        long sum=0;
        for(int i = n-1; i > 0; i--){
            sum += array[i];
            lab[i - 1] = sum;
        }
        sum += array[0];
        merge(lab,0,lab.length-1);
        long outNum = 0;
        for(int i = lab.length - 1; i >= n-m; i--){
            outNum += lab[i];
        }

        out.println(outNum+sum);

        out.close();
    }

    public static void merge(long[] array, int l, int r){
        if(l<r) {
            int mid = (r + l) / 2;
            merge(array, l, mid);
            merge(array, mid + 1, r);
            merge_sort(array, l, mid, r);
        }
    }

    public static void merge_sort(long[] array,int l,int mid, int r){
        //size of those two subarray
        int size1 = mid - l + 1;
        int size2 = r - mid;

        long [] Left = new long[size1];
        long [] Right = new long[size2];

        //copy the array[] element date to the two subarray
        for(int i = 0;i < size1; i++){
            Left[i] = array[l + i];
        }
        for(int j = 0;j < size2; j++){
            Right[j] = array[mid + 1 + j];
        }


        int i = 0; int j = 0;
        int k = l;

        while(i < size1 && j < size2){
            if(Left[i] <= Right[j]){
                array[k] = Left[i];
                k++;
                i++;
            }else {
                array[k] = Right[j];
                k++;
                j++;
            }
        }
        // if excite some elements of subarray
        while (i < size1){
            array[k] = Left[i];
            i++;
            k++;
        }
        while (j < size2){
            array[k] = Right[j];
            j++;
            k++;
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