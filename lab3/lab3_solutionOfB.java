import java.io.*;
import java.util.*;

public class lab3_solutionOfB {
    //使用merge
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }

        merge(array,0,n-1);
        out.println(array[k-1]);
        out.close();
    }

// Merge two subarray of array[]
    //the first subarray is array[l...mid]
    //the second subarray is array[mid+1...r]
    public static void merge_sort(int[] array,int l,int mid, int r){
        //size of those two subarray
        int size1 = mid - l + 1;
        int size2 = r - mid;

        int [] Left = new int[size1];
        int [] Right = new int[size2];

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

// sort array[l...r] by using
// merge_sort()
    public static void merge(int[] array, int l, int r){
        if(l<r) {
            int mid = (r + l) / 2;
            merge(array, l, mid);
            merge(array, mid + 1, r);
            merge_sort(array, l, mid, r);
        }
    }



    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
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

        public double nextDouble() throws IOException
        {
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

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

}