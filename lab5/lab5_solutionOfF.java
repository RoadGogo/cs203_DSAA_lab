import java.io.*;
import java.util.*;

public class lab5_solutionOfF {
    public static class node{
        int value;
        int index;
        node prev;
        node next;
        node(int value,int index){
            this.value=value;
            this.index=index;
            prev = null;
            next = null;
        }
    }
    public static class linkList{
        node head=new node(-1,-1);
        node tail=new node(-2,-2);
        int length;
        linkList(){
            head.next=tail;
            tail.prev=head;
            length=0;
        }
        boolean isEmpty(){
            if(length==0){
                return true;
            }else {
                return false;
            }
        }
        void enQueue(int value,int index){
            node node = new node(value, index);
            tail.prev.next = node;
            node.prev = tail.prev;
            node.next = tail;
            tail.prev = node;
            length+=1;
        }
        void dequeue(){
            node node = head.next;
            head.next = node.next;
            node.next.prev = head;
            node.next=null;
            node.prev=null;
            length-=1;
        }
        void dequeue2(){
            node node = tail.prev;
            tail.prev = node.prev;
            node.prev.next = tail;
            node.next=null;
            node.prev=null;
            length-=1;
        }
    }


    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int limit =in.nextInt();
        int dataLength = in.nextInt();
        int[] array =new int[dataLength];
        for(int i =0;i<dataLength;i++){
            array[i] = in.nextInt();
        }
        int a= longestSubarray(array,limit);
        out.println(a);
        out.close();
    }

    private static int longestSubarray(int[] array, int limit) {
        int ans =0;
        linkList max=new linkList();
        linkList min=new linkList();
        int start=0;
        for(int end=0; end<array.length;end++){
            //max
            while(!max.isEmpty()&& max.tail.prev.value < array[end]){//max.tail.prev.value这里卡了我很久
                max.dequeue2();
            }
            max.enQueue(array[end],end);

            //min
            while(!min.isEmpty()&& min.tail.prev.value > array[end]){//max.tail.prev.value这里卡了我很久
                min.dequeue2();
            }
            min.enQueue(array[end],end);

            //sum
            while(!max.isEmpty()&& !min.isEmpty()&& limit < Math.abs(max.head.next.value-min.head.next.value)){

                start++;
                if(max.head.next.index < start){
                    max.dequeue();
                }
                if(min.head.next.index < start){
                    min.dequeue();
                }

            }
            ans = Math.max(ans,end-start+1);
        }
        return ans;
    }


    static class Reader{
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