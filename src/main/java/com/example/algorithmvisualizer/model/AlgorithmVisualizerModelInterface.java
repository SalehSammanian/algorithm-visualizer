package com.example.algorithmvisualizer.model;


import com.example.algorithmvisualizer.Node;

public interface AlgorithmVisualizerModelInterface {

    void initialize();
    void sort(String algo);
    void stopSorting();
    void randomizeArray();
    Node[] getArray();
    boolean arrayIsSorted();
    void setArray(Node[] array);
    void registerObserver(NodeArrayObserver o);
    void removeObserver(NodeArrayObserver o);
    void notifyObservers();

}
