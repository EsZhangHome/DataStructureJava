package 链表Link;

import java.util.Stack;

public class SingleLinkTest {
    public static void main(String[] args) {
        SingleLink singleLink = new SingleLink();
        HeroNode heto1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heto2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heto3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heto4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode heto5 = new HeroNode(5, "ffas", "fff");

//        singleLink.add(heto1);
//        singleLink.add(heto2);
//        singleLink.add(heto3);
//        singleLink.add(heto4);

        // 添加按照标号排序
        singleLink.addByOrder(heto1);
        singleLink.addByOrder(heto2);
        singleLink.addByOrder(heto3);
        singleLink.addByOrder(heto4);
        singleLink.addByOrder(heto5);


        SingleLink singleLink1 = new SingleLink();
        HeroNode heto_4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode heto_5 = new HeroNode(5, "ffas", "fff");
        HeroNode heto_6 = new HeroNode(6, "fadf", "aaa");
        HeroNode heto_7 = new HeroNode(7, "fadf", "aaa");

        singleLink1.addByOrder(heto_4);
        singleLink1.addByOrder(heto_5);
        singleLink1.addByOrder(heto_6);
        singleLink1.addByOrder(heto_7);

//        singleLink.del(3);

//        singleLink.list();
//        System.out.println("单链表中有效节点的个数：" + singleLink.size());
//
//        HeroNode indexNode = singleLink.getIndex(8);
//        System.out.println(indexNode.toString());
//        HeroNode node = reverseLinkList(singleLink.getHead());
//        HeroNode temp = node.next;
//        while (temp != null) {
//            System.out.println(temp.toString());
//            temp = temp.next;
//        }
//        reversePrint(singleLink.getHead());
//        singleLink.list();
        HeroNode heroNode = merge2(singleLink.getHead().next, singleLink1.getHead().next);
        HeroNode temp = heroNode.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }

    }

    // 单链表的反转
    // 思路：
    // 1. 定义一个节点 reverse 初始化一个新的节点
    // 2. 从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在reverse链表的最前端
    // 3. 原来的链表 head.next = reverse.next
    public static HeroNode reverseLinkList(HeroNode head) {
        //如果链表为空或者只有一个节点，直接返回当前链表
        if (head.next == null || head.next.next == null) {
            return head;
        }

        HeroNode cur = head.next; // 定义一个辅助指针，帮助遍历原来的链表
        HeroNode next = null; // 指向当前链表的下一个节点
        HeroNode reverseNode = new HeroNode(0, "", "");
        // 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverse 的最前面
        while (cur != null) {
            next = cur.next; // 先暂存当前节点的下一个节点，后面会用到
            cur.next = reverseNode.next; // 将当前节点的next 指向 reverse 的最前端 ，也就是放在最前面
            reverseNode.next = cur; // 将 reverse.next 指向当前的节点，就相当于把当前节点插入到 reverse 链表的最前端
            cur = next; // 然后将当前节点后移到下一个节点，进行下一个节点的获取
        }
        head.next = reverseNode.next; // 将reverse 的 链表 赋值给 head 的 next 链表
        return head;
    }

    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return; //空链表
        }

        // 创建一个栈，将每个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur); // 节点入栈
            cur = cur.next;  // 节点后移
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }

    }

    // 合并两个有序的单链表，合并之后的链表仍然是有序的

    public static HeroNode merge(HeroNode node1, HeroNode node2) {
        if (node1 == null) {//如果 链表 1 为空，直接返回链表 2
            return node2;
        }
        if (node2 == null) {//如果 链表 2 为空，直接返回链表 1
            return node1;
        }
        if (node1.num < node2.num) {
            node1.next = merge(node1.next, node2);
            return node1;
        } else {
            node2.next = merge(node1, node2.next);
            return node2;
        }
    }

    public static HeroNode merge2(HeroNode node1, HeroNode node2) {
        //1. 判断当前链表是否都为null
        if (node1 == null && node2 == null) {
            return null;
        }

        //2. 如果 node1 为空，直接返回node2
        if (node1 == null) return node2;

        //3. 如果 node2 为空，直接返回node3
        if (node2 == null) return node1;

        //4. 定义一个首节点和尾结点
        HeroNode headNode = null, lastNode = null;

        //4. 获取 node1 和 node2 的首节点的最小节点
        if (node1.num > node2.num) {
            headNode = node2;
            lastNode = node2;
            node2 = node2.next;
        } else {
            headNode = node1;
            lastNode = node1;
            node1 = node1.next;
        }

        //5. 定义一个循环获取node1 和 node2 的最小节点
        while (node1 != null && node2 != null) {
            if (node1.num < node2.num) {
                lastNode.next = node1;
                lastNode = node1;
                node1 = node1.next;
            } else {
                lastNode.next = node2;
                lastNode = node2;
                node2 = node2.next;
            }
        }

        //6. 判断其中某个链表还有数据
        if (node1 == null) {
            lastNode.next = node2;
        }
        if (node2 == null) {
            lastNode.next = node1;
        }

        //7. 返回合并后的链表
        return headNode;
    }

}

