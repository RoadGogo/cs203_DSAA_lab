import java.io.*;
import java.math.*;
import java.util.*;

public class lab4_solutionOfF {
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
            for (int times = 0; times < caseTimes; times++) {
                String string = in.next();
                char[] oriArray = string.toCharArray();
                char[] array = new char[220000];
                System.arraycopy(oriArray,0,array,0,oriArray.length);
                int length = string.length();
                int operationNum = in.nextInt();
                for (int i = 0; i < operationNum; i++) {
                    int operation = in.nextInt();
                    if (operation == 1) {
                        char data = in.next().charAt(0);
                        int index = in.nextInt();
                        System.arraycopy(array, index - 1, array, index, length - index + 1);
                        array[index - 1] = data;
                        length += 1;
                    } else {
                        int index = in.nextInt();
                        out.println(array[index - 1]);
                    }
                }


            }
        }
    }

    static void insert(String data, int index, char[] array) {
    }

    static void query(int index, char[] array, PrintWriter out) {

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

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
}