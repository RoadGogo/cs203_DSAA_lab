import java.util.Scanner;

public class lab5_solutionOfC {
    public static class node {
        int value;
        node next;
        node prev;

        node(int value) {
            this.value = value;
            next = null;
            prev = null;
        }
    }

    public static class linkList {
        node front = new node(-1);
        node rear = new node(-2);
        int num;
        int length;

        linkList(int num) {
            this.num = num;
            length = 0;
            front.next = rear;
            rear.prev = front;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
        int queNum = in.nextInt();
        int operaNum = in.nextInt();
        linkList[] myDeQueList = new linkList[queNum + 1];
        for (int i = 0; i <= queNum; i++) {
            myDeQueList[i] = new linkList(i);
        }
        for (int i = 0; i < operaNum; i++) {
            int type = in.nextInt();
            if (type == 1) {
                int dequeOfNum = in.nextInt();
                int judge = in.nextInt();
                int value = in.nextInt();
                operA(myDeQueList[dequeOfNum], judge, value);
            } else if (type == 2) {
                int dequeOfNum = in.nextInt();
                int judge = in.nextInt();
                operB(myDeQueList[dequeOfNum], judge);
            } else if (type == 3) {
                int dequeOfNum1 = in.nextInt();
                int dequeOfNum2 = in.nextInt();
                int judge = in.nextInt();
                operC(myDeQueList[dequeOfNum1], myDeQueList[dequeOfNum2], judge);
            }
        }
    }
    }

    private static void operC(linkList deque1, linkList deque2, int judge) {
        if (judge == 0) {
            node node = deque2.front.next;
            while (node.value != -2) {
                node temp = new node(node.value);
                deque1.rear.prev.next = temp;
                temp.next=deque1.rear;
                temp.prev = deque1.rear.prev;
                deque1.rear.prev =temp;
                node = node.next;
            }
        } else {
            node node = deque2.rear.prev;
            while (node.value != -1) {
                node temp = new node(node.value);
                deque1.rear.prev.next = temp;
                temp.next=deque1.rear;
                temp.prev = deque1.rear.prev;
                deque1.rear.prev =temp;
                node = node.prev;
            }
        }

        deque2.rear.prev = deque2.front;
        deque2.front.next = deque2.rear;
        deque1.length += deque2.length;
        deque2.length = 0;
    }

    private static void operB(linkList deque, int judge) {
        if (deque.length == 0) {
            System.out.println(-1);
        } else {
            deque.length -= 1;
            if (judge == 0) {
                node cur = deque.front.next;
                System.out.println(cur.value);
                cur.next.prev = cur.prev;
                cur.prev.next = cur.next;
                cur.next = null;
                cur.prev = null;

            } else {
                node cur = deque.rear.prev;
                System.out.println(cur.value);
                cur.next.prev = cur.prev;
                cur.prev.next = cur.next;
                cur.next = null;
                cur.prev = null;
            }
        }
    }

    private static void operA(linkList deque, int judge, int value) {
        node node = new node(value);
        if (judge == 0) {
            node.next = deque.front.next;
            deque.front.next.prev = node;
            deque.front.next = node;
            node.prev = deque.front;
        } else {
            deque.rear.prev.next = node;
            node.prev = deque.rear.prev;
            node.next = deque.rear;
            deque.rear.prev = node;
        }
        deque.length += 1;
    }
}
