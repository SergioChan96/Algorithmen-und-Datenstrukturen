package uebung4;

import java.awt.*;
import java.util.*;
import java.util.List;

public class TelNet {

    private int size;
    private Map<TelKnoten, Integer> nodeMap;
    private List<TelVerbindung> minCostTree;
    private int lbg;

    public TelNet(int lbg) {
        size  = 0;
        this.lbg = lbg;
        nodeMap = new HashMap<>();
        minCostTree = new LinkedList<>();
    }

    public boolean addTelKnoten(int x, int y) {
        TelKnoten newNode =  new TelKnoten(x,y);
        if(!nodeMap.containsKey(newNode)) {
            nodeMap.put(newNode, size);
            size++;
            return true;
        }
        return false;
    }

    public boolean computeOptTelNet() {
        UnionFind forest = new UnionFind(size);
        PriorityQueue<TelVerbindung> edges = new PriorityQueue<>(size, (o1, o2) -> o1.c - o2.c);
        minCostTree.clear();

        for (var tempSet : nodeMap.entrySet()) {
            for (var tempSet1 : nodeMap.entrySet()) {
                if (tempSet.equals(tempSet1)){
                    continue;
                } else {
                    int cost = (Math.abs(tempSet.getKey().x - tempSet1.getKey().x) + Math.abs(tempSet.getKey().y - tempSet1.getKey().y)); //taxicab geometry
                    if (cost <= lbg) {
                        edges.add(new TelVerbindung(tempSet.getKey(), tempSet1.getKey(), cost));
                    }
                }
            }
        }

        while (forest.size() != 1 && !edges.isEmpty()) {
            TelVerbindung tempTV = edges.poll();
            int t1 = forest.find(nodeMap.get(tempTV.u));
            int t2 = forest.find(nodeMap.get(tempTV.v));
            if (t1 != t2) {
                forest.union(t1,t2);
                minCostTree.add(tempTV);
            }
        }

        if (forest.size() != 1) {
            return false;
        } else {
            return true;
        }
    }
    public List<TelVerbindung> getOptTelNet() throws IllegalStateException {
        return minCostTree;
    }
    public int getOptTelNetKosten() throws IllegalStateException {
        int cost = 0;
        for (var tv : minCostTree) {
            cost += tv.c;
        }
        return cost;
    }

    public  void drawOptTelNet(int xMax, int yMax) throws IllegalStateException {
        StdDraw.clear();

        StdDraw.setPenRadius(0.019);
        StdDraw.setPenColor(StdDraw.RED);

        // draw Vertexes
        nodeMap.keySet().forEach(i -> StdDraw.point(i.x / (double) xMax, i.y / (double) yMax));

        // draw connection
        StdDraw.setPenRadius(0.0055);
        StdDraw.setPenColor(StdDraw.BLUE);
        minCostTree.forEach(i -> {
            StdDraw.line(
                    i.u.x / (double) xMax,
                    i.u.y / (double) yMax,
                    i.v.x / (double) xMax,
                    i.v.y / (double) yMax);

        });

        StdDraw.show(0);
    }
    public void generateRandomTelNet(int n, int xMax, int yMax) {
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            int x = r.nextInt(xMax);
            int y = r.nextInt(yMax);
            if (!this.addTelKnoten(x,y)) {
                i--;
            }
        }
    }
    public int size() {
        return size;
    }
    @Override
    public String toString() {
        return nodeMap.toString();
    }
    public static void main(String[] args) {
        System.out.println("Baum 1");
        TelNet newTN = new TelNet(7);
        newTN.addTelKnoten(1,1);
        newTN.addTelKnoten(3,1);
        newTN.addTelKnoten(4,2);
        newTN.addTelKnoten(3,4);
        newTN.addTelKnoten(7,5);
        newTN.addTelKnoten(2,6);
        newTN.addTelKnoten(4,7);

        System.out.println("Gibt es einen einzelnen Baum nach dem computen: " + newTN.computeOptTelNet());
        System.out.println("Gesamte Kosten: " + newTN.getOptTelNetKosten());
        System.out.println("TelNet Size is: " + newTN.size());

        newTN.drawOptTelNet(7,7);

        System.out.println("Baum 2");
        int max = 1000;
        TelNet newTN2 = new TelNet(100);

        newTN2.generateRandomTelNet(max, max, max);

        System.out.println("Gibt es einen einzelnen Baum nach dem computen: " + newTN2.computeOptTelNet());
        System.out.println("Gesamte Kosten: " + newTN2.getOptTelNetKosten());
        System.out.println("TelNet Size is: " + newTN2.size());
        newTN2.drawOptTelNet(max,max);

    }
}
