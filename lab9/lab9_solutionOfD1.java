import java.io.*;
import java.math.*;
import java.util.*;

public class lab9_solutionOfD1 {
    static class Dijkstra {
        private DirectedEdge[] edgeTo;
        private int[] distTo;
        private PriorityQueue<IntPair> pq;

        private static final int INFINITY = -1;

        public Dijkstra(EdgeWeightGraph graph, int source) {
            int nodes = graph.nodes;
            edgeTo = new DirectedEdge[nodes];
            distTo = new int[nodes];
            pq = new PriorityQueue<>(nodes, Comparator.comparingInt(it -> it.weight));

            for (int i = 0; i < nodes; i++) {
                distTo[i] = INFINITY;
            }
            distTo[source] = 0;

            pq.add(new IntPair(source, 0));
            while (!pq.isEmpty()) {
                relax(graph, pq.poll().key);
            }
        }

        private void relax(EdgeWeightGraph graph, int node) {
            for (DirectedEdge e : graph.adj[node]) {
                if (distTo[e.to] == INFINITY || distTo[e.to] > distTo[e.start] + e.weight) {
                    int oldDist = distTo[e.to];
                    distTo[e.to] = distTo[e.start] + e.weight;
                    edgeTo[e.to] = e;

                    IntPair query = new IntPair(e.to, oldDist);
                    if (pq.contains(query)) {
                        pq.remove(query);
                        pq.add(new IntPair(e.to, distTo[e.to]));
                    } else {
                        pq.add(new IntPair(e.to, distTo[e.to]));
                    }
                }
            }
        }

    }
    static class EdgeWeightGraph {

        private final int nodes;
        private int edges;

        private ArrayList<DirectedEdge>[] adj;

        EdgeWeightGraph(int nodes) {
            this.nodes = nodes;
            this.edges = 0;
            adj = new ArrayList[nodes];
            for (int i = 0; i < adj.length; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        void addEdge(DirectedEdge e) {
            adj[e.start].add(e);
            edges++;
        }
    }
    static class DirectedEdge {
        private final int start;
        private final int to;
        private final int weight;

        DirectedEdge(int start, int to, int weight) {
            this.start = start;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return start + "->" + to + " " + weight;
        }
    }
    static class IntPair {
        final int key;
        final int weight;

        public IntPair(int left, int right) {
            this.key = left;
            this.weight = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IntPair intPair = (IntPair) o;
            return key == intPair.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);

        int n = in.nextInt();
        EdgeWeightGraph graph = new EdgeWeightGraph(n);
        int m = in.nextInt();

        for (int i = 0; i < m; i++) {
            int start = in.nextInt() ;
            int to = in.nextInt() ;
            int weight = in.nextInt();
            graph.addEdge(new DirectedEdge(start-1, to-1, weight));
        }

        Dijkstra dijkstra = new Dijkstra(graph, 0);
        int ans = dijkstra.distTo[n - 1];
        out.println(ans);
        out.close();
    }

    static class Task {
        public void solve(InputReader in, PrintWriter out) {

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