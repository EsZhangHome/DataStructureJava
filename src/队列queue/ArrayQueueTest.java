package 队列queue;

import java.util.Scanner;

/**
 *
 */
public class ArrayQueueTest {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s（show）: 显示队列");
            System.out.println("e（exit）: 退出程序");
            System.out.println("a（add）: 添加数据到队列");
            System.out.println("g（get）: 从队列取出数据");
            System.out.println("h（head）: 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符串
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

//使用数组模拟队列 --- 编写一个ArrayQueue 类
class ArrayQueue {
    private int maxSize; //表示数组的最大容量
    private int front; //对列头
    private int rear; //队列尾
    private int[] arr; //该数组存储数据，模拟队列

    // 创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; //指向队列头部，分析出来front是指向队列头的前一个位置
        rear = -1;//指向队列尾，指向队列尾部的数据（即就是队列最后一个数据）
    }

    // 判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已经满了");
        }
        rear++;
        arr[rear] = n;
    }

    //获取队列的数据  出队
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已经为空");
        }
        front++;
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已经为空");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[" + i + "] = %d\n", arr[i]);
        }
        for (int data : arr) {

        }
    }

    //查看队列头的数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已经为空");
        }
        return arr[front];
    }
}
