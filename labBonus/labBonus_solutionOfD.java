import java.io.*;
import java.math.*;
import java.util.*;

public class labBonus_solutionOfD{
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
            int length = in.nextInt();
            LinkedList<String> linklist = new LinkedList<>();

            String[] strings = new String[caseTimes];
            for (int i = 0; i < caseTimes; i++) {
                for (int j = 0; j < 256; j++)
                    strings[i] += in.next();
            }

            for (int times = 0; times < caseTimes; times++) {
                if (linklist.contains(strings[times])) {
                    out.println("hit");
                    int index = linklist.indexOf(strings[times]);
                    String b = linklist.get(index);
                    linklist.remove(index);
                    linklist.addFirst(b);
                } else {
                    out.println("miss");
                    if (linklist.size() != length) {
                        linklist.addFirst(strings[times]);
                    } else {
                        linklist.removeLast();
                        linklist.addFirst(strings[times]);
                    }

                }
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