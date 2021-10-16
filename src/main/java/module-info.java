module com.example.algorithmvisualizer.algorithmvisualizer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.algorithmvisualizer to javafx.fxml;
    exports com.example.algorithmvisualizer;
    exports com.example.algorithmvisualizer.controller;
    opens com.example.algorithmvisualizer.controller to javafx.fxml;
    exports com.example.algorithmvisualizer.model;
    opens com.example.algorithmvisualizer.model to javafx.fxml;
    exports com.example.algorithmvisualizer.view;
    opens com.example.algorithmvisualizer.view to javafx.fxml;
}