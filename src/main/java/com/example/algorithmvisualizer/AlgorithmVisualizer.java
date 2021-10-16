package com.example.algorithmvisualizer;

import com.example.algorithmvisualizer.controller.AlgorithmVisualizerControllerInterface;
import com.example.algorithmvisualizer.model.AlgorithmVisualizerModelInterface;
import com.example.algorithmvisualizer.controller.AlgorithmVisualizerController;
import com.example.algorithmvisualizer.model.AlgorithmVisualizerModel;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class AlgorithmVisualizer extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        AlgorithmVisualizerModelInterface model = new AlgorithmVisualizerModel();
        AlgorithmVisualizerControllerInterface controller = new AlgorithmVisualizerController(model, stage);
    }



}