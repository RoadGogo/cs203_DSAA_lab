import java.io.*;
import java.util.*;

public class lab4_solutionOfD{
    public static class nodeList{
        int value;
        int order;
        nodeList next;
        nodeList prev;
        nodeList(int value,int order){
            this.value = value;
            this.order = order;
            next = null;
            prev = null;
        }
    }
    static nodeList head = new nodeList(Integer.MIN_VALUE,Integer.MIN_VALUE);
    static nodeList tail = new nodeList(Integer.MAX_VALUE,Integer.MAX_VALUE);
    static nodeList cur;

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int caseTimes = in.nextInt();
        for(int times =0; times < caseTimes; times++){
            initial();
            int length = in.nextInt();
            nodeList[] front;
            nodeList[] after;
            nodeList[] printArray = new nodeList[(length+1)/2];
            
            if(length%2==1){
                front = new nodeList[length];
                after = new nodeList[length];
            }else {
                front = new nodeList[length-1];
                after = new nodeList[length-1];
            }
            
            for(int i =0;i<length;i++){
                int data = in.nextInt();
                nodeList node = new nodeList(data,data);
                if(length % 2 ==1){
                    front[i] = node;
                    after[i] = node;
                }else {
                    if(i!=length-1){
                        front[i] = node;
                        after[i] = node;
                    }
                }
            }
            
            merge(after,0,after.length-1);

            for(int i =0 ;i<after.length;i++){
                add(after[i],i);
            }

            cur = after[after.length/2];
            printArray[printArray.length-1]=cur;
            for(int i = printArray.length-2; 0<=i ;i--){
                int right = 0;int left =0;int same =0;
                nodeList remove1 = front[(front.length-1)-2*((printArray.length-2)-i)];
                nodeList remove2 = front[(front.length-1)-2*((printArray.length-2)-i)-1];

                if(remove1.order>cur.order){
                    right += 1;
                }else if(remove1.order<cur.order){
                    left +=1;
                }else{
                    same++;
                }
                if(remove2.order>cur.order){
                    right += 1;
                }else if(remove2.order<cur.order){
                    left +=1;
                }else{
                    same++;
                }

                if(right ==2){
                    cur = cur.prev;
                }else if(left ==2){
                    cur =cur.next;
                }else if(left==1 && right==1){

                }else if(right ==1&& same==1){
                    cur = cur.prev;
                }else if(left ==1 && same==1){
                    cur = cur.next;
                }
                remove(remove1);
                remove(remove2);
                printArray[i] =cur;

            }
            for(int i= 0;i<printArray.length;i++){
                out.print(printArray[i].value+" ");
            }
            out.println();
        }

        out.close();
    }


    static void initial(){
        head.next = tail;
        tail.prev = head;
        cur = head;
    }
    static void add(nodeList nodeList,int i) {
        nodeList temp = cur;
        temp.next.prev = nodeList;
        nodeList.next = temp.next;
        temp.next =nodeList;
        nodeList.prev = temp;
        nodeList.order=i;
        cur = nodeList;
    }
    static void remove(nodeList a) {
        a.next.prev = a.prev;
        a.prev.next = a.next;
        a.next = null;
        a.prev = null;
    }
    static void merge_sort(nodeList[] array, int l, int mid, int r) {
        //size of those two subarray
        int size1 = mid - l + 1;
        int size2 = r - mid;

        nodeList[] Left = new nodeList[size1];
        nodeList[] Right = new nodeList[size2];

        //copy the array[] element date to the two subarray
        for (int i = 0; i < size1; i++) {
            Left[i] = array[l + i];
        }
        for (int j = 0; j < size2; j++) {
            Right[j] = array[mid + 1 + j];
        }


        int i = 0;
        int j = 0;
        int k = l;

        while (i < size1 && j < size2) {
            if (Left[i].value <= Right[j].value) {
                array[k] = Left[i];
                k++;
                i++;
            } else {
                array[k] = Right[j];
                k++;
                j++;
            }
        }

        // if excite some elements of subarray
        while (i < size1) {
            array[k] = Left[i];
            i++;
            k++;
        }
        while (j < size2) {
            array[k] = Right[j];
            j++;
            k++;
        }
    }
    static void merge(nodeList[] array, int l, int r) {
        if (l < r) {
            int mid = (r + l) / 2;
            merge(array, l, mid);
            merge(array, mid + 1, r);
            merge_sort(array, l, mid, r);
        }
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