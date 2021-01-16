import java.io.*;
import java.math.*;
import java.util.*;

public class labGame_solutionOfF {
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
            int caseTimes = in .nextInt();
            for(int times = 0; times<caseTimes;times++){
                int length = in.nextInt();
                String[] list1 =new String[length/2];
                String[] list2 =new String[length/2];
                char[] ansList = new char[length];
                for(int i =0;i<length/2 ;i++){
                    list1[i] = in.next();
                }
                for(int i =0;i<length/2 ;i++){
                    list2[i] = in.next();
                }
                for(int i =0;i<length/2;i++){
                    ansList[2*i] = morseDecode(list1[i]);
                    ansList[2*i+1] = morseDecode(list2[i]);
                }
                for(int i =0;i<length;i++){
                    out.print(ansList[i]);
                }
                out.println();
            }
        }
    }


    static char morseDecode(String x) {
        switch (x) {
            case ".-":
                return 'A';
            case "-...":
                return 'B';
            case "-.-.":
                return 'C';
            case "-..":
                return 'D';
            case ".":
                return 'E';
            case "..-.":
                return 'F';
            case "--.":
                return 'G';
            case "....":
                return 'H';
            case "..":
                return 'I';
            case ".---":
                return 'J';
            case "-.-":
                return 'K';
            case ".-..":
                return 'L';
            case "--":
                return 'M';
            case "-.":
                return 'N';
            case "---":
                return 'O';
            case ".--.":
                return 'P';
            case "--.-":
                return 'Q';
            case ".-.":
                return 'R';
            case "...":
                return 'S';
            case "-":
                return 'T';
            case "..-":
                return 'U';
            case "...-":
                return 'V';
            case ".--":
                return 'W';
            case "-..-":
                return 'X';
            case "-.--":
                return 'Y';
            case "--..":
                return 'Z';
            case".----":
                return '1';
            case"..---":
                return '2';
            case"...--":
                return '3';
            case"....-":
                return '4';
            case".....":
                return '5';
            case"-....":
                return '6';
            case"--...":
                return '7';
            case"---..":
                return '8';
            case"----.":
                return '9';
            case"-----":
                return '0';
        }
        return ' ';
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