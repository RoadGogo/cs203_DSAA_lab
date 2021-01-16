import java.io.*;
import java.math.*;
import java.util.*;

public class lab10_solutionOfB {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in);
        out.close();
    }
    //DFS searching
    static class DFS {
        Integer numVertex;
        Integer numEdge;
        Vode[] Vodes;
        boolean[] vst;
        ArrayList<Integer> order = new ArrayList<>();
        int[] types;
        int count = 0;


        DFS(Vode[] Vodes) {
            this.numEdge = 0;
            this.numVertex = Vodes.length;
            this.Vodes = Vodes;
            vst = new boolean[numVertex];
            types = new int[numVertex];
        }

        void addEdge(Integer start, Integer end) {
            Vode Vode = Vodes[start];
            Eode Eode = new Eode(end);

            Eode firstEode = Vode.firstEdge;
            if (firstEode == null) {
                Vode.firstEdge = Eode;
            } else {
                Eode.next = firstEode;
                Vode.firstEdge = Eode;
            }
        }

        void dfs(int root) {
            Vode Vode = this.Vodes[root];
            vst[root] = true;

            Eode currentEode = Vode.firstEdge;

            while (currentEode != null) {
                int VodeIndex = currentEode.adjvex;
                if (!vst[VodeIndex]) {
                    dfs(VodeIndex);
                }
                currentEode = currentEode.next;
            }
            order.add(root);
        }

        void judge(int root, Integer count) {
            types[root] = count;
            Vode Vode = this.Vodes[root];
            Eode currentEode = Vode.firstEdge;

            while (currentEode != null) {
                int VodeIndex = currentEode.adjvex;
                if (types[VodeIndex] == 0) {
                    judge(VodeIndex, count);
                }
                currentEode = currentEode.next;
            }
        }
    }

    static class Task {
        void solve(InputReader in) {
            int numVertex = in.nextInt();
            int numEdge = in.nextInt();
            Vode[] Vodes = new Vode[numVertex + 1];
            Vode[] Vodes1 = new Vode[numVertex + 1];
            for (int i = 1; i <= numVertex; i++) {
                Vodes[i] = new Vode(i);
                Vodes1[i] = new Vode(i);
            }
            DFS graph_ori = new DFS(Vodes);
            DFS graph_rev = new DFS(Vodes1);

            initial(in, numEdge, numVertex, graph_ori, graph_rev);

            int count = 0;
            for (int i = numVertex - 1; i >= 0; i--) {
                if (graph_rev.types[graph_ori.order.get(i)] == 0) {
                    count++;
                    graph_rev.judge(graph_ori.order.get(i), count);
                }
            }
            printAns(count);
        }

        private void initial(InputReader in, int numEdge, int numVertex, DFS graph_ori, DFS graph_rev) {
            for (int i = 1; i <= numEdge; i++) {
                int first = in.nextInt();
                int second = in.nextInt();
                graph_rev.addEdge(second, first);
                graph_ori.addEdge(first, second);

            }
            for (int i = 1; i <= numVertex; i++) {
                if (!graph_ori.vst[i]) {
                    graph_ori.dfs(i);
                }
            }
        }


    }

    static void printAns(int count) {
        if (count == 1) {
            System.out.println("Bravo");
        } else if (count > 1) {
            System.out.println("wawawa");
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
class Vode {
    int data;
    Eode firstEdge;

    Vode(int data) {
        this.data = data;
    }
}
class Eode {
    int adjvex;
    Eode next;

    Eode(int adjvex) {
        this.adjvex = adjvex;
    }
}

