// Name: Amanda Brock
// Assignment: Module-2
// Purpose: Reads contents of data file and displays all stored data.

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadDataFile extends Application {

    @Override
    public void start(Stage stage) {
        TextArea output = new TextArea();
        output.setEditable(false);
        output.setWrapText(true);
        output.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 14px;");
        Button readButton = new Button("Read File");
        readButton.setMaxWidth(Double.MAX_VALUE);
        readButton.setOnAction(e -> {
            output.clear();
            // Open file and read contents
            try (BufferedReader reader =
                         new BufferedReader(
                                 new FileReader("Brock_DataFile.dat"))) {
                String line;
                int dataSet = 1;
                while ((line = reader.readLine()) != null) {
                    if (line.equals("Integer Array:")) {
                        String integers = reader.readLine();
                        reader.readLine(); 
                        String doubles = reader.readLine();
                        reader.readLine(); 
                        // Display data set
                        output.appendText(
                                "Data Set #" + dataSet +
                                "\n====================================\n" +
                                "Integers: " + integers +
                                "\n" +
                                "Doubles : " + doubles +
                                "\n\n"
                        );
                        dataSet++;
                    }
                }
            } catch (IOException ex) {
                output.setText("File not found or cannot be read.");
            }
        });

        VBox root = new VBox(10, readButton, output);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 650, 300);
        stage.setTitle("Random Data File Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}