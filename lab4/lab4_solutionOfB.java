import java.io.*;
import java.math.*;
import java.util.*;

public class lab4_solutionOfB{
    public static class Node{
        int value;
        Node prev;
        Node next;
        Node(int value){
            this.value = value;
            prev = null;
            next = null;
        }
    }
    static Node head = new Node(-1);
    static Node tail = new Node(-2);
    static Node current;//initial in tail
    static Node cur;//initial in head
    static void initial(){
        head.next=tail;
        tail.prev=head;
        cur = head;
        current = tail;
    }

    static void insertBeforeCurrent(String string){
        Node node = new Node(Integer.valueOf(string));
        Node temp = current.prev;
        Node tempTail = current;
        temp.next = node;
        node.prev = temp;
        node.next = tempTail;
        tempTail.prev = node;
    }

    static void remove(){//remove the Current
        if(current.value!=-2) {
            Node temp = current;
            current = current.next;
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            temp.next = null;
            temp.prev = null;
        }
    }

    static void query(int p){
        Node temp = head;
        for(int i=1;i<=p;i++){
            temp = temp.next;
        }
        System.out.println(temp.value);
    }

    static void replace(String string){//string next to 'r'
        if(current.value!=-2) {
            current.value =Integer.valueOf(string);
        }else {
            insertBeforeCurrent(string);
            current = current.prev;
        }
    }

    static void moveCurrent(String string){
        if(string.equals("L")){//->
            if(current.value!=-2) {
                current = current.next;
                return;
            }
        }else if(string.equals("H")){//<-
            if(current.prev.value!=-1) {
                current = current.prev;
                return;
            }
        }else if(string.equals("I")){
            while(current.prev.value!=-1){
                current=current.prev;
            }
            return;
        }
    }

    static void printList(PrintWriter out){
        cur = head.next;
        while(cur.value!=-2){
            out.print(cur.value);
            cur = cur.next;
        }
        out.println();
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            int caseTimes = in.nextInt();
            for(int times=0;times<caseTimes;times++){
                initial();
                int stringLength = in.nextInt();
                String string = in.next();
                for(int i =0 ;i< stringLength;){
                    String data = String.valueOf(string.charAt(i));
                    if(data.equals("L")||data.equals("H")||data.equals("I")){
                        moveCurrent(data);
                        i++;
                        continue;
                    }
                    else if(data.equals("r")){
                        if(i!=stringLength-1) {
                            String nextOfData = String.valueOf(string.charAt(i+1));
                            replace(nextOfData);
                            i+=2;
                        }else {
                            break;
                        }
                    }
                    else if(data.equals("x")){
                        remove();
                        i++;
                        continue;
                    }
                    else {
                        insertBeforeCurrent(data);
                        i++;
                        continue;
                    }
                }
                printList(out);
            }
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
//             try {
//                 return reader.ready();
//             } catch(IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
}
