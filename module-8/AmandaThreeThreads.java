// Amanda Brock
// July 12, 2026
// Purpose: Uses 3 separate threads to generate random
// letters, numbers, and special characters. Output is
// a mixture of all 3 threads.

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AmandaThreeThreads extends Application {

    private static final int COUNT = 10000;
    private static final int TOTAL = COUNT * 3;
    private TextArea outputArea;
    private Label statusLabel;
    private Button startButton;
    private AtomicInteger completedThreads = new AtomicInteger(0);
    private AtomicInteger totalCharacters = new AtomicInteger(0);

    @Override
    public void start(Stage stage) {

        Label title = new Label("Three Threads Generator");
        title.setStyle(
                "-fx-font-size:20px;"
              + "-fx-font-weight:bold;"
              + "-fx-text-fill:#5E35B1;"
        );

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setStyle(
                "-fx-font-family:Consolas;"
              + "-fx-font-size:14px;"
              + "-fx-border-color:#C8C8C8;"
              + "-fx-border-width:1;"
        );

        startButton = new Button("Start Threads");
        startButton.setPrefSize(170,40);
        startButton.setStyle(
                "-fx-background-color:#7E57C2;"
              + "-fx-text-fill:white;"
              + "-fx-font-size:14px;"
              + "-fx-font-weight:bold;"
              + "-fx-background-radius:8;"
        );
        startButton.setOnAction(e -> {
            outputArea.clear();
            completedThreads.set(0);
            totalCharacters.set(0);
            statusLabel.setText("Status: Running...");
            startButton.setDisable(true);
            runThreads();
        });

        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(120,40);
        exitButton.setStyle(
                "-fx-background-color:#7E57C2;"
              + "-fx-text-fill:white;"
              + "-fx-font-size:14px;"
              + "-fx-font-weight:bold;"
              + "-fx-background-radius:8;"
        );
        exitButton.setOnAction(e -> Platform.exit());

        statusLabel = new Label("Status: Ready");

        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(12));

        HBox statusBox = new HBox(statusLabel);
        statusBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(startButton, exitButton);

        VBox bottomBox = new VBox(10);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(10));
        bottomBox.getChildren().addAll(statusBox, buttonBox);

        BorderPane pane = new BorderPane();
        pane.setTop(titleBox);
        pane.setCenter(outputArea);
        pane.setBottom(bottomBox);
        BorderPane.setMargin(outputArea, new Insets(15));

        Scene scene = new Scene(pane,750,500);

        stage.setTitle("Three Threads");
        stage.setScene(scene);
        stage.show();
    }
        // 3 threads
    private void runThreads() {

        Thread letters = new Thread(new LetterTask());
        Thread numbers = new Thread(new NumberTask());
        Thread symbols = new Thread(new SymbolTask());
        System.out.println("Starting Letter Thread...");
        System.out.println("Starting Number Thread...");
        System.out.println("Starting Symbol Thread...");
        letters.start();
        numbers.start();
        symbols.start();
    }

    private void appendCharacter(char c) {
        Platform.runLater(() ->
                outputArea.appendText(String.valueOf(c))
        );
    }

    // Progress Update
    private void updateProgress() {
        int total = totalCharacters.incrementAndGet();
        if (total % 1000 == 0 || total == TOTAL) {
            int percent = (total * 100) / TOTAL;
            Platform.runLater(() ->
                statusLabel.setText(
                    "Status: Running... "
                    + percent + "% ("
                    + total + "/" + TOTAL + ")"
                )
            );
        }
    }

    private void threadFinished() {
        if (completedThreads.incrementAndGet() == 3) {
            Platform.runLater(() -> {
                statusLabel.setText(
                    "Status: Complete! Generated "
                    + TOTAL + " characters."
                );
                startButton.setDisable(false);
                startButton.setText("Run Again");
            });

            System.out.println("All threads completed successfully.");
        }
    }

    // Thhread: Letters
    class LetterTask implements Runnable {
        private final Random random = new Random();
        @Override
        public void run() {
            for (int i = 0; i < COUNT; i++) {
                char letter = (char) ('a' + random.nextInt(26));
                appendCharacter(letter);
                updateProgress();
                try {
                    Thread.sleep(1);
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            threadFinished();
        }
    }

    // Thread: Numbers
    class NumberTask implements Runnable {
        private final Random random = new Random();
        @Override
        public void run() {
            for (int i = 0; i < COUNT; i++) {
                char digit = (char) ('0' + random.nextInt(10));
                appendCharacter(digit);
                updateProgress();
                try {
                    Thread.sleep(1);
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            threadFinished();
        }
    }

    // Thread: Special Characters
    class SymbolTask implements Runnable {
        private final Random random = new Random();
        private final char[] symbols = {
            '!', '@', '#', '$', '%', '&', '*'
        };
        @Override
        public void run() {
            for (int i = 0; i < COUNT; i++) {
                char symbol = symbols[random.nextInt(symbols.length)];
                appendCharacter(symbol);
                updateProgress();
                try {
                    Thread.sleep(1);
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            threadFinished();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}