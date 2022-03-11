package 栈stack;

import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        // 栈 特点 先进后出
        Stack<String> stack = new Stack<>();
        stack.add("zrs");
        stack.add("css");
        stack.add("java");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
