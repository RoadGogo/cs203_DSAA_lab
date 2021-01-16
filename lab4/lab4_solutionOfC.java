import java.io.*;
import java.util.*;

//lab4_C1
public class lab4_solutionOfC {

    public static class nodeList {
        int coefficient;
        int exponent;
        nodeList next;

        nodeList(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
            next = null;
        }
    }

    static nodeList head = new nodeList(-1, Integer.MIN_VALUE);// it is not really head
    static nodeList tail = new nodeList(-1, Integer.MAX_VALUE);//it is not reallt tail
    static nodeList cur;
    static nodeList current;

    static void initial() {
        head.next = tail;
        cur =head;
    }

    static void add(nodeList node) {

    }

    static String string(int coe, int exp) {
        if (coe == 1 || coe == -1) {
            if (exp == 0) {
                return coe + "";
            } else if (exp == 1) {
                if (coe == 1) {
                    return "x";
                } else {
                    return "-x";
                }
            } else {
                return "x^" + exp;
            }
        } else {
            if (exp == 0) {
                return coe + "";
            } else if (exp == 1) {
                return coe + "x";
            } else {
                return coe + "x^" + exp;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int caseTimes = in.nextInt();
        for (int times = 0; times < caseTimes; times++) {
            initial();
            int reserve = in.nextInt();
            for (int i = 0; i < reserve; i++) {
                cur = head;
                int coe = in.nextInt();
                int exp = in.nextInt();
                while (cur.next.exponent <= exp) {
                    cur = cur.next;
                }
                if (cur.exponent == exp) {
                    cur.coefficient += coe;
                } else {
                    nodeList node = new nodeList(coe, exp);
                    node.next = cur.next;
                    cur.next = node;
                }

            }


            int intoInventory = in.nextInt();
            for (int i = 0; i < intoInventory; i++) {
                cur = head;
                int coe = in.nextInt();
                int exp = in.nextInt();
                while (cur.next.exponent <= exp) {
                    cur = cur.next;
                }
                if (cur.exponent == exp) {
                    cur.coefficient += coe;
                } else {
                    nodeList node = new nodeList(coe, exp);
                    node.next = cur.next;
                    cur.next = node;
                }
            }


            cur = head.next;
            if (intoInventory + reserve != 0) {
                boolean sign = false;
                boolean flag = false;
                while (cur.exponent != Integer.MAX_VALUE) {
                    if (cur.coefficient != 0) {
                        if (cur.coefficient > 0) {
                            if (sign) {
                                out.print("+" + string(cur.coefficient, cur.exponent));
                            } else {
                                out.print(string(cur.coefficient, cur.exponent));
                                sign = true;
                            }
                            flag=true;
                        } else {
                            if (sign) {
                                out.print("-"+string(-cur.coefficient, cur.exponent));
                            } else {
                                out.print("-"+string(-cur.coefficient, cur.exponent));
                                sign = true;
                            }
                            flag = true;
                        }
                    }
                    cur = cur.next;
                }
                if (!flag) {
                    out.print(0);
                }
               out.println();
            } else {
                out.print(0);
                out.println();
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