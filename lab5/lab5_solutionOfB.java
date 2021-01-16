
import java.util.Scanner;

public class lab5_solutionOfB {
    static int front=0; //remove
    static int rear=0;  //add
    static int length=0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int operaNum = scanner.nextInt(); //operaNum
        int[] array = new int[operaNum];

        for(int i=0; i<operaNum;i++){
            String string = scanner.next();
            if(string.equals("E")){
                int item = scanner.nextInt();
                enQueue(item,array);
                continue;
            }else if(string.equals("D")){
                deQueue();
                continue;
            }else if(string.equals("A")){
                printQueueOfHead(array);
                continue;
            }
        }
        printQueue(length,array);
    }

    private static void printQueueOfHead(int[] array) {
        if(length!=0) {
            System.out.println(array[front]);
        }else {
            System.out.println();
        }
    }

    private static void printQueue(int length,int[] array) {
        if(length!=0) {
            for (int i = front; i < rear; i++) {
                System.out.print(array[i] + " ");
            }
        }else {
            System.out.println();
        }
    }

    private static void deQueue() {
        if(front < rear && length!=0){
            front++;
            length--;
        }else if(front == rear && length!=0){
            length--;
        }
    }

    private static void enQueue(int item,int[] array){
        if(rear<array.length){
            array[rear] = item;
            rear++;
            length++;
        }
    }
}
