package com.example.algorithmvisualizer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Node extends Rectangle implements Comparable<Node> {

    private static final Random random = new Random();

    //default width, height and y position for every node (height gets overridden in constructor)
    private static final double defaultNodeHeight =  1;
    private static final double defaultNodeWidth = 5;
    private static final double y = 0;
    private static final Color defaultColor = Color.WHITE;

    //flags to identify if a node is selected or is the minimum node(node changes color based on these flags)
    private boolean isSelected;
    private boolean isMin;
    private boolean isSorted;


    public Node(double x) {
       //first initialize rectangle node with default width, height, y
       super(x, y , defaultNodeWidth, defaultNodeHeight);

       //override default height with random height, set coilor to white
       this.setHeight(random.nextInt(500) + 1);
       this.setFill(defaultColor);

       this.isSelected = false;
       this.isMin = false;
    }



    //compareTo method compares height of nodes
    @Override
    public int compareTo(Node n) {
        return Double.compare(this.getHeight(), n.getHeight());
    }

    public static double getNodeWidth() {
        return defaultNodeWidth;
    }



    public static double getDefaultNodeHeight() {
        return defaultNodeHeight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeHeight=" + this.getHeight() +
                '}';
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        if(isSelected) {
            this.setFill(Color.GREEN);
        } else {
            this.setFill(Color.WHITE);
        }
    }

    public boolean isMin() {
        return isMin;
    }


    public void setMin(boolean min) {
        isMin = min;
        if(isMin) {
            this.setFill(Color.RED);
        } else {
            this.setFill(Color.WHITE);
        }
    }

    public boolean isSorted() {
        return isSorted;
    }

    public void setSorted(boolean sorted) {
        isSorted = sorted;
        if(isSorted) {
            this.setFill(Color.CYAN);
        }
    }
}
