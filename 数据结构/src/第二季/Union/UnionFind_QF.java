package 第二季.Union;

/**
 * Quick Find
 */
public class UnionFind_QF extends UnionFind {
    public UnionFind_QF(int capacity) {
        super(capacity);
    }
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }

    public void union(int v1, int v2) {
        int p1 = parents[v1];
        int p2 = parents[v2];
        if (p1 == p2) return;
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == p1) {
                parents[i] = p2;
            }
        }
    }

}
