// Amanda Brock
// June 21, 2026
// Module 3.2
// Purpose: Creates an ArrayList containing 50 random integers between
// 1 and 20 and removes any duplicate values while displaying the
// original and updated lists.

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;

public class RemoveDuplicatesFX extends Application {
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> uniqueList = new ArrayList<>();
        for (E element : list) {
            if (!uniqueList.contains(element)) {
                uniqueList.add(element);
            }
        }
        return uniqueList;
    }

    @Override
    public void start(Stage primaryStage) {
        ArrayList<Integer> originalList = new ArrayList<>();
        // Generate 50 random values from 1-20
        for (int i = 0; i < 50; i++) {
            originalList.add((int) (Math.random() * 20) + 1);
        }
        ArrayList<Integer> uniqueList = removeDuplicates(originalList);

        Label titleLabel = new Label("ArrayList Duplicate Removal");
        titleLabel.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #6f42c1;"
        );
        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);


        Label originalTitle =
                new Label("Original List (50 Random Integers)");
        originalTitle.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #5b21b6;"
        );
        Label originalData =
                new Label(originalList.toString());
        originalData.setWrapText(true);
        VBox originalBox = new VBox(10,
                originalTitle,
                originalData);
        originalBox.setPadding(new Insets(15));
        originalBox.setStyle(
                "-fx-background-color: #f5f0ff;" +
                "-fx-border-color: #c4b5fd;" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;"
        );


        Label uniqueTitle =
                new Label("List Without Duplicates");
        uniqueTitle.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1d4ed8;"
        );
        Label uniqueData =
                new Label(uniqueList.toString());
        uniqueData.setWrapText(true);
        VBox uniqueBox = new VBox(10,
                uniqueTitle,
                uniqueData);
        uniqueBox.setPadding(new Insets(15));
        uniqueBox.setStyle(
                "-fx-background-color: #eff6ff;" +
                "-fx-border-color: #93c5fd;" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;"
        );


        VBox root = new VBox(20,
                titleBox,
                originalBox,
                uniqueBox);
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root, 400, 350);
        primaryStage.setTitle("ArrayList Duplicate Removal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}