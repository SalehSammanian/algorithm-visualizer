package com.example.algorithmvisualizer.model;


import com.example.algorithmvisualizer.Node;

public interface AlgorithmVisualizerModelInterface {

    void initialize();
    void sort(String algo);
    void stopSorting();

    void randomizeArray();
    void setArray(Node[] array);
    Node[] getArray();
    boolean arrayIsSorted();

    void registerObserver(NodeArrayObserver o);
    void removeObserver(NodeArrayObserver o);
    void notifyObservers();

}
