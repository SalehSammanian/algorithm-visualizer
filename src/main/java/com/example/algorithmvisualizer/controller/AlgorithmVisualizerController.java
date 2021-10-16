package com.example.algorithmvisualizer.controller;

import com.example.algorithmvisualizer.model.AlgorithmVisualizerModelInterface;
import com.example.algorithmvisualizer.view.AlgorithmVisualizerView;
import javafx.stage.Stage;

public class AlgorithmVisualizerController implements AlgorithmVisualizerControllerInterface{
    private AlgorithmVisualizerModelInterface model;
    private AlgorithmVisualizerView view;



    public AlgorithmVisualizerController(AlgorithmVisualizerModelInterface model, Stage stage) {
        this.model = model;
        model.initialize();
        this.view = new AlgorithmVisualizerView(this, model, stage);
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
        model.stopSorting();
        model.randomizeArray();
    }
}