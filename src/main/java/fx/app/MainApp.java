package fx.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class MainApp extends Application {

    private final Pane pane = new Pane();
    private final Label scoreLabel = new Label();
    private int score = 0;
    private int missScore = 0;
    private final Label missLabel = new Label();

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) {
        missLabel.setLayoutX(100);
        pane.setPrefSize(500, 400);
        pane.setMaxSize(500, 400);
        pane.getChildren().addAll(scoreLabel, missLabel);
        pane.setOnMouseClicked(event -> {
            missLabel.setText("Miss = " + ++missScore);
        });
        for (int i = 0; i < 10; i++) {
            addButterfly();
        }
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public Pane getPane() {
        return pane;
    }

    //use flyweight
    private void addButterfly(){
        AtomicReference<Butterfly> butterfly = new AtomicReference<>(new Butterfly());
        butterfly.get().setMainApp(this);
        pane.getChildren().add(butterfly.get());
        butterfly.get().setOnMouseClicked(event -> {
            scoreLabel.setText("Score = " + ++score);
            --missScore;
            pane.getChildren().remove(butterfly.get());
            butterfly.set(null);
        });
        new Thread(butterfly.get()).start();
    }


}
