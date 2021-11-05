package com.example.algorithmvisualizer.algorithms;

import com.example.algorithmvisualizer.Node;
import com.example.algorithmvisualizer.model.AlgorithmVisualizerModelInterface;

public class InsertionSort implements SortingAlgorithmInterface{

    private Node[] nodeArray;
    private AlgorithmVisualizerModelInterface model;

    public InsertionSort (AlgorithmVisualizerModelInterface model) {
        this.model = model;
        this.nodeArray = model.getArray();
    }

    @Override
    public void sort() {
        int n = nodeArray.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                nodeArray[j].setSelected(true);
                nodeArray[j-1].setBeingCompared(true);

                try {
                    Thread.sleep(18);
                } catch (InterruptedException e) {
                    return;
                }

                if (less(nodeArray[j], nodeArray[j-1])) {
                    nodeArray[j-1].setBeingCompared(false);
                    nodeArray[j-1].setSorted(true);

                    if(j-1 == 0) {
                        nodeArray[j].setSelected(false);
                        nodeArray[j].setSorted(true);
                    }

                    exch(nodeArray, j, j-1);
                } else {
                    nodeArray[j-1].setBeingCompared(false);
                    nodeArray[j-1].setSorted(true);
                    nodeArray[j].setSelected(false);
                    nodeArray[j].setSorted(true);
                    break;
                }
            }
        }
    }



    private boolean less(Node v, Node w) {
        return v.compareTo(w) < 0;
    }

    //swaps nodes
    private void exch(Node[] a, int i, int j) {
        Node swap = a[i];
        a[i] = a[j];
        a[j] = swap;
        model.setArray(nodeArray);
        model.notifyObservers();
    }

    @Override
    public void run() {
        this.nodeArray = model.getArray();
        sort();
    }
}
