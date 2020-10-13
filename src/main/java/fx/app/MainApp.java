package fx.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainApp extends Application {

    private final Pane pane = new Pane();
    private final Label scoreLabel = new Label();
    private final Label missLabel = new Label();

    private int missScore = 0;
    private int score = 0;

    private final List<Butterfly> butterflyList = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) {
        createSceneInstance();
        for (int i = 0; i < 10; i++) {
            addButterfly();
        }
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void createSceneInstance() {
        missLabel.setLayoutX(100);
        pane.setPrefSize(500, 400);
        pane.setMaxSize(500, 400);
        pane.getChildren().addAll(scoreLabel, missLabel);
        pane.setOnMouseClicked(event -> {
            missLabel.setText("Miss = " + ++missScore);
        });
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
            butterfly.get().destroy();
        });
        new Thread(butterfly.get()).start();
    }


    public List<Butterfly> getButterflyList() {
        return butterflyList;
    }
}
