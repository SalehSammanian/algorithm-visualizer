package com.example.algorithmvisualizer.controller;

import com.example.algorithmvisualizer.model.AlgorithmVisualizerModelInterface;
import com.example.algorithmvisualizer.view.AlgorithmVisualizerView;
import javafx.stage.Stage;

public class AlgorithmVisualizerController implements AlgorithmVisualizerControllerInterface{
    private AlgorithmVisualizerModelInterface model;
    private AlgorithmVisualizerView view;



    public AlgorithmVisualizerController(AlgorithmVisualizerModelInterface model, Stage stage) {
        //initialize model and view
        this.model = model;
        this.model.initialize();
        this.view = new AlgorithmVisualizerView(this, this.model, stage);
    }

    @Override
    public void sort(String algo) {
        //if its not already sorted, start sort
        if(!model.arrayIsSorted()) {
            model.sort(algo);
        }
    }

    @Override
    public void reset() {
        //stops sorting and resets the node array
        model.stopSorting();
        model.randomizeArray();
    }
}