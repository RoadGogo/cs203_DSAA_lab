import java.util.Scanner;

public class lab7_solutionOfA {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        int caseTimes = in.nextInt();
        for(int times =0; times<caseTimes;times++){
            long count =in.nextLong();
            long childNum = in.nextLong();
            int i=0;
            while(count - Math.pow(childNum,i)>0){
                count = (long) (count - Math.pow(childNum,i));
                i++;
            }
            long ans = (long) (Math.pow(childNum,i-1) +count-(count+childNum-1)/childNum);
            System.out.println(ans);
        }
    }
}
