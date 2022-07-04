package org.tonkushin.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Взвешенный граф
 * Хранит список вершин и список рёбер.
 */
public class Graph {
    List<Edge> edgeList;        // список рёбер
    List<Vertex> vertexList;    // список вершин

    int weight = 0;

    public Graph() {
        edgeList = new ArrayList<>();
        vertexList = new ArrayList<>();
    }

    /**
     * Добавляет вершину в граф
     * @param v - вершина
     * @return возвращает добавленную или уже существующую вершину
     */
    private Vertex addVertex(Vertex v) {
        if (!vertexList.contains(v)) {
            vertexList.add(v);
            return v;
        }

        return vertexList.get(vertexList.indexOf(v));
    }

    /**
     * Добавляет ребро в граф
     * @param vertex1Name название вершины 1
     * @param vertex2Name название вершины 2
     * @param weight вес ребра
     */
    public void addEdge(String vertex1Name, String vertex2Name, int weight) {
        // Создаём вершины
        Vertex vertex1 = new Vertex(vertex1Name);
        Vertex vertex2 = new Vertex(vertex2Name);

        // Добавляем вершины в граф
        Vertex v1 = this.addVertex(vertex1);
        Vertex v2 = this.addVertex(vertex2);

        // Создаём ребро
        Edge edge = new Edge(v1, v2, weight);

        // Добавляем ребро в список рёбер графа
        this.edgeList.add(edge);

        // Добавляем ребро к списку инцидентности каждой из вершин
        v1.edges.add(edge);
        v2.edges.add(edge);

        // Увеличиваем вес графа
        this.weight += weight;
    }

    /**
     * Добавляет ребро в граф
     * @param newEdge новое ребро
     */
    public void addEdge(Edge newEdge) {
        addEdge(newEdge.vertex1.name, newEdge.vertex2.name, newEdge.weight);
    }

    public void removeEdge(Edge e) {
        Edge edgeToRemove = null;

        for (Edge edge : this.edgeList) {
            if (edge.vertex1.equals(e.vertex1) && edge.vertex2.equals(e.vertex2) ) {
                edgeToRemove = edge;
            }
        }

        if (edgeToRemove != null && this.edgeList.remove(edgeToRemove)) {
            edgeToRemove.vertex1.edges.remove(edgeToRemove);
            edgeToRemove.vertex2.edges.remove(edgeToRemove);
            this.weight -= e.weight;
        }
    }

    /**
     * Список вершин графа
     * @return список вершин графа
     */
    public List<Vertex> getVertexList() {
        return vertexList;
    }

    /**
     * Список рёбер графа
     * @return список рёбер графа
     */
    public List<Edge> getEdgeList() {
        return edgeList;
    }

    /**
     * Вес графа
     * @return вес графа
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Сортирует рёбра по возрастанию
     */
    public void sortEdgeList(){
        this.edgeList.sort(Edge::compareTo);
    }

    private boolean isCyclicUtil(Vertex v, List<Vertex> visited, Vertex parent) {
        // Помечаем вершину посещённой
        visited.add(v);

        // Рекурсивно обходим все смежные вершины
        for (Vertex v1 : v.getAdjacencyList()) {
            // Если вершина ещё не посещена, то посещаем её
            if (!visited.contains(v1)) {
                if (isCyclicUtil(v1, visited, v)) {
                    return true;
                }
                // Если вершина посещена и не является родительской, то присутствует цикл
            } else if (v1 != parent) {
                return true;
            }
        }

        return false;
    }

    /**
     * Проверка графа на цикличность
     * @return true - если граф цикличен
     */
    public boolean isCyclic() {
        // Список посещённых вершин
        List<Vertex> visited = new ArrayList<>();

        for (Vertex v : this.vertexList) {
            if (!visited.contains(v)) {
                if (isCyclicUtil(v, visited, null)) {
                    return true;
                }
            }
        }

        return false;
    }
}
