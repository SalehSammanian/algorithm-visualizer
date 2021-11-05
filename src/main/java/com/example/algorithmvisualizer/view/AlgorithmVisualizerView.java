package com.example.algorithmvisualizer.view;

import com.example.algorithmvisualizer.Node;
import com.example.algorithmvisualizer.controller.AlgorithmVisualizerControllerInterface;
import com.example.algorithmvisualizer.model.AlgorithmVisualizerModelInterface;
import com.example.algorithmvisualizer.model.NodeArrayObserver;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Arrays;

public class AlgorithmVisualizerView implements NodeArrayObserver {
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;


    private final AlgorithmVisualizerControllerInterface controller;
    private final AlgorithmVisualizerModelInterface model;
    private static Scene scene;
    private static Node[] nodeArray;
    private static BorderPane root;
    private static Group nodeGroup;
    private static FlowPane buttonsBox;
    private static ComboBox<String> sortSelectionBox;
    private static Button sortButton;
    private static Button resetButton;
    private static String selectedAlgorithm;
    private static AnimationTimer timer;
    private static final ObservableList<String> sortingAlgorithms =
            FXCollections.observableArrayList(Arrays.asList("Selection Sort", "Bubble Sort", "Insertion Sort", "Merge Sort", "Quick Sort", "Heap Sort"));


    public AlgorithmVisualizerView(AlgorithmVisualizerControllerInterface controller, AlgorithmVisualizerModelInterface model, Stage stage) {
        this.controller = controller;
        this.model = model;
        this.model.registerObserver(this);
        nodeArray = model.getArray();

        setupView();

        scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle("Algorithm Visualizer");
        stage.setScene(scene);
        stage.show();
    }

    private void setupView() {
        root = new BorderPane();
        nodeGroup = new Group();
        buttonsBox = new FlowPane();

        setRoot();

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                redraw();
            }
        };
    }

    private void setRoot() {
        root.setStyle("-fx-background-color: black");
        setButtons();
        redraw();

        root.setTop(nodeGroup);
        root.setBottom(buttonsBox);
    }

    private void setButtons() {
        buttonsBox.setPadding(new Insets(0, 0, 50, 100));
        buttonsBox.setHgap((double) WINDOW_WIDTH / 4);


        //setting ComboBox for selecting sorting algorithm
        sortSelectionBox = new ComboBox<>(sortingAlgorithms);
        sortSelectionBox.setPrefSize(200, 50);
        sortSelectionBox.setStyle("-fx-font-size: 2em; ");
        sortSelectionBox.setValue(sortingAlgorithms.get(0));
        sortSelectionBox.setVisibleRowCount(3);


        //setting up the sort button
        sortButton = new Button("Sort");
        sortButton.setPrefSize(100, 50);
        sortButton.setStyle("-fx-font-size: 2em; ");
        sortButton.setOnAction(e -> {
            selectedAlgorithm = sortSelectionBox.getValue();
            timer.start();
            controller.sort(selectedAlgorithm);
            timer.stop();
        });


        //setting up the reset button
        resetButton = new Button("Reset");
        resetButton.setPrefSize(100, 50);
        resetButton.setStyle("-fx-font-size: 2em;");
        resetButton.setOnAction(e -> {
            controller.reset();
            updateArray();
            redraw();
        });

        buttonsBox.getChildren().addAll(sortButton, sortSelectionBox, resetButton);
    }

    //overridden from nodeArrayObserver interface
    @Override
    public void updateArray() {
        nodeArray = model.getArray();
    }

    public static void redraw() {
        nodeGroup.getChildren().clear();
        nodeGroup.getChildren().addAll(nodeArray);
    }

    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }

}
