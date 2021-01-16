import java.io.*;
import java.math.*;
import java.util.*;

public class labBonus_solutionOfB {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static Stack<Integer> A = new Stack();
    static Stack<Integer> B = new Stack();
    static int[] sum =  new int[1000010];
    static int[] mx =  new int[1000010];

    static class Task {
        public void solve(InputReader in, PrintWriter out) {
                sum[0] = 0;
                mx[0] = Integer.MIN_VALUE;
//                while (!A.empty()) A.pop();
//                while (!B.empty()) B.pop();
                int operTimes = in.nextInt();
                for (int times = 0; times < operTimes; times++) {
                    char operation = in.next().charAt(0);
                    if (operation == 'I') {
                        int x = in.nextInt();
                        A.push(x);
                        updata();
                    } else if (operation == 'D') {
                        A.pop();
                    } else if (operation == 'L') {
                        if(A.empty()) continue;
                        B.push(A.peek()); A.pop();
                    } else if (operation == 'R') {
                        if(B.empty()) continue;
                        A.push(B.peek()); B.pop();
                        updata();
                    } else if (operation == 'Q') {
                        int k = in.nextInt();
                        out.println(mx[k]);
                    }
                }
            }
        }

    static void updata(){
        int p=A.size();
        sum[p]=sum[p-1]+A.peek();
        mx[p]=Math.max(mx[p-1],sum[p]);
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

        public BigDecimal nextBigDecimal() {
            return new BigDecimal(next());
        }
    }
}