class SingleLink {
    //先初始化一个头结点，头结点不要动
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到链表
    public void add(HeroNode heroNode) {
        // 1. 找到当前链表的最后节点，
        // 2. 将最后节点的next 指向 新的节点
        // 因为head 头结点不能动，因此引入一个辅助节点temp
        HeroNode temp = head;
        // 遍历链表找到最后，当temp.next == null 说明是最后一个节点，
        // 将新的节点添加到 temp.next 指向新的节点
        while (temp.next != null) {//当退出while循环的时候，temp 指向最后的节点
            temp = temp.next; // 后移temp
        }
        temp.next = heroNode; // 直接将新的节点赋值给 temp.next
    }

    //添加addByOrder 根据 num 进行排序
    // 1. 首先找到新添加节点的位置，是通过辅助的变量（指针），通过变量来搞定
    // 2. 新的节点.next = temp.next
    // 3. temp.next = 新节点
    public void addByOrder(HeroNode heroNode) {
        // 因为头结点head 不能动，因此通过辅助节点 temp 来帮助找到位置
        // 因为是单链表，所以我们的 temp 需要位于 添加位置的前一个节点 ，否则添加不了
        HeroNode temp = head;
        boolean flag = false;// 用于标识是否添加过 默认为 false
        while (true) {
            if (temp.next == null) { //说明temp 已经是链表的最后
                break;
            }

            if (temp.next.num > heroNode.num) {// 找到添加新节点的前一个位置
                break;
            } else if (temp.next.num == heroNode.num) {
                //说明编号存在
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            System.out.printf("准备插入的英雄 %s 已经存在~\n", heroNode.toString());
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //删除某个节点
    // 1. 利用辅助节点搞定,找到删除节点的前一个节点 temp
    // 2. 将删除节点的前一个节点.next = 前一个节点.next.next  temp.next = temp.next.next
    // 3. 要删除的节点不被任何引用指向，会被垃圾回收机制回收

    public void del(int num) {
        if (head.next == null) {
            System.out.println("当前链表为空");
            return;
        }

        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                System.out.println("要删除的节点不存在~");
                return;
            }
            if (temp.next.num == num) {
                break;
            }
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }

    //求单链表中有效节点的个数
    public int size() {
        if (head.next == null)
            return 0;
        int count = 0;
        HeroNode temp = head;
        while (temp.next != null) { //不统计头结点
            count++;
            temp = temp.next;
        }
        return count;
    }

    // 获取倒数 第 K 个
    // 1. 先遍历获取链表的总长度
    // 2. 获取倒数 第 K 个 ，其实就是正数 第 (length - K)个
    public HeroNode getIndex(int k) {
        // 先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return null;
        }

        int length = size();

        if (k <= 0 || k > length) {
            throw new IndexOutOfBoundsException("链表长度越界 ，链表长度 length = " + length + " , index = " + k);
        }
        int index = length - k;
        HeroNode temp = head.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //打印链表
    public void list() {
        // 先判断链表是否为空
        if (head.next == null) {
            System.out.println("当前链表为空~~");
            return;
        }
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            System.out.println(temp.toString());
        }
    }
}


class HeroNode {
    public int num;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.num = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
