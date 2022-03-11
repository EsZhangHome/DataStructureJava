package 稀疏数组sparsearray;

import java.io.*;

/**
 * 稀疏数组定义：
 * 1. 当一个数组中大部分元素为 0 忙活着为同一个值的数组时，可以使用稀疏数组来保存该数组。
 * 2. 稀疏数组的处理方法:
 * 1) 记录数组一共有几行几列，有多少个不同的值。
 * 2) 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模。
 */
public class SparseArrayTest {
    public static void main(String[] args) {
        //创建一个原始的二维数组
        // 0 表示没有棋子
        // 1 表示 黑子
        // 2 表示 篮子
        int chessArray[][] = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[4][5] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //把二维数据转成稀疏数组
        // 1. 先遍历非零数据的个数
        int sum = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0) {
                    sum++;
                }
            }
        }

        System.out.println("一共有 " + sum + " 个非零数据");

        int sparseArray[][] = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        //遍历二维数据将非零的值，放在稀疏数组中
        int count = 0; //用于记录是第几个非零数据
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }

        System.out.println("转化为稀疏数组后");

        for (int[] row : sparseArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将稀疏数组存储到磁盘
        saveToFile(sparseArray);

        //将稀疏数组从磁盘读取出来
        int[][] sparseArray2 = readFromFile("E:\\JavaProjectHome\\DataStructureStudy\\src\\稀疏数组sparsearray\\map.data");

        System.out.println("从磁盘回复后的稀疏数组");
        for (int[] row : sparseArray2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将稀疏数组恢复成二维数组
        System.out.println("将稀疏数组恢复到二维数组");
        // 1. 先读取稀疏数组的第一行，根据第一行的数据，创建二维数组，
        int[][] newChessArray = new int[sparseArray2[0][0]][sparseArray2[0][1]];
        //再遍历数据
        // 2. 再读取稀疏数组的后几行数据，并赋值给原始的二维数组。

        // 用 非零的个数做循环次数
//        for (int i = 1; i < sparseArray2[0][2]; i++) {
//            newChessArray[sparseArray2[i][0]][sparseArray2[i][1]] = sparseArray2[i][2];
//        }

        // 用稀疏数组的长度做循环次数
        for (int i = 1; i < sparseArray2.length; i++) {
            newChessArray[sparseArray2[i][0]][sparseArray2[i][1]] = sparseArray2[i][2];
        }

        System.out.println("转换后的二维数组");
        for (int[] row : newChessArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    public static void saveToFile(int[][] sparseArray) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("E:\\JavaProjectHome\\DataStructureStudy\\src\\稀疏数组sparsearray\\map.data"));
            for (int[] array : sparseArray) {
                fileWriter.write(array[0] + "\t" + array[1] + "\t" + array[2]);
                fileWriter.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static int[][] readFromFile(String path) {
        int[][] sparseArray = null;
        boolean isNotRead = false;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(path)));
            String lineStr = null;
            int curCount = 0;
            while ((lineStr = bufferedReader.readLine()) != null) {
                String[] tempStr = lineStr.split("\t");
                if (!isNotRead) {
                    sparseArray = new int[Integer.parseInt(tempStr[2])+1][3];
                    sparseArray[curCount][0] = Integer.parseInt(tempStr[0]);
                    sparseArray[curCount][1] = Integer.parseInt(tempStr[1]);
                    sparseArray[curCount][2] = Integer.parseInt(tempStr[2]);
                    curCount++;
                    isNotRead = true;
                } else {
                    sparseArray[curCount][0] = Integer.parseInt(tempStr[0]);
                    sparseArray[curCount][1] = Integer.parseInt(tempStr[1]);
                    sparseArray[curCount][2] = Integer.parseInt(tempStr[2]);
                    curCount++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sparseArray;
    }
}
