import java.util.Scanner;

public class lab5_solutionOfE {
    public static class stack {
        int top = -1;
        int num;
        int[] array;
        stack(int num) {
            this.num = num;
            array =new int[300000/num];
        }
    }

    public static void push(stack stack, int item) {
        stack.top++;
        stack.array[stack.top] = item;
    }

    public static void pop(stack stack,int[] arr) {
        System.out.println(stack.array[stack.top]);
        arr[stack.array[stack.top]]-=1;
        stack.top--;
    }

    static int outside = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] array = new int[300001];
        int length = 0;
        stack[] stackList = new stack[300001];
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
            if(i!=0) {
                stackList[i] = new stack(i);
            }
        }
        String oper = in.next();
        while (!oper.equals("nsdd")) {
            if (oper.equals("put-in")) {
                int item = in.nextInt();
                array[item] += 1;
                if (array[item] > outside) {
                    outside = array[item];
                }
                length++;
                //push the item into the #array[num] stack
                push(stackList[array[item]], item);
            } else if (oper.equals("eat")) {
                if (length == 0) {
                    System.out.println("pa");
                } else {
                    length--;
                    //pop the outside and the top stack item, and the array[num]-1;
                    if (stackList[outside].top != -1) {
                        pop(stackList[outside],array);
                    } else {
                        outside -= 1;
                        pop(stackList[outside],array);
                    }
                }
            }
            oper = in.next();
        }
    }
}
