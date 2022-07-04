package org.tonkushin;

import org.tonkushin.graph.Edge;
import org.tonkushin.graph.Graph;
import org.tonkushin.graph.Vertex;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();

        Scanner scan = new Scanner(System.in);
        System.out.println("Тест алгоритма поиска минимального остовного дерева");
        System.out.println("Введите количество рёбер");
        int e = scan.nextInt();             // количество рёбер
        System.out.println("Введите " + e + " названия вершин и их вес x, y, w");
        for (int i = 0; i < e; i++) {
            String v1 = scan.next();
            String v2 = scan.next();
            int y = scan.nextInt();

            graph.addEdge(v1, v2, y);
        }

        System.out.println("Вес графа: " + graph.getWeight());
        printVertexList(graph);
        printEdgeList(graph);


        Graph minGraph = new Graph();

        graph.sortEdgeList();
        for (Edge edge : graph.getEdgeList()) {
            minGraph.addEdge(edge);

            if (minGraph.isCyclic()) {
                minGraph.removeEdge(edge);
            }
        }

        System.out.println("Вес минимального остовного дерева: " + minGraph.getWeight());
        printVertexList(minGraph);
        printEdgeList(minGraph);
    }

    private static void printVertexList(Graph graph){
        System.out.println("Вершины: ");
        for (Vertex v : graph.getVertexList()) {
            System.out.println(v.toString() + ": " + v.getAdjacencyList());
        }
    }

    private static void printEdgeList(Graph graph) {
        System.out.println("Рёбра:");
        for (Edge edge : graph.getEdgeList()) {
            System.out.println(edge);
        }
    }
}
