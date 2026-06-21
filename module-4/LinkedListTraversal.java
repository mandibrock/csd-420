// Amanda Brock
// June 21, 2026
// Module 4.2
// Purpose: Program that tests the time it takes to traverse a LinkedList using
// an iterator vs get(index). Tested with 50k and 500k integers.

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.FontWeight;
import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListTraversal extends Application {
    private Label statusLabel;
    private Label results50KLabel;
    private Label results500KLabel;
    private Button button50K;
    private Button button500K;

    // Traverses a LinkedList using an Iterator.
    public static long testIteratorTraversal(LinkedList<Integer> list) {
        long startTime = System.nanoTime();
        Iterator<Integer> iterator = list.iterator();
        long sum = 0;
        while (iterator.hasNext()) {
            sum += iterator.next();
        }
        long endTime = System.nanoTime();
        if (sum == -1) {
            System.out.println(sum);
        }
        return endTime - startTime;
    }

    // Traverses a LinkedList using get(index).
    public static long testGetIndexTraversal(LinkedList<Integer> list) {
        long startTime = System.nanoTime();
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        long endTime = System.nanoTime();
        if (sum == -1) {
            System.out.println(sum);
        }
        return endTime - startTime;
    }

    // Creates and fills a LinkedList with integers.
    public static LinkedList<Integer> createList(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    // Verifies that the LinkedList was created correctly.
    public static boolean verifyList(LinkedList<Integer> list, int expectedSize) {
        return list.size() == expectedSize
                && list.getFirst() == 0
                && list.getLast() == expectedSize - 1;
    }

    // Runs the selected test.
    private void runTest(int size) {
        button50K.setDisable(true);
        button500K.setDisable(true);
        Label targetLabel =
                (size == 50000) ? results50KLabel : results500KLabel;
        targetLabel.setText(
                "Status: Running...\n\n" +
                "Verification: --\n" +
                "Iterator: --\n" +
                "get(index): --"
        );
        Task<String> task = new Task<>() {

            @Override
            protected String call() {
                updateMessage("Application Status: Building LinkedList...");
                LinkedList<Integer> list = createList(size);
                updateMessage("Application Status: Running Verification...");
                boolean passed = verifyList(list, size);
                updateMessage("Application Status: Running Iterator Traversal...");
                long iteratorTime = testIteratorTraversal(list);
                updateMessage("Application Status: Running get(index) Traversal...");
                long getIndexTime = testGetIndexTraversal(list);
                updateMessage("Application Status: Test Complete");
                double iteratorMs = iteratorTime / 1_000_000.0;
                double getIndexMs = getIndexTime / 1_000_000.0;
                return "Status: Completed\n\n"
                        + "Verification: "
                        + (passed ? "PASSED" : "FAILED")
                        + "\n\n"
                        + "Iterator: "
                        + String.format("%.3f", iteratorMs)
                        + " ms"
                        + "\n"
                        + "get(index): "
                        + String.format("%.3f", getIndexMs)
                        + " ms";
            }
        };

        statusLabel.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(e -> {
            statusLabel.textProperty().unbind();
            statusLabel.setText("Application Status: Ready");
            targetLabel.setText(task.getValue());
            button50K.setDisable(false);
            button500K.setDisable(false);
        });

        task.setOnFailed(e -> {
            statusLabel.textProperty().unbind();
            statusLabel.setText("Application Status: Error");
            targetLabel.setText("An error occurred.");
            button50K.setDisable(false);
            button500K.setDisable(false);
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void start(Stage primaryStage) {
        Label titleLabel =
                new Label("LinkedList Traversal Performance Test");
        titleLabel.setFont(Font.font(24));
        Label subtitleLabel =
                new Label("Compare LinkedList traversal using an Iterator and get(index)");
        VBox titleBox = new VBox(5);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(titleLabel, subtitleLabel);
        button50K = new Button("Run 50,000 Test");
        button500K = new Button("Run 500,000 Test");
        button50K.setPrefWidth(160);
        button500K.setPrefWidth(160);
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(button50K, button500K);
        statusLabel = new Label("Application Status: Ready");
        VBox statusBox = new VBox(8);
        statusBox.setAlignment(Pos.CENTER);
        statusBox.getChildren().add(statusLabel);
        Label header50K = new Label("50,000 Results");
        header50K.setFont(Font.font("System", FontWeight.BOLD, 16));
        results50KLabel = new Label(
                "No test has been run.\n\n" +
                "Verification: --\n" +
                "Iterator: --\n" +
                "get(index): --"
        );

        VBox results50KBox = new VBox(10);
        results50KBox.setPadding(new Insets(10));
        results50KBox.setPrefWidth(240);
        results50KBox.setMinHeight(180);
        results50KBox.setStyle(
                "-fx-border-color: black;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;"
        );
        results50KBox.getChildren().addAll(
                header50K,
                results50KLabel
        );

        Label header500K = new Label("500,000 Results");
        header500K.setFont(Font.font("System", FontWeight.BOLD, 16));
        results500KLabel = new Label(
                "No test has been run.\n\n" +
                "Verification: --\n" +
                "Iterator: --\n" +
                "get(index): --"
        );
        VBox results500KBox = new VBox(10);
        results500KBox.setPadding(new Insets(10));
        results500KBox.setPrefWidth(240);
        results500KBox.setMinHeight(180);
        results500KBox.setStyle(
                "-fx-border-color: black;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;"
        );
        results500KBox.getChildren().addAll(
                header500K,
                results500KLabel
        );

        HBox resultsBox = new HBox(20);
        resultsBox.setAlignment(Pos.TOP_CENTER);
        resultsBox.setMaxWidth(500);
        resultsBox.getChildren().addAll(
                results50KBox,
                results500KBox
        );

        button50K.setOnAction(e -> runTest(50000));
        button500K.setOnAction(e -> runTest(500000));

        VBox root = new VBox(20);
        root.setPadding(new Insets(15));

        root.getChildren().addAll(
                titleBox,
                buttonBox,
                statusBox,
                resultsBox
        );

        Scene scene = new Scene(root, 500, 425);

        primaryStage.setTitle("LinkedList Traversal Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Results:

    // The results showed a big difference between using an iterator
    // and using get(index). The iterator completed the traversal much
    // faster, and the gap became even larger whent he list size increased
    // from 50k to 500k integers.

    // The bigger the list became, the slower get(index) was. Since it
    // has to keep finding each position in the LinkedList, it ends up
    // doing a lot more work than the iterator.

    // The iterator was able to move through the list much more efficiently,
    // which is why its performance stayed relatively fast even with the 
    // larger data set.

    // Based on these results, I would use an iterator when traversing a 
    // LinkedList because it scales much better as the amount of data increases.

}
