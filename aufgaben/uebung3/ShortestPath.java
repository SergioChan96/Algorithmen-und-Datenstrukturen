// O. Bittel;
// 01.04.2021

package uebung3;

import uebung3.sim.SYSimulation;
import uebung2.DirectedGraph;

import java.util.*;

// ...

/**
 * Kürzeste Wege in Graphen mit A*- und Dijkstra-Verfahren.
 * @author Oliver Bittel
 * @since 27.01.2015
 * @param <V> Knotentyp.
 */
public class ShortestPath<V> {
	
	SYSimulation sim = null;
	Map<V,Double> dist; 		// Distanz für jeden Knoten
	Map<V,V> pred; 				// Vorgänger für jeden Knoten
	IndexMinPQ<V,Double> cand; 	// Kandidaten als PriorityQueue PQ
	DirectedGraph<V> graph;
	Heuristic h;
	V start;
	V goal;

	/**
	 * Konstruiert ein Objekt, das im Graph g k&uuml;rzeste Wege 
	 * nach dem A*-Verfahren berechnen kann.
	 * Die Heuristik h schätzt die Kosten zwischen zwei Knoten ab.
	 * Wird h = null gewählt, dann ist das Verfahren identisch 
	 * mit dem Dijkstra-Verfahren.
	 * @param g Gerichteter Graph
	 * @param h Heuristik. Falls h == null, werden kürzeste Wege nach
	 * dem Dijkstra-Verfahren gesucht.
	 */
	public ShortestPath(DirectedGraph<V> g, Heuristic<V> h) {
		dist = new HashMap<>();
		pred = new HashMap<>();
		cand = new IndexMinPQ<>();
		graph = g;
		if (h == null) {
			this.h = new Heuristic<V>() {
				@Override
				public double estimatedCost(V u, V v) {
					return 0;
				}
			};
		} else {
			this.h = h;
		}
	}

	/**
	 * Diese Methode sollte nur verwendet werden, 
	 * wenn kürzeste Wege in Scotland-Yard-Plan gesucht werden.
	 * Es ist dann ein Objekt für die Scotland-Yard-Simulation zu übergeben.
	 * <p>
	 * Ein typische Aufruf für ein SYSimulation-Objekt sim sieht wie folgt aus:
	 * <p><blockquote><pre>
	 *    if (sim != null)
	 *       sim.visitStation((Integer) v, Color.blue);
	 * </pre></blockquote>
	 * @param sim SYSimulation-Objekt.
	 */
	public void setSimulator(SYSimulation sim) {
		this.sim = sim;
	}

	/**
	 * Sucht den kürzesten Weg von Starknoten s zum Zielknoten g.
	 * <p>
	 * Falls die Simulation mit setSimulator(sim) aktiviert wurde, wird der Knoten,
	 * der als nächstes aus der Kandidatenliste besucht wird, animiert.
	 * @param s Startknoten
	 * @param g Zielknoten
	 */
	public void searchShortestPath(V s, V g) {
		cand.clear();
		LinkedList<V> candList = new LinkedList<>();
		start = s;
		goal = g;
		for(V v : graph.getVertexSet()) {
			dist.put(v, Double.MAX_VALUE);
			pred.put(v, null);
		}
		dist.put(s, 0.0);
		candList.add(s);
		while(!candList.isEmpty()){
			V minV = s;
			double minDist = Double.MAX_VALUE;
			for(V v : candList){
				if(dist.get(v) + h.estimatedCost(s,g) < minDist) {
					minDist = dist.get(v) + h.estimatedCost(s,g);
					minV = v;
				}
				if (v.equals(g)) {
					cand.add(v, dist.get(v));
					System.out.println("Besuche Knoten "+ v + " mit d: " + dist.get(v));
					return;
				}
			}
			candList.remove(minV);
			System.out.println("Besuche Knoten " + minV+ " mit d: " + dist.get(minV));
			cand.add(minV, dist.get(minV));
			for (V v : graph.getSuccessorVertexSet(minV)) {
				if (dist.get(v) == Double.MAX_VALUE) {
					candList.add(v);
				}
				if (dist.get(minV) + graph.getWeight(minV, v) < dist.get(v)) {
					pred.put(v, minV);
					dist.put(v, dist.get(minV) + graph.getWeight(minV, v));
				}
			}
		}

	}

	/**
	 * Liefert einen kürzesten Weg von Startknoten s nach Zielknoten g.
	 * Setzt eine erfolgreiche Suche von searchShortestPath(s,g) voraus.
	 * @throws IllegalArgumentException falls kein kürzester Weg berechnet wurde.
	 * @return kürzester Weg als Liste von Knoten.
	 */
	public List<V> getShortestPath() {
		try {
			LinkedList<V> shortestPath = new LinkedList<>();
			V temp = goal;
			while (temp != start) {
				shortestPath.add(temp);
				temp = pred.get(temp);
			}
			shortestPath.add(start);
			Collections.reverse(shortestPath);
			return shortestPath;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Liefert die Länge eines kürzesten Weges von Startknoten s nach Zielknoten g zurück.
	 * Setzt eine erfolgreiche Suche von searchShortestPath(s,g) voraus.
	 * @throws IllegalArgumentException falls kein kürzester Weg berechnet wurde.
	 * @return Länge eines kürzesten Weges.
	 */
	public double getDistance() {
		try {
			return dist.get(goal);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

}
