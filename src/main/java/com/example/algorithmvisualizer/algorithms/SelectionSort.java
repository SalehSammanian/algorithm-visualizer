package com.example.algorithmvisualizer.algorithms;

import com.example.algorithmvisualizer.Node;
import com.example.algorithmvisualizer.model.AlgorithmVisualizerModelInterface;


public class SelectionSort implements SortingAlgorithmInterface{

    private Node[] nodeArray;
    private AlgorithmVisualizerModelInterface model;


    public SelectionSort (AlgorithmVisualizerModelInterface model) {
        this.model = model;
        //this.nodeArray = model.getArray();
    }

    @Override
    public void sort() {
        int n = nodeArray.length;
        for(int i = 0; i < n; i++) {
            int min = i;
            nodeArray[min].setMin(true);
            for(int j = i+1; j < n; j++) {
                nodeArray[j].setSelected(true);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    return;
                }
                if(less(nodeArray[j], nodeArray[min])) {
                    nodeArray[min].setMin(false);

                    min = j;
                    nodeArray[j].setSelected(false);
                    nodeArray[j].setMin(true);
                } else {
                    nodeArray[j].setSelected(false);
                }
            }
            exchange(nodeArray , i , min);
            nodeArray[i].setMin(false);
            nodeArray[i].setSorted(true);
        }
    }

    //node v is less than w if its height it's shorter
    private boolean less(Node v, Node w) {
        return v.compareTo(w) < 0;
    }

    //swaps nodes
    private void exchange(Node[] a, int i, int j) {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            return;
        }
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
