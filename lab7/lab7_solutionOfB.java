import java.util.Scanner;

public class lab7_solutionOfB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseTimes =in.nextInt();
        for(int times =0;times<caseTimes;times++){
            long nodeNum = in.nextLong();
            long i =0;
            while(nodeNum-Math.pow(2,i)>0){
                nodeNum = (long) (nodeNum-Math.pow(2,i));
                i++;
            }
            long ans = i+1;
            System.out.println(ans);
        }
    }
}
