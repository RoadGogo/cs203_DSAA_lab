import java.util.PriorityQueue;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
public class lab10_solutionOfD {
    public static void main(String[] args)throws IOException {
        Reader in = new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] grid = new int[n][m];
        WeightedGraph weightedGraph = new WeightedGraph(n*m);
        for (int i =0;i<n;i++){
            for (int j=0;j<m;j++){
                grid[i][j] = in.nextInt();
            }
        }
        for (int i =0;i<n;i++){
            for (int j =0;j<m;j++){
                int position = i*m+j;
                if (j!=0){
                    weightedGraph.insertEdge(new Edge(grid[i][j]*grid[i][j-1],position,i*m+j-1));
                }
                if (j!=m-1){
                    weightedGraph.insertEdge(new Edge(grid[i][j]*grid[i][j+1],position,i*m+j+1));
                }
                if (i!=0) {
                    weightedGraph.insertEdge(new Edge(grid[i][j] * grid[i-1][j], position, (i-1) * m + j));
                }
                if (i!=n-1){
                    weightedGraph.insertEdge(new Edge(grid[i][j]*grid[i+1][j],position,(i+1)*m+j));
                }
            }
        }
        Comparator<Edge> cmp = new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o2.weight-o1.weight;
            }
        };
        UnionFind unionFindSet =  new UnionFind(n*m);
        ArrayList<Edge> list = new ArrayList<>();
        PriorityQueue<Edge> queue = new PriorityQueue(cmp);
        queue.addAll(weightedGraph.edgeList);
        while (!queue.isEmpty()&&list.size()<=n*m-1){
            Edge cur = queue.poll();
            System.out.println(cur.weight);
            int first = cur.vertex1;
            int second = cur.vertex2;
            if (unionFindSet.isConnected(first,second)){
                continue;
            }else {
                unionFindSet.unionElements(first,second);
                list.add(cur);
            }
        }
        long res = 0;
        for (int i=0;i<list.size();i++){
            res+=list.get(i).weight;
        }
        out.println(res);
        out.close();
    }
    static class WeightedGraph{
        int numVertexes;
        int numEdges;
        ArrayList<Edge>[] adjacent;
        ArrayList<Edge> edgeList = new ArrayList<>();

        WeightedGraph(int numVertexes){
            this.numVertexes = numVertexes;
            this.numEdges = 0;
            this.adjacent = new ArrayList[numVertexes];
            for (int i=0;i<adjacent.length;i++){
                adjacent[i] = new ArrayList<>();
            }
        }

        public void insertEdge(Edge edge){
            adjacent[edge.vertex1].add(edge);
            adjacent[edge.vertex2].add(edge);
            edgeList.add(edge);
            ++numEdges;
        }

    }
    static class Edge{
        int weight;
        int vertex1;
        int vertex2;

        public Edge(int weight,int vertex1,int vertex2){
            this.weight = weight;
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }
    }
    static class UnionFind {
        private int[] parent;
        private int count;
        private int[] rank;

        public UnionFind(int n) {

            this.parent =new int[n];
            this.rank=new int[n];
            this.count = n;
            for(int i=0;i<n;i++){
                parent[i]=i;
                rank[i]=1;
            }
        }

        public int find(int p){
            assert(p>=0&&p<count);
            if(p!=parent[p])
                parent[p]=find(parent[p]);
            return parent[p];
        }

        public boolean isConnected(int p,int q){
            return find(p)==find(q);
        }

        public void unionElements(int p,int q){
            int pRoot=find(p);
            int qRoot=find(q);
            if(pRoot==qRoot)
                return;
            if(rank[pRoot]<rank[qRoot]){
                parent[pRoot]=qRoot;
            }else if(rank[qRoot]<rank[qRoot]){
                parent[qRoot]=pRoot;
            }else{
                parent[pRoot]=qRoot;
                rank[qRoot]+=1;
            }

        }


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
