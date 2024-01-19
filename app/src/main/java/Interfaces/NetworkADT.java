package Interfaces;

public interface NetworkADT<T> extends GraphADT<T> {
    void addEdge(T vertex1, T vertex2, double weight);
    double shortestPathWeight(T startVertex, T targetVertex);
}

