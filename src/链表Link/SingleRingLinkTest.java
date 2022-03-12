package 链表Link;

public class SingleRingLinkTest {
    public static void main(String[] args) {
        SingleRingLink singleRingLink = new SingleRingLink();
        singleRingLink.addBoy(5);
        singleRingLink.showBoy();
        singleRingLink.outBoy(1,2,5);
    }
}

class SingleRingLink {
    //创建一个first 节点，当前没有编号
    private Boy first = null;

    // 添加一个节点，构建成一个环形的链表
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums 值不正确");
            return;
        }
        Boy curboy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.next = first;
                curboy = first;
            } else {
                curboy.next = boy;
                boy.next = first;
                curboy = boy;
            }
        }
    }

    public void showBoy() {
        if (first.next == null) {
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.num);
            if (curBoy.next == first) {
                break;
            }
            curBoy = curBoy.next;
        }
    }

    //出圈的顺序

    /**
     * @param startNo  表示从第几个小号开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void outBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("数据有误");
            return;
        }

        // 先创建一个辅助指针，帮助小孩出圈
        Boy helper = first;
        // 将helper 指向聊表的最后这个节点
        while (true) {
            if (helper.next == first) {
                break;
            }
            helper = helper.next;
        }

        //报数
        // 报数之前，先移动到开始的位置
        // 先让 first 和 helper 移动到 k -1 次
        for (int i = 0; i < startNo-1; i++) {
            first = first.next;
            helper = helper.next;
        }
        // 当小孩报数的时候，让 first 和 helper 指针同时 移动 m-1 次，然后出圈
        while (true){
            if (helper ==first){
                break;
            }

            //让 first 和 helper 同时移动 countNum-1 次
            for (int i = 0; i < countNum-1; i++) {
                first = first.next;
                helper = helper.next;
            }
            // 这时 first 指向的节点是出圈的节点
            System.out.printf("小孩 %d 出圈 \n",first.num);

            //这时first 指向iang的节点出圈
            first = first.next;
            helper.next = first;
        }

        // 最后一个节点，此时helper 和 first 指向同一个节点
        System.out.printf("圈中最后一个小孩 %d \n",first.num);
    }
}

class Boy {
    public int num;
    public Boy next;

    public Boy(int num) {
        this.num = num;
    }
}
