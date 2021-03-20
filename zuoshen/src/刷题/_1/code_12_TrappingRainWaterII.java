package 刷题._1;

//https://leetcode-cn.com/problems/trapping-rain-water-ii/
//407题

import java.util.Comparator;
import java.util.PriorityQueue;

public class code_12_TrappingRainWaterII {

    public static class Node {
        private int value;
        private int rank;
        private int column;

        public Node(int value, int rank, int column) {
            this.value = value;
            this.rank = rank;
            this.column = column;
        }
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    public static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length <= 0 || heightMap[0] == null || heightMap[0].length <= 0) return 0;
        PriorityQueue<Node> queue = new PriorityQueue<>(new NodeComparator());
        //1、将四周的围墙放到小顶堆中，获得最小的口
        int Rank = heightMap.length;
        int Column = heightMap[0].length;

        boolean[][] isEnter = new boolean[Rank][Column];
        for (int i = 0; i < Column - 1; i++) {
            queue.add(new Node(heightMap[0][i], 0, i));
            isEnter[0][i] = true;
        }
        for (int i = 0; i < Rank - 1; i++) {
            queue.add(new Node(heightMap[i][Column - 1], i, Column - 1));
            isEnter[i][Column - 1] = true;
        }
        for (int i = Column - 1; i > 0; i--) {
            queue.add(new Node(heightMap[Rank - 1][i], Rank - 1, i));
            isEnter[Rank - 1][i] = true;
        }
        for (int i = Rank - 1; i > 0; i--) {
            queue.add(new Node(heightMap[i][0], i, 0));
            isEnter[i][0] = true;
        }
        //弹出最小值为边界的最大雨水值
        int max = 0;
        int water = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            //如果取出的值大于前一个进行修改湖围墙
            max = Math.max(max, node.value);
            int x = node.rank;
            int y = node.column;
            if (x > 0 && !isEnter[x - 1][y]) {
                water += Math.max(0, max - heightMap[x - 1][y]);
                isEnter[x - 1][y] = true;
                queue.add(new Node(heightMap[x - 1][y], x - 1, y));
            }
            if (x < Rank - 1 && !isEnter[x + 1][y]) {
                water += Math.max(0, max - heightMap[x + 1][y]);
                isEnter[x + 1][y] = true;
                queue.add(new Node(heightMap[x + 1][y], x + 1, y));
            }
            if (y > 0 && !isEnter[x][y - 1]) {
                water += Math.max(0, max - heightMap[x][y - 1]);
                isEnter[x][y - 1] = true;
                queue.add(new Node( heightMap[x][y - 1], x, y - 1));
            }
            if (y < Column - 1 && !isEnter[x][y + 1]) {
                water += Math.max(0, max - heightMap[x][y + 1]);
                isEnter[x][y + 1] = true;
                queue.add(new Node( heightMap[x][y+1], x, y + 1));
            }

        }
        return water;
    }

    public static void main(String[] args) {
        int water = trapRainWater(new int[][]{{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}});
        System.out.println(water);

    }

}
