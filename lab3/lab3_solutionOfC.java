import java.io.*;
import java.util.*;

public class lab3_solutionOfC {
    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int times =in.nextInt();
        for(int time=0;time<times;time++){
            int n =in.nextInt();
            int[] array=new int[n];
            for(int i=0;i<array.length;i++){
                array[i]=in.nextInt();
            }

            int[] arraylab=Arrays.copyOfRange(array,0,array.length+1);

            if(selectionSort(n,array)>insertionSort(n,arraylab)){
                for(int i =0; i<array.length;i++){
                    out.print(arraylab[i]+" ");
                }
                out.println();
                out.println("Insertion Sort wins!");
            }else {
                for(int i =0; i<array.length;i++){
                    out.print(arraylab[i]+" ");
                }
                out.println();
                out.println("Selection Sort wins!");
            }


        }

        out.close();
    }

//最后一位是否比较的问题(最后一次交换未完成）
    public static int selectionSort(int n,int[] array){
        int[] arraylab=Arrays.copyOfRange(array,0,array.length+1);
        int sumTimes=0;

        for(int i=0;i<n;i++){
            int k=i;
            for(int j=i+1;j<n;j++){
                if(arraylab[k]>arraylab[j]){
                   k = j;
                }
                sumTimes++;
            }

                int temp = arraylab[k];
                arraylab[k] = arraylab[i];
                arraylab[i] = temp;
                sumTimes++;

        }




//        System.out.println("selectiongtimes: "+sumTimes );
        return sumTimes;
    }


//交换次数出现问题,大问题
    public static int insertionSort(int n,int[] arraylab){
        int sumtimes=0;
        for(int i=1;i<n;i++){
            for(int j= i ;j>0;j--){
                sumtimes++;
                if(arraylab[j-1]>arraylab[j]){
                    int temp =arraylab[j-1];
                    arraylab[j-1] =arraylab[j];
                    arraylab[j]=temp;
                   sumtimes++;
                }else {
                  break;
                }
            }

        }

//        System.out.println("inserttimes: "+sumtimes);
        return sumtimes;
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