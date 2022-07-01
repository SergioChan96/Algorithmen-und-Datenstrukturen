package uebung2test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uebung2.AdjacencyListDirectedGraph;
import uebung2.DirectedGraph;

import java.util.Set;

public class JUnitAdjacencyListDirectedGraph {
    private DirectedGraph<Integer> g = new AdjacencyListDirectedGraph<>();
    @Before
    public void prepareAssets() {
        g.addEdge(1,2);
        g.addEdge(2,5);
        g.addEdge(5,1);
        g.addEdge(2,6);
        g.addEdge(3,7);
        g.addEdge(4,3);
        g.addEdge(4,6);
        g.addEdge(7,4);
    }
    @Test
    public void TestClass() {
        Assert.assertEquals(7, g.getNumberOfVertexes());
        Assert.assertEquals(8, g.getNumberOfEdges());
        Set<Integer> expectedSets = Set.of(1, 2, 3, 4, 5, 6, 7);
        Assert.assertEquals(expectedSets, g.getVertexSet());
        Assert.assertEquals(2, g.getOutDegree(2));
        Assert.assertEquals(2, g.getInDegree(6));
        expectedSets = Set.of(5 ,6);
        Assert.assertEquals(expectedSets, g.getSuccessorVertexSet(2));
        expectedSets = Set.of(2 ,4);
        Assert.assertEquals(expectedSets, g.getPredecessorVertexSet(6));

        Assert.assertTrue(g.containsEdge(1,2));
        Assert.assertFalse(g.containsEdge(2,1));
        Assert.assertEquals(1, g.getWeight(1, 2), 0.0001);
        g.addEdge(1, 2, 5.0);
        Assert.assertEquals(5,g.getWeight(1,2), 0.0001);

        System.out.println(g.invert());
        // 1 --> 5 weight = 1.0
        // 2 --> 1 weight = 5.0
        // 3 --> 4 weight = 1.0
        // 4 --> 7 weight = 1.0
        // ...

        Set<Integer> s = g.getSuccessorVertexSet(2);
        System.out.println(s);
        s.remove(5);	// Laufzeitfehler! Warum?
    }
}
