import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class lab5_solutionOfD {
    public static class stack{
        node top;
        node head=new node(-1,Integer.MAX_VALUE);
        node tail=new node(-2,Integer.MIN_VALUE);
        stack(){
            head.next=tail;
            tail.prev=head;
            top=head;
        }
        void push(node node){
            top.next.prev =node;
            node.next = top.next;
            node.prev =top;
            top.next =node;
            top =node;
        }
        void pop(){
            top.prev.next = top.next;
            top.next.prev = top.prev;
            top = top.prev;
        }

    }

    public static class node{
        int index;
        int value;
        node prev;
        node next;
        node(int index,int value){
            this.value=value;
            this.index=index;
            prev=null;
            next=null;
        }
    }

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int caseTimes = in.nextInt();
        for(int times =0 ;times<caseTimes;times++){
            int foreLength = in.nextInt();
            int[] ans=new int[foreLength];
            stack stack =new stack();
            for(int i =0;i<foreLength;i++){
                int value =in.nextInt();
                node node =new node(i,value);
                if(stack.top.value>=value){
                    stack.push(node);
                }else {
                    while(stack.top.value<value){
                        int ansIndex=stack.top.index;
                        stack.pop();
                        ans[ansIndex]=i-ansIndex;
                    }
                    stack.push(node);
                }
            }
            node cur = stack.head.next;
            while(cur.value!=Integer.MIN_VALUE){
                ans[cur.index]=-1;
                cur =cur.next;
            }

            int queLength = in.nextInt();
            for(int i=0;i <queLength;i++){
                out.println(ans[in.nextInt()-1]);
            }
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


