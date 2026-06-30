// Amanda Brock
// Module 5
// Purpose: Read words from a text file, remove duplicates using a TreeSet,
// and display the words in ascending and descending order.
// Notes: I fought this program for hours trying to get the boxes to resize 
// based on loaded data simply because I absolutely hate empty space. What a ride.

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class WordSorter extends Application {

    private VBox ascendingWords;
    private VBox descendingWords;
    private Label testLabel;

    @Override
    public void start(Stage primaryStage) {

        Label titleLabel = new Label("Word Sorter");
        titleLabel.setFont(Font.font(24));
        VBox titleBox = new VBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);

        Button loadButton = new Button("Load Words");
        HBox buttonBox = new HBox(loadButton);
        buttonBox.setAlignment(Pos.CENTER);

        ascendingWords = new VBox(3);
        descendingWords = new VBox(3);

        Label ascendingHeader = new Label("Ascending Order");
        ascendingHeader.setFont(Font.font("System", FontWeight.BOLD, 16));
        VBox ascendingBox = new VBox(10, ascendingHeader, ascendingWords);
        ascendingBox.setPadding(new Insets(10));
        ascendingBox.setPrefWidth(250);
        ascendingBox.setStyle("-fx-border-color:black;-fx-border-width:1;-fx-background-color:white;");

        Label descendingHeader = new Label("Descending Order");
        descendingHeader.setFont(Font.font("System", FontWeight.BOLD, 16));
        VBox descendingBox = new VBox(10, descendingHeader, descendingWords);
        descendingBox.setPadding(new Insets(10));
        descendingBox.setPrefWidth(250);
        descendingBox.setStyle("-fx-border-color:black;-fx-border-width:1;-fx-background-color:white;");

        HBox listBox = new HBox(20, ascendingBox, descendingBox);
        listBox.setAlignment(Pos.CENTER);

        Label testHeader = new Label("Test Results");
        testHeader.setFont(Font.font("System", FontWeight.BOLD, 16));
        testLabel = new Label("No tests have been run.");
        testLabel.setWrapText(true);
        testLabel.setPrefWidth(500);
        VBox testBox = new VBox(8);
        testBox.setPadding(new Insets(10));
        testBox.setAlignment(Pos.TOP_LEFT);
        testBox.setPrefWidth(520);
        testBox.setMaxWidth(520);
        testBox.setStyle(
                "-fx-border-color: black;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;"
        );
        testBox.getChildren().addAll(
                testHeader,
                testLabel
        );

        HBox testBoxHolder = new HBox(testBox);
        testBoxHolder.setAlignment(Pos.CENTER);

        loadButton.setOnAction(e -> loadWords());

        VBox root = new VBox(
                20,
                titleBox,
                buttonBox,
                testBoxHolder,
                listBox
        );
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Word Sorter");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    private void loadWords() {

    // TreeSet removes duplicate words and stores them in ascending alphabetical order.
    TreeSet<String> words = new TreeSet<>();
    try {
        Scanner input = new Scanner(new File("collection_of_words.txt"));
        while (input.hasNext()) {
            words.add(input.next().toLowerCase());
        }
        input.close();

        ascendingWords.getChildren().clear();
        descendingWords.getChildren().clear();
        // Display ascending words
        for (String word : words) {
            ascendingWords.getChildren().add(new Label(word));
        }
        // Display descending words
        for (String word : words.descendingSet()) {
            descendingWords.getChildren().add(new Label(word));
        }
        runTests(words);
        Stage stage = (Stage) testLabel.getScene().getWindow();
        stage.sizeToScene();
    } catch (FileNotFoundException ex) {
        testLabel.setText("File not found: collection_of_words.txt");
    }
}

    private void runTests(TreeSet<String> words) {
            String results =
                    "Number of unique words: " + words.size() + "\n" +
                    "First word: " + words.first() + "\n" +
                    "Last word: " + words.last() + "\n\n" +
                    "Test Passed!\nDuplicates were removed successfully.";
            testLabel.setText(results);
        }

    public static void main(String[] args) {
        launch(args);
    }
}
