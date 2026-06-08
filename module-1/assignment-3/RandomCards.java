// Name: Amanda Brock
// Date: June 8, 2026
// Purpose: Display 4 random cards using Lambda Expressions

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class RandomCards extends Application {

    private HBox cardBox = new HBox(10);

    @Override
    public void start(Stage primaryStage) {

        cardBox.setAlignment(Pos.CENTER);

        Button refreshBtn = new Button("Refresh");

        // Lambda Expression
        refreshBtn.setOnAction(e -> displayCards());

        BorderPane pane = new BorderPane();
        pane.setCenter(cardBox);

        HBox buttonBox = new HBox(refreshBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 20, 0));
        pane.setBottom(buttonBox);

        displayCards();

        Scene scene = new Scene(pane, 500, 250);
        primaryStage.setTitle("Random Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayCards() {

        cardBox.getChildren().clear();

        ArrayList<Integer> cards = new ArrayList<>();

        for (int i = 1; i <= 52; i++) {
            cards.add(i);
        }

        Collections.shuffle(cards);

        for (int i = 0; i < 4; i++) {
            Image image = new Image(
                getClass().getResourceAsStream(
                    "/cards/" + cards.get(i) + ".png"
                )
            );

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
            cardBox.getChildren().add(imageView);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}