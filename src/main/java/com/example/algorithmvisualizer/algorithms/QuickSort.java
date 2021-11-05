package com.example.algorithmvisualizer.algorithms;

import com.example.algorithmvisualizer.Node;
import com.example.algorithmvisualizer.model.AlgorithmVisualizerModelInterface;

public class QuickSort implements SortingAlgorithmInterface{

    private Node[] nodeArray;
    private AlgorithmVisualizerModelInterface model;
    private boolean exit = false;

    public QuickSort(AlgorithmVisualizerModelInterface model) {
        this.model = model;
        this.nodeArray = model.getArray();
    }
    @Override
    public void sort() {
        sort(0, nodeArray.length - 1);
    }


    private void sort(int lo, int hi) {
        if(exit) {
            return;
        }

        if (hi <= lo) return;

        //partition choosing first item as pivot point
        int j = partition(lo, hi);
        if(j == -1) {
            return;
        }

        //sort left half
        sort(lo, j-1);

        //after sorting left half, set all nodes of the half to sorted
        for(int k = lo; k <= j; k++){
            nodeArray[k].setSorted(true);
        }

        //sort right half
        sort(j+1, hi);

        //after whole array is sorted, set sorted to true for last element
        nodeArray[hi].setSorted(true);
    }

    private int partition(int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        //select first node as pivot
        Node v = nodeArray[lo];
        v.setSelected(true);

        while (true) {
            if(exit) {
                return -1;
            }

            // find item on lo to swap
            while (less(nodeArray[++i], v)) {
                nodeArray[i].setBeingCompared(true);
                if(j < nodeArray.length){
                    nodeArray[j].setBeingCompared(true);
                }

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    exit = true;
                }

                nodeArray[i].setBeingCompared(false);
                if(j < nodeArray.length){
                    nodeArray[j].setBeingCompared(false);
                }

                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(v, nodeArray[--j])) {
                nodeArray[j].setBeingCompared(true);
                nodeArray[i].setBeingCompared(true);

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    exit = true;
                }

                nodeArray[j].setBeingCompared(false);
                nodeArray[i].setBeingCompared(false);

                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            nodeArray[i].setBeingCompared(true);
            nodeArray[j].setBeingCompared(true);

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                exit = true;
            }

            exch(nodeArray, i, j);

            nodeArray[i].setBeingCompared(false);
            nodeArray[j].setBeingCompared(false);
        }


        // put partitioning item v at a[j]
        nodeArray[j].setBeingCompared(true);
        exch(nodeArray, lo, j);
        nodeArray[lo].setBeingCompared(false);
        v.setSelected(false);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    private boolean less(Node v, Node w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private void exch(Node[] a, int i, int j) {
        Node swap = a[i];
        a[i] = a[j];
        a[j] = swap;
        model.setArray(nodeArray);
        model.notifyObservers();
    }

    @Override
    public void run() {
        this.exit = false;
        this.nodeArray = model.getArray();
        sort();
    }
}
