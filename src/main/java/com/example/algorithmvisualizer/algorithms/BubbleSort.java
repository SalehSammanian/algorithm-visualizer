package com.example.algorithmvisualizer.algorithms;

import com.example.algorithmvisualizer.Node;
import com.example.algorithmvisualizer.model.AlgorithmVisualizerModelInterface;

public class BubbleSort implements SortingAlgorithmInterface{

    private Node[] nodeArray;
    private AlgorithmVisualizerModelInterface model;

    public BubbleSort (AlgorithmVisualizerModelInterface model) {
        this.model = model;
        this.nodeArray = model.getArray();
    }

    public void sort(){
        int n = nodeArray.length;
        for (int i = 0; i < n; i++) {
            //keeps track of how many exchanges were done durng each pass
            int exchanges = 0;
            for (int j = n-1; j > i; j--) {
                nodeArray[j].setSelected(true);
                nodeArray[j-1].setBeingCompared(true);

                try {
                    Thread.sleep(18);
                } catch (InterruptedException e) {
                    return;
                }

                if (less(nodeArray[j], nodeArray[j-1])) {
                    nodeArray[j-1].setBeingCompared(false);
                    exch(nodeArray, j, j-1);
                    exchanges++;
                }

                nodeArray[j].setSelected(false);
                nodeArray[j-1].setBeingCompared(false);
            }
            nodeArray[i].setSorted(true);

            //if exchanges equals 0 on the pass then the array is sorted
            if (exchanges == 0) {
                nodeArray[i].setSorted(true);
            }
        }
    }


    //node v is less than w if its height it's shorter
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
