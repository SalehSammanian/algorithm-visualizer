package com.example.algorithmvisualizer.model;

import com.example.algorithmvisualizer.algorithms.*;
import com.example.algorithmvisualizer.view.AlgorithmVisualizerView;
import com.example.algorithmvisualizer.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AlgorithmVisualizerModel implements AlgorithmVisualizerModelInterface{

    private static Map<String, SortingAlgorithmInterface> sortingAlgorithms;
    private static Thread sortingThread;
    private static SortingAlgorithmInterface algorithm;
    private static int numOfElements;
    private static Node[] nodeArray;
    private static final int gapBetweenNodes = 6;
    private ArrayList<NodeArrayObserver> observers = new ArrayList<>();


    public AlgorithmVisualizerModel() {

    }

    @Override
    public void initialize() {
        //initializing array of nodes
        numOfElements = AlgorithmVisualizerView.getWindowWidth() / gapBetweenNodes;
        nodeArray = new Node[numOfElements];
        randomizeArray();

        //initializing sortingAlgorithms
        initializeSortingAlgorithms();

        algorithm = new SelectionSort(this);
        sortingThread = new Thread(algorithm);
    }


    @Override
    public void sort(String algo) {
        //if sortingThread is not currently running, make new thread and start sort
        if(!sortingThread.isAlive()) {

            //check if the selected string is an available sorting algorithm, otherwise default to selection sort
            if(!sortingAlgorithms.containsKey(algo)) {
                algo = "Selection Sort";
            }

            algorithm = sortingAlgorithms.get(algo);
            sortingThread = new Thread(algorithm);
            sortingThread.start();
        }
    }

    @Override
    public void stopSorting() {
        //interrupts the sorting thread which terminates it
        if(sortingThread.isAlive()) {
            sortingThread.interrupt();
        }
    }

    @Override
    public void randomizeArray() {
        int x = 0;
        for(int i = 0; i < numOfElements; i++) {
            nodeArray[i] = new Node(x);
            x += gapBetweenNodes;
        }
    }


    @Override
    public void registerObserver(NodeArrayObserver o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(NodeArrayObserver o) {
        if(observers.contains(o)) {
            this.observers.remove(o);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void notifyObservers() {
        for (NodeArrayObserver o: observers) {
            o.updateArray();
        }
    }

    @Override
    public void setArray(Node[] a) {
        int x = 0;
        for(int i = 0; i < a.length; i++) {
            a[i].setX(x);
            nodeArray[i] = a[i];
            x += gapBetweenNodes;
        }
    }

    @Override
    public Node[] getArray() {
        return nodeArray;
    }

    private void initializeSortingAlgorithms() {
        sortingAlgorithms = new HashMap<>();
        sortingAlgorithms.put("Selection Sort", new SelectionSort(this));
        sortingAlgorithms.put("Bubble Sort", new BubbleSort(this));
        sortingAlgorithms.put("Insertion Sort", new InsertionSort(this));
        sortingAlgorithms.put("Merge Sort", new MergeSort(this));
        sortingAlgorithms.put("Quick Sort", new QuickSort(this));
        sortingAlgorithms.put("Heap Sort", new HeapSort(this));
    }

    @Override
    public boolean arrayIsSorted() {
        for(int i = 0; i < nodeArray.length - 1; i++) {
            if(nodeArray[i].compareTo(nodeArray[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

}
