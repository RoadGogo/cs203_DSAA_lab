import java.util.Scanner;

//lab4_A
public class lab4_solutionOfA {
    public static class Nodelist{
        int value;
        Nodelist next;
        Nodelist(int value){
            this.value=value;
            next=null;
        }
    }
    static Nodelist head = new Nodelist(-2);
    static Nodelist first;

    static void add(Nodelist nodelist){
        first.next = nodelist;
        first = nodelist;
    }

    static void remove(int skip){
            Nodelist temp = first;
            for (int i = 1; i < skip - 1; i++) {
                temp = temp.next;
            }
            System.out.print(temp.next.value + " ");
            first = temp.next.next;
            temp.next.next = null;
            temp.next = first;

    }

    static void printNodelist(int length){
        Nodelist temp = first;
        for(int i =0; i< length;i++){
            System.out.print(temp.value+" ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseTimes = scanner.nextInt();
        for(int times =0; times<caseTimes;times++){
            first=head;
            int length = scanner.nextInt();// the nums of dishes
            int skip = scanner.nextInt();// if skip=3, begin from 1 ,and ++ until = 3,then print it,and del it.
                                         //the next dish will be the first read
            for(int i = 1; i<=length;i++){
                Nodelist nodelist = new Nodelist(i);//create the length's nodeList
                add(nodelist);
                if(i==length){
                    first.next=head.next;//create cycle list
                    first = head.next;
                }
            }

            while(length!=0&&skip!=1){
                remove(skip);
                length--;
            }
            if(skip==1){
                printNodelist(length);
            }
            System.out.println();
        }

    }
}