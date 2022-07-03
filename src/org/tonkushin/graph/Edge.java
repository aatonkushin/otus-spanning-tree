package org.tonkushin.graph;

import org.tonkushin.graph.Vertex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Ребро
 */
public class Edge implements Comparable<Edge>{
    Vertex vertex1;         // вершина 1
    Vertex vertex2;         // вершина 2

    int weight;             // вес

    public Edge(Vertex vertex1, Vertex vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return Integer.compare(this.weight, e.weight);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "v1=" + vertex1 +
                ", v2=" + vertex2 +
                ", weight=" + weight +
                '}';
    }
}
