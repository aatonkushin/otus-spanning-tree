package org.tonkushin.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Вершина
 */
public class Vertex {
    String name;        // название вершины
    List<Edge> edges;   // список инцидентных рёбер

    public Vertex(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!obj.getClass().equals(Vertex.class)){
            return false;
        }

        return this.name.equals(((Vertex)obj).name);
    }

    @Override
    public String toString() {
        return "Vertex{" +name +
                '}';
    }

    /**
     * Возвращает список смежных вершин
     * @return список смежных вершин
     */
    public Set<Vertex> getAdjacencyList(){
        Set<Vertex> set = new HashSet<>();

        for (Edge e : edges) {
            if (e.vertex1 != this) {
                set.add(e.vertex1);
            } else {
                set.add(e.vertex2);
            }
        }

        return set;
    }
}
