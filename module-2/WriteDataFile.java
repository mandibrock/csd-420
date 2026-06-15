// Name: Amanda Brock
// Assignment: Module-2
// Purpose: Generates random integer and double arrays
// and saves them to a data file.

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WriteDataFile extends Application {

    @Override
    public void start(Stage stage) {
        TextArea output = new TextArea();
        output.setEditable(false);
        output.setWrapText(true);
        output.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 14px;");
        Button generateButton = new Button("Generate and Save Data");
        generateButton.setMaxWidth(Double.MAX_VALUE);
        generateButton.setOnAction(e -> {
            try {
                // Create random number generator
                Random random = new Random();
                // Create arrays to store values
                int[] integers = new int[5];
                double[] doubles = new double[5];
                StringBuilder integerValues = new StringBuilder();
                StringBuilder doubleValues = new StringBuilder();
                // Open file in append mode
                FileWriter writer =
                        new FileWriter("Brock_DataFile.dat", true);
                writer.write("Integer Array:\n");
                // Generate 5 random integers and save them
                for (int i = 0; i < integers.length; i++) {
                    integers[i] = random.nextInt(100);
                    writer.write(integers[i] + " ");
                    integerValues.append(integers[i]).append(" ");
                }
                writer.write("\nDouble Array:\n");
                // Generate 5 random doubles and save them
                for (int i = 0; i < doubles.length; i++) {
                    doubles[i] = random.nextDouble() * 100;
                    writer.write(String.format("%.2f ", doubles[i]));
                    doubleValues.append(
                            String.format("%.2f ", doubles[i]));
                }
                writer.write("\n---------------------\n");
                writer.close();
                output.clear();
                // Show data that was generated
                output.appendText(
                        "Current Data Set\n" +
                        "====================================\n\n" +
                        "Integers: " + integerValues +
                        "\n\n" +
                        "Doubles : " + doubleValues +
                        "\n\n" +
                        "Data saved successfully.\n" +
                        "Data appended to Brock_DataFile.dat"
                );
            } catch (IOException ex) {
                output.setText("Error writing file.");
            }
        });

        VBox root = new VBox(10, generateButton, output);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 650, 300);
        stage.setTitle("Random Data File Generator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}