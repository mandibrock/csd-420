// Amanda Brock
// July 12, 2026
// Purpose: Displays 4 circles using css

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class circles extends Application {
    @Override
    public void start(Stage stage) {

        Circle circle1 = new Circle(40);
        Circle circle2 = new Circle(40);
        Circle circle3 = new Circle(40);
        Circle circle4 = new Circle(40);

        circle1.getStyleClass().add("plaincircle");
        circle2.getStyleClass().add("plaincircle");
        circle3.setId("redcircle");
        circle4.setId("greencircle");

        HBox pane = new HBox(20);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(circle1, circle2, circle3, circle4);

        Scene scene = new Scene(pane, 450, 150);
        scene.getStylesheets().add("style.css");
        stage.setTitle("Module 7 Programming Assignment");
        stage.setScene(scene);
        stage.show();

        runTests(circle1, circle2, circle3, circle4);
    }

    private void runTests(Circle c1, Circle c2, Circle c3, Circle c4) {
        System.out.println("----- Test Results -----");
        boolean classTest =
                c1.getStyleClass().contains("plaincircle") &&
                c2.getStyleClass().contains("plaincircle");
        boolean idTest =
                c3.getId().equals("redcircle") &&
                c4.getId().equals("greencircle");
        System.out.println("CSS Class Applied: " + (classTest ? "PASS" : "FAIL"));
        System.out.println("CSS IDs Applied:   " + (idTest ? "PASS" : "FAIL"));
        if (classTest && idTest) {
            System.out.println("Overall Test: PASS");
        } else {
            System.out.println("Overall Test: FAIL");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}