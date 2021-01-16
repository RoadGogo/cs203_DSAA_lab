import java.io.*;
import java.util.*;
//use 递归 and visited[] to find
public class lab9_solutionOfB {
    public static class graph{
        int nodeNum;
        LinkedList<Integer> adj[];

        graph(int nodeNum){
            this.nodeNum = nodeNum;
            adj = new LinkedList[nodeNum];
            for(int i =0 ;i<nodeNum;i++) adj[i] =new LinkedList<>();
        }

        void addEdge(int v1,int v2){
            adj[v1].add(v2);
            adj[v2].add(v1);
        }

        boolean isCycleUntil(int v,boolean[] hasVisited,int parents){
            hasVisited[v] = true;
            Iterator<Integer> iterator = adj[v].iterator();
            while(iterator.hasNext()){
                int i = iterator.next();
                if(!hasVisited[i]){
                    if(isCycleUntil(i,hasVisited,v)){
                        return true;
                    }
                }else if(i != parents ){
                    return true;
                }
            }
            return false;
        }
        boolean isCycle(int v){
            boolean[] hasVisited = new boolean[v];
            for(int i=0;i<v;i++){
                hasVisited[i] = false;
            }
            for(int i =0;i<v;i++){
                if(!hasVisited[i]){
                    if(isCycleUntil(i,hasVisited,-1)){
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int nodeNum = in.nextInt();
        int edgeNum = in.nextInt();
        graph map = new graph(nodeNum);
        for(int i = 0 ;i<edgeNum;i++){
            int v1 = in.nextInt()-1;
            int v2 = in.nextInt()-1;
            map.addEdge(v1,v2);
        }
        if(map.isCycle(nodeNum)){
            out.println("Bad");
        }else {
            out.println("Good");
        }

        out.close();

    }



}
