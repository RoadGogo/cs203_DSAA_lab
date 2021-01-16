import java.io.*;
import java.util.*;

public class lab9_solutionOfD{

    static class Dijkstra {
        private DirectedEdge[] edgeTo;
        private int[] distTo;
        public PriorityQueue<IntPair> pq;

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
                if (distTo[e.to] == INFINITY || distTo[e.to] > distTo[e.from] + e.weight) {
                    int oldDist = distTo[e.to];
                    distTo[e.to] = distTo[e.from] + e.weight;
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
            adj[e.from].add(e);
            edges++;
        }
    }
    static class DirectedEdge {
        private final int from;
        private final int to;
        private final int weight;

        DirectedEdge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + "->" + to + " " + weight;
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

    public static void main(String[] args)  {
        Scanner in = new Scanner(System.in);
        PrintWriter out  = new PrintWriter(System.out);
        int n = in.nextInt();
        EdgeWeightGraph graph = new EdgeWeightGraph(n);

        int m = in.nextInt();

        int i =0;
        while ( i < m) {
            int from = in.nextInt() ;
            int to = in.nextInt() ;
            int weight = in.nextInt();
            graph.addEdge(new DirectedEdge(from-1, to-1, weight));
            i++;
        }
        Dijkstra dijkstra = new Dijkstra(graph, 0);
        out.println(dijkstra.distTo[n - 1]);
        out.close();
    }
    
}