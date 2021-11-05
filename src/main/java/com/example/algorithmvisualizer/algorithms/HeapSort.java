package com.example.algorithmvisualizer.algorithms;

import com.example.algorithmvisualizer.Node;
import com.example.algorithmvisualizer.model.AlgorithmVisualizerModelInterface;

public class HeapSort implements SortingAlgorithmInterface{

    private Node[] priorityQueue;
    private AlgorithmVisualizerModelInterface model;
    private boolean exit = false;

    public HeapSort(AlgorithmVisualizerModelInterface model) {
        this.model = model;
        this.priorityQueue = model.getArray();
    }

    @Override
    public void sort(){
            int n = priorityQueue.length;

            // heapify phase
            for (int k = n/2; k >= 1; k--){
                if (exit) {
                    return;
                }
                sink(k, n);
            }

            // sortdown phase
            int k = n;
            while (k > 1) {
                exch(priorityQueue, 1, k--);
                sink(1, k);

                //check if thread was interrupted after sink operation
                if (exit) {
                    return;
                }

                priorityQueue[k].setSorted(true);
            }
        priorityQueue[0].setSorted(true);
    }


    private void sink(int k, int n){
        while (2*k <= n) {
            if (exit) {
                return;
            }

            int j = 2*k;
            priorityQueue[k-1].setSelected(true);
            priorityQueue[j-1].setBeingCompared(true);

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                exit = true;
            }

            if (j < n && less(j, j+1)){
                priorityQueue[k-1].setSelected(false);
                priorityQueue[j-1].setBeingCompared(false);
                j++;
            }
            if (!less(k, j)){
                priorityQueue[k-1].setSelected(false);
                priorityQueue[j-1].setBeingCompared(false);
                break;
            }
            exch(priorityQueue, k, j);

            priorityQueue[j-1].setSelected(false);
            priorityQueue[k-1].setBeingCompared(false);

            k = j;
        }
    }

    private boolean less(int i, int j) {
        return priorityQueue[i-1].compareTo(priorityQueue[j-1]) < 0;
    }

    private void exch(Node[] pq, int i, int j){
        Node swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
        model.setArray(priorityQueue);
        model.notifyObservers();
    }

    @Override
    public void run() {
        this.exit = false;
        this.priorityQueue = model.getArray();
        sort();
    }

}
