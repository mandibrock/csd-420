// Amanda Brock
// Assignment 6.2
// July 5, 2026
// Purpose: Demonstrates 2 generic bubble sort methods. (Comparable and Comparator)

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Comparator;

public class BubbleSort extends Application {

    // Labels to display the results
    private Label comparableOriginal = new Label();
    private Label comparableSorted = new Label();
    private Label comparatorOriginal = new Label();
    private Label comparatorSorted = new Label();
    private Button runButton = new Button("Run Tests");
    private boolean testsRan = false;

    @Override
    public void start(Stage stage) {

    // Making it pretty
    Label title = new Label("Generic Bubble Sort");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
    title.setTextFill(Color.web("#6A3FB8"));

    Label comparableTitle = new Label("Comparable Bubble Sort");
    comparableTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    comparableTitle.setTextFill(Color.WHITE);
    comparableTitle.setAlignment(Pos.CENTER);
    comparableTitle.setMaxWidth(Double.MAX_VALUE);
    comparableTitle.setPadding(new Insets(8));
    comparableTitle.setStyle("""
        -fx-background-color: #9C7DFF;
        -fx-background-radius: 6 6 0 0;
        """);

    Label comparatorTitle = new Label("Comparator Bubble Sort");
    comparatorTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    comparatorTitle.setTextFill(Color.WHITE);
    comparatorTitle.setAlignment(Pos.CENTER);
    comparatorTitle.setMaxWidth(Double.MAX_VALUE);
    comparatorTitle.setPadding(new Insets(8));
    comparatorTitle.setStyle("""
        -fx-background-color: #9C7DFF;
        -fx-background-radius: 6 6 0 0;
        """);


    runButton.setStyle("""
        -fx-background-color: #9C7DFF;
        -fx-text-fill: white;
        -fx-font-size: 14px;
        -fx-background-radius: 8;
        """);

    // Changes "run tests" button to an "exit" button
    runButton.setOnAction(e -> {
        if (!testsRan) {
            runTests();
        } else {
            Platform.exit();
        }
    });

    comparableOriginal.setWrapText(true);
    comparableSorted.setWrapText(true);
    comparatorOriginal.setWrapText(true);
    comparatorSorted.setWrapText(true);

    comparableOriginal.setVisible(false);
    comparableSorted.setVisible(false);
    comparatorOriginal.setVisible(false);
    comparatorSorted.setVisible(false);

    comparableOriginal.setManaged(false);
    comparableSorted.setManaged(false);
    comparatorOriginal.setManaged(false);
    comparatorSorted.setManaged(false);

    VBox comparableBox = new VBox(10);
    comparableBox.setPadding(new Insets(10));
    comparableBox.setStyle("""
        -fx-background-color: #FCFAFF;
        -fx-border-color: #B38CFF;
        -fx-border-width: 2;
        -fx-background-radius: 8;
        -fx-border-radius: 8;
        """);

    HBox comparableResults = new HBox(60);
    comparableResults.setAlignment(Pos.TOP_CENTER);
    comparableResults.getChildren().addAll(
        comparableOriginal,
        comparableSorted
    );
    comparableBox.getChildren().addAll(
        comparableTitle,
        comparableResults
    );

    VBox comparatorBox = new VBox(10);
    comparatorBox.setPadding(new Insets(10));
    comparatorBox.setStyle("""
        -fx-background-color: #FCFAFF;
        -fx-border-color: #B38CFF;
        -fx-border-width: 2;
        -fx-background-radius: 8;
        -fx-border-radius: 8;
        """);

    HBox comparatorResults = new HBox(60);
    comparatorResults.setAlignment(Pos.CENTER);
    comparatorResults.getChildren().addAll(
        comparatorOriginal,
        comparatorSorted
    );
    comparatorBox.getChildren().addAll(
        comparatorTitle,
        comparatorResults
    );

    VBox root = new VBox(20);
    root.setAlignment(Pos.TOP_CENTER);
    root.setPadding(new Insets(20));
    root.getChildren().addAll(
        title,
        comparableBox,
        comparatorBox,
        runButton
    );

    Scene scene = new Scene(root, 350, 550);
    stage.setTitle("Generic Bubble Sort");
    stage.setScene(scene);
    stage.show();
    }

    // Runs both test cases
    private void runTests() {

        comparableOriginal.setVisible(true);
        comparableSorted.setVisible(true);
        comparatorOriginal.setVisible(true);
        comparatorSorted.setVisible(true);
        comparableOriginal.setManaged(true);
        comparableSorted.setManaged(true);
        comparatorOriginal.setManaged(true);
        comparatorSorted.setManaged(true);

    // Comparable test
    String[] dogBreeds = {
        "German Shepherd",
        "Beauceron",
        "Border Collie",
        "Australian Shepherd",
        "Belgian Malinois"
    };

    comparableOriginal.setText(
            "Original Array\n\n" +
                    String.join("\n", dogBreeds));

    bubbleSort(dogBreeds);

    comparableSorted.setText(
            "Sorted Array\n\n" +
                    String.join("\n", dogBreeds));

    // Comparator test
    String[] games = {
        "The Planet Crafter",
        "Meccha Chameleon",
        "Overwatch",
        "Peak",
        "Subnautica"
    };

    comparatorOriginal.setText(
            "Original Array\n\n" +
                    String.join("\n", games));

    bubbleSort(
            games,
            Comparator.comparingInt(String::length));

    comparatorSorted.setText(
            "Sorted by Length\n\n" +
                    String.join("\n", games));

    // Button changed to Exit after run
    testsRan = true;
    runButton.setText("Exit");
    }

    // Bubble sort using Comparable
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        boolean swapped;
        for (int i = 0; i < list.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    // Bubble sort using Comparator
    public static <E> void bubbleSort(E[] list,
                                      Comparator<? super E> comparator) {

        boolean swapped;
        for (int i = 0; i < list.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (comparator.compare(list[j], list[j + 1]) > 0) {
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}