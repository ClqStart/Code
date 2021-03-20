package 刷题._1;

import java.util.HashMap;


public class  code_20_TopKTimesRealTime {
    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }

    public static class TopKRecord {
        private Node[] heap;
        private int heapSize;

        //词频表
        private HashMap<String, Node> strNodeMap;
        // node所在堆得位置
        private HashMap<Node, Integer> nodeIndexMap;

        public TopKRecord(int K) {
            heap = new Node[K];
            heapSize = 0;
            strNodeMap = new HashMap<>();
            nodeIndexMap = new HashMap<>();
        }

        public void add(String str) {
            Node curNode = null;
            int preIndex = -1;
            if (!strNodeMap.containsKey(str)) {
                curNode = new Node(str, 1);
                strNodeMap.put(str, curNode);
                nodeIndexMap.put(curNode, -1);
            } else {
                curNode = strNodeMap.get(str);
                curNode.times++;
                preIndex = nodeIndexMap.get(curNode);
            }
            if (preIndex == -1) {
                if (heapSize == heap.length) {
                    if (heap[0].times < curNode.times) {
                        nodeIndexMap.put(heap[0], -1);
                        nodeIndexMap.put(curNode, 0);
                        heap[0] = curNode;
                        heapfy(0, heapSize);
                    }
                } else {
                    nodeIndexMap.put(curNode, heapSize);
                    heap[heapSize] = curNode;
                    heapInsert(heapSize++);
                }
            } else {
                heapfy(preIndex, heapSize);
            }

        }



        public void printTopK() {
            System.out.println("TOP: ");
            for (int i = 0; i < heap.length; i++) {
                if (heap[i] == null) {
                    break;
                }
                System.out.print("Str: " + heap[i].str);
                System.out.println("Times: " + heap[i].times);
            }
        }

        private void heapInsert(int index) {
            while (index > 0) {
                int parent = (index - 1) / 2;
                if (heap[index].times < heap[parent].times) {
                    swap(heap, index, parent);
                    index = parent;
                } else {
                    break;
                }
            }
        }

        private void heapfy(int index, int heapsize) {
            int left = index * 2 + 1;
            int right = left + 1;
            int smallest = index;
            while (left < heapsize) {
                if(heap[left].times < heap[index].times){
                    smallest = left;
                }
                 smallest = (right < heapsize && heap[right].times < heap[smallest].times) ? right : left;
                if (smallest !=index) {
                    swap(heap, index, smallest);
                    index = smallest;
                } else {
                    break;
                }
                left = index * 2 + 1;
                right = left + 1;
            }

        }

        private void swap(Node[] heap, int index1, int index2) {
            nodeIndexMap.put(heap[index1], index2);
            nodeIndexMap.put(heap[index2], index1);
            Node tmp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = tmp;
        }


    }


    public static String[] generateRandomArray(int len, int max) {
        String[] res = new String[len];
        for (int i = 0; i != len; i++) {
            res[i] = String.valueOf((int) (Math.random() * (max + 1)));
        }
        return res;
    }

    public static void printArray(String[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TopKRecord record = new TopKRecord(2);
        record.add("zuo");
        record.printTopK();
        record.add("cheng");
        record.add("cheng");
        record.printTopK();
        record.add("Yun");
        record.add("Yun");
        record.printTopK();
    }

}
