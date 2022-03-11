package 链表Link;

public class DoubleLinkTest {
    public static void main(String[] args) {
        DoubleLink doubleLink = new DoubleLink();
        Node heto1 = new Node(1, "宋江", "及时雨");
        Node heto2 = new Node(2, "卢俊义", "玉麒麟");
        Node heto3 = new Node(3, "吴用", "智多星");
        Node heto4 = new Node(4, "林冲", "豹子头");
        Node heto5 = new Node(5, "ffas", "fff");

        doubleLink.add(heto1);
        doubleLink.add(heto2);
        doubleLink.add(heto3);
        doubleLink.add(heto4);
//        doubleLink.insert(heto5,2);

//        doubleLink.del(3);
//        doubleLink.del(heto4);
//        doubleLink.update(3,"zes","小九");

//        doubleLink.addByOrder(heto5);
//        doubleLink.addByOrder(heto3);
//        doubleLink.addByOrder(heto2);
//        doubleLink.addByOrder(heto4);
//        doubleLink.addByOrder(heto1);


        doubleLink.list();
    }
}

class DoubleLink {
    private Node head = new Node(0, "", "");

    public Node getHead() {
        return head;
    }

    // 添加数据到双向链表
    // 添加方法定义为添加到链表的尾部
    public void add(Node node) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    // 根据顺序添加
    public void addByOrder(Node node) {
        if (head.next == null) {
            head.next = node;
            node.pre = head;
            return;
        }
        Node temp = head.next;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.num >= node.num) {
                break;
            }
            temp = temp.next;
        }
        temp.pre.next = node;
        node.pre = temp.pre;
        node.next = temp;
        temp.pre = node;
    }

    // 某个位置插入数据
    public void insert(Node node, int index) {
        if (head.next == null) {
            return;
        }
        if (size() < index) {
            return;
        }
        Node temp = head.next;
        int count = 0;
        while (temp != null) {
            if (count == index) {
                break;
            }
            count++;
            temp = temp.next;
        }
        temp.pre.next = node;
        node.pre = temp.pre;
        node.next = temp;
        temp.pre = node;
    }

    // 删除数据 链表尾部删除(没有重复的数据)
    public void del(Node node) {
        if (head.next == null)
            return;
        Node temp = head.next;
        while (temp != null) {
            if (temp.num == node.num) {
                temp.pre.next = temp.next;
                if (temp.next != null)
                    temp.next.pre = temp.pre;
                return;
            }
            temp = temp.next;
        }
    }

    // 删除某个位置的数据
    public void del(int index) {
        if (head.next == null)
            return;
        if (size() < index) {
            return;
        }
        Node temp = head.next;
        int count = 0;
        while (temp != null) {
            if (count == index) {
                temp.pre.next = temp.next;
                if (temp.next != null)
                    temp.next.pre = temp.pre;
                return;
            }
            count++;
            temp = temp.next;
        }
    }

    //更细某个节点的数据
    public void update(int num, String... strings) {
        if (head.next == null) {
            return;
        }
        if (num <= 0) {
            return;
        }
        Node temp = head.next;
        while (temp != null) {
            if (temp.num == num) {
                temp.name = strings[0];
                temp.nickName = strings[1];
                return;
            }
            temp = temp.next;
        }
    }

    // 获取链表的有效数据
    public int size() {
        if (head.next == null)
            return 0;
        Node temp = head.next;
        int count = 0;
        while (true) {
            if (temp == null) {
                break;
            }
            count++;
            temp = temp.next;
        }
        return count;
    }

    // 遍历展示链表的数据
    public void list() {
        Node temp = head.next;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }
}

class Node {
    public int num;
    public String name;
    public String nickName;
    public Node next;
    public Node pre;

    public Node(int no, String name, String nickName) {
        this.num = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}