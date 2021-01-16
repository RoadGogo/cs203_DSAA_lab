import java.util.ArrayList;
        import java.util.Scanner;

public class lab4_solutionOfH{//use arrayList to do Lab4_H
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        int length = scanner.nextInt();
        int stepTimes = scanner.nextInt();
        ArrayList arrayList = new ArrayList();
        for(int i =0;i < length;i++){
            arrayList.add(scanner.nextInt());
        }
        for(int i =0;i < stepTimes;i++){
            String a=scanner.next();
            if(a.equals("i")){
                int index = scanner.nextInt();
                int value = scanner.nextInt();
                //做插入处理
                arrayList.add(index-1,value);
                continue;
            }else if(a.equals("r")){
                // 做删除处理
                int index = scanner.nextInt();
                arrayList.remove(index-1);
                continue;
            }else if(a.equals("q")){
                //查询处理
                int index = scanner.nextInt();
                System.out.println(arrayList.get(index-1));
                continue;
            }
        }
    }
}
