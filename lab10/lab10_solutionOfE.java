import java.io.*;
import java.math.*;
import java.util.*;

public class lab10_solutionOfE {
    static class Graph {
        ArrayList<Integer>[] arrayList;
        ArrayList<Integer>[] arrayListRS;
        Stack stack;
        boolean[] visited;
        boolean[] visitedRS;
        int size;
        ArrayList<TreeSet<Integer>> ansList;

        Graph(int size) {
            this.size = size;
            arrayList = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                arrayList[i] = new ArrayList<>();
            }
            arrayListRS = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                arrayListRS[i] = new ArrayList<>();
            }
            visited = new boolean[size];
            visitedRS = new boolean[size];
            ansList = new ArrayList<>();
        }

        void addEdge(int begin, int end) {
            arrayList[begin].add(end);
            arrayListRS[end].add(begin);
        }

        void forwardDFS(int i) {
            visited[i] = true;
            for (int s : arrayList[i]) {
                if (!visited[s]) {
                    forwardDFS(s);
                }
            }
            stack.push(i);
        }

        void backDFS(int i, int index) {
            visitedRS[i] = true;
            ansList.get(index).add(i);
            for (int s : arrayListRS[i]) {
                if (!visitedRS[s]) {
                    backDFS(s, index);
                }
            }
        }
    }

    static int getAnswer(Graph graph) {
        graph.stack = new Stack();
        for (int i = 0; i < graph.size; i++) {
            if (!graph.visited[i]) {
                graph.forwardDFS(i);
            }
        }
        while (!graph.stack.empty()) {
            int v = (int) graph.stack.pop();
            if (!graph.visitedRS[v]) {
                graph.ansList.add(new TreeSet<>());
                graph.backDFS(v, graph.ansList.size() - 1);
            }
        }
        int min = Integer.MAX_VALUE;
        for (TreeSet treeset : graph.ansList) {
            Iterator iterator = treeset.iterator();
            boolean flag = true;
            while (iterator.hasNext()) {
                int index = (int) iterator.next();
                for (int s : graph.arrayList[index]) {
                    if (!treeset.contains(s)) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            if (flag && treeset.size() < min) {
                min = treeset.size();
            }
        }
        return min;
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
        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int t = in.nextInt();
            int[][] arr = new int[n][2];
            for (int i = 0; i < n; i++) {
                arr[i][0] = in.nextInt();
                arr[i][1] = in.nextInt();
            }
            int[] arrList = new int[m];
            for (int i = 0; i < m; i++) {
                arrList[i] = in.nextInt();
            }
            Graph Graph = new Graph(m);
            initial(arr, arrList, Graph, n, t);
            int ans= getAnswer(Graph);
            out.println(ans);
        }
    }

    static void initial(int[][] arr, int[] arrList, Graph Graph, int n, int t) {
        for (int i = 0; i < n; i++) {
            int a = arr[i][0] - 1;
            int b = arr[i][1] - 1;
            if ((arrList[a] + 1) % t == arrList[b]) {
                Graph.addEdge(a, b);
            }
            if ((arrList[b] + 1) % t == arrList[a]) {
                Graph.addEdge(b, a);
            }
        }
    }

    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        char[] nextCharArray() {
            return next().toCharArray();
        }

        boolean hasNext() {
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

        BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        BigDecimal nextBigDecimal() {
            return new BigDecimal(next());
        }
    }
}