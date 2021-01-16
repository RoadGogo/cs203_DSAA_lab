import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;
public class lab10_solutionOfC{
    public static void main(String[] args)throws IOException {
        Reader in = new Reader();
        PrintWriter out=new PrintWriter(System.out);
        double[] power = new double[20];
        power[0] = 1;
        for (int i=1;i<20;i++){
            power[i] = 2*power[i-1];
        }
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] height = new int[n+1][m+1];
        double[][] speed = new double[n+1][m+1];
        for (int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                height[i][j] = in.nextInt();
            }
        }
        for (int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if (height[i][j]<=height[1][1]){
                    speed[i][j] = power[height[1][1]-height[i][j]];
                }else {
                    speed[i][j] = 1/power[height[i][j]-height[1][1]];
                }
            }
        }
        int[] vis = new int[n*m];
        double[] dis = new double[n*m];
        ArrayList<Node>[] graph = new ArrayList[n*m];
        for (int i=0;i<n*m;i++){
            graph[i] = new ArrayList<Node>();
        }
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (i>0){
                    graph[i*m+j].add(new Node((i-1)*m+j,1/speed[i+1][j+1]));
                }
                if (j>0){
                    graph[i*m+j].add(new Node(i*m+j-1,1/speed[i+1][j+1]));
                }
                if (i<n-1){
                    graph[i*m+j].add(new Node((i+1)*m+j,1/speed[i+1][j+1]));
                }
                if (j<m-1){
                    graph[i*m+j].add(new Node(i*m+j+1,1/speed[i+1][j+1]));
                }
            }
        }
        dijkstra(0,n*m,vis,dis,graph);
        System.out.printf("%.2f",dis[(n-1)*m+m-1]);
        out.close();
    }

    static void dijkstra(int start,int n,int[] vis,double[] dis,ArrayList<Node>[] graph){
        for (int i=0;i<n;i++){
            vis[i] = 0;
            dis[i] = Double.MAX_VALUE;
        }
        dis[start] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<Node>(Comparator.comparingDouble(it -> it.weight));
        queue.add(new Node(start,0));
        Node cur = null;
        while(!queue.isEmpty()){
            cur = queue.peek();
            queue.poll();
            if (vis[cur.id]==1)
                continue;
            vis[cur.id] = 1;
            for (int i=0;i<graph[cur.id].size();i++){
                int j = graph[cur.id].get(i).id;
                double k = graph[cur.id].get(i).weight;
                if (cur.weight+k<dis[j]&&vis[j]==0){
                    dis[j] = cur.weight+k;
                    queue.add(new Node(j,dis[j]));
                }
            }
        }
    }

    static class Node{
        int id;
        double weight;
        Node(int id,double weight){
            this.id = id;
            this.weight = weight;
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
