package com.example.algorithmvisualizer.algorithms;

import com.example.algorithmvisualizer.Node;
import com.example.algorithmvisualizer.model.AlgorithmVisualizerModelInterface;

import java.util.Arrays;

public class MergeSort implements SortingAlgorithmInterface{

    private Node[] nodeArray;
    private Node[] aux;
    private AlgorithmVisualizerModelInterface model;
    private boolean exit = false;

    public MergeSort (AlgorithmVisualizerModelInterface model) {
        this.model = model;
        this.nodeArray = model.getArray();
    }

    private void merge(Node[] aux, int lo, int mid, int hi) {
        //copy array into auxiliary array
        if (hi + 1 - lo >= 0) System.arraycopy(nodeArray, lo, aux, lo, hi + 1 - lo);

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if(exit) {
                return;
            }

            nodeArray[k].setSelected(true);

            try {
                Thread.sleep(18);
            } catch (InterruptedException e) {
                exit = true;
            }

            if (i > mid) {
                nodeArray[k].setSelected(false);
                nodeArray[k] = aux[j++];
            } else if (j > hi){
                nodeArray[k].setSelected(false);
                nodeArray[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                nodeArray[k].setSelected(false);
                nodeArray[k] = aux[j++];
            } else {
                nodeArray[k].setSelected(false);
                nodeArray[k] = aux[i++];
            }
            nodeArray[k].setSelected(false);

        }
    }

    //node v is less than w if its height it's shorter
    private boolean less(Node v, Node w) {
        return v.compareTo(w) < 0;
    }


    @Override
    public void sort(){
        int n = nodeArray.length;
        aux = new Node[n];
        sort(aux, 0, n - 1);
    }


    private void sort(Node[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        // Calculate middle node
        int mid = lo + (hi-lo) / 2;

        // Sort first and second halves
        sort(aux, lo, mid);

        sort(aux, mid + 1, hi);

        // Merge the sorted halves and set them to sorted
        merge(aux, lo, mid, hi);

        //check if thread was interrupted before setting nodes to sorted
        if (exit) {
            return;
        }

        for(int k = 0; k <= hi; k++){
            nodeArray[k].setSorted(true);
        }

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
