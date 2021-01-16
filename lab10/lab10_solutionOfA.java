import java.io.*;
import java.math.*;
import java.util.*;


public class lab10_solutionOfA {
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
            int cityNum = in.nextInt();//start from 0
            int roadNum = in.nextInt();
            int[][] matrix = new int[cityNum][cityNum];
            for (int i = 0; i < cityNum; i++) {
                for (int j = 0; j <cityNum; j++) {
                    matrix[i][j] = 0;
                }
            }
            for (int i = 0; i < roadNum; i++) {
                //adj matrix
                int city1 = in.nextInt() - 1;
                int city2 = in.nextInt() - 1;
                int weight = in.nextInt();
                matrix[city1][city2] = weight;
                matrix[city2][city1] = weight;
            }
            int ans = primAlgorithm(cityNum, roadNum, matrix);
            out.println(ans);
        }
    }

     static int primAlgorithm(int cityNum, int roadNum, int[][] matrix) {
        int ans = 0;
        int[] lowest = new int[cityNum];
        int[] closest = new int[cityNum];//record the closest city
        boolean visited[] = new boolean[cityNum];
        //initial
        for (int i = 0; i < cityNum; i++) {
            lowest[i] = matrix[0][i];
            visited[i] = false;
            closest[i] = 0;
        }
        visited[0] =true;
        //compare
        for (int i = 0; i < cityNum; i++) {
            int min = Integer.MAX_VALUE;
            int j = 0;
            for (int k = 1; k < cityNum; k++) {
                if (lowest[k] != 0 && lowest[k] < min && !visited[k]) {
                    min = lowest[k];
                    j = k;
                }
            }

            visited[j] = true;
            ans += matrix[closest[j]][j];

            //update
            for (int k = 0; k < cityNum; k++) {
                if ((!visited[k] && matrix[j][k] != 0)&&(matrix[j][k] < lowest[k] || lowest[k] == 0)) {
                    lowest[k] = matrix[j][k];
                    closest[k] = j;

                }
            }

        }
        return ans;
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